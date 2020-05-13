package com.cxyxh.mashibing.c_001.T03_otherlock.L05_Phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 模拟聚会
 *  人 到达    吃   离开
 */
public class TestPhaser1 {

    static JuhuiPhaser phaser = new JuhuiPhaser();

    public static void main(String[] args) {
        String[] persons = new String[]{"海飞", "嘉俊", "小王儿", "戚戬", "王鹏", "阿伟", "阿文", "书柜"};
        phaser.bulkRegister(8);
        for (int i = 0; i < 8; i++) {
            Thread person = new Thread(new Person(persons[i]));
            person.start();
        }

    }

    static void sleep(long mills){
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Runnable{
        String name;
        public Person(String name){
            this.name = name;
        }

        public void arrive(){
            sleep(1000);
            System.out.println(name + "到达了");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            sleep(1000);
            System.out.println(name + "吃完了");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            sleep(1000);
            System.out.println(name + "走了");
            phaser.arriveAndAwaitAdvance();
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
        }
    }

    static class JuhuiPhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0 :
                    System.out.println("所有人到齐了，开整");
                    return false;
                case 1:
                    System.out.println("所有人都吃完了，开溜");
                    return false;
                case 2:
                    System.out.println("所有人都遛完了，结账吧");
                    System.out.println("聚会结束");
                    return true;
                default:
                    return true;
            }
        }
    }

}
