package com.learn.leetcode.easy;

/**
 * 58. 最后一个单词的长度 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 简单
 * 
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int count = 0;
        int lastCount = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (count != 0) {
                    lastCount = count;
                }
                count = 0;
            } else {
                count++;
            }
        }
        return count == 0 ? lastCount : count;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfLastWord("Hello World"));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(new LengthOfLastWord().lengthOfLastWord("luffy is still joyboy"));
    }
}
