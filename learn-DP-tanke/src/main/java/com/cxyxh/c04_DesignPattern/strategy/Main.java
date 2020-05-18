package com.cxyxh.c04_DesignPattern.strategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Sort sort = new Sort();
        Cat[] cats = new Cat[]{new Cat(77, 1), new Cat(23, 1), new Cat(62, 1), new Cat(12, 1), new Cat(27, 1), new Cat(67, 1), };
//        sort.sort(cats);
//        System.out.println(Arrays.toString(cats));

        Sort<Cat> catSort = new Sort<>();
        catSort.sort(cats, new CatCompatator());
        System.out.println(Arrays.toString(cats));
    }
}
