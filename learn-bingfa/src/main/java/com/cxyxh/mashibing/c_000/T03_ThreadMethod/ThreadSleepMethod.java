package com.cxyxh.mashibing.c_000.T03_ThreadMethod;

public class ThreadSleepMethod {
    private static class Thread1 extends Thread {
        @Override
        public void run() {
            method();
        }
    }
    private static class Thread2 extends Thread {
        @Override
        public void run() {
            method();
        }
    }

    private synchronized static void method(){
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.setName("t1");
        Thread2 thread2 = new Thread2();
        thread2.setName("t2");
        thread1.start();
        thread2.start();
    }

}
