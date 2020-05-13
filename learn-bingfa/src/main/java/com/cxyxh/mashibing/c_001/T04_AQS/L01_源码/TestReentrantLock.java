package com.cxyxh.mashibing.c_001.T04_AQS.L01_源码;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    public static void main(String[] args) {
        int i = 0;
        Lock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();

    }


}
