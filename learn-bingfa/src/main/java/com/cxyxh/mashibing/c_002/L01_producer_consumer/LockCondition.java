package com.cxyxh.mashibing.c_002.L01_producer_consumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangww
 * @date 2020/4/18
 *
 */
public class LockCondition {

    public static void main(String[] args) {
        Container<String> container = new LockCondition.Container<>();
        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            consumers.add(new Thread(new LockCondition.Comsumer(container)));
        }

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            producers.add(new Thread(new LockCondition.Producer(container)));
        }

        consumers.forEach(Thread::start);
        producers.forEach(Thread::start);
    }

    static class Container<T> {

        private LinkedList<T> container = new LinkedList<>();
        public static int max = 10;
        private int size = 0;

        Lock lock = new ReentrantLock();
        Condition producer = lock.newCondition();
        Condition consumer = lock.newCondition();

        public void remove() {
            try{
                lock.lock();
                while (getSize() == 0){
                    System.err.println("没了，等着");
                    consumer.await();
                }
                container.removeFirst();
                System.out.println("消费者消费一个");
                size--;
                producer.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void add(T t) {
            try{
                lock.lock();
                while (getSize() == max){
                    System.err.println("满了，等着");
                    producer.await();
                }
                container.add(t);
                System.out.println("生产者生产一个");
                size++;
                consumer.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public synchronized int getSize() {
            return size;
        }

    }

    static class Producer implements Runnable {

        private Container<String> container;

        public Producer(Container<String> container) {
            this.container = container;
        }

        @Override
        public void run() {
            container.add("biubiubiu");
        }
    }

    static class Comsumer implements Runnable {

        private Container<String> container;

        public Comsumer(Container<String> container) {
            this.container = container;
        }

        @Override
        public void run() {
            container.remove();
        }
    }

}
