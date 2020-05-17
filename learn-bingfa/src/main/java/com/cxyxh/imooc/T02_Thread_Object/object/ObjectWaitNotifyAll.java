package com.cxyxh.imooc.T02_Thread_Object.object;

public class ObjectWaitNotifyAll implements Runnable{
    private static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        ObjectWaitNotifyAll object = new ObjectWaitNotifyAll();
        Thread thread1 = new Thread(object);
        Thread thread2 = new Thread(object);
        Thread thread3 = new Thread(() -> {
            synchronized(resourceA){
                resourceA.notifyAll();
                System.out.println("thread3准备唤醒全部线程了");
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(200);
        thread3.start();
    }


    @Override
    public void run() {
        synchronized(resourceA){
            System.out.println(Thread.currentThread().getName() + "获取到了锁");
            try {
                System.out.println(Thread.currentThread().getName() + "准备释放锁");
                resourceA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束方法");
        }
    }
}
