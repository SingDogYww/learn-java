package com.cxyxh.c04_DesignPattern.factory.factoryMethod;

/**
 * 简单工厂模式
 */
public class SimpleVehicleFactory {
    public Car createCar(){
        //做一些前置操作
        return new Car();
    }

    public Plane createPlane(){
        return new Plane();
    }
}
