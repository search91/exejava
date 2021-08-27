package com.learn.leetcode.easy;

/**
 * 53. 最大子序和
 * 
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 不会做，不过像接雨水的题 42.
 *
 * @author hzliuzhujie
 * @date 2021-08-24
 **/
public class MaxSubArray {

    /**
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     *
     * 输出：6
     *
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 不会做 答案很巧妙
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            // 算出到x的最大的值
            pre = Math.max(pre + x, x);
            System.out.println(pre);
            // 总的最大值
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
