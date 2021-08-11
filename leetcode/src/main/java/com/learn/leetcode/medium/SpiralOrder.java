package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * @author hzliuzhujie
 * @date 2021-03-15
 **/
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;

        List<Integer> result = new ArrayList<>();
        while (i < n && j < m) {

            result.add(matrix[0][0]);
            result.add(matrix[0][n - 1 - i]);

            result.add(matrix[m - 1 - j][n - 1 - i]);

            result.add(matrix[0][n - 1 - i]);

            result.add(matrix[0][1]);

            result.add(matrix[1][1]);

        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
         * 输出：[1,2,3,6,9,8,7,4,5]
         *
         * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
         * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
         */
        System.out.println(new SpiralOrder().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(new SpiralOrder().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));

    }
}
