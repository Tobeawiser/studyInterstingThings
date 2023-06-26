package com.example.lovestory.util.test.sort;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {

    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(1000000000);
        int o = 0;
        while (true) {
            if (o == 100000) {
                break;
            }
            long l = random.nextLong();
            String s = String.valueOf(l);
            s = s.replaceAll("-", "");
            char[] chars = s.toCharArray();
            int[] waitSort = new int[50];
            for (int i = 0; i < chars.length; i++) {
                waitSort[i] = Integer.valueOf(String.valueOf(chars[i]));
            }
            //int[] waitSort = new int[]{2,4,6,5,3,0,9,7,8,1};
            int[] sort = sort(waitSort);
            Arrays.stream(sort).forEach(a -> System.out.print(a + " "));
            System.out.println("-------------------");
            o++;
        }


    }

    public static int[] sort(int[] waitSort) {
        //插入排序  也就是相当于缓慢排序让前面先有序，而后插入
        if (waitSort != null && waitSort.length > 0) {
            int length = waitSort.length;
            for (int i = 1; i < length; i++) {
                //从自身前一个数开始
                //存一下
                int value = waitSort[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (waitSort[j] > value) {
                        //前一个数比它大，将当前位置给前一个数，往前移
                        waitSort[j + 1] = waitSort[j];
                        //已经移到最后一位
                        if (j == 0) {
                            //占住此位置
                            waitSort[j] = value;
                            break;
                        }
                    } else {
                        //前一个数比它小，占住此位置
                        waitSort[j + 1] = value;
                        break;
                    }
                }
            }
            return waitSort;
        }
        return null;
    }
}
