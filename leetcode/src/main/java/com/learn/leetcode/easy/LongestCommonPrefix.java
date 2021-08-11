package com.learn.leetcode.easy;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author hzliuzhujie
 * @date 2021-03-21
 **/
public class LongestCommonPrefix {
    public String run(String[] strs) {
        int i = 0;
        int size = strs.length;
        if (size == 0) {
            return "";
        }
        boolean isBreak = false;
        while (!isBreak) {
            for (int j = 0; j < size; j++) {
                if (strs[j].length() == i) {
                    isBreak = true;
                    break;
                }
                if (j != 0) {
                    if (strs[j].charAt(i) != strs[0].charAt(i)) {
                        isBreak = true;
                        break;
                    }
                }
            }
            if (!isBreak) {
                i++;
            }
        }
        return strs[0].substring(0, i);
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().run(new String[]{}));
        System.out.println(new LongestCommonPrefix().run(new String[]{"flower"}));
        System.out.println(new LongestCommonPrefix().run(new String[]{"flower", "flow", "flight"}));
        System.out.println(new LongestCommonPrefix().run(new String[]{"flower", "flow", "floight"}));
        System.out.println(new LongestCommonPrefix().run(new String[]{"dog", "racecar", "car"}));
    }
}
