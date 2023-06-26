package com.example.lovestory.util.test.sort;

public class BubbleSort {

    public static void main(String[] args) {
        //addBinary("1111","1111");
        System.out.println(mySqrt(2147395599));
//        char a = '1' - '0';
//        char b = '1' - '0';
//        StringBuilder stringBuilder = new StringBuilder();
//        int max = Math.max(3, 5);
//        int i1 = (a + b);
//        String s = "abcde";
//        int i2 = s.charAt(1) - '0';
//        System.out.println(s.charAt(1));
//        System.out.println(s.charAt(-1));
//        System.out.println(i1);
//        int[] waitSort = new int[]{2,4,6,5,3,0,9,7,8,1};
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        int i = maxSubArray(nums);
//        System.out.println("最大子列和为："+ i);
//
//        //int[] sort = sort(waitSort);
//        //比较Assic码
//        int r = 'a' - '1';
//        char rq = 'a' - '1';
//        System.out.println("r:"+r);
//        System.out.println("rq:"+rq);


        //Arrays.stream(sort).forEach(System.out::println);
    }

    public static int[] sort(int[] waitSort) {
        //冒泡排序
        if (waitSort != null && waitSort.length > 0) {
            int length = waitSort.length;
            for (int i = 0; i < length; i++) {
                //倒着遍历到i的元素，把最小给放置到i处
                //第i个位置需要比较 j-1
                for (int j = length - 1; j > i; j--) {
                    //如果小就交换位置
                    if (waitSort[j] > waitSort[j - 1]) {
                        int jValue = waitSort[j];
                        waitSort[j] = waitSort[j - 1];
                        waitSort[j - 1] = jValue;
                    }
                }
            }
            return waitSort;
        }
        return null;
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int beforeSum = nums[0];
        int curSum;

        for (int i = 1; i < nums.length; i++) {
            //前面和小于0 更新前面和为当前值
            if (beforeSum < 0) {
                //前面和
                beforeSum = nums[i];
                curSum = nums[i];
            } else {
                curSum = beforeSum + nums[i];
                beforeSum = beforeSum + nums[i];
            }
            String s = "abc ";
            char[] chars = s.toCharArray();
            s.length();
            char c = s.charAt(1);
            for (int j = 0; j < chars.length; j++) {
                System.out.println(j + "----------");
                System.out.println(' ' == chars[j]);

            }

            //和最大值比较并更新
            if (curSum > max) {
                max = curSum;
            }


        }
        return max;
    }

    public static String addBinary(String a, String b) {

        int length = Math.max(a.length(), b.length());
        int add = 0;
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            int al = a.length() - i - 1;
            int bl = b.length() - i - 1;
            int aVal = 0;
            if (al >= 0) {
                aVal = a.charAt(al) - '0';
            }
            int bVal = 0;
            if (bl >= 0) {
                bVal = b.charAt(bl) - '0';
            }

            int r = aVal + bVal + add;

            if (r == 0) {
                stringBuilder.append("0");
            } else if (r == 1) {
                stringBuilder.append("1");
                add = 0;
            } else if (r == 2) {
                stringBuilder.append("0");
                add = 1;
            } else if (r == 3) {
                stringBuilder.append("1");
                add = 1;
            }


        }
        if (add == 1) {
            stringBuilder.append("1");
        }

        return stringBuilder.reverse().toString();

    }

    public static long mySqrt(int x) {
        long begin = 0;
        long end = x;
        long middle = 0;
        if (x > 20000) {
            middle = x / 100;
        } else {
            middle = (0 + x) / 2;
        }

        long result = 0;
        long resultAdd = 0;
        while (true) {
            result = middle * middle;
            resultAdd = (middle + 1) * (middle + 1);

            if (result == x || (x > result && x < resultAdd)) {
                return middle;
            }
            if (resultAdd == x) {
                return middle + 1;
            }

            if (x < result) {
                end = middle;
            } else if (x > resultAdd) {
                begin = middle;
            }
            middle = (begin + end) / 2;
            System.out.println(middle);


        }

    }

    public int climbStairs(int n) {
        //f(n) = f(n - 1) + f(n -2) 只能从

        return 1;
    }
}
