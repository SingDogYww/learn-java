package com.cxyxh.imooc.T02_Thread_Object.object;

/**
 * 证明：wait()方法只释放指定的锁
 */
public class ObjectWaitReleaseOwnLock {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1启动");
            synchronized (resourceA){
                System.out.println("thread1获取到resourceA锁");
                synchronized (resourceB){
                    System.out.println("thread1获取到resourceB锁");
                    try {
                        System.out.println("thread1释放A锁");
                        resourceA.wait();
                        //不会继续往下继续执行，因为当前线程没有获取到resourceA锁，相当于这个代码块中的代码必须要同时持有两个锁才能执行
                        System.out.println("thread1继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (resourceA){
                System.out.println("thread2获取到了resourceA锁");
                System.out.println("thread2尝试获取resourceB锁");
                synchronized (resourceB){
                    System.out.println("thread2获取到了resourceB锁");
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }


}
