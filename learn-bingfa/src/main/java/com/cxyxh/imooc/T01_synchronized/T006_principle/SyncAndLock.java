package com.cxyxh.imooc.T01_synchronized.T006_principle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不是同一个锁
 */
public class SyncAndLock {

    Lock lock = new ReentrantLock();

    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName() + "进入synchronized范围");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "我是synchronized格式的锁");
    }

    public void method2(){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "进入lock范围");
        try {
            System.out.println("我是lock格式的锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncAndLock syncAndLock = new SyncAndLock();
        Thread thread = new Thread(syncAndLock::method1);
        Thread thread1 = new Thread(syncAndLock::method2);
        thread.start();
        Thread.sleep(1000);
        thread1.start();


    }




}
