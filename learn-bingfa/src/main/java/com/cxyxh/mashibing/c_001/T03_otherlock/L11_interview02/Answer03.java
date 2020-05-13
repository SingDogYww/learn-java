package com.cxyxh.mashibing.c_001.T03_otherlock.L11_interview02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Answer03 {

    private Lock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    List<Integer> list = new ArrayList<>();
    int size = 8;
    int count = 0;
    public synchronized void put(int i){

        try {
            lock.lock();
            while(count == size) {
                producer.await();
            }
            list.add(i);
            System.out.println("添加一个" + i);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public synchronized int get(){
        try {
            lock.lock();
            while(count == 0) {
                consumer.await();
            }
            Integer remove = list.remove(count - 1);
            System.out.println("获取一个" + remove);
            count++;
            producer.signalAll();
            return remove;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return -1;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Answer03 answer01 = new Answer03();
        List<Thread> producer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            producer.add(new Thread(() -> answer01.put(j)));
        }

        List<Thread> comsumer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            comsumer.add(new Thread(answer01::get));
        }
        comsumer.forEach(Thread::start);
        Thread.sleep(1000);
        producer.forEach(Thread::start);
    }



}
