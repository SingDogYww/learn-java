package com.cxyxh.mashibing.c_001.T03_otherlock.L04_CyclicBarriar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 不能加synchronized，不然会死锁，当前线程在等待人数齐，不能释放锁
 * 但是其他的线程在等待这个锁释放锁
 */
public class TestCyclicBarriar3 {

    static CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("人齐了"));

    public /*synchronized*/ static void method01() {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 105; i++) {
            threads.add(new Thread(TestCyclicBarriar3::method01));
        }
        threads.forEach(Thread::start);
    }


}
