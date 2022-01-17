package com.learn.leetcode.medium;

/**
 * 179 largestNumber 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author hzliuzhujie
 * @date 2022-01-08
 **/
public class LargestNumber {
    public String largestNumber(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return "";
        }
        if (size == 1) {
            return nums[0] + "";
        }
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int m = nums[i];
                int n = nums[j];
                int mm = (m + "").length();
                int nn = (n + "").length();
                if (m * Math.pow(10, nn) + n < n * Math.pow(10, mm) + m) {
                    swap(i, j, nums);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (nums[0] == 0) {
            return "0";
        }
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        // 210
        System.out.println(largestNumber.largestNumber(new int[] {10, 2}));
        // 9534330
        System.out.println(largestNumber.largestNumber(new int[] {3, 30, 34, 5, 9}));
        // "343234323"
        System.out.println(largestNumber.largestNumber(new int[] {34323, 3432}));
        // "0"
        System.out.println(largestNumber.largestNumber(new int[] {0, 0}));
    }
}
