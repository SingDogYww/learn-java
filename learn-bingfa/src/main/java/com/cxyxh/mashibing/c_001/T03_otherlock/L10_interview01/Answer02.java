package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Exchanger
 */
public class Answer02 {

    static List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Answer02 answer = new Answer02();
        Exchanger exchanger = new Exchanger();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                answer.add(i);
                System.out.println("add" + i);
                if (answer.size() == 5) {
                    try {
                        exchanger.exchange(new Object());
                        exchanger.exchange(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            try {
                exchanger.exchange(new Object());
                System.out.println("收到通知，结束");
                exchanger.exchange(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
