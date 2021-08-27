package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 75. 颜色分类 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0] 输出：[0,0,1,1,2,2] 示例 2：
 *
 * 输入：nums = [2,0,1] 输出：[0,1,2] 示例 3：
 *
 * 输入：nums = [0] 输出：[0] 示例 4：
 *
 * 输入：nums = [1] 输出：[1]
 *
 * 简单。这道题很诡异，计数下分别几个，重新new输出下就行; 排序算法用下。
 *
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class SortColors {

    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        int[] origin = new int[] {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(origin);
        System.out.println(Arrays.toString(origin));
    }
}
