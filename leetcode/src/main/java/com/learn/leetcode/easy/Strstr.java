package com.learn.leetcode.easy;

/**
 * 28. 实现 strStr()
 *
 * @author hzliuzhujie
 * @date 2021-04-09
 **/
public class Strstr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int i = 0;
        for (; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(j + i) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /**
         * 输入: haystack = "hello", needle = "ll"
         * 输出: 2
         *
         *
         * 输入: haystack = "aaaaa", needle = "bba"
         * 输出: -1
         */
        Strstr strstr = new Strstr();
        System.out.println(strstr.strStr("hello", "ll"));
        System.out.println(strstr.strStr("aaaaa", "bba"));
        System.out.println(strstr.strStr("a", "a"));
        System.out.println(strstr.strStr("aaa", "aaa"));
        System.out.println(strstr.strStr("aaa", "aaaA"));
        System.out.println(strstr.strStr("mississippi", "issipi"));
        System.out.println(strstr.strStr("abc", "c"));
    }
}
