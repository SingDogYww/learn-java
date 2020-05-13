package com.cxyxh.imooc.T01_synchronized.T000_start;

import java.util.concurrent.TimeUnit;

/**
 * 正常的增加了，因为在t2中先执行了t1，再执行的t2
 */
public class DispearRequest2 {
    static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 20000; i++) {
                count++;
            }
        });
        t1.start();
        t2.start();
        TimeUnit.MICROSECONDS.sleep(5);
        System.out.println(count);
    }
}
