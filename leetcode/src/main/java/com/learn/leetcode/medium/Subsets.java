package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hzliuzhujie
 * @date 2021-08-30
 **/
public class Subsets {

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

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 90. 子集 II
     *
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     * 输入：nums = [1,2,2] 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]] 示例 2：
     *
     * 输入：nums = [0] 输出：[[],[0]]
     *
     *
     * 77. 78. 90. 一起看，回溯法为主，其他会有些高级的写法
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums) {
        System.out.println("ans:" + ans);
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }

        dfs(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }

    public static void main(String[] args) {
        // 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     /*   System.out.println(new Subsets().subsets(new int[] {1, 2, 4}));

        System.out.println(new Subsets().subsets(new int[] {1, 2, 4, 5}));*/

        // [[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println(new Subsets().subsetsWithDup(new int[] {1, 2, 2}));

    }

}
