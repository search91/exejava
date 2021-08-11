package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * @author hzliuzhujie
 * @date 2021-04-26
 **/
public class NextPermutation {
    // 从后往前，推到前比后小的那位，获取比他略大的那个数字，剩下的升序排序
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int size = nums.length;
        int i = size - 1;
        for (; i >= 0; i--) {
            if (i - 1 >= 0 && nums[i] > nums[i - 1]) {
                break;
            }
        }

        if (i - 1 >= 0) {
            for (int j = size - 1; j > i - 1; j--) {
                if (nums[j] > nums[i - 1]) {
                    int tmp = nums[i - 1];
                    nums[i - 1] = nums[j];
                    nums[j] = tmp;
                    break;
                }
            }
        }
        if (i < 0) {
            Arrays.sort(nums);
        } else {
            Arrays.sort(nums, i, size);
        }
    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     * 输入：nums = [1]
     * 输出：[1]
     * 输入：nums = [1,2,4,3]
     * 输出：[1,3,2,4]
     * 输入：nums = [1,2,4,3,1]
     * 输出：[1,3,1,2,4]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 5};
        new NextPermutation().nextPermutation(nums);
        // 151
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2, 3};
        new NextPermutation().nextPermutation(nums);
        // 132
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2, 3, 6, 4};
        new NextPermutation().nextPermutation(nums);
        // 12436
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2, 4, 3};
        new NextPermutation().nextPermutation(nums);
        // 1324
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 2, 1};
        new NextPermutation().nextPermutation(nums);
        // 123
        System.out.println(Arrays.toString(nums));
    }
}

