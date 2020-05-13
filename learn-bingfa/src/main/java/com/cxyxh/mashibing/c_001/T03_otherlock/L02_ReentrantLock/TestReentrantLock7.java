package com.cxyxh.mashibing.c_001.T03_otherlock.L02_ReentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock的公平锁实现
 */
public class TestReentrantLock7 {
    //如果使用了公平锁，基本上会是交替获取锁的
    static Lock lock = new ReentrantLock(true);
    //不加的话，那么基本上是连续执行的
//    static Lock lock = new ReentrantLock();

    public static void method1() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "进入lock()");
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(TestReentrantLock7::method1);
        Thread thread2 = new Thread(TestReentrantLock7::method1);
        thread1.start();
        thread2.start();
    }


}
