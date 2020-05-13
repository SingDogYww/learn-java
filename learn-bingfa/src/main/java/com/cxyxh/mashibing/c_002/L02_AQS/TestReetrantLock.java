package com.cxyxh.mashibing.c_002.L02_AQS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangww
 * @date 2020/4/18
 *
 */
public class TestReetrantLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
