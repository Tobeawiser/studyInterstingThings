package com.example.lovestory.util.test;

import java.util.Arrays;

public class MergeArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 0, 0, 0, 0};
        int m = 4;
        int[] nums2 = {2, 5, 6, 7};
        int n = 4;
        //output
        //第一个数组延展为nums1后0，不要开辟额外空间
        int h = m + n - 1;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[h] = nums1[m - 1];
                m--;
            } else {
                nums1[h] = nums2[n - 1];
                n--;
            }
            h--;
        }
        Arrays.stream(Arrays.stream(nums1).toArray()).forEach(a -> System.out.println(a));
        //longestMountain(new int[]{});
        twoSum(new int[]{3, 2, 4}, 6);

    }

    public static int longestMountain(int[] arr) {

        arr = new int[]{1, 2, 5, 7, 9, 10, 5, 4, 3, 6, 9, 11, 14, 15};
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = arr[i - 1] < arr[i] ? left[i - 1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = arr[i + 1] < arr[i] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
