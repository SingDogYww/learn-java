package com.cxyxh.imooc.chapter_01.createThreads;

/**
 * 使用继承Thread类实现的线程
 */
public class ThreadStyle extends Thread{


    @Override
    public void run() {
        System.out.println("这是通过继承Thread类创建的线程");
    }

    public static void main(String[] args) {
//        Thread thread = new Thread(new ThreadStyle());
        new ThreadStyle().start();
    }
}
