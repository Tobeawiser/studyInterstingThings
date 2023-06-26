package com.example.lovestory.util.test.likemath;

public class DigitReverse {
    public static void main(String[] args) throws Exception {
        int s = reverse(1534236469);
        System.out.println(s);
    }

    public static int reverse(int x) {
        //判断正数或者负数
        boolean flag = true;
        int reverseNum = 0;
        if (x < 0) {
            x = x * (-1);
            flag = false;
        }
        //判断个位数 第一位不为零
        while (x > 0) {
            int end = x % 10;
            //正数
            //reverseNum*10 + end < 2 147 483 647
            if (flag) {
                if (reverseNum > (2147483647 / 10)) {
                    return 0;

                } else if (reverseNum == (2147483647 / 10)) {
                    if (end >= 8) {
                        return 0;
                    }
                }
                //负数
                //(reverseNum*10 + end  ) * -1 > - 2 147 483 648
                //reverseNum*10 + end  < 2 147 483 648
            } else {
                if (reverseNum > (2147483647 / 10)) {
                    return 0;

                } else if (reverseNum == (2147483647 / 10)) {
                    if (end >= 9) {
                        return 0;
                    }
                }
            }
            reverseNum = reverseNum * 10 + end;
            x = x / 10;
        }
        if (!flag) {
            return reverseNum * (-1);
        }
        return reverseNum;
    }
}
