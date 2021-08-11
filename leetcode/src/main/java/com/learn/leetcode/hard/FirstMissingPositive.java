package com.learn.leetcode.hard;

import java.util.Arrays;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n)并且只使用常数级别额外空间的解决方案。
 *
 * 不会做
 *
 * @author hzliuzhujie
 * @date 2021-08-03
 **/
public class FirstMissingPositive {
    // 很巧妙，利用数组下表作为索引，负数代表这个下表的元素存在，只要找到第一个不为负数的下标
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < len; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }

    // 置换法，将正确的值放在正确的下标，第一个值和下标不同的就是缺少的，这种好理解点
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // 注意是个while
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }


    public static void main(String[] args) {
        // 3
        System.out.println(new FirstMissingPositive().firstMissingPositive2(new int[]{1, 2, 0}));

        // 2
        System.out.println(new FirstMissingPositive().firstMissingPositive2(new int[]{3, 4, -1, 1}));

        // 2
        System.out.println(new FirstMissingPositive().firstMissingPositive2(new int[]{4, 3, -1, 1}));

        // 1
        System.out.println(new FirstMissingPositive().firstMissingPositive2(new int[]{7, 8, 9, 11, 12}));
    }
}
