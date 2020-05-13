package com.cxyxh.imooc.T01_synchronized.T005_reentrant;

/**
 * 可重入粒度测试：递归调用本方法
 */
public class SyncRecursive {
    int i = 0;

    public synchronized void method(){
        System.out.println("这是递归方法，i = " + i);
        if (i == 0){
            i ++;
            method();
        }
    }

    public static void main(String[] args) {
        SyncRecursive syncRecursive = new SyncRecursive();
        Thread thread = new Thread(syncRecursive::method);
        thread.start();
    }
}
