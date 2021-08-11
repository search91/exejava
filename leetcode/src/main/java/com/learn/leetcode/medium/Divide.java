package com.learn.leetcode.medium;

/**
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231− 1]。本题中，如果除法结果溢出，则返回 231− 1。
 *
 * @author hzliuzhujie
 * @date 2021-04-14
 **/
public class Divide {
    // 比如 20 / 3 => 3 6 12 24 超过20 然后18，21（超过了，确认18）
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            // 只要不是最小的那个整数，都是直接返回相反数就好啦
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int mulit = 1;
        long a = dividend;
        long b = divisor;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            mulit = -1;
        }
        if (dividend < 0) {
            a = -a;
        }
        if (divisor < 0) {
            b = -b;
        }
        addCount(1, a, b);


        int i = 0;
        long tmp = b;
        int k = 1;
        long sum = 0;
        while (sum + tmp <= a) {
            i += k;
            sum += tmp;
            k = k + k;
            tmp = tmp + tmp;
        }
        while (sum + b <= a) {
            sum = sum + b;
            i++;
        }
        return mulit * i;
    }

    public int addCount(int initCount, long a, long b) {
        long tmp = b;
        int k = 1;
        long sum = 0;
        while (sum + tmp <= a) {
            initCount += k;
            sum += tmp;
            k = k + k;
            tmp = tmp + tmp;
        }
        return initCount;
    }

    public int divide2(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 1) {
            return dividend;
        }
        long a = dividend;
        long b = divisor;
        int sign = 1;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long res = div(a, b);
        if (sign > 0) {
            return res > Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) res;
        }
        return (int) -res;
    }

    long div(long a, long b) {  // 似乎精髓和难点就在于下面这几句
        if (a < b) {
            return 0;
        }
        long count = 1;
        long tb = b; // 在后面的代码中不更新b
        while ((tb + tb) <= a) {
            count = count + count; // 最小解翻倍
            tb = tb + tb; // 当前测试的值也翻倍
        }
        return count + div(a - tb, b);
    }

    public static void main(String[] args) {
        // 3
        System.out.println(new Divide().divide(10, 3));
        // 2
        System.out.println(new Divide().divide(6, 3));
        // 2
        System.out.println(new Divide().divide(-6, -3));
        // -2
        System.out.println(new Divide().divide(7, -3));
        System.out.println(new Divide().divide(10, 10));
        // 1
        System.out.println(new Divide().divide(Integer.MAX_VALUE, Integer.MAX_VALUE));
        // 0
        System.out.println(new Divide().divide(Integer.MAX_VALUE, Integer.MIN_VALUE));
        // 1
        System.out.println(new Divide().divide(Integer.MIN_VALUE, Integer.MIN_VALUE));
        // 0
        System.out.println(new Divide().divide(-2147483647, -2147483648));
        // -1073741824
        System.out.println(new Divide().divide(-2147483648, 2));

    }
}
