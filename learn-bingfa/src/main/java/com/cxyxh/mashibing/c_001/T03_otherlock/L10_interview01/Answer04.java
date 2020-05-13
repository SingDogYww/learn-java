package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 直接进行判断会有问题，除非使用线程睡眠
 */
public class Answer04 {

    static volatile List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        Answer04 answer = new Answer04();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
            }
        });

        t2 = new Thread(() -> {
            while (answer.size() != 5){
            }
            System.out.println("收到通知，结束");
        });

        t1.start();
        t2.start();

    }
}
