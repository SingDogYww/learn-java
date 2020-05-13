package com.cxyxh.mashibing.c_001.T03_otherlock.L03_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 花式倒数
 */
public class TestCountDownLatch4 {

    public static void useCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "执行");
                }
                long count = latch.getCount();
                //单个执行latch.countDown();是安全的，使用了CAS操作，但是在for中使用
                for (int j = 0; j < count / 15 + 1; j++) {
                    latch.countDown();
                }
                System.out.println("当前倒数：" + latch.getCount());
            }));
        }
        threads.forEach(Thread::start);
        try {
            System.out.println("等着吧");
            latch.await();
            System.out.println("等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void useJoin(){
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "执行");
                }
            }));
        }
        threads.forEach(Thread::start);
        threads.forEach(o1 -> {
            try {
                o1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("其余线程执行完");
    }


    public static void main(String[] args) {
        useCountDownLatch();
//        useJoin();
    }


}
