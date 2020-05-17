package com.cxyxh.imooc.T02_Thread_Object.object;

/**
 * 面试题：交替打印奇偶数，范围0-100
 * 解决方案：使用synchronized
 */
public class AlternatePrintWaitNotify {
    private static int count;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new PrintThread(), "偶数").start();
        new Thread(new PrintThread(), "奇数").start();
    }

    static class PrintThread implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
