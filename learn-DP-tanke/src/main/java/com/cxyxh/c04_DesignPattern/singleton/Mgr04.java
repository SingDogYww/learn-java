package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 懒加载
 * 懒汉式加载，使用锁来保证线程安全
 * 缺点：锁的粒度太大，instance有值之后可以不需要再加锁了
 */
public class Mgr04 {
    private static Mgr04 instance;

    private Mgr04(){}

    public synchronized static Mgr04 getInstance() {
        if (instance == null){
            instance = new Mgr04();
        }
        return instance;
    }
}
