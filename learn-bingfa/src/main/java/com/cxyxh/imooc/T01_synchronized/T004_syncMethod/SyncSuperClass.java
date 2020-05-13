package com.cxyxh.imooc.T01_synchronized.T004_syncMethod;

public class SyncSuperClass {
    public synchronized void doSomething(){
        System.out.println(Thread.currentThread().getName() + "这是父类方法");
    }
}

class SonClass extends SyncSuperClass {
    @Override
    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + "调用子类方法");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "开始调用父类方法");
        super.doSomething();
    }

    public void useSuperClass(){
        super.doSomething();
    }


    public static void main(String[] args) throws InterruptedException {
        SonClass sonClass = new SonClass();
        Thread thread = new Thread(sonClass::doSomething);
        thread.start();
        Thread.sleep(1000);
        Thread thread1 = new Thread(sonClass::useSuperClass);
        thread1.start();
    }
}
