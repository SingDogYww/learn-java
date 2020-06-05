package com.cxyxh.c02_frame.cor;

import com.cxyxh.c02_frame.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    /**
     * 连个链条之间添加了关系
     */
    public ColliderChain() {
        addCollider(new TankTankCollider());
        addCollider(new BulletTankCollider());
    }

    private List<Collider> colliders = new LinkedList<>();

    public void addCollider(Collider collider){
        colliders.add(collider);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliders) {
            if (collider.collide(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
