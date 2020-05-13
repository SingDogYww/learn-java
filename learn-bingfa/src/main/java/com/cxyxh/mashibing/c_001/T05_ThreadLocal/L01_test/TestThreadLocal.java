package com.cxyxh.mashibing.c_001.T05_ThreadLocal.L01_test;

import java.util.concurrent.TimeUnit;

public class TestThreadLocal {
    /**
     * 每个线程都有自己的ThreadLocal对象
      */
    static ThreadLocal<Person> person = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.set(new Person());
        }).start();




    }

}
