package com.cxyxh.c02_frame.cor;

import com.cxyxh.c02_frame.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
