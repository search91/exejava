package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 字节面到
 *
 * @author hzliuzhujie
 * @date 2021-05-07
 **/
public class FullPermute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            res.add(tmp);
        }

        for (int i = 0; i < n - 1; i++) {
            res = dfs(res, n);
        }

        return convertIndex2Num(res, nums);
    }

    private List<List<Integer>> convertIndex2Num(List<List<Integer>> res, int[] nums) {
        for (List<Integer> single : res) {
            int num = single.size();
            for (int i = 0; i < num; i++) {
                single.set(i, nums[single.get(i)]);
            }
        }
        return res;
    }

    private List<List<Integer>> dfs(List<List<Integer>> origin, int n) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> single : origin) {
            for (int i = 0; i < n; i++) {
                if (!single.contains(i)) {
                    List<Integer> tmp = new ArrayList<>(single);
                    tmp.add(i);
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FullPermute().permute(new int[] {1}));
        System.out.println(new FullPermute().permute(new int[] {0, 1}));
        System.out.println(new FullPermute().permute(new int[] {1, 2, 3}));
    }

}
