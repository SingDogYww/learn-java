package com.cxyxh.imooc.chapter_02.stopThread;

public class WithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && num <= 500) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }

                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行结束");
        };
        /**
         * FIXME 在线程sleep的过程中，打断会报错
         *  java.lang.InterruptedException: sleep interrupted
         *  	at java.lang.Thread.sleep(Native Method)
         *  	at com.cxyxh.chapter_02.stopThread.WithSleep.lambda$main$0(WithSleep.java:13)
         *  	at java.lang.Thread.run(Thread.java:748)
         */

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
