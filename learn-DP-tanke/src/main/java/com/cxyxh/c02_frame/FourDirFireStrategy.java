package com.cxyxh.c02_frame;

/**
 * 开火策略2：四个方向都开火
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HIGHT / 2 - Bullet.HIGHT / 2;
        tank.tf.bullet.add(new Bullet(bX, bY, Dir.LEFT, tank.tf, tank.getGroup()));
        tank.tf.bullet.add(new Bullet(bX, bY, Dir.DOWN, tank.tf, tank.getGroup()));
        tank.tf.bullet.add(new Bullet(bX, bY, Dir.UP, tank.tf, tank.getGroup()));
        tank.tf.bullet.add(new Bullet(bX, bY, Dir.RIGHT, tank.tf, tank.getGroup()));
    }
}