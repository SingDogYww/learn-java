package com.cxyxh.mashibing.c_001.T03_otherlock.L09_LockSupport;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {


        t1 = new Thread(() -> {
            System.out.println("我是" + Thread.currentThread().getName());
            LockSupport.unpark(t2);
            LockSupport.park();
        }, "T1");

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("我是" + Thread.currentThread().getName());
            LockSupport.unpark(t1);
        }, "T2");
        t1.start();
        t2.start();
    }
}
