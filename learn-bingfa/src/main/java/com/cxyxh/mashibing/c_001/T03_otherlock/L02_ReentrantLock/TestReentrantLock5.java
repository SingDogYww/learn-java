package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的tryLock();
 * 会尝试获取锁，如果没有获取到返回false
 * 如果获取到了锁，返回true，并且持有锁、
 * 这个是CAS操作，synchronized是锁升级​
 */
public class TestReentrantLock5 {
    static Lock lock = new ReentrantLock();

    public static void method1(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入lock()");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void method2(){
        boolean b = false;
        try{
            //会尝试去获取锁，如果没有获取到锁，那么会返回false
            //如果获取到了锁那么会，直接用有锁
            b = lock.tryLock();
            System.out.println(Thread.currentThread().getName() + "尝试获取锁" + b);
        } finally {
            //如果获取锁成功了的话，在最后必须释放掉锁
            if (b) lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(TestReentrantLock5::method1);
        Thread thread2 = new Thread(TestReentrantLock5::method2);
        thread1.start();
        Thread.sleep(3000);
        thread2.start();
    }




}
