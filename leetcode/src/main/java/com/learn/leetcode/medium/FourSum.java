package com.learn.leetcode.medium;

import java.util.*;

/**
 * 18. 四数之和 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 *
 * 不会做，看答案，其实o(n3) 直接暴力
 *
 * @author hzliuzhujie
 * @date 2021-04-02
 **/
public class FourSum {

    /**
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public static void main(String[] args) {

        // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(new FourSum().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));

        // [[2,2,2,2]]
        System.out.println(new FourSum().fourSum(new int[] {2, 2, 2, 2}, 8));
    }

}
