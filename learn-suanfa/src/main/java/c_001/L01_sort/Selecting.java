package c_001.L01_sort;

import java.util.Arrays;

public class Selecting {
    public static void main(String[] args) {
        int[] arr = new int[]{24, 14, 34, 53, 12, 66, 26};
        Selecting selecting = new Selecting();
        selecting.selecting(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void selecting(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = 0;
            int j = 0;
            for (; j < arr.length - i; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            swap(arr, max, j - 1);
        }
    }

    private void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
