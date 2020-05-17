package com.cxyxh.c04_DesignPattern.singleton;

/**
 * 懒加载
 * 懒汉式加载，使用锁来保证线程安全
 * 缺点：双重检测，可以保证线程安全，也可以保证懒加载
 */
public class Mgr06 {
    //添加volatile防止指令重排序
    private volatile static Mgr06 instance;

    private Mgr06(){}

    public static Mgr06 getInstance() {
        //第一次可以保证在初始化完成之后可以不进入锁
        if (instance == null){
            synchronized (Mgr06.class) {
                if (instance == null) {
                    instance = new Mgr06();
                }
            }
        }
        return instance;
    }
}
