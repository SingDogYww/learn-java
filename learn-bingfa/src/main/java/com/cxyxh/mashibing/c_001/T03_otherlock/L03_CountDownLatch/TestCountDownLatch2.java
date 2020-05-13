package com.cxyxh.mashibing.c_001.T03_otherlock.L03_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 倒数设置，
 * 先阻塞，当倒数到0时，阻塞结束，继续执行
 * 可以达到join的效果，但是和join不一样
 * join必须要将加入进来的线程执行完，才能继续执行
 * 但是CountDownLatch，是可以随意倒数的，
 * 我可以使用斐波那契数列模式递减，啥时候减成0了啥时候结束
 */
public class TestCountDownLatch2 {

    public static void useCountDownLatch(){
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "执行");
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
