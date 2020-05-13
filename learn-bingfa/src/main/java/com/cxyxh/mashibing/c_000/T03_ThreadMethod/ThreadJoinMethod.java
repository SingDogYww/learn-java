package com.cxyxh.mashibing.c_000.T03_ThreadMethod;

public class ThreadJoinMethod {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("t1");
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                //先执行线程1
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("t2");
            }
        });

        thread1.start();
        thread2.start();
    }
}
