package com.learn.leetcode.medium;


/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 说明：num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author hzliuzhujie
 * @date 2021-08-04
 **/
public class Multiply {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder sum = new StringBuilder();
        int m = num1.length();
        int n = num2.length();
        for (int i = 0; i < m; i++) {
            sum.append(0);
            int m1 = num1.charAt(i) - '0';
            for (int j = 1; j <= m1; j++) {
                sum = add(num2, sum);
            }
        }
        return sum.toString();
    }

    private StringBuilder add(String num2, StringBuilder sum) {
        int m = num2.length();
        int n = sum.length();
        int jinwei = 0;
        StringBuilder sb = new StringBuilder();
        int i = m - 1, j = n - 1;
        for (; i >= 0 || j >= 0; i--, j--) {
            int total = (j >= 0 ? (sum.charAt(j) - '0') : 0) + (i >= 0 ? (num2.charAt(i) - '0') : 0) + jinwei;
            jinwei = total / 10;
            sb.append(total % 10);
        }
        if (jinwei != 0) {
            sb.append(jinwei);
        }
        return sb.reverse();
    }

    public static void main(String[] args) {
        // 56088
        System.out.println(new Multiply().multiply("123", "456"));
        System.out.println(new Multiply().multiply("1111111111111", "456"));
    }
}
