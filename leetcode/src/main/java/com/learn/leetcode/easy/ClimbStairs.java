package com.learn.leetcode.easy;

/**
 * @author hzliuzhujie
 * @date 2021-08-05
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 动态规划
 *
 * 这我也不会做 还菲波那切数列，递归会超时，得转成数组
 **/
public class ClimbStairs {


    public int climbStairs(int n) {
       if (n < 0) {
            return 0;
        }
        if (n <= 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);


     /*  int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];*/
    }

    public static void main(String[] args) {
        // 2
        System.out.println(new ClimbStairs().climbStairs(2));
        // 3
        System.out.println(new ClimbStairs().climbStairs(3));
        // 8
        System.out.println(new ClimbStairs().climbStairs(5));
        // 89
        System.out.println(new ClimbStairs().climbStairs(10));
        // 987
        System.out.println(new ClimbStairs().climbStairs(15));

        // 1134903170
        System.out.println(new ClimbStairs().climbStairs(44));
    }
}
