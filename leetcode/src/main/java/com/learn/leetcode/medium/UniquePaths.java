package com.learn.leetcode.medium;

/**
 * 62. 不同路径 一个机器人位于一个 mXn网格的左上角 （起始点在下图中标记为 “Start” ）。 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 问总共有多少条不同的路径？
 * 
 * 63. 62的基础上考虑障碍物
 * 
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 网格中的障碍物和空位置分别用1和0来表示。
 * 
 * @author hzliuzhujie
 * @date 2021-08-16
 **/
public class UniquePaths {

    // 62. 就是排列组合 C (m+n-2) (m-1) = (m+n-2)!(n-1)!/(m-1)! 注意4
    public int uniquePaths(int m, int n) {
        // 注意 double的4舍5入的问题
        return (int)Math.round(factorial(m + n - 2) / factorial(n - 1) / factorial(m - 1));
    }

    // 63.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return 0;
    }

    public static double factorial(long number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(3, 2));
        System.out.println(new UniquePaths().uniquePaths(100, 100));
        System.out.println(new UniquePaths().uniquePaths(23, 12));
        System.out.println(new UniquePaths().uniquePaths(2, 100));
    }

}
