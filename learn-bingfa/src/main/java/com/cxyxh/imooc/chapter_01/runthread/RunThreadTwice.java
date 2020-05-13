package com.cxyxh.imooc.chapter_01.runthread;

/**
 * 连续两次调用start()
 */
public class RunThreadTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();

//        Exception in thread "main" java.lang.IllegalThreadStateException
//        at java.lang.Thread.start(Thread.java:708)
//        at com.cxyxh.chapter_01.runthread.RunThreadTwice.main(RunThreadTwice.java:10)

    }
}
