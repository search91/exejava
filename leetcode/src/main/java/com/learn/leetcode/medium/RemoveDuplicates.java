package com.learn.leetcode.medium;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 80. 删除有序数组中的重复项 II
 * 
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 中等偏简单，但是我解题思路可能不太好
 *
 * @author hzliuzhujie
 * @date 2021-08-26
 **/
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int size = nums.length;
        if (size <= 2) {
            return size;
        }
        int count = size;
        for (int i = 0; i < size; i++) {
            if (i >= count) {
                break;
            }
            int dig = nums[i];
            if (i + 2 < count) {
                while (nums[i + 1] == nums[i + 2] && nums[i + 2] == dig && i + 2 < count) {
                    // 移动
                    for (int j = i + 3; j < size && j < count; j++) {
                        nums[j - 1] = nums[j];
                    }
                    count--;
                }
            }
        }
        return count;
    }

    /**
     * 优解
     * 
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] num = new int[] {1, 1, 1, 2, 2, 3};
        // 1,1,2,2,3
        Assert.assertEquals(5, new RemoveDuplicates().removeDuplicates(num));
        System.out.println(Arrays.toString(num));

        num = new int[] {1, 1, 1, 1, 2, 2, 3, 3};
        // 1,1,2,2,3,3
        Assert.assertEquals(6, new RemoveDuplicates().removeDuplicates(num));
        System.out.println(Arrays.toString(num));

        num = new int[] {0, 0, 1, 1, 1, 1, 2, 3, 3};
        // 0,0,1,1,2,3,3
        Assert.assertEquals(7, new RemoveDuplicates().removeDuplicates(num));
        System.out.println(Arrays.toString(num));

        num = new int[] {1, 1, 1};
        // 1,1
        Assert.assertEquals(2, new RemoveDuplicates().removeDuplicates(num));
        System.out.println(Arrays.toString(num));

    }
}
