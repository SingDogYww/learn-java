package com.cxyxh.mashibing.c_001.T03_otherlock.L06_ReadWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock1 {

    public static void main(String[] args) {
        TestReadWriteLock1 test = new TestReadWriteLock1();

        Lock lock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        //1. 全使用lock
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            threads.add(new Thread(() -> test.read(lock)));
        }
        for (int i = 0; i < 20; i++) {
            threads.add(new Thread(() -> test.write(lock)));
        }
        threads.forEach(Thread::start);
        threads.forEach(o1 -> {
            try {
                o1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        读写锁全使用互斥锁，耗时：10751
        System.out.println("读写锁全使用互斥锁，耗时：" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            threads2.add(new Thread(() -> test.read(readLock)));
        }
        for (int i = 0; i < 20; i++) {
            threads2.add(new Thread(() -> test.write(writeLock)));
        }
        threads2.forEach(Thread::start);
        threads2.forEach(o1 -> {
            try {
                o1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //读写锁全使用互斥锁，耗时：10751
        System.out.println("读使用共享锁，写使用互斥锁，耗时：" + (System.currentTimeMillis() - start2));
    }


    public void read(Lock lock){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "读数据中");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void write(Lock lock){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "写数据中");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
