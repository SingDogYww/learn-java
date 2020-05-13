package com.cxyxh.mashibing.c_001.T03_otherlock.L03_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch1 {

    public static void useCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                synchronized (TestCountDownLatch1.class) {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + "执行");
                    }
                }
                latch.countDown();
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

    public static void main(String[] args) {
        useCountDownLatch();
    }


}
