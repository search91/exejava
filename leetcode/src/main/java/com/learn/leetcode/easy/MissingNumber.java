package com.learn.leetcode.easy;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 提示：
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 *
 * @author hzliuzhujie
 * @date 2021-08-04
 **/
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int len = nums.length;
        int diff = 0;
        for (int i = 0; i < len; i++) {
            diff += i + 1 - nums[i];
        }
        return diff;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(new MissingNumber().missingNumber(new int[]{3, 0, 1}));
        // 2
        System.out.println(new MissingNumber().missingNumber(new int[]{0, 1}));
        // 8
        System.out.println(new MissingNumber().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        // 1
        System.out.println(new MissingNumber().missingNumber(new int[]{0}));
    }
}
