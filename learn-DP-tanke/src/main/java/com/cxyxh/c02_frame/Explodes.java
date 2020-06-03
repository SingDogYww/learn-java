package com.cxyxh.c02_frame;

import java.awt.*;

public class Explodes extends GameObject {
    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HIGHT = ResourceMgr.bulletL.getHeight();
    private int x, y;
    private GameModel gameModel;
    private boolean alive;
    private int step = 0;


    public Explodes(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
        if (step >= ResourceMgr.explodes.length) {
            gameModel.removeObject(this);
        }
    }

}
