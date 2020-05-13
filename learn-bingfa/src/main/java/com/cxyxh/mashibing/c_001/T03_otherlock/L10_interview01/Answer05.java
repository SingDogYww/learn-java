package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 使用semaphore需要保证第一个线程先执行
 */
public class Answer05 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws InterruptedException {
        Answer05 answer = new Answer05();
        Semaphore semaphore = new Semaphore(1,true);
        t1 = new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
                if (answer.size() == 5){
                    semaphore.release();
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            semaphore.release();
        });

        Thread.sleep(100);

        t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("收到通知，结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }
}
