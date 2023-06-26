package com.example.lovestory.util.test.likemath;

import java.util.Arrays;

public class MergeTwoArray {

    public static void main(String[] args) {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //滑动指针 将n的数据一个个遍历插入
        int[] result = new int[m + n];
        int l = 0;
        int r = 0;
        while (l < m && r < n) {
            //小的放进去
            if (nums1[l] <= nums2[r]) {
                result[l + r] = nums1[l];
                l++;
            } else {
                result[l + r] = nums2[r];
                r++;
            }
        }
        //剩下的给放入
        if (l == m) {
            //r放入result
            while (r < n) {
                result[r + l] = nums2[r];
                r++;
            }
        } else if (r == n) {
            //l放入result
            while (l < m) {
                result[r + l] = nums1[l];
                l++;
            }
        }
        Arrays.stream(nums1).forEach(value -> System.out.print(value));
        System.out.println("---");
        nums1 = result;
        Arrays.stream(nums1).forEach(value -> System.out.print(value));
    }
}
