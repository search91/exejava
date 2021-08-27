package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class Combine {

    /**
     * 77. 组合 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     *
     * 感觉不难，但是不是最优解，回溯部分加强
     *
     * @author hzliuzhujie
     * @date 2021-08-26
     **/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        while (k > 0) {
            list = combine(n, k, list);
            k--;
        }
        return list;
    }

    private List<List<Integer>> combine(int n, int k, List<List<Integer>> list) {
        List<List<Integer>> newList = new ArrayList<>();
        if (list.isEmpty()) {
            for (int i = 1; i <= n; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                newList.add(tmp);
            }
        } else {
            for (List<Integer> single : list) {
                for (int i = single.get(single.size() - 1) + 1; i <= n - k + 1; i++) {
                    List<Integer> newSingle = new ArrayList<>(single);
                    newSingle.add(i);
                    newList.add(newSingle);
                }
            }
        }
        return newList;
    }

    /**
     * 78. 子集
     * 
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 77的升级版，k是1到n的情形
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int i = 1;
        List<List<Integer>> newList = new ArrayList<>();
        while (i <= size) {
            newList = dfs(nums, newList, i++);
            result.addAll(newList);
        }
        // 转换下下标
        for (List<Integer> list : result) {
            for (int j = 0; j < list.size(); j++) {
                list.set(j, nums[list.get(j)]);
            }
        }
        return result;
    }

    /**
     * 比较高级的写法，用二进制来标识是否存在
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    // 跟combine类似，先记录下标，再转换
    private List<List<Integer>> dfs(int[] nums, List<List<Integer>> list, int k) {
        int n = nums.length;
        List<List<Integer>> newList = new ArrayList<>();
        if (list.isEmpty()) {
            for (int i = 0; i < n; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                newList.add(tmp);
            }
        } else {
            for (List<Integer> single : list) {
                for (int i = single.get(single.size() - 1) + 1; i < n; i++) {
                    List<Integer> newSingle = new ArrayList<>(single);
                    newSingle.add(i);
                    newList.add(newSingle);
                }
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        /*    System.out.println(new Combine().combine(4, 2));
        System.out.println(new Combine().combine(4, 3));
        System.out.println(new Combine().combine(10, 1));*/

        // 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        System.out.println(new Combine().subsets(new int[] {1, 2, 4}));

        System.out.println(new Combine().subsets(new int[] {1, 2, 4, 5}));
    }
}
