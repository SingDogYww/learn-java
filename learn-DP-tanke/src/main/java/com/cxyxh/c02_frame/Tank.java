package com.cxyxh.c02_frame;

import com.cxyxh.c02_frame.strategy.DefaultFireStrategy;
import com.cxyxh.c02_frame.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    private int x, y;
    private Dir dir;
    private static final int speed = 5;
    private boolean moving = false;
    private boolean alive = true;
    public GameModel gameModel;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HIGHT = ResourceMgr.goodTankU.getHeight();
    private Random random = new Random();
    private Group group = Group.BAD;
    Rectangle rectangle = new Rectangle();
    public Tank(int x, int y, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.gameModel = gameModel;
        rectangle.x = this.x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HIGHT;
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!alive) gameModel.removeObject(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU, x, y, null);
                break;
        }
        move();
    }

    public void move() {
        if (!moving) return;
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
        if (this.group == Group.BAD && random.nextInt() > 5) {
            this.fire(new DefaultFireStrategy());
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 32) y = 32;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2 ;
        if (this.y > TankFrame.GAME_HIGHT - Tank.HIGHT) y = TankFrame.GAME_HIGHT - Tank.HIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire(FireStrategy strategy) {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HIGHT / 2 - Bullet.HIGHT / 2;
        strategy.fire(this);
    }

    public void die() {
        this.alive = false;
    }

    public void fire() {
        String defaultFire = PropertyMgr.getString("defaultFire");
        try {
            Class<?> aClass = Class.forName(defaultFire);
            Object o = aClass.newInstance();
            fire((FireStrategy) o);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止坦克
     */
    public void stop(){
        moving = false;
    }

    /**
     * 反转坦克方向
     */
    public void reverseDir(){
        switch (dir) {
            case LEFT:
                dir = Dir.RIGHT;
                break;
            case RIGHT:
                dir = Dir.LEFT;
                break;
            case DOWN:
                dir = Dir.UP;
                break;
            case UP:
                dir = Dir.DOWN;
                break;
        }
    }
}
