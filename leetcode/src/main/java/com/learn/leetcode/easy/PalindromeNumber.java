package com.learn.leetcode.easy;

/**
 * 9. Palindrome Number
 *
 * @author hzliuzhujie
 * @date 2019-07-16
 **/
public class PalindromeNumber {

    //17 ms
    public boolean isPalindrome(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int x1 = x;
        double num = 0d;
        while (x > 0 && num <= Integer.MAX_VALUE) {
            int yushu = x % 10;
            x = x / 10;
            num = num * 10 + yushu;
        }
        return !(num > Integer.MAX_VALUE) && x1 == (int) num;
    }


    //17 ms
    public boolean isPalindrome2(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }

        char[] chars = (x + "").toCharArray();
        int len = chars.length;
        for (int i = 0; i < len / 2; i++) {
            if (chars[i] != chars[len - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        System.out.println(palindromeNumber.isPalindrome(1000));
        System.out.println(palindromeNumber.isPalindrome(11));
        System.out.println(palindromeNumber.isPalindrome(111));
        System.out.println(palindromeNumber.isPalindrome(1111));
        System.out.println(palindromeNumber.isPalindrome(1211));
        System.out.println(palindromeNumber.isPalindrome(1000021));
        System.out.println(palindromeNumber.isPalindrome(123454321));
        System.out.println(palindromeNumber.isPalindrome(323));
        System.out.println(palindromeNumber.isPalindrome(2147483647));

    }
}
