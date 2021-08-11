package com.learn.leetcode.easy;

import java.util.Arrays;

/**
 * @author hzliuzhujie
 * @date 2021-05-29
 **/
public class MinPairSum {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int sum = 0;
        for (int i = 0; i < length / 2; i++) {
            int tmp = nums[i] + nums[length - 1 - i];
            if (tmp > sum) {
                sum = tmp;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // 7
        System.out.println(new MinPairSum().minPairSum(new int[]{3,5,2,3}));

        // 8
        System.out.println(new MinPairSum().minPairSum(new int[]{3,5,4,2,4,6}));

    }

}
