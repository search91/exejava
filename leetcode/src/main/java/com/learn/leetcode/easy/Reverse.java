package com.learn.leetcode.easy;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−231,231−1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @author hzliuzhujie
 * @date 2021-03-21
 **/
public class Reverse {
    public int reverse(int x) {
        boolean isSmallZero = false;
        if (x < 10 && x > -10) {
            return x;
        }
        long x1 = x;
        long y = 0;
        if (x1 < 0) {
            x1 = -x1;
            isSmallZero = true;
        }
        while (x1 / 10 > 0) {
            y = y * 10 + x1 % 10;
            x1 = x1 / 10;
        }
        y = y * 10 + x1 % 10;
        if (isSmallZero) {
            y = -y;
        }
        if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) y;
    }

    /**
     * 优解
     *
     * @param x
     * @return
     */
    public int reverse2(int x) {
        long ret = 0;
        while (x != 0) {
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return (int) ret == ret ? (int) ret : 0;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverse(123));
        System.out.println(reverse.reverse(1230));
        System.out.println(reverse.reverse(-123));
        System.out.println(reverse.reverse(120));
        System.out.println(reverse.reverse(0));
        System.out.println(reverse.reverse(2147483647));
        System.out.println(reverse.reverse(-2147483648));
    }
}
