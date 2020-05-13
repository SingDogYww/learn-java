package com.cxyxh.imooc.T01_synchronized.T003_resolveStartQS;

/**
 * 经过两个线程的累加之后最后的count值不为40000
 * 因为++操作不是一个，而是三条命令
 */
public class DispearRequestResolve1 {
    static int i = 0;
    private static class Thread1 implements Runnable{
        @Override
        public void run() {
            /**
             * 在这段代码上添加synchronized
             * 四种添加的方式
             */
            synchronized (this) {
                for (int j = 0; j < 20000; j++) {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread1);
        t1.start();
        t2.start();
        //如果不加的话，就会main线程就会先执行
        t1.join();
        t2.join();
        System.out.println("当前值：" + i);
    }
}
