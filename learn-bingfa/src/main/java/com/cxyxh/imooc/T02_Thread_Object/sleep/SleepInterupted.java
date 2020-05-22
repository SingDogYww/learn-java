package com.cxyxh.imooc.T02_Thread_Object.sleep;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每隔一秒钟打印当前时间，被中断
 * @author yangww
 * @date 2020/5/22
 *
 */
public class SleepInterupted implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterupted());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                e.printStackTrace();
            }
        }
    }
}
