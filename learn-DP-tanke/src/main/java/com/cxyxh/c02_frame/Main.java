package com.cxyxh.c02_frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * model就是GameModel中的各种实体类
         * View就是TankFrame
         */
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
