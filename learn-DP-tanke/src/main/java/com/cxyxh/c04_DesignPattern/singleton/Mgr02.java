package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 使用静态语句块
 * 和饿汉式差不多
 */
public class Mgr02 {
    private static Mgr02 instance;
    static {
        instance = new Mgr02();
    }
    private Mgr02(){}

    public static Mgr02 getInstance() {
        return instance;
    }
}
