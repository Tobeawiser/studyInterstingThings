package com.example.lovestory.util.test.likemath;

public class RemoveDuplicates {
    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        removeDuplicates.removeDuplicatesDoublePointer(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        //int i = removeDuplicates.removeElementAgain(new int[]{2,3,3}, 3);
        //int s = removeDuplicates.removeElement(new int[]{3,3}, 5);
        //System.out.println(s);
    }

    public int removeDuplicates(int[] nums) {
        //原地删除，不开辟新数组空间
        int length = nums.length;
        if (length < 0 || length > 30000) {
            return 0;
        }
        //升序排列
        int realLength = length;
        int end = 10001;
        for (int i = 0; i < length; i++) {
            if (i != 0 && nums[i] >= (-10000) && nums[i] <= 10000) {
                //如果和前面值相同，后面全部往前挪一位；覆盖掉
                if (nums[i] == nums[i - 1]) {
                    //所有往前移动
                    for (int j = i; j < realLength - 1; j++) {
                        nums[j] = nums[j + 1];
                    }
                    //挪完后 length - 1
                    nums[realLength - 1] = end;
                    length = length - 1;
                    //比较一次后，因为挪动，再进行比较
                    i--;
                }

            }
        }
        return length;
    }

    //双指针
    public int removeDuplicatesDoublePointer(int[] nums) {
        //{0,0,1,1,1,2,2,3,3,4}
        //快的先走，发现不一样及更新
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            //和前一个一样不用更新 继续走
            if (nums[fast] == nums[fast - 1]) {
                //相等继续走
            } else {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    //这个算法不行
    public int removeElement(int[] nums, int val) {
        //数组有索引 和 排序（有时）
        //移除的放到后面去

        int last = nums.length;
        if (last == 0) {
            return 0;
        }
        if (last == 1) {
            if (nums[0] == val) {
                return 0;
            }
            return 1;
        }
        //慢指针做循环，快指针做更新
        for (int i = 0; i < last - 1; i++) {
            if (nums[i] == val) {
                //替换到相对最后面  如果最后面一个值也与此相等？？
                while (nums[last] == val && last > i + 1) {
                    last--;
                }
                if (i + 1 == last - 1) {
                    last--;
                    return last;
                }
                //替换
                int value = nums[last];
                nums[last] = nums[i];
                nums[i] = value;
            }
        }
        return last;
    }

    //第二次重写 {3, 2, 2, 3} 3; {3,3} 3
    public int removeElementAgain(int[] nums, int val) {
        //原地线性处理，前后指针，前往前移动，后与其交换
        //移除的放到后面去
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            if (nums[0] == val) {
                return 0;
            }
            return 1;
        }
        //慢指针做循环，快指针做更新
        for (int i = 0; i < length; i++) {
            if (nums[i] == val) {
                //第一位，或，最后一位
                if (length == 1 | i + 1 == length) {
                    length--;
                    continue;
                }
                //替换到相对最后面 寻找最后面不为val 的值
                while (nums[length - 1] == val && length > 1) {
                    length--;
                }

                //替换
                int value = nums[length - 1];
                nums[length - 1] = nums[i];
                nums[i] = value;
                //替换完后进行往后移一位
                length--;
            }
        }
        return length;
    }
}
