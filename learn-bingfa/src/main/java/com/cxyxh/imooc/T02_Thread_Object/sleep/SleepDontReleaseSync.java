package com.cxyxh.imooc.T02_Thread_Object.sleep;

/**
 * @author yangww
 * @date 2020/5/22
 *
 */
public class SleepDontReleaseSync implements Runnable{


    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "退出同步代码块");
    }

    public static void main(String[] args) {
        SleepDontReleaseSync sleepDontReleaseSync = new SleepDontReleaseSync();
        Thread thread1 = new Thread(sleepDontReleaseSync);
        Thread thread2 = new Thread(sleepDontReleaseSync);
        thread1.start();
        thread2.start();
    }
}
