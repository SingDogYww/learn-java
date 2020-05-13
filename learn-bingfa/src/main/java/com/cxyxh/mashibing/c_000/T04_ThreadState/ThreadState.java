package com.cxyxh.mashibing.c_000.T04_ThreadState;

import java.util.concurrent.locks.LockSupport;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                LockSupport.park();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1");
        });
        //Thread的状态 java.lang.Thread.State
        //初始状态
        System.out.println(thread.getState());
        thread.start();
        //Runnable，可执行的包含正在执行的
        System.out.println(thread.getState());
        Thread.sleep(3000);
        //TIMED_WAITING，被设置了等待时间的
        System.out.println(thread.getState());
        Thread.sleep(3000);
        //WAITING，被掉用了LockSupport.park()方法
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
        Thread.sleep(1000);
        //结束状态TERMINATED
        System.out.println(thread.getState());
    }



}
