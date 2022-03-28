package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括n 个整数的数组nums和 一个目标值target。
 * 找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 不会做， 应该跟3数之和相同的方式去做，先确定一个数，再双指针
 *
 * @author hzliuzhujie
 * @date 2021-03-29
 **/
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
/*        输入：nums = [-1,2,1,-4], target = 1
        输出：2
        解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。*/
    }

}
