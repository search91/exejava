package com.learn.leetcode.medium;

import org.junit.Assert;

/**
 * 33. 搜索旋转排序数组 整数数组 nums 按升序排列，数组中的值互不相同 。 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3
 * 处经旋转后可能变为[4,5,6,7,0,1,2] 。 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。 可以设计一个时间复杂度为 O(log
 * n) 的解决方案吗？
 *
 * @author hzliuzhujie
 * @date 2021-05-07
 **/
public class SearchSortArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] == target) {
                return l;
            }
            if (nums[r] == target) {
                return r;
            }
            // 在0~断点
            if (nums[0] < nums[mid]) {
                if (target < nums[mid] && target > nums[0]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 在断点到末尾
            else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 81. 搜索旋转排序数组 II
     * 
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0],
     * nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * 
     * 跟33的区别是非降.这两题感觉一样的，变形二分查找，需要理解
     * 
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int min = nums[left];
        int max = nums[right];
        if (target == min || target == max) {
            return true;
        }
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                if (target > min) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else if (nums[middle] < target) {
                if (target > max) {
                    left = middle - 1;
                } else {
                    right = middle + 1;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * 示例 1： 输入：nums = [4,5,6,7,0,1,2], target = 0 输出：4
         * 
         * 示例2： 输入：nums = [4,5,6,7,0,1,2], target = 3 输出：-1
         * 
         * 示例 3： 输入：nums = [1], target = 0 输出：-1
         **/
        Assert.assertEquals(4, new SearchSortArray().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        Assert.assertEquals(-1, new SearchSortArray().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
        Assert.assertEquals(-1, new SearchSortArray().search(new int[] {1}, 0));
        Assert.assertEquals(1, new SearchSortArray().search(new int[] {1, 3}, 3));
        Assert.assertEquals(1, new SearchSortArray().search(new int[] {3, 1}, 1));

        Assert.assertTrue(new SearchSortArray().search2(new int[] {2, 5, 6, 0, 0, 1, 2}, 0));
        Assert.assertFalse(new SearchSortArray().search2(new int[] {2, 5, 6, 0, 0, 1, 2}, 3));
    }
}
