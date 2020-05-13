package com.cxyxh.mashibing.c_001.T03_otherlock.L10_interview01;

import java.util.ArrayList;
import java.util.List;

/**
 * wait/notify
 */
public class Answer01 {

    static List<Integer> list = new ArrayList<>();

    public void add(Integer ob) {
        list.add(ob);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Answer01 answer = new Answer01();
        new Thread(() -> {
            synchronized (answer) {
                for (int i = 0; i < 10; i++) {
                    answer.add(i);
                    System.out.println("add" + i);
                    if (answer.size() == 5){
                        try {
                            answer.notify();
                            answer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (answer) {
                try {
                    if (answer.size() != 5) {
                        answer.wait();
                    }
                    System.out.println("收到通知，结束");
                    answer.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
