package com.learn.leetcode.medium;

import sun.java2d.pipe.AATileGenerator;

/**
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数myAtoi(string s) 的算法如下：
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231, 2 31−1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * @author hzliuzhujie
 * @date 2021-03-22
 **/
public class Atoi {

    // s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
    public int myAtoi(String s) {
        long num = 0;
        int multi = 1;
        boolean isStart = false;
        for (char c : s.toCharArray()) {
            if (num > Integer.MAX_VALUE) {
                break;
            }
            if (c == '+' || c == '-') {
                if (c == '-' && num == 0) {
                    multi = -1;
                }
                if (!isStart) {
                    isStart = true;
                } else {
                    if (num > 0) {
                        break;
                    } else {
                        return 0;
                    }
                }
            } else if (c == ' ') {
                if (isStart) {
                    break;
                }
            } else if (c >= '0' && c <= '9') {
                isStart = true;
                num = num * 10 + c - '0';
            } else {
                break;
            }
        }
        if (multi * num != (int) (multi * num)) {
            return multi < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return (int) (multi * num);
    }

    public static void main(String[] args) {
        // 42
        System.out.println(new Atoi().myAtoi("42"));
        // 42
        System.out.println(new Atoi().myAtoi("42.0"));
        // -42
        System.out.println(new Atoi().myAtoi("   -42"));
        // 4193
        System.out.println(new Atoi().myAtoi("4193 with words"));
        // 0
        System.out.println(new Atoi().myAtoi("words and 98"));
        // -2147483648
        System.out.println(new Atoi().myAtoi("-91283472332"));
        // -2147483647
        System.out.println(new Atoi().myAtoi("-2147483647"));
        // 0
        System.out.println(new Atoi().myAtoi("+ 0 123"));
        // 123
        System.out.println(new Atoi().myAtoi("+0123"));
        // -5
        System.out.println(new Atoi().myAtoi("-5-"));
        // 312
        System.out.println(new Atoi().myAtoi("312-"));
        // 0
        System.out.println(new Atoi().myAtoi(" b11228552307"));
        // 2147483647
        System.out.println(new Atoi().myAtoi("18446744073709551617"));
        // 2147483647
        System.out.println(new Atoi().myAtoi("-18446744073709551617"));
    }
}
