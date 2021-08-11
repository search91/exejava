package com.learn.leetcode.easy;

/**
 * 5754. 长度为三且各字符不同的子字符串
 *
 * @author hzliuzhujie
 * @date 2021-05-29
 **/
public class CountGoodSubstrings {

    public int countGoodSubstrings(String s) {
        if (s == null || s.length() < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1)
                    && s.charAt(i) != s.charAt(i + 2)
                    && s.charAt(i + 1) != s.charAt(i + 2)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 1
        System.out.println(new CountGoodSubstrings().countGoodSubstrings("xyzzaz"));

        // 4
        System.out.println(new CountGoodSubstrings().countGoodSubstrings("aababcabc"));

        // 8
        System.out.println(new CountGoodSubstrings().countGoodSubstrings("owuxoelszb"));
    }
}
