package com.cxyxh.imooc.T01_synchronized.T001_locktype;

public class ClassLock_StaticMethod implements Runnable {

    static ClassLock_StaticMethod instance1 = new ClassLock_StaticMethod();
    static ClassLock_StaticMethod instance2 = new ClassLock_StaticMethod();

    @Override
    public void run() {
        method();
    }

    /**
     * 当方法方法不为static时，两个不同的线程获取到的实际上不是同一个锁
     * 所以如果需要全局的对这个方法加上锁static
     */
    public static synchronized void method() {
        System.out.println(Thread.currentThread().getName() + "开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
    }
}
