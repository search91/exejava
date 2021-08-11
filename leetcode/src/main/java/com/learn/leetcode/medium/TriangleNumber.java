package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 *
 * @author hzliuzhujie
 * @date 2021-08-04
 **/
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                int left = j + 1;
                int right = len - 1;
                int y = j;
                while (left <= right) {
                    int middle = (left + right) / 2;
                    int maxK = nums[i] + nums[j];
                    if (nums[middle] >= maxK) {
                        right = middle - 1;
                    } else {
                        y = middle;
                        left = middle + 1;
                    }
                }
                System.out.println(nums[i] + " " + nums[j] + " " + (y - j));
                count += y - j;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 3
        // 2-2-3 2-3-4 2-3-4
        System.out.println(new TriangleNumber().triangleNumber(new int[]{2, 2, 3, 4}));

        // 18
        // 2-4
        System.out.println(new TriangleNumber().triangleNumber(new int[]{2, 2, 3, 3, 4, 4}));
    }
}
