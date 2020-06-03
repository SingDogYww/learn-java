package com.cxyxh.c02_frame;

import java.awt.*;

public class Bullet extends GameObject{
    private static final int speed = 10;
    private int x, y;
    private Dir dir;
    private static final int width = 50, height = 50;
    private boolean live = true;
    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HIGHT = ResourceMgr.bulletL.getHeight();
    private Group group;
    private Rectangle rectangle = new Rectangle();

    public Rectangle getRectangle() {
        return rectangle;
    }

    GameModel gameModel;

    public Bullet(int x, int y, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HIGHT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if (!live){
            gameModel.removeObject(this);
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

    private void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case UP:
                y -= speed;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HIGHT) live = false;
    }


    public void collideWith(Tank tank) {
        if (this.group.equals(tank.getGroup())) return;
        //TODO 每次都会new对象，会让垃圾回收器时不时的回收一下
        if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            int bX = tank.getX() + Tank.WIDTH / 2 - Explodes.WIDTH / 2;
            int bY = tank.getY() + Tank.HIGHT / 2 - Explodes.HIGHT / 2;
            this.gameModel.addGameObject(new Explodes(bX, bY, this.gameModel));
        }
    }

    private void die() {
        this.live = false;
    }
}
