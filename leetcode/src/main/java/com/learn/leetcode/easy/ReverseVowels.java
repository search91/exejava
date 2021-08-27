package com.learn.leetcode.easy;

/**
 * 345. 反转字符串中的元音字母 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 *
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 * 
 * 不简单的
 *
 * @author hzliuzhujie
 * @date 2021-08-26
 **/
public class ReverseVowels {

    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < j && !isVowel(arr[i])) {
                i++;
            }
            while (j > i && !isVowel(arr[j])) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        // holle
        System.out.println(new ReverseVowels().reverseVowels("hello"));

        System.out.println(new ReverseVowels().reverseVowels("helloe"));

        // leotcede
        System.out.println(new ReverseVowels().reverseVowels("leetcode"));

        // Aa
        System.out.println(new ReverseVowels().reverseVowels("aA"));
    }

}
