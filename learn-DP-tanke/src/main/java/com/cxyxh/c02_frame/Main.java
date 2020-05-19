package com.cxyxh.c02_frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        //初始化 敌方坦克
        for (int i = 0; i < PropertyMgr.getInteger("initial.enemy.count"); i++) {
            BadTankFactory factory = new BadTankFactory();
            Tank tank1 = factory.createTank(100 + 100 * i, 100, Dir.DOWN, frame);
            tank1.setMoving(true);
            frame.tanks.add(tank1);
        }

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
