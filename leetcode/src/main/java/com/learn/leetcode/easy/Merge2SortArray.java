package com.learn.leetcode.easy;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * @author hzliuzhujie
 * @date 2021-06-28
 **/
public class Merge2SortArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int j = n - 1;
        int i = m - 1;
        while (i >= 0 && j >= 0) {
            int left = nums1[i];
            int right = nums2[j];
            if (right < left) {
                nums1[j + i + 1] = left;
                i--;
            } else {
                nums1[j + i + 1] = right;
                j--;
            }
        }
        while (j >= 0) {
            nums1[j + i + 1] = nums2[j];
            j--;
        }
    }

    public static void main(String[] args) {
        // [1,2,2,3,5,6]
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        new Merge2SortArray().merge(num1, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(num1));

        // [1]
        num1 = new int[]{1};
        new Merge2SortArray().merge(num1, 1, new int[]{}, 0);
        System.out.println(Arrays.toString(num1));

        // [1,4,5,5,6,6,8]
        num1 = new int[]{4, 5, 6, 0, 0, 0, 0};
        new Merge2SortArray().merge(num1, 3, new int[]{1, 5, 6, 8}, 4);
        System.out.println(Arrays.toString(num1));
    }
}
