package com.cxyxh.c04_DesignPattern.factory.abstractFactory;

public class FairyFactory extends AbstractFactory{
    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }

    @Override
    Food createFood() {
        return new Chips();
    }

    @Override
    Verhicle createVerhicle() {
        return new Broom();
    }
}
