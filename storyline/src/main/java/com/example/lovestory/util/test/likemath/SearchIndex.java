package com.example.lovestory.util.test.likemath;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class SearchIndex {
    public static void main(String[] args) {
//        int[] nums = {1,3,5,6};
//        int[] nums1 = {1,3,5,6};
//        int[] nums2 = {1,3,5,6};
//        int[] nums3 = {1,3};
//        int target = 12;
//        int result = searchInsert(nums, 5);
//
//        int result1 = searchInsert(nums1, 2);
//
//        int result2 = searchInsert(nums2, 7);
//        int result4 = searchInsert(nums2, 0);
//        int result5 = searchInsert(nums3, 1);
//        System.out.println("searchResult:" + result);
//        System.out.println("searchResult1:" + result1);
//        System.out.println("searchResult2:" + result2);
//        System.out.println("searchResult4:" + result4);
//        System.out.println("searchResult5:" + result5);
        SearchIndex searchIndex = new SearchIndex();
        int[] nums3 = {3, 4, 5, 1, 2};
        System.out.println(searchIndex.minArray2(nums3));
    }

    public static int searchInsert(int[] nums, int target) {
        //[1,2,3,4,5,6,7,8,9,10] target= 10/2 = 5; (5+10)/2 = 7
        //[1,2,3,4,5,6,7,8,9]  target=7  9/2 = 4;
        //二分搜索
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            if (target > nums[0]) {
                return 1;
            } else {
                return 0;
            }
        }

        int beginIndex = 0;
        int endIndex = nums.length - 1;
        //初始寻找位置
        int i = (beginIndex + endIndex) / 2;
        //两个下标挨着也没有值 进行退出
        while (beginIndex + 1 != endIndex) {
            if (nums[i] > target) {
                endIndex = i;
                i = (beginIndex + endIndex) / 2;
            } else if (nums[i] < target) {
                beginIndex = i;
                i = (beginIndex + endIndex) / 2;
            } else {
                return i;
            }
        }
        if (beginIndex + 1 == endIndex) {
            if (target <= nums[beginIndex]) {
                return beginIndex;
            } else if (target > nums[endIndex]) {
                return endIndex + 1;
            } else {
                return endIndex;
            }
        }

        return 0;
    }

    public int minArray(int[] numbers) {
        //二分查找
        if (numbers == null) {
            return 0;
        }
        int l = 0;
        int r = numbers.length - 1;
        int mid = (l + r + 1) / 2;

        while (l <= r) {
            if (numbers[mid] > numbers[l]) {
                l = mid;
                mid = (mid + r + 1) / 2;
            } else if (numbers[mid] < numbers[l]) {
                r = mid;
                mid = (mid + l) / 2;
            } else {//相等 todo
                l++;
                mid = (l + r) / 2;
            }
        }
        return numbers[mid];


        //线性查找
        // for(int i = 0; i < numbers.length; i++){
        //     if(numbers[i] < numbers[0]){
        //         numbers[0] = numbers[i];
        //     }
        // }
        // return numbers[0];
    }

    public int minArray2(int[] numbers) {
        //二分查找
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

}
