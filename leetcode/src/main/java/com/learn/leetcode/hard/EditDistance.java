package com.learn.leetcode.hard;

/**
 * 72. 编辑距离 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符 删除一个字符 替换一个字符
 *
 * 不会做 动态规划
 *
 * @author hzliuzhujie
 * @date 2022-01-18
 **/
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] res = new int[m + 1][n + 1];
        // 边界状态初始化
        for (int i = 0; i <= m ; i++) {
            res[i][0] = i;
        }
        for (int j = 0; j <= n ; j++) {
            res[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                int cost = 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost = 0;
                }
                int d1 = res[i - 1][j] + 1; // delete
                int d2 = res[i][j - 1] + 1; // insert
                int d3 = res[i - 1][j - 1] + cost; // substitution
                res[i][j] = Math.min(d1, Math.min(d2, d3));
            }
        }
        return res[n][m];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("wo8rd", "word2"));
    }
}
