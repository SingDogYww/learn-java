package com.cxyxh.imooc.T01_synchronized.T001_locktype;

/**
 * 代码块锁
 * 当线程1释放lock1的时候，线程2能马上获取到lock1
 */
public class ObjectLock_CodeBlock2 {

    private static class codeBlock implements Runnable{
        Object lock1 = new Object();
        Object lock2 = new Object();

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "获取到lock1的锁，开始运行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "准备释放lock1的锁，结束");
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获取到lock2的锁，开始运行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "准备释放lock1的锁，结束");
            }
        }
    }

    public static void main(String[] args) {
        codeBlock codeBlock = new codeBlock();
        Thread t1 = new Thread(codeBlock);
        Thread t2 = new Thread(codeBlock);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){
        }
        System.out.println("程序结束");
    }
}
