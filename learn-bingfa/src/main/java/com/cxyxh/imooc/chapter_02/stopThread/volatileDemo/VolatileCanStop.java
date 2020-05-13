package com.cxyxh.imooc.chapter_02.stopThread.volatileDemo;

public class VolatileCanStop implements Runnable {
    private static volatile boolean cancle = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < Integer.MAX_VALUE / 2 && !VolatileCanStop.cancle) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }

                Thread.sleep(1);

                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new VolatileCanStop());
        thread.start();
        Thread.sleep(5000);
        VolatileCanStop.cancle = true;
    }


}
