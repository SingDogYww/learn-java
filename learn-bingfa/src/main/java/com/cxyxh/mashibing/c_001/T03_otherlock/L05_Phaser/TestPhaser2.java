package com.cxyxh.mashibing.c_001.T03_otherlock.L05_Phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 模拟结婚
 * 人 到达  随份子  吃   离开 新郎/新娘  拜堂    敬酒  啪啪啪
 */
public class TestPhaser2 {

    static MarryPhaser phaser = new MarryPhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(20);
        for (int i = 0; i < 19; i++) {
            final int nameIndex = i;
            new Thread(new Person("name" + nameIndex)).start();
        }
        new Thread(new Person("新郎&新娘")).start();
    }

    static void sleep(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            sleep(1000);
            System.out.println(name + "到达了");
            phaser.arriveAndAwaitAdvance();
        }

        public void suifenzi() {
            sleep(1000);
            System.out.println(name + "开始随份子了");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            sleep(1000);
            System.out.println(name + "开始吃了");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            //不能缺席其中一个环节，如果设置了的话，会直接跳到下一个环节
//            if (!"新郎&新娘".equals(name)) {
                sleep(1000);
                System.out.println(name + "离开了");
                phaser.arriveAndAwaitAdvance();
//            }
//            else{
//                phaser.arriveAndDeregister();
//            }
        }

        public void baitang() {
            if ("新郎&新娘".equals(name)) {
                sleep(1000);
                System.out.println(name + "拜堂了");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        public void jingjiu() {
            if ("新郎&新娘".equals(name)) {
                sleep(1000);
                System.out.println(name + "敬酒了");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        public void papapa() {
            if ("新郎&新娘".equals(name)) {
                sleep(1000);
                System.out.println(name + "啪啪啪");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            suifenzi();
            eat();
            leave();
            baitang();
            jingjiu();
            papapa();
        }
    }

    static class MarryPhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (registeredParties) {
                case 0:
                    System.out.println("人齐，开始");
                    return false;
                case 1:
                    System.out.println("随份子，开吃");
                    return false;
                case 2:
                    System.out.println("吃完，开溜");
                    return false;
                case 3:
                    System.out.println("遛完，开始");
                    return false;
                case 4:
                    System.out.println("拜堂结束");
                    return false;
                case 5:
                    System.out.println("敬酒结束");
                    return false;
                case 6:
                    System.out.println("啪啪啪结束");
                    return true;
                default:
                    return true;
            }

        }
    }

}
