package com.learn.leetcode.easy;

import java.util.Arrays;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author hzliuzhujie
 * @date 2021-04-09
 **/
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int i = nums.length;;
        int j = 0;
        boolean isStart = false;
        for (; j < nums.length; j++) {
            if (nums[j] == val) {
                if (!isStart) {
                    isStart = true;
                    i = j;
                }
            } else {
                if (isStart) {
                    nums[i] = nums[j];
                    i++;
                }
            }
        }
        return i;
    }

    public static void main(String[] args) {
        /**
         * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
         * 输出：5, nums = [0,1,4,0,3]  [0,1,3,0,4]  都行
         */

        RemoveElement removeElement = new RemoveElement();
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement.removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));


        nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement.removeElement(nums, 0));
        System.out.println(Arrays.toString(nums));


        nums = new int[]{2};
        System.out.println(removeElement.removeElement(nums, 0));
        System.out.println(Arrays.toString(nums));
    }

}
