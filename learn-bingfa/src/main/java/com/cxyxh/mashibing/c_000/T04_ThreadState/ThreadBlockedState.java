package com.cxyxh.mashibing.c_000.T04_ThreadState;

/**
 * 代码块锁
 *  1. 不加锁的情况下，基本上是两个线程同时执行，也是差不多同时结束
 *  2. 加了锁之后，必须等另一个线程执行结束才能执行另一个线程
 *  3. sleep方法不会释放锁
 */
public class ThreadBlockedState {



    private static class codeBlock implements Runnable{
        Object lock = new Object();

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "开始运行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        codeBlock codeBlock = new codeBlock();
        Thread t1 = new Thread(codeBlock);
        Thread t2 = new Thread(codeBlock);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(t2.getState());
    }
}
