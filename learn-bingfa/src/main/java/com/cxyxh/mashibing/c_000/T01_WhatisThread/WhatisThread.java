package com.cxyxh.mashibing.c_000.T01_WhatisThread;

import java.util.concurrent.TimeUnit;

public class WhatisThread {
    private static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("继承自Thread："+Thread.currentThread().getName());
        }
    }

    private static class Thread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("实现Runnable："+Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        TimeUnit.MICROSECONDS.sleep(2);
        Thread thread2 = new Thread(new Thread2());
        thread2.start();
    }
}
