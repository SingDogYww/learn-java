package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 懒汉式单例
 *  在多线程下面会有问题，在这种情况下，每次获取单例对象时，都会去抢夺锁，比较占用资源所以会有问题
 *
 */
public class SingletonLazy2 {

    private static SingletonLazy2 instance = null;

    private SingletonLazy2(){}

    public synchronized static SingletonLazy2 getInstance(){
        if (instance == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonLazy2();
        }
        return instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(SingletonLazy2.getInstance().hashCode())));
        }
        threads.forEach(Thread::start);
    }
}
