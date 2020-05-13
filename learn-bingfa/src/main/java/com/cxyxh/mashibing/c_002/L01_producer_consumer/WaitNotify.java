package com.cxyxh.mashibing.c_002.L01_producer_consumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangww
 * @date 2020/4/18
 *
 */
public class WaitNotify {


    public static void main(String[] args) {
        WaitNotify.Container<String> container = new WaitNotify.Container<>();
        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            consumers.add(new Thread(new WaitNotify.Comsumer(container)));
        }

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            producers.add(new Thread(new WaitNotify.Producer(container)));
        }

        consumers.forEach(Thread::start);
        producers.forEach(Thread::start);
    }

   static class Container<T> {

        private LinkedList<T> container = new LinkedList<>();
        public static int max = 10;
        private int size = 0;

        public synchronized T remove() {
            while (getSize() == 0) {
                System.err.println("没了，等着");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = container.removeFirst();
            System.out.println("消费者消费一个");
            notifyAll();
            size--;
            return t;
        }

        public synchronized void add(T t) {
            while (getSize() == max) {
                System.err.println("满了，等着");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.add(t);
            System.out.println("生产者生产一个");
            size++;
            notifyAll();
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
