package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 */
public class Answer03 {

    static List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        Answer03 answer = new Answer03();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
                if (answer.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("收到通知，结束");
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();

    }
}
