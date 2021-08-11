package com.learn.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 *
 * @author hzliuzhujie
 * @date 2021-03-03
 **/
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1 = nums1.length;
        int num2 = nums2.length;
        if (num1 == 0) {
            return (nums2[num2 / 2] + nums2[(num2 - 1) / 2]) / 2.0;
        } else if (num2 == 0) {
            return (nums1[num1 / 2] + nums1[(num1 - 1) / 2]) / 2.0;
        }

        List<Integer> sortList = new ArrayList<>();

        int middleNum = (num1 + num2) / 2;
        boolean isMatch = false;
        int i = 0;
        int j = 0;
        while (i < num1 && j < num2 && !isMatch) {
            if (nums1[i] < nums2[j]) {
                sortList.add(nums1[i]);
                i++;
            } else {
                sortList.add(nums2[j]);
                j++;
            }
            if (sortList.size() > middleNum) {
                isMatch = true;
            }
        }

        while (!isMatch) {
            if (i == num1) {
                sortList.add(nums2[j]);
                j++;
            } else {
                sortList.add(nums1[i]);
                i++;
            }
            if (sortList.size() > middleNum) {
                isMatch = true;
            }
        }
        return (sortList.get((num1 + num2) / 2) + sortList.get((num1 + num2 - 1) / 2)) / 2.0;
    }


    // 以前做的，好像不是原创
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        double median = 0.0;
        if ((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) {
            return median;
        } else if (nums1 == null || nums1.length == 0) {
            int m = nums2.length / 2;
            if (nums2.length % 2 == 0) {
                median = (nums2[m - 1] + nums2[m]) / 2;
            } else {
                median = nums2[m];
            }
        }
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 > len2)   //保证数组1一定最短
            return findMedianSortedArrays2(nums2, nums1);
        int L1 = 0, L2 = 0, R1 = 0, R2 = 0, c1, c2, lo = 0, hi = 2 * len1;  //我们目前是虚拟加了'#'所以数组1是2*n长度
        while (lo <= hi) { //二分
            c1 = (lo + hi) / 2;  //c1是二分的结果
            c2 = len1 + len2 - c1;
            L1 = (c1 == 0) ? (int) Integer.MIN_VALUE : nums1[(c1 - 1) / 2];   //map to original element
            R1 = (c1 == 2 * len1) ? (int) Integer.MAX_VALUE : nums1[c1 / 2];
            L2 = (c2 == 0) ? (int) Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
            R2 = (c2 == 2 * len2) ? (int) Integer.MAX_VALUE : nums2[c2 / 2];

            if (L1 > R2) {
                hi = c1 - 1;
            } else if (L2 > R1) {
                lo = c1 + 1;
            } else {
                break;
            }
        }
        return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        System.out.println("2.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println("2.5=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println("3.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{3, 4}));
        System.out.println("3.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{3, 4}));
        System.out.println("0.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}));
        System.out.println("1.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println("2.0=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{2}, new int[]{}));
        System.out.println("1.5=" + findMedianSortedArrays.findMedianSortedArrays(new int[]{2}, new int[]{1}));
    }
}
