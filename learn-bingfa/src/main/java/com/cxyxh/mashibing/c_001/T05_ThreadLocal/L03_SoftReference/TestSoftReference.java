package com.cxyxh.mashibing.c_001.T05_ThreadLocal.L03_SoftReference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class TestSoftReference {

    public static void main(String[] args) throws IOException, InterruptedException {
//        SoftReference<M> m = new SoftReference<>(new M());
//        System.out.println(m.get());
//        m = null;
//        System.gc();
//        TimeUnit.SECONDS.sleep(1);




        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(softReference.get());
        //使用了设置软引用指向为null之后会被回收
//        softReference = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024*1024*10];
        //当堆内存满了之后，JVM才会回收这些软引用
        System.out.println(softReference.get());
    }
}
