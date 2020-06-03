package com.cxyxh.c02_frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
