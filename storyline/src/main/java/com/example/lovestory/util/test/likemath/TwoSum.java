package com.example.lovestory.util.test.likemath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSum {
    public static void main(String[] args) throws Exception {
        isValid("(])"
        );
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

    public static int[] twoSumImprove(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            //put前进行检查
            Integer addTwo = target - nums[i];
            if (map.get(addTwo) == null) {
                map.put(nums[i], i);
            } else {
                return new int[]{i, map.get(addTwo)};
            }

        }

        return null;
    }

    public static boolean isValid(String s) {
        //括号闭合 直接数数就行
        int length = s.length();
        if (length < 1 || length > 10000) {
            return false;
        }
        int l = 0;
        int k = 0;
        int j = 0;
        List<Character> list = new ArrayList();
        //左半开口 右半闭口  如果加入为闭口，则必须与堆顶开口匹配
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            //进行加减
            if (list.size() == 0) {
                if (')' == c || '}' == c || ']' == c) {
                    return false;
                }
            }

            if ('(' == c || '{' == c || '[' == c) {
                list.add(c);
            }
            if (')' == c || '}' == c || ']' == c) {
                int size = list.size();
                char before = list.get(size - 1);
                if (')' == c) {
                    c = '(';
                }
                if ('}' == c) {
                    c = '{';
                }
                if (']' == c) {
                    c = '[';
                }
                if (before == c) {
                    list.remove(size - 1);
                } else {
                    return false;
                }

            }

        }
        if (list.size() == 0) {
            return true;
        }
        return false;
    }


}
