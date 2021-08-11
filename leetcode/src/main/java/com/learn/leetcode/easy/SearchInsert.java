package com.learn.leetcode.easy;

import org.junit.Assert;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * @author hzliuzhujie
 * @date 2021-06-09
 **/
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 5));
        Assert.assertEquals(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 5), 2);

        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 2));
        Assert.assertEquals(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 2), 1);

        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 7));
        Assert.assertEquals(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 7), 4);

        System.out.println(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 0));
        Assert.assertEquals(new SearchInsert().searchInsert(new int[]{1, 3, 5, 6}, 0), 0);
    }
}
