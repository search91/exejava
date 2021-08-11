package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 48.旋转图像
 * 给定一个 n×n 的二维矩阵matrix表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * 做了很久，我的做法的是先外层正方形循环，再一层层到小正方形
 *
 * @author hzliuzhujie
 * @date 2021-08-06
 **/
public class MatrixRotate {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int maxCount = n / 2;
        for (int i = 0; i < maxCount; i++) {
            for (int j = i; j < n - i - 1; j++) {
                // 四个点： i,j  j,n-i-1  4-i-1,4-j-1  4-j-1,i
                int tmp = matrix[i][j];
                int tmp2 = matrix[j][n - i - 1];
                int tmp3 = matrix[n - i - 1][n - j - 1];
                int tmp4 = matrix[n - j - 1][i];
                matrix[j][n - i - 1] = tmp;
                matrix[n - i - 1][n - j - 1] = tmp2;
                matrix[n - j - 1][i] = tmp3;
                matrix[i][j] = tmp4;
               /* System.out.println(i + " " + j);
                System.out.println(j + " " + (n - i - 1));
                System.out.println((n - i - 1) + " " + (n - j - 1));
                System.out.println((n - j - 1) + " " + i);
                System.out.println("++++++++++++");*/
            }
        }
    }

    public static void main(String[] args) {
        int[][] maxtri = new int[][]{{1, 2}, {3, 4}};
        new MatrixRotate().rotate(maxtri);
        //[[3,1],[4,2]]
        System.out.println(Arrays.deepToString(maxtri));

        maxtri = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new MatrixRotate().rotate(maxtri);
        //[[7,4,1],[8,5,2],[9,6,3]]
        System.out.println(Arrays.deepToString(maxtri));

        maxtri = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new MatrixRotate().rotate(maxtri);
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        System.out.println(Arrays.deepToString(maxtri));

        maxtri = new int[][]{{5, 1, 9, 11, 8}, {2, 4, 8, 10, 17}, {13, 3, 6, 7, 0},
                {15, 14, 12, 16, 2}, {19, 20, 21, 22, 23}};
        new MatrixRotate().rotate(maxtri);
        // [[19,15,13,2,5],[20,14,3,4,1],[21,12,6,8,9],[22,16,7,10,11],[23,2,0,17,8]]
        System.out.println(Arrays.deepToString(maxtri));


    }

}
