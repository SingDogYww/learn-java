package com.cxyxh.mashibing.c_001.T05_ThreadLocal.L02_StrongReference;

import com.cxyxh.mashibing.c_001.T05_ThreadLocal.M;

import java.io.IOException;

public class TestStrongReference {

    public static void main(String[] args) throws IOException {
        //强引用在引用还在的时候，gc不会去清除掉那块内存块，只有在没有引用指向这块内存时才会被回收
        M m = new M();
        m = null;
        //这个方法，可以提醒JVM可以执行FullGC了
        System.gc();
        //GC 是跑在另一个线程中的，所以主线程需要在这里阻塞，不然主线程结束，也就看不到是否GC了
        System.in.read();
    }
}
