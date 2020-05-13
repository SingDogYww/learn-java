package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 使用semaphore需要保证第一个线程先执行
 */
public class Answer06 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws InterruptedException {
        Answer06 answer = new Answer06();
        CountDownLatch latch1 = new CountDownLatch(5);
        CountDownLatch latch2 = new CountDownLatch(1);
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
                latch1.countDown();
                if (answer.size() == 5) {
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2 = new Thread(() -> {
            try {
                latch1.await();
                System.out.println("收到通知，结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch2.countDown();
        });

        t1.start();
        t2.start();

    }
}
