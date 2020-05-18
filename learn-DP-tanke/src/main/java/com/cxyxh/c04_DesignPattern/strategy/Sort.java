package com.cxyxh.c04_DesignPattern.strategy;


public class Sort<T> {

    /**
     * 给各个类型的数组进行排序
     */

    public void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) == 1) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public void swap(T[] arr, int j, int i) {
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private void swap(Comparable[] arr, int j, int i) {
        Comparable temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
