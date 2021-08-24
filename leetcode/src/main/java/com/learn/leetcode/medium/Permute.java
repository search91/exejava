package com.learn.leetcode.medium;

import java.util.*;

/**
 * 46. 全排列 给定一个不含重复数字的数组nums，返回其所有可能的全排列。你可以按任意顺序返回答案。 47. 全排列2 给定一个可包含重复数字的序列nums，按任意顺序 返回所有不重复的全排列。
 * 
 * @author hzliuzhujie
 * @date 2021-08-24
 **/
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new LinkedList<>();
        for (int single : nums) {
            if (results.isEmpty()) {
                List<Integer> k = new ArrayList<>();
                k.add(single);
                results.add(k);
            } else {
                List<List<Integer>> newResult = new ArrayList<>();
                for (List<Integer> l : results) {
                    int size = l.size();
                    for (int i = 0; i <= size; i++) {
                        List<Integer> m = new ArrayList<>(l.subList(0, i));
                        m.add(single);
                        m.addAll(l.subList(i, size));
                        newResult.add(m);
                    }
                }
                results = newResult;
            }
        }
        return results;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new LinkedList<>();
        for (int single : nums) {
            if (results.isEmpty()) {
                List<Integer> k = new ArrayList<>();
                k.add(single);
                results.add(k);
            } else {
                List<List<Integer>> newResult = new ArrayList<>();
                for (List<Integer> l : results) {
                    int size = l.size();
                    for (int i = 0; i <= size; i++) {
                        List<Integer> m = new ArrayList<>(l.subList(0, i));
                        m.add(single);
                        m.addAll(l.subList(i, size));
                        if (!newResult.contains(m)) {
                            newResult.add(m);
                        }
                    }
                }
                results = newResult;
            }
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Permute().permute(new int[] {1}));
        System.out.println(new Permute().permute(new int[] {1, 2}));
        System.out.println(new Permute().permute(new int[] {1, 2, 3}));
        System.out.println(new Permute().permute(new int[] {1, 2, 3, 4}));
        System.out.println(new Permute().permuteUnique(new int[] {1, 1, 2}));
    }

}
