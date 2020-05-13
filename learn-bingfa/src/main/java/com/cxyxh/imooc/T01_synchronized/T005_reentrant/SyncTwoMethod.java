package com.cxyxh.imooc.T01_synchronized.T005_reentrant;

/**
 * 递归粒度测试：测试调用不同的被锁方法
 */
public class SyncTwoMethod {
    public synchronized void method1(){
        System.out.println("这是方法1");
        method2();
    }

    public synchronized void method2(){
        System.out.println("这是方法2");
    }

    public static void main(String[] args) {
        SyncTwoMethod syncRecursive = new SyncTwoMethod();
        Thread thread = new Thread(syncRecursive::method1);
        thread.start();
    }
}
