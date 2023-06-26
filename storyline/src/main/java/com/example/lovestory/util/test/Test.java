package com.example.lovestory.util.test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        /**
         * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
         *  要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
         */
        String s = "123456";
        //returnStringDigit(s);
        String s2 = Base64.getEncoder().encode("123456".getBytes(StandardCharsets.ISO_8859_1)).toString();
        System.out.println(s2);
        String s1 = Base64.getDecoder().decode(s2).toString();
        System.out.println(s1);
    }

    private static Integer returnStringDigit(String s) {


        String s1 = s;
        boolean a = s1.matches("A");


        if (s != null) {
            String[] split = s.split("");
            char[] chars = s.toCharArray();
            int result = 0;
            //相乘起始数
            int x = 1;
            for (int i = split.length; i > 0; i--) {
                if (i == split.length) {
                    if (split[i - 1].equals("0")) {
                        return 0;
                    }
                }
                if (!split[i - 1].matches("0-9")) {
                    return 0;
                }
                switch (split[i - 1]) {
                    case "1":
                        result += 1 * x;
                        break;
                    case "2":
                        result += 2 * x;
                        break;
                    case "3":
                        result += 3 * x;
                        break;
                    case "4":
                        result += 4 * x;
                        break;
                    case "5":
                        result += 5 * x;
                        break;
                    case "6":
                        result += 6 * x;
                        break;
                    case "7":
                        result += 7 * x;
                        break;
                    case "8":
                        result += 8 * x;
                        break;
                    case "9":
                        result += 9 * x;
                        break;
                }
            }
            return result;
        }
        return 1;
    }


}
