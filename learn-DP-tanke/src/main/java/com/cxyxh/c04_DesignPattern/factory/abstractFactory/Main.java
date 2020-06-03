package com.cxyxh.c04_DesignPattern.factory.abstractFactory;

/**
 * 抽象工厂模式是在最基础的工厂模式中纵向拓展，一个工厂可以生产多个产品，但是不能增加产品的数量，在产品方向上进行扩展
 * 而工厂方法模式是在基础上横向扩展，可以增加工厂和产品，但是只能一个工厂只能生产一种产品，在产品族上扩展
 */
public class Main {

    public static void main(String[] args) {
        AbstractFactory factory = new FairyFactory();
        Food food = factory.createFood();
        food.eat();
        Verhicle verhicle = factory.createVerhicle();
        verhicle.go();
        Weapon weapon = factory.createWeapon();
        weapon.attack();
    }
}
