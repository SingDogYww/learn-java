package com.cxyxh.imooc.T02_Thread_Object.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep()不释放锁
 * @author yangww
 * @date 2020/5/22
 */
public class SleepDontReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了锁");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "退出同步代码块");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseSync = new SleepDontReleaseLock();
        Thread thread1 = new Thread(sleepDontReleaseSync);
        Thread thread2 = new Thread(sleepDontReleaseSync);
        thread1.start();
        thread2.start();
    }
}
