package com.learn.leetcode.medium;

/**
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author hzliuzhujie
 * @date 2021-02-26
 **/
public class MaxArea {

    public int maxArea(int[] height) {
        int length = height.length;
        int left = height[0];
        int max = Math.min(left, height[length - 1]) * (length - 1);
        int maxi = 0;
        for (int i = 0; i < length; i++) {
            if (height[i] > maxi) {
                maxi = height[i];
            } else {
                continue;
            }
            int maxj = 0;
            for (int j = length - 1; j > i; j--) {
                if (height[j] > maxj) {
                    maxj = height[j];
                    max = Math.max(Math.min(height[i], maxj) * (j - i), max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        // 49
        System.out.println(maxArea.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea.maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea.maxArea(new int[]{1, 2, 1}));
        System.out.println(maxArea.maxArea(new int[]{1, 1}));
        //25
        System.out.println(maxArea.maxArea(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));

    }


}
