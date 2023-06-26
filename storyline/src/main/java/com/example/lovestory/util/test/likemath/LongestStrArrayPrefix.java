package com.example.lovestory.util.test.likemath;

import java.util.Stack;

public class LongestStrArrayPrefix {
    public static void main(String[] args) {
        String s = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        System.out.println(s);
    }

    public static String longestCommonPrefix(String[] strs) {
        //条件 strs.length [0,200]
        int length = strs.length;
        if (length > 200 || length <= 0) {
            return "";
        }
        //寻找最长公共前缀 仅由小写英文字母
        //先找最短的
        int min = 0;
        String ele = "";
        for (int i = 0; i < length; i++) {
            int eleLength = strs[i].length();
            if (i == 0) {
                ele = strs[i];
                min = eleLength;
            }

            if (eleLength < min) {
                min = eleLength;
                ele = strs[i];
            }
        }
        Stack stack = new Stack();

        //用最短的 直接比较
        for (int i = 0; i < min; i++) {
            char a = ele.charAt(i);
            for (int j = 0; j < length; j++) {
                //一个个字符比较 出现不相等就直接返回
                if (strs[j].charAt(i) != a) {
                    return ele.substring(0, i);
                }
            }
        }
        return ele;
    }

    //第二种方法  竖着切找
}
