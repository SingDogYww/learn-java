package com.cxyxh.mashibing.c_001.T03_otherlock.L08_Exchanger;

import java.util.concurrent.Exchanger;

public class TestExchanger {

    static Exchanger<String> exchanger = new Exchanger<>();


    public static void main(String[] args) {
        new Thread(() -> {
            String name = "你大舅, 我是T1";
            System.out.println(name);
            try {
                String msg = exchanger.exchange(name);
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            String name = "你大爷, 我是t2";
            try {
                exchanger.exchange(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }


}
