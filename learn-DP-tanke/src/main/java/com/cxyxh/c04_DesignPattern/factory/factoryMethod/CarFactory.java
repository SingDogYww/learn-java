package com.cxyxh.c04_DesignPattern.factory.factoryMethod;

public class CarFactory {
    public Car create(){
        System.out.println("日志处理");
        return new Car();
    }
}
