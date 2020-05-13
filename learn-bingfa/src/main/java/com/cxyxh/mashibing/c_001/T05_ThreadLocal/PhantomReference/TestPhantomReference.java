package com.cxyxh.mashibing.c_001.T05_ThreadLocal.PhantomReference;

import com.cxyxh.mashibing.c_001.T05_ThreadLocal.M;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class TestPhantomReference {
    private static List<Byte[]> bytes = new ArrayList<>();
    static ReferenceQueue<Object> q = new ReferenceQueue<>();
    //虚引用的作用在于，虚拟机在回收时会发送一个信息表示他要回收这一块内存了
    //所以可以被用来管理堆外内存
    public static void main(String[] args) {
        PhantomReference<M> m = new PhantomReference<>(new M(), q);
        new Thread(() -> {
            while (true){
                bytes.add(new Byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(m.get());
            }
        }).start();

        new Thread(() -> {
            while (true){
                Reference<?> poll = q.poll();
                if (poll != null){
                    System.out.println("这个虚引用被虚拟机回收了");
                }
            }
        }).start();
    }
}
