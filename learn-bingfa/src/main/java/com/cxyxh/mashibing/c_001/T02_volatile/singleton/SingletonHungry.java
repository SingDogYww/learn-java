package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 饿汉式单例
 */
//TODO volatile的内存可见性暂时没有示例，可以使用停止线程的那个来
public class SingletonHungry {
    private static final SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){
    }

    public static SingletonHungry getInstance(){
        try {
            //模拟业务逻辑
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                System.out.println(SingletonHungry.getInstance().hashCode());
            }));
        }
        threads.forEach(Thread::start);
    }
}
