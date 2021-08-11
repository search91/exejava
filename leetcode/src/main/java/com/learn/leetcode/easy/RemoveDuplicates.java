package com.learn.leetcode.easy;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author hzliuzhujie
 * @date 2021-04-07
 **/
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int num = nums.length;
        if (num <= 1) {
            return num;
        }
        int i = 0;
        int j = i + 1;
        for (; j < num; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 1};
        System.out.println(removeDuplicates.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}