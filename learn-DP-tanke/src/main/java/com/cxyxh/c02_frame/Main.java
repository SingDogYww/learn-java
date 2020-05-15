package com.cxyxh.c02_frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        //初始化 敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(100 + 100 * i,100, Dir.DOWN, frame, Group.BAD);
            tank.setMoving(true);
            frame.tanks.add(tank);
        }

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
