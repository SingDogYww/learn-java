package com.cxyxh.imooc.chapter_02.stopThread;

public class CantBeStop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            //FIXME 如果不加判断是否已经被中断的话，他只是在catch之后打印了一下就没有继续操作了，所以还是继续往下执行
            //FIXME 如果加上了判断是否已经被中断之后，也是一样的情况，应为sleep在设计时，添加了一个机制，就是如果中断被处理之后，是否中断这个标记位会被清除
            while(num <= Integer.MAX_VALUE / 2 && !Thread.currentThread().isInterrupted()){
                if (num % 100 == 0){
                    System.out.println(num + "是100的倍数");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    System.out.println(interrupted);
                }
                num++;
            }
        };


        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
