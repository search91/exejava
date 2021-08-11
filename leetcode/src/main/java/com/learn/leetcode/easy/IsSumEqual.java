package com.learn.leetcode.easy;

/**
 * 5772. 检查某单词是否等于两单词之和
 *
 * @author hzliuzhujie
 * @date 2021-05-30
 **/
public class IsSumEqual {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        long sum1 = 0;
        for (char c : firstWord.toCharArray()) {
            sum1 = sum1 * 10 + (c - 'a');
        }

        long sum2 = 0;
        for (char c : secondWord.toCharArray()) {
            sum2 = sum2 * 10 + (c - 'a');
        }

        long sum3 = 0;
        for (char c : targetWord.toCharArray()) {
            sum3 = sum3 * 10 + (c - 'a');
        }

        return sum1 + sum2 == sum3;
    }

    public static void main(String[] args) {
        System.out.println(new IsSumEqual().isSumEqual("acb", "cba", "cdb"));
        System.out.println(new IsSumEqual().isSumEqual("aaa", "a", "aab"));

        System.out.println(new IsSumEqual().isSumEqual("aaa", "a", "aaaa"));

    }
}
