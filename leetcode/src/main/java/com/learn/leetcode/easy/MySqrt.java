package com.learn.leetcode.easy;

/**
 * 69. x的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 各种溢出问题
 *
 * @author hzliuzhujie
 * @date 2021-08-04
 **/
public class MySqrt {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        long left = 1;
        long right = x;
        long middle = left;
        long k = 1;
        while (left <= right) {
            middle = (left + right) / 2;
            double multi = ((double) middle) * middle;
            if (multi > x) {
                right = middle - 1;
            } else if (multi < x) {
                left = middle + 1;
                k = middle;
            } else {
                return (int) middle;
            }
        }
        return (int) k;
    }

    /**
     * 牛顿迭代
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    public static void main(String[] args) {
        System.out.println(new MySqrt().mySqrt(3));
        System.out.println(new MySqrt().mySqrt(4));
        System.out.println(new MySqrt().mySqrt(5));
        System.out.println(new MySqrt().mySqrt(8));
        System.out.println(new MySqrt().mySqrt(9));
        System.out.println(new MySqrt().mySqrt(10));
        System.out.println(new MySqrt().mySqrt(101));
        // 46339
        System.out.println(new MySqrt().mySqrt(2147395599));
        // 46340
        System.out.println(new MySqrt().mySqrt(2147483647));

    }
}
