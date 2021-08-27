package com.learn.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 73. 矩阵置零 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 *
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *
 * 你能想出一个仅使用常量空间的解决方案吗？
 *
 * 简单，两个数组，保存需要清零的行和列
 * 执行耗时:2 ms,击败了24.47% 的Java用户
 *
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class SetZeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> hengSet = new HashSet<>();
        Set<Integer> lieSet = new HashSet<>();

        int hengCount = matrix.length;
        int lieCount = matrix[0].length;
        for (int i = 0; i < hengCount; i++) {
            for (int j = 0; j < lieCount; j++) {
                if (matrix[i][j] == 0) {
                    hengSet.add(i);
                    lieSet.add(j);
                }
            }
        }

        for (int i = 0; i < hengCount; i++) {
            for (int j = 0; j < lieCount; j++) {
                if (hengSet.contains(i) || lieSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {

        // [[1,0,1],[0,0,0],[1,0,1]]
        int[][] origin = new int[][] {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        new SetZeroes().setZeroes(origin);
        System.out.println(Arrays.deepToString(origin));

        // [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        origin = new int[][] {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        new SetZeroes().setZeroes(origin);
        System.out.println(Arrays.deepToString(origin));

    }
}
