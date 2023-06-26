package com.example.lovestory.util.test.sort;

import java.util.Arrays;

public class QuikSort {

    public static void main(String[] args) {
        int[] waitSort = new int[]{6, 4, 2, 5, 3, 0, 9, 7, 8, 1};
        int[] sort = quikSort(waitSort);
        Arrays.stream(sort).forEach(System.out::println);
    }

    public static int[] quikSort(int[] waitSort) {
        //冒泡排序
        if (waitSort != null && waitSort.length > 0) {
            int length = waitSort.length;
            //快速排序
            //分区  递归
            int start = 0;
            int end = length - 1;
            //第一个数为基数 基数进行调换时候和左边交换
            int base = waitSort[0];
            //左右指针相遇基准值交换
            while (start + 1 != end && start < end) {
                int leftValue = waitSort[start];
                //只要比基准值小，指针往后移
                while (leftValue <= base && leftValue < length) {
                    start++;
                    leftValue = waitSort[start];
                }
                int rightValue = waitSort[end];
                //只要比基准值大，指针往前移
                while (rightValue >= base && rightValue > 0) {
                    end--;
                    rightValue = waitSort[end];
                }
                //没有走过界才需要交换
                if (start < end) {
                    //遇到需要调换
                    int midValue = waitSort[start];
                    waitSort[start] = waitSort[end];
                    waitSort[end] = midValue;
                }
            }
            //相遇左边值与start交换
            if (start > 0) {
                start--;
            }
            waitSort[0] = waitSort[start];
            waitSort[start] = base;
            //我这递归方法参数有些问题
            //递归不用返回条件，能够完成各自分配任务就ok
            //左边递归调用    有时参数的抽象还是有用的
            //quikSort(waitSort[0,start])
            //右边递归调用
            return waitSort;
        }
        return null;
    }
}
