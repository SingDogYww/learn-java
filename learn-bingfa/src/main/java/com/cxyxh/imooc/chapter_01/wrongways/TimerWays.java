package com.cxyxh.imooc.chapter_01.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class TimerWays {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
