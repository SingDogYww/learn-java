package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态内部类的类型的单例模式
 */
public class Singleton7 {

    private static class SingletonInner{
        private static final Singleton7 instance =  new Singleton7();
    }

    private Singleton7(){}

    public static Singleton7 getInstance(){
        return SingletonInner.instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(Singleton7.getInstance().hashCode())));
        }
        threads.forEach(Thread::start);
    }
}
