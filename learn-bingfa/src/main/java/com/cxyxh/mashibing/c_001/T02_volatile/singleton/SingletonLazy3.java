package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 懒汉式单例
 * 在多线程下面会有问题，
 */
public class SingletonLazy3 {

    private static SingletonLazy3 instance = null;

    private SingletonLazy3() {
    }

    public static SingletonLazy3 getInstance() {
        if (instance == null) {
            //线程会阻塞在这里，所以也是线程不安全的
            synchronized (SingletonLazy3.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new SingletonLazy3();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(SingletonLazy3.getInstance().hashCode())));
        }
        threads.forEach(Thread::start);
    }
}
