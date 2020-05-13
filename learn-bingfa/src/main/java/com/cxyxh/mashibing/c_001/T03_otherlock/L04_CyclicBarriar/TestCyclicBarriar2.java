package com.cxyxh.mashibing.c_001.T03_otherlock.L04_CyclicBarriar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 当线程执行的时候，阻塞在await这里，必须等到barrier指定的数量之后，才能继续执行
 * 如果一直不齐，那么就一直等到自己设置的时间为止，如果没有设置时间，那么它将一直等下去
 */
public class TestCyclicBarriar2 {

    static CyclicBarrier barrier = new CyclicBarrier(50, () -> System.out.println("人齐了"));


    public static void method01() {
        try {
            barrier.await(5, TimeUnit.SECONDS);
            System.out.println("还剩下：" + barrier.getParties() + "个线程");
            System.out.println("已经到达：" + barrier.getNumberWaiting() + "个线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            //当时间到达，还没有齐的话，就会报错
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 105; i++) {
            threads.add(new Thread(TestCyclicBarriar2::method01));
        }
        threads.forEach(Thread::start);
        System.out.println("哇哈哈");
    }


}
