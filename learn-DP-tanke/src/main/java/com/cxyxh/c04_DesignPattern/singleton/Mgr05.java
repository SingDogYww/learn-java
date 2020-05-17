package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 懒加载
 * 懒汉式加载，使用锁来保证线程安全
 * 缺点：有线程安全问题
 */
public class Mgr05 {
    private static Mgr05 instance;

    private Mgr05(){}

    public static Mgr05 getInstance() {
        if (instance == null){
            synchronized (Mgr05.class) {
                instance = new Mgr05();
            }
        }
        return instance;
    }
}
