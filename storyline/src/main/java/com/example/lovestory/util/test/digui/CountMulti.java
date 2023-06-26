package com.example.lovestory.util.test.digui;

public class CountMulti {

    public static void main(String[] args) {
        int number = 13;
        int result = countMultiMath(number);
        System.out.println(number + "的阶乘为：" + result);
    }

    private static int countMultiMath(int number) {
        // 5 阶乘 为 5 * 4 * 3 * 2 * 1
        //递归公式 a * a - 1
        //返回条件 a = 1
        if (number == 1) {
            return 1;
        }
        return number * countMultiMath(number - 1);

    }
}
