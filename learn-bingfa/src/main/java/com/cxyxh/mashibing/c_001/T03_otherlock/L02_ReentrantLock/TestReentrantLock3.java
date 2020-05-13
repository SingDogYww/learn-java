package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以起到替代synchronized的作用
 * 这个是CAS操作，synchronized是锁升级​
 * lock.lock()和synchronized(this)类对象锁也不是同一个
 */
public class TestReentrantLock3 {
    Lock lock = new ReentrantLock();

    public void method1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入lock()");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }


    public synchronized void method2() {
        try {
            System.out.println(Thread.currentThread().getName() + "进入synchronized方法中");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestReentrantLock3 testReentrantLock3 = new TestReentrantLock3();
        Thread thread = new Thread(testReentrantLock3::method1);
        Thread thread2 = new Thread(testReentrantLock3::method2);
        thread.start();
        Thread.sleep(2000);
        thread2.start();
    }

}
