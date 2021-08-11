package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 40. 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 *
 * 不会做
 *
 * @author hzliuzhujie
 * @date 2021-05-14
 **/
public class CombinationSum {
    /**
     * 官方解法，回溯法
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            //System.out.println("=========");
            return;
        }
        // 等于0才是正好可被加和，能返回结果
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳到最后一个最大数
        dfs(candidates, target, ans, combine, idx + 1);
        // 从大到小选择当前数
        if (target - candidates[idx] >= 0) {
            // 当前数加入候选
            combine.add(candidates[idx]);
            //System.out.println("start  idx" + idx + " " + combine);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            //System.out.println("end  idx" + idx + " " + combine);
            // 到这里是不能return为0，要把加进去的删掉
            combine.remove(combine.size() - 1);
        }
    }

    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }


    public static void main(String[] args) {
        /**
         * [
         *   [7],
         *   [2, 2, 3]
         * ]
         */
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));


        /**
         * [
         *   [2, 2, 2, 2],
         *   [2, 3, 3],
         *   [3, 5]
         * ]*/
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 5}, 8));

        /**
         [
         * [1,1,6],
         * [1,2,5],
         * [1,7],
         * [2,6]
         * ]
         */
        System.out.println(new CombinationSum().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));


        /**
         * [
         * [1,2,2],
         * [5]
         * ]*/
        System.out.println(new CombinationSum().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));


    }
}
