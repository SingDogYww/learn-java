package com.cxyxh.c04_DesignPattern.singleton;

import java.util.HashMap;

/**
 * 懒加载
 * 懒汉式加载
 * 静态内部类
 *
 */
public class Mgr07 {
    private static class Mgr07Holder{
        private static Mgr07 instance = new Mgr07();
    }

    private Mgr07(){}

    public static Mgr07 getInstance() {
        return Mgr07Holder.instance;
    }
}
