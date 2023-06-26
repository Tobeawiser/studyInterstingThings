package com.example.lovestory.util.test.likemath;

public class IsPalindrome {
    public static void main(String[] args) {
        int result = romanToInt("IVXL");
    }

    public static int romanToInt(String s) {
        //从第一个寻找，如果后面比前面大，后面-前面，如果后面小，则相加
        //倒着来
        int length = s.length();
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            int changeResult = romanChange(s.charAt(i));
            if (i == length - 1) {
                result = changeResult;
            } else {
                int changeResultAfter = romanChange(s.charAt(i + 1));
                if (changeResult >= changeResultAfter) {
                    result = result + changeResult;
                } else {
                    result = result - changeResult;
                }
            }
        }
        return result;

    }

    private static int romanChange(char roman) {
        switch (roman) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


}
