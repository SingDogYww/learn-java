package com.cxyxh.mashibing.c_001.T05_ThreadLocal.L04_WeakReference;

import com.cxyxh.mashibing.c_001.T05_ThreadLocal.M;

import java.lang.ref.WeakReference;

public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        //一旦有GC就会被回收，保证可能会产生内存泄漏的地方，一定会被回收
        System.gc();
        System.out.println(m.get());


    }
}
