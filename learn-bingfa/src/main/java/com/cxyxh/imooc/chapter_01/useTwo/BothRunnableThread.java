package com.cxyxh.imooc.chapter_01.useTwo;

public class BothRunnableThread{




    public static void main(String[] args) {
        new Thread(() -> System.out.println("这是来自Runnable")){
            @Override
            public void run() {
                System.out.println("这是来自Thread");
            }
        }.start();
    }


}
