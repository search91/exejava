package com.learn.leetcode.medium;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * @author hzliuzhujie
 * @date 2021-03-01
 **/
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        String result = s.substring(0, 1);
        int max = 0;

        for (int i = 1; i <= length - 1; i++) {
            PosClass posClass = null;
            for (int maxLen = 1; maxLen < s.length(); maxLen++) {
                // 比较奇数
                PosClass posClass1 = compareJishu(i, maxLen, s);
                if (posClass1 == null) {
                    break;
                }
                posClass = posClass1;
            }
            if (posClass != null && posClass.end - posClass.start + 1 > max) {
                max = posClass.end - posClass.start + 1;
                result = s.substring(posClass.start, posClass.end + 1);
            }

            for (int maxLen = 1; maxLen < s.length(); maxLen++) {
                // 比较偶数
                PosClass posClass1 = compareOushu(i, maxLen, s);
                if (posClass1 == null) {
                    break;
                }
                posClass = posClass1;
            }
            if (posClass != null && posClass.end - posClass.start + 1 > max) {
                max = posClass.end - posClass.start + 1;
                result = s.substring(posClass.start, posClass.end + 1);
            }
        }

        return result;
    }

    private PosClass compareOushu(int i, int maxLen, String s) {
        int start = i - maxLen;
        int end = i + maxLen - 1;
        if (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)) {
            return new PosClass(start, end);
        } else {
            return null;
        }
    }

    private PosClass compareJishu(int i, int maxLen, String s) {
        int start = i - maxLen;
        int end = i + maxLen;
        if (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)) {
            return new PosClass(start, end);
        }
        return null;
    }

    static class PosClass {
        public int start = -1;
        public int end = -1;

        public PosClass(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 以前做的
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int maxLen = 1;
        String res = s.charAt(0) + "";

        for (int i = 0; i < s.length(); i++) {
            int L = i;
            int R = i;
            String temp = getPalindromicSubstring(s, L, R);

            if (temp.length() > maxLen) {
                maxLen = temp.length();
                res = temp;
            }

            if (i != s.length() - 1) {
                L = i;
                R = i + 1;
                temp = getPalindromicSubstring(s, L, R);
                if (temp.length() > maxLen) {
                    maxLen = temp.length();
                    res = temp;
                }
            }
        }

        return res;
    }

    public String getPalindromicSubstring(String s, int begin, int end) {
        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println("bab=" + longestPalindrome.longestPalindrome("babad"));
        System.out.println("aa=" + longestPalindrome.longestPalindrome("aabb"));
        System.out.println("bb=" + longestPalindrome.longestPalindrome("bb"));  // bb
        System.out.println("bb=" + longestPalindrome.longestPalindrome("abb"));  // bb
        System.out.println("bbb=" + longestPalindrome.longestPalindrome("abbb"));  // bbb
        System.out.println("bbbb=" + longestPalindrome.longestPalindrome("abbbb"));  // bbbb
        System.out.println("bbb=" + longestPalindrome.longestPalindrome("bbb"));
        System.out.println("bbbb=" + longestPalindrome.longestPalindrome("bbbb"));
        System.out.println("baab=" + longestPalindrome.longestPalindrome("cbaabad"));
        System.out.println("a=" + longestPalindrome.longestPalindrome("ac"));
        System.out.println("bb=" + longestPalindrome.longestPalindrome("cbbd"));
        System.out.println("cbbc=" + longestPalindrome.longestPalindrome("acbbcd"));
        System.out.println("bbcccbb=" + longestPalindrome.longestPalindrome("abbcccbbbcaaccbababcbcabca"));


    }
}
