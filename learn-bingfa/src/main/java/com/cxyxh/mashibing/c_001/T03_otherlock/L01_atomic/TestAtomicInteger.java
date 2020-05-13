package com.cxyxh.mashibing.c_001.T03_otherlock.L01_atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 替代synchronized {i++}
 */
public class TestAtomicInteger {
    static int i = 0;
    static AtomicInteger count = new AtomicInteger(0);
    public static void method1() {
        for (int j = 0; j < 1000; j++) {
            synchronized (TestAtomicInteger.class) {
                i++;
            }
        }
    }

    public static void method2(){
        for (int j = 0; j < 1000; j++) {
            count.incrementAndGet();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
//            threads.add(new Thread(TestAtomicInteger::method1));
            threads.add(new Thread(TestAtomicInteger::method2));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("最後的数为" + count);
    }
}
