package com.learn.leetcode.medium;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
 *
 * @author hzliuzhujie
 * @date 2021-08-05
 * 不会做
 **/
public class MyPow {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        } else if (n < 0) {
            return 1.0 / pow(x, -n);
        } else {
            return pow(x, n);
        }
    }

    public double pow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }

    public static void main(String[] args) {
        // 1024
        System.out.println(new MyPow().myPow(2.0, 10));
        // 9.261
        System.out.println(new MyPow().myPow(2.1, 3));
        // 0.25
        System.out.println(new MyPow().myPow(2, -2));
    }
}
