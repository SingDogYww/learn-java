package com.cxyxh.imooc.T01_synchronized.T005_reentrant;

/**
 * 可重入粒度测试：调用父类被锁方法
 */
public class SyncSuperClass {
    public synchronized void doSomething(){
        System.out.println("这是父类方法");
    }
}

class SonClass extends SyncSuperClass{
    @Override
    public synchronized void doSomething() {
        System.out.println("调用子类方法");
        super.doSomething();
    }

    public static void main(String[] args) {
        SonClass sonClass = new SonClass();
        Thread thread = new Thread(sonClass::doSomething);
        thread.start();
    }
}
