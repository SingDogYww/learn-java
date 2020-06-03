package com.cxyxh.c02_frame.strategy;

import com.cxyxh.c02_frame.Bullet;
import com.cxyxh.c02_frame.Tank;
import com.cxyxh.c02_frame.strategy.FireStrategy;

/**
 * 开火策略1：正常发放
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HIGHT / 2 - Bullet.HIGHT / 2;
        tank.gameModel.addGameObject(new Bullet(bX, bY, tank.getDir(), tank.gameModel, tank.getGroup()));
    }
}
