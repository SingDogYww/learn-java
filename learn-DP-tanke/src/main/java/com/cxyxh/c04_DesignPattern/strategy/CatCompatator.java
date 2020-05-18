package com.cxyxh.c04_DesignPattern.strategy;

public class CatCompatator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
