package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以起到替代synchronized的作用
 * 这个是CAS操作，synchronized是锁升级​
 * 这个锁在不同的方法中都有用
 */
public class TestReentrantLock4 {
    static Lock lock = new ReentrantLock();

    public static void method1() {
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


    public static void method2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入第二个方法中");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(TestReentrantLock4::method1);
        Thread thread2 = new Thread(TestReentrantLock4::method2);
        thread.start();
        Thread.sleep(2000);
        thread2.start();
    }

}
