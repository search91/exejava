package com.learn.leetcode.hard;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author hzliuzhujie
 * @date 2021-07-01
 **/
public class Trap {
    // 看了答案来解答，答案比较厉害
    public int trap(int[] height) {
        int sum = 0;
        int size = height.length;
        if (size <= 2) {
            return sum;
        }
        int[] leftMax = new int[size];
        leftMax[0] = height[0];
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        int[] rightMax = new int[size];
        rightMax[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 0; i < size; i++) {
            sum += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        /**
         * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
         * 输出：6
         * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
         * 示例 2：
         *
         * 输入：height = [4,2,0,3,2,5]
         * 输出：9
         *
         */
        System.out.println(new Trap().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

        System.out.println(new Trap().trap(new int[]{4, 2, 0, 3, 2, 5}));

        // 1
        System.out.println(new Trap().trap(new int[]{4, 2, 3}));

        // 1
        System.out.println(new Trap().trap(new int[]{0, 4, 2, 3}));

    }
}

