package com.cxyxh.c02_frame;

import com.cxyxh.c02_frame.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对于TanKFrame来说
 * 门面模式
 * Facade 抽象出一个对象，用于管理这些关系比较复杂的对象
 * 对于游戏物品来说
 * 调停者
 * 将所有游戏物品解耦
 * 相当于居委会大妈
 */
public class GameModel {
    ColliderChain colliderChain = new ColliderChain();
    Tank tank = new Tank(400, 600, Dir.UP, this, Group.GOOD);
//    public List<Bullet> bullet = new ArrayList<>();
//    public List<Tank> tanks = new ArrayList<>();
//    //    Explodes explodes = new Explodes(200, 200, this);
//    public List<Explodes> explodesList = new ArrayList<>();

    public List<GameObject> gameObjects = new ArrayList<>();

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }


    public GameModel() {
        //初始化 敌方坦克
        for (int i = 0; i < PropertyMgr.getInteger("initial.enemy.count"); i++) {
            Tank tank = new Tank(100 + 100 * i, 100, Dir.DOWN, this, Group.BAD);
            tank.setMoving(true);
            gameObjects.add(tank);
        }
    }

    public void paint(Graphics g) {
        tank.paint(g);
//        List<Bullet> collect = bullet.stream().filter(o1 -> (o1.getX() < GAME_WIDTH && o1.getX() > 0) && (o1.getY() < GAME_HIGHT && o1.getY() > 0)).collect(Collectors.toList());
//        bullet = collect;
//        collect.forEach(o1 -> o1.paint(g));
        g.setColor(Color.WHITE);
//        g.drawString("子弹数量" + bullet.size(), 10, 60);
//        g.drawString("敌人数量" + tanks.size(), 10, 80);
//        for (int i = 0; i < bullet.size(); i++) {
//            Bullet bullet = this.bullet.get(i);
//            bullet.paint(g);
//        }
//        for (int i = 0; i < explodesList.size(); i++) {
//            Explodes explodes = explodesList.get(i);
//            explodes.paint(g);
//        }
//        for (int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }

        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            gameObject.paint(g);
        }

        //碰撞检测
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                //可以使用反射，获取所有的碰撞器来进行碰撞检测
                colliderChain.collide(o1,o2);
            }
        }




//
//        for (int i = 0; i < bullet.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullet.get(i).collideWith(tanks.get(j));
//            }
//            //判断己方坦克碰撞检测
//            bullet.get(i).collideWith(tank);
//        }

//        explodes.paint(g);
    }

    public Tank getMainTank() {
        return tank;
    }


}
