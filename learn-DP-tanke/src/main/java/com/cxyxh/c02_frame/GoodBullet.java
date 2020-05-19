package com.cxyxh.c02_frame;

import java.awt.*;

public class GoodBullet extends Bullet {
    private static int speed = 10;
    public static int width = ResourceMgr.bulletL.getWidth();
    public static int height = ResourceMgr.bulletL.getHeight();

    public GoodBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        super(x, y, dir, tf, group, speed, new GoodTankFactory());
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
    }

    @Override
    public void paint(Graphics g) {
        if (!live) {
            tf.bullet.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
        move();
    }


}
