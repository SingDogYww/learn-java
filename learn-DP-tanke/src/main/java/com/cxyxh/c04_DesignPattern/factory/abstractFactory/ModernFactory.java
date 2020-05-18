package com.cxyxh.c04_DesignPattern.factory.abstractFactory;

public class ModernFactory extends AbstractFactory {

    @Override
    Weapon createWeapon() {
        return new AK47();
    }

    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Verhicle createVerhicle() {
        return new Car();
    }
}
