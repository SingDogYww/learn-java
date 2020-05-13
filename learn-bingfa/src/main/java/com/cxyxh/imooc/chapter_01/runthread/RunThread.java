package com.cxyxh.imooc.chapter_01.runthread;

/**
 * 启动线程 run()和start()
 */
public class RunThread {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
