package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举类型的单例模式
 * 这种模式可以防止反射，修改构造方法进行获取对象
 * 但是单例模式和枚举类，我觉得这俩意思完全不一样
 * 一个是只能有一个
 * 另一个是可以有一个
 * 就像是大炮打蚊子一样
 */
public enum Singleton6 {
    INSTANCE;
    static Singleton6 getInstance(){
        return Singleton6.INSTANCE;
    }
}

class test{
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(Singleton6.getInstance().hashCode())));
        }
        threads.forEach(Thread::start);
    }
}
