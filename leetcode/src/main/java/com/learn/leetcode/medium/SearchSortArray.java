package com.learn.leetcode.medium;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 * 可以设计一个时间复杂度为 O(log n) 的解决方案吗？
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

    public static void main(String[] args) {
        /**
         示例 1：
         输入：nums = [4,5,6,7,0,1,2], target = 0
         输出：4

         示例2：
         输入：nums = [4,5,6,7,0,1,2], target = 3
         输出：-1

         示例 3：
         输入：nums = [1], target = 0
         输出：-1
         **/
        System.out.println(new SearchSortArray().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new SearchSortArray().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(new SearchSortArray().search(new int[]{1}, 0));
        System.out.println(new SearchSortArray().search(new int[]{1, 3}, 3));
        System.out.println(new SearchSortArray().search(new int[]{3, 1}, 1));
    }
}
