package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 懒加载
 * 懒汉式
 * 缺点：有线程安全问题
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03(){}

    public static Mgr03 getInstance() {
        if (INSTANCE == null){
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }
}
