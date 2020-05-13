package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 懒汉式单例
 *  在多线程下面会有问题，获取到的对象不是单例的，是不同的
 */
public class SingletonLazy1 {

    private static SingletonLazy1 instance = null;

    private SingletonLazy1(){}

    public static SingletonLazy1 getInstance(){
        if (instance == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonLazy1();
        }
        return instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                System.out.println(SingletonLazy1.getInstance().hashCode());
            }));
        }
        threads.forEach(Thread::start);
    }
}
