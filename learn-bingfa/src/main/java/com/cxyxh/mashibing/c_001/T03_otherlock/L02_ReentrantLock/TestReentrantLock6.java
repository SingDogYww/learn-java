package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的lockInterruptibly，设置这个锁可以响应中断
 * 不设置的话是不会响应的
 */
public class TestReentrantLock6 {
    static Lock lock = new ReentrantLock();

    public static void method1(){
        try{
            lock.lock();
            //设置可以响应中断，如果不加这句的话，不会响应中断
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "进入lock()");
            for (;;);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(TestReentrantLock6::method1);
        thread1.start();
        Thread.sleep(10);
        thread1.interrupt();
    }




}
