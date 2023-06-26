package com.example.lovestory.util.test.likemath;

import java.util.Arrays;

public class RemoveElem {
    public static void main(String[] args) {
        RemoveElem removeElem = new RemoveElem();
        //removeElem.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}, 1);
        int[] ints = {4, 5};
        int i = removeElem.removeElement(ints, 4);
        System.out.println(i);
        Arrays.stream(ints).forEach(System.out::print);
    }

    public int removeDuplicates(int[] nums, int val) {
        //也就是不断把与val相等的值往后移
        int length = nums.length;
        //一个移动指针，一个交换指针,相遇时候退出
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        int moveP = 0;
        int changeP = length - 1;
        while (moveP != changeP) {
            if (nums[moveP] == val) {
                //判断末尾值是否与val相等
                while (changeP > 0 && nums[changeP] == val) {
                    changeP--;
                }
                nums[moveP] = nums[changeP];
                nums[changeP] = val;
                changeP--;
                if (changeP == -1) {
                    return 0;
                }
            }
            moveP++;
        }
        moveP++;
        return moveP;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
