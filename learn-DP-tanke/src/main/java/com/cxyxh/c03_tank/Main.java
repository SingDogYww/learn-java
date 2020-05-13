package com.cxyxh.c03_tank;

import com.cxyxh.c03_tank.TankFrame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
