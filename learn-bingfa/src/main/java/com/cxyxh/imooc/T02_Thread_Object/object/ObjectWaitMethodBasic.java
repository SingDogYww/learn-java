package com.cxyxh.imooc.T02_Thread_Object.object;

public class ObjectWaitMethodBasic {
    private static Object lock = new Object();

    public static class Thread1 implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("线程1启动");
                try {
                    //执行了wait()方法了之后，线程会释放锁
                    //当线程持有一把锁的时候，会在这个锁对象的对象头里面的MarkWord里面加上自己的标记，然后释放的时候也会移除掉
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我胡汉三又回来了");
            }
        }
    }


    public static class Thread2 implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("这个sb走了");
                //把线程1叫醒之后，锁还是有线程2持有，必须等线程2释放了锁之后，线程1才有机会获取到
                lock.notify();
                System.out.println("把他叫醒");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
