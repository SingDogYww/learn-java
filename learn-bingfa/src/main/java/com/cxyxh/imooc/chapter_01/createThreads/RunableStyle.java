package com.cxyxh.imooc.chapter_01.createThreads;

/**
 * 使用Runable创建线程
 */
public class RunableStyle implements Runnable{

    @Override
    public void run() {

        System.out.println("使用Runnable接口实现线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunableStyle());
        thread.start();
    }
}
