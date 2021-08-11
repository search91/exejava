package com.learn.leetcode.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. 两数之和
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            int tmp2 = target - tmp;
            if (map.get(tmp2) != null) {
                return new int[]{map.get(tmp2), i};
            }
            map.put(tmp, i);
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] tmp = twoSum.twoSum(new int[]{3, 3, 4}, 6);
        System.out.println(tmp[0]);
        System.out.println(tmp[1]);
    }
}
