package com.learn.leetcode.medium;

/**
 * 8. String to Integer (atoi)
 */
public class String2Integer {
    public int myAtoi(String s) {
        boolean isFirstBlank = true;
        long result = 0;
        int multi = 1;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                isFirstBlank = false;
                result = result * 10 + (c - '0');
                if (result >= Integer.MAX_VALUE && multi == 1) {
                    return Integer.MAX_VALUE;
                }
                if (result >= 2147483648L && multi == -1) {
                    return Integer.MIN_VALUE;
                }
            } else if (c != ' ' && c != '-' && c != '+') {
                break;
            } else if (c == '-' && isFirstBlank) {
                isFirstBlank = false;
                multi = -1;
            } else if (c == '+' && isFirstBlank) {
                isFirstBlank = false;
                multi = 1;
            } else if (!isFirstBlank) {
                break;
            }
        }

        if (result >= Integer.MAX_VALUE && multi == 1) {
            return Integer.MAX_VALUE;
        }
        if (result >= 2147483648L && multi == -1) {
            return Integer.MIN_VALUE;
        }
        return (int) (multi * result);
    }

    public static void main(String[] args) {
        System.out.println(new String2Integer().myAtoi("42"));
        System.out.println(new String2Integer().myAtoi("4193 with words"));
        System.out.println(new String2Integer().myAtoi("  -42"));
        System.out.println(new String2Integer().myAtoi("words and 987"));
        System.out.println(new String2Integer().myAtoi("-91283472332")); // -2147483648
        System.out.println(new String2Integer().myAtoi("  0032")); // 32
        System.out.println(new String2Integer().myAtoi("  +0032")); // 32
        System.out.println(new String2Integer().myAtoi("+-12")); // 0
        System.out.println(new String2Integer().myAtoi("9223372036854775808")); // 2147483647
    }
}