package com.example.lovestory.util.test.likemath;

import java.util.ArrayList;
import java.util.List;

public class StrStr {
    private static int sum = 0;

    public static void main(String[] args) {
//        StrStr strStr = new StrStr();
//        //removeElem.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}, 1);
//        int i = strStr.indexOf("", "");
//        System.out.println(i);
//        String s = "";
//        System.out.println(s == "");
        int i = climbStairs(45);
        System.out.println(i);
    }

    public static int climbStairs(int n) {
        // 1 1
        // 2 1 1;2
        // 3 1 2 ;2 1

        // 4 2 2;1 3;1 1 1 1;

        //f(n) = f(n - 1) + f(n - 2) 就这两种爬法
        //1836311903
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            sum = climbStairs(n - 1) + climbStairs(n - 2);
        }
        return sum;


    }

    public int indexOf(String str, String need) {
        if (str == "" || need == "") {
            return 0;
        }
        char[] strChars = str.toCharArray();
        char[] needChars = need.toCharArray();

        char needChar1 = needChars[0];
        List<Integer> list = new ArrayList();
        for (int i = 0; i < strChars.length; i++) {
            if (strChars[i] == needChar1) {
                list.add(i);
            }
        }
        for (Integer integer : list) {
            for (int j = 0; j < needChars.length; j++) {
                if (needChars[j] == strChars[integer]) {
                    integer++;
                } else {
                    break;
                }
                return integer - 1;
            }
        }
        return -1;
    }

}
