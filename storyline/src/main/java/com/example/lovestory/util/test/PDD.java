package com.example.lovestory.util.test;

import java.util.HashMap;
import java.util.Map;

public class PDD {
    /**
     * 给你一个数组，其中数组中的每个值与相邻元素之间的差值的绝对值是m，现在给你一个目标值k，找到数组中所有等于k的元素的索引，使用集合返回。
     * 遍历的元素越少越好，无序
     * List fun(List<Integer> list,int m,int k)
     * 就比如[a,b,c,d,e,f,g,h,i] `m=2` k=3 返回[2] ，k一定是数组中的某个值
     */
    public PDD() {
        super();
        this.equals('d');

    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 5};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints[0] + ";" + ints[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        //下标，值
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[0];
    }
}
