package com.example.lovestory.util.test.likemath;

public class MathAdd {

    public static void main(String[] args) {
        /*给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

        最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

        你可以假设除了整数 0 之外，这个整数不会以零开头

        输入 [1,2,3]        [1,2,4]
        **/
        MathAdd mathAdd = new MathAdd();
        int[] digits = new int[]{8, 8, 9, 9};
        //int[] ints = mathAdd.addOne(digits);
        int[] ints = mathAdd.plusOne(digits);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    /**
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     *
     * @param digits
     * @return
     */
    public int[] addOne(int[] digits) {
        //方式 1 创建新数组
        int length = digits.length;
        int end = digits[length - 1];
        if (end < 9 || (length == 1 && end < 9)) {
            digits[length - 1]++;
            return digits;
        } else {
            //中间数字是否都为9
            boolean isAllNine = true;
            int flag = 0;
            for (int i = length - 1; i >= 0; i--) {
                if (digits[i] != 9) {
                    isAllNine = false;
                    flag = i;
                    break;
                }
            }

            if (isAllNine) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                return result;
            } else {
                //倒着遍历
                digits[flag] = digits[flag] + 1;
                for (int i = flag + 1; i < length; i++) {
                    digits[i] = 0;
                }

            }
        }
        return digits;

    }

    public int[] plusOne(int[] digits) {
        //找9 就行
        int index = -1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
                index = i;
                continue;
            } else {
                break;
            }
        }

        if (index == -1) {
            digits[digits.length - 1]++;
        } else if (index > 0) {
            digits[index - 1]++;
        } else if (index == 0) {
            int[] r = new int[digits.length + 1];
            r[0] = 1;
            for (int j = 1; j < r.length; j++) {
                r[j] = 0;
            }
            return r;
        }

        return digits;


    }

}
