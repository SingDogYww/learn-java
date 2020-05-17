package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 饿汉式单例
 * 缺点：一装载就实例化了，所以不用他，干嘛加载
 * 所以就有了懒汉式加载
 */
public class Mgr01 {
    private static final Mgr01 instance = new Mgr01();

    private Mgr01(){}

    public static Mgr01 getInstance() {
        return instance;
    }


}
