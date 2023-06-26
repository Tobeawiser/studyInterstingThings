package com.example.lovestory.util.test.sort;

import java.util.Arrays;

public class ChooseSort {

    public static void main(String[] args) {
        int[] waitSort = new int[]{2, 4, 6, 5, 3, 0, 9, 7, 8, 1};
        int[] sort = sort(waitSort);
        Arrays.stream(sort).forEach(System.out::println);
    }

    public static int[] sort(int[] waitSort) {
        //选择排序
        if (waitSort != null && waitSort.length > 0) {
            int length = waitSort.length;
            //记录一个最小值 以及索引
            int min = 0;
            int minIndex = 0;
            for (int i = 0; i < length; i++) {
                //从 i 到 length - 1
                //最小值初始化
                min = waitSort[i];
                minIndex = i;
                for (int j = i; j < length; j++) {
                    if (waitSort[j] < min) {
                        //最小值更新 索引更新
                        min = waitSort[j];
                        minIndex = j;
                    }
                    //最后一个元素时，对i进行交换
                    if (j == length - 1) {
                        waitSort[minIndex] = waitSort[i];
                        waitSort[i] = min;
                    }
                }
            }
            return waitSort;
        }
        return null;
    }
}
