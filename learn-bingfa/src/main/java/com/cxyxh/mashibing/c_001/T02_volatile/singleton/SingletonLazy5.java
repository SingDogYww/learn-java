package com.cxyxh.mashibing.c_001.T02_volatile.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 懒汉式单例
 * 在多线程下面会有问题，在这种情况下，new 对象时，其实是分成了三步
 *  第一步 申请内存空间 并初始化数据
 *  第二步 给对象赋值
 *  第三步 将内存空间引用赋值给变量
 *  在这个里面 第二步和第三步没有关联，所以在CPU执行的时候，可能会重排序
 *  在某些情况下可能会出现 获取到半初始话完成的对象，
 *
 *  FIXME 修理我 最后选择使用volatile来禁止指令重排序
 */
public class SingletonLazy5 {
    private int a = 8;

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    private static volatile SingletonLazy5 instance = null;

    private SingletonLazy5() {
    }

    public static SingletonLazy5 getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy5.class) {
                //执行双重判断
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonLazy5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(SingletonLazy5.getInstance().hashCode())));
        }
        threads.forEach(Thread::start);
    }
}
