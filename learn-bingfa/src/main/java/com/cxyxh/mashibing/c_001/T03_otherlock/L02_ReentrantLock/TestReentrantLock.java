package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以起到替代synchronized的作用
 * 这个是CAS操作，synchronized是锁升级​
 * 但是lock必须手动释放锁，不然的话会一直阻塞
 * 并且必须加一次锁，释放一次锁，必须有，不然的话会阻塞
 */
public class TestReentrantLock {
    static Lock lock = new ReentrantLock();

    public static void method1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入lock()");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(TestReentrantLock::method1);
        Thread thread2 = new Thread(TestReentrantLock::method1);
        thread.start();
        Thread.sleep(2000);
        thread2.start();
    }


}
