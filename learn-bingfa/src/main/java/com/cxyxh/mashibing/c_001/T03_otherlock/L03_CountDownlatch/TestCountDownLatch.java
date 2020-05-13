package com.cxyxh.mashibing.c_001.T03_otherlock.L03_CountDownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangww
 * 用于 等待几个线程结束才继续往下执行，
 * 具体是，开始阻塞，等待countDownLatch
 *  等到countDownLatch到达0了之后，就不会阻塞在哪里了
 * @date 2020/4/10
 */
public class TestCountDownLatch {

     static CountDownLatch countDownLatch = new CountDownLatch(100);


    public synchronized void method(){
        for (int i = 0; i < 10; i++) {
            System.out.println("这里是" + Thread.currentThread().getName() + "在执行");
        }
        countDownLatch.countDown();
        System.out.println("倒数: " + countDownLatch.getCount());
    }


    public static void main(String[] args) {
        TestCountDownLatch test = new TestCountDownLatch();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(test::method));
        }

        threads.forEach(Thread::start);
        try {
            System.out.println("开始准备倒数");
            countDownLatch.await();
            System.out.println("倒数成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
