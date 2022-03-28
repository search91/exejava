package com.learn.leetcode.medium;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author hzliuzhujie
 * @date 2022-01-20
 **/
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int num : nums) {
            if (priorityQueue.size() >= k) {
                if (priorityQueue.peek() < num) {
                    priorityQueue.poll();
                } else {
                    continue;
                }
            }
            priorityQueue.add(num);
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        // 5
        System.out.println(new FindKthLargest().findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
    }
}
