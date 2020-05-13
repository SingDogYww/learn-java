package com.cxyxh.mashibing.c_001.T03_otherlock.L01_atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicLong synchronized LongAdder性能测试
 * LongAdder是使用了 分段加锁
 */
public class TestAtomicInteger2 {
    static int i = 0;
    static AtomicInteger count = new AtomicInteger(0);
    //在方法中耗时越长，执行时间越长
    static LongAdder longAdder = new LongAdder();
    public static void method1() {
        for (int j = 0; j < 1000000; j++) {
            synchronized (TestAtomicInteger2.class) {
                i++;
            }
        }
    }

    public static void method2(){
        for (int j = 0; j < 1000000; j++) {
            count.incrementAndGet();
        }
    }

    public static void method3(){
        for (int j = 0; j < 1000000; j++) {
            longAdder.increment();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            threads.add(new Thread(TestAtomicInteger::method1));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("synchronized耗时" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            threads2.add(new Thread(TestAtomicInteger::method2));
        }
        threads2.forEach(Thread::start);
        for (Thread thread : threads2) {
            thread.join();
        }
        System.out.println("atomicXXX耗时" + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        List<Thread> threads3 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            threads3.add(new Thread(TestAtomicInteger2::method3));
        }
        threads3.forEach(Thread::start);
        for (Thread thread : threads3) {
            thread.join();
        }
        System.out.println("LongAdder耗时" + (System.currentTimeMillis() - start3));
    }
}
