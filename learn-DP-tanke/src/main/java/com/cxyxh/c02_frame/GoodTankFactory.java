package com.cxyxh.c02_frame;

public class GoodTankFactory extends AbstractFactory {
    @Override
    Bullet createBullet(Tank tank, Dir dir) {
        int bX = tank.getX() + GoodTank.width / 2 - GoodBullet.width / 2;
        int bY = tank.getY() + GoodTank.height / 2 - GoodBullet.height / 2;
        return  new GoodBullet(bX, bY, dir, tank.tf, tank.group);
    }

    @Override
    Tank createTank(int x, int y, Dir dir, TankFrame tf) {
        return new GoodTank(x, y, dir, tf, Group.GOOD);
    }

    @Override
    Explodes createExplodes(Tank tank) {
        int bX = tank.getX() + tank.getWidth() / 2 - GoodExplodes.width / 2;
        int bY = tank.getY() + tank.getHeight() / 2 - GoodExplodes.height / 2;
        return new GoodExplodes(bX, bY, tank.tf);
    }
}
