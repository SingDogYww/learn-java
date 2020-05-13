package com.cxyxh.mashibing.c_001.T03_otherlock.L03_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 测试await的时候会不会被打断
 * await的时候是主线程在阻塞，所以只能使用主线程.interrupt()
 */
public class TestCountDownLatch5 {

    public static void useCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                synchronized (TestCountDownLatch5.class) {
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
            //这里阻塞是让主线程阻塞,设置超时时间，超过这时候还没完，那么就结束
            latch.await(5, TimeUnit.SECONDS);
            System.out.println("等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        useCountDownLatch();
    }


}
