package com.cxyxh.c02_frame;

public interface FireStrategy {
    /**
     * 对修改关闭，对扩展开放
     * 就是尽量不去修改现有的代码，而进行扩展
     * @param tank
     */
    void fire(Tank tank, AbstractFactory factory);
}
