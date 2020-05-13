package com.cxyxh.imooc.chapter_02.stopThread;

/**
 * 在没有阻塞的情况下停止线程
 */
public class WithoutSleep implements Runnable{

    @Override
    public void run() {
        int num  = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2){
            if (num % 10000 == 0){
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务执行结束");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WithoutSleep());
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }
}
