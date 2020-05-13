package com.cxyxh.imooc.T01_synchronized.T004_syncMethod;

/**
 * 在锁了方法中调用另一个没加锁的方法，不会阻塞
 */
public class SyncMethodAndNoSyncMethod {

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "这是有锁的方法");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
    }

    public void method2(){
        System.out.println(Thread.currentThread().getName() + "这是无锁的方法");
    }

    public static void main(String[] args) throws InterruptedException {
        SyncMethodAndNoSyncMethod testMethod = new SyncMethodAndNoSyncMethod();
        Thread thread1 = new Thread(testMethod::method1);
        Thread thread2 = new Thread(testMethod::method2);
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }

}
