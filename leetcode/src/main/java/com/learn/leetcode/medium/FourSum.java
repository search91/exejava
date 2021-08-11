package com.learn.leetcode.medium;

import javax.management.relation.InvalidRelationTypeException;
import java.util.*;

/**
 * 18. 四数之和
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 *
 * @author hzliuzhujie
 * @date 2021-04-02
 **/
public class FourSum {

    /**
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 3) {
            return result;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<List<Integer>>> sumMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                sumMap.putIfAbsent(sum, new LinkedHashSet<>());
                List<Integer> newArrayList = new ArrayList<>();
                newArrayList.add(nums[i]);
                newArrayList.add(nums[j]);
                sumMap.get(sum).add(newArrayList);
            }
        }

        int min = Integer.MAX_VALUE;
        for (Integer key : sumMap.keySet()) {
            if (key < min) {
                min = key;
            }

            int other = target - key;
            if (other < min) {
                break;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

}
