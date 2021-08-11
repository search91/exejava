package com.learn.leetcode.medium;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author hzliuzhujie
 * @date 2021-02-25
 **/
public class LengthOfLongestSubstring {
    /**
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4:
     * <p>
     * 输入: s = ""
     * 输出: 0
     *
     * @param s
     * @return
     */
    // 7ms
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        int start = 0;
        int end = 0;
        int length = s.length();
        Map<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            end = i;
            if (cMap.containsKey(c)) {
                if (cMap.get(c) < start) {
                    cMap.put(c, i);
                    continue;
                }
                end = end - 1;
                max = Math.max(max, end - start + 1);
                start = cMap.get(c) + 1;
                cMap.put(c, i);
            } else {
                cMap.putIfAbsent(c, i);
            }
        }
       /* System.out.println(cMap);
        System.out.println(start);*/
        return Math.max(end - start + 1, max);
    }

    // 以前做的23ms
    public int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        int max = 0;
        Set<Character> set = new HashSet<>();
        Deque<Character> queue = new LinkedBlockingDeque<>();
        // 利用set和双向队列，set包含，则pop队列
        char[] stringChar = s.toCharArray();
        for (char c : stringChar) {
            if (set.add(c)) {
                queue.push(c);
            } else {
                while (!queue.isEmpty()) {
                    Character removeS = queue.pollLast();
                    set.remove(removeS);
                    if (set.add(c)) {
                        break;
                    }
                }
                set.add(c);
                queue.push(c);
            }
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring instance = new LengthOfLongestSubstring();
        System.out.println(instance.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(instance.lengthOfLongestSubstring("pwkeo")); // 5
        System.out.println(instance.lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(instance.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(instance.lengthOfLongestSubstring(""));//0
        System.out.println(instance.lengthOfLongestSubstring("cbcbcbcbcbc"));//2
        System.out.println(instance.lengthOfLongestSubstring("aab"));//2
        System.out.println(instance.lengthOfLongestSubstring("abb"));//2
        System.out.println(instance.lengthOfLongestSubstring("abba"));//2
        System.out.println(instance.lengthOfLongestSubstring("abcca"));//3
        System.out.println(instance.lengthOfLongestSubstring("abcccccbad"));//4
    }
}
