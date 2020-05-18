package com.cxyxh.c02_frame;

/**
 * 开火策略1：正常发放
 */
public class FireStrategy01 implements FireStrategy {
    @Override
    public void fire(TankFrame tf, int bX, int bY, Dir dir, Group group) {
        tf.bullet.add(new Bullet(bX, bY, dir, tf, group));
    }
}
