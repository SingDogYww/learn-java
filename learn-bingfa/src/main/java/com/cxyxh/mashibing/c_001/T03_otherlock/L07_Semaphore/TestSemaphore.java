package com.cxyxh.mashibing.c_001.T03_otherlock.L07_Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
    Semaphore semaphore = new Semaphore(4);


    public void method() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + "通过");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        TestSemaphore test = new TestSemaphore();
        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            threads2.add(new Thread(test::method));
        }
        threads2.forEach(Thread::start);
    }

}
