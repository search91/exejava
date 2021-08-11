package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * @author hzliuzhujie
 * @date 2021-05-13
 **/
public class SearchRange {
    /*
    虽然性能还行，结题太不优雅了 呜呜呜
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums[0] == nums[nums.length - 1]) {
            if (nums[0] == target) {
                return new int[]{0, nums.length - 1};
            } else {
                return new int[]{-1, -1};
            }
        }
        int left = 0, right = nums.length - 1;

        while (left < right) {
            if (nums[left] == target) {
                right = left;
                break;
            }
            if (nums[right] == target) {
                left = right;
                break;
            }
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
                left++;
            } else if (nums[middle] < target) {
                left = middle + 1;
                right--;
            } else {
                left = middle;
                right = middle;
                break;
            }
        }

        if (left > right) {
            return new int[]{-1, -1};
        } else if (left == right) {
            if (nums[left] != target) {
                return new int[]{-1, -1};
            }
        }
        for (int i = left; i > 0; ) {
            if (nums[i - 1] == target) {
                i--;
            } else {
                left = i;
                break;
            }
        }
        for (int i = right; i < nums.length - 1; ) {
            if (nums[i + 1] == target) {
                i++;
            } else {
                right = i;
                break;
            }
        }
        return new int[]{left, right};
    }

    /**
     * 学习优秀解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        // -1 -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));

        // -1, -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{1, 5}, 4)));

        // 3, 4
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));

        // -1, -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{1}, 0)));

        // -1, -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{2, 2}, 3)));

        // -1, -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{1, 2}, 0)));

        // 0 20
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10}, 8)));

        // 1 20
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10}, 8)));

        // -1 -1
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{}, 0)));
    }

}
