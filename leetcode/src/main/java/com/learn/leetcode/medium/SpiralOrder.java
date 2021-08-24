package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 54. 螺旋矩阵 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 59. 螺旋矩阵2 给你一个正整数n，生成一个包含 1到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n
 * 正方形矩阵matrix。
 *
 * 做起来很费劲，像是数学题
 *
 * @author hzliuzhujie
 * @date 2021-03-15
 **/
public class SpiralOrder {
    /**
     * i=0 i不变 j++ -> n-1 j=n-1
     * j不变 i++ -> m-1 i=m-1
     * i不变 j-- -> 0 j=0
     * j不变 i-- ->1
     * -----------------
     * i=1 i不变 j++ -> n-1-1
     * 
     * @param matrix 矩阵
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = 0;
        double sum = m * n;

        List<Integer> result = new ArrayList<>();
        int starti = 0;
        int startj = 0;
        while (starti <= n - 1 && startj <= m - 1) {
            for (i = starti, j = startj; j < m - startj; j++) {
                System.out.println(i + " " + j);
                result.add(matrix[starti][j]);
            }
            if (result.size() == sum) {
                break;
            }
            System.out.println("++++++");
            for (i = starti + 1, j = m - startj - 1; i < n - starti; i++) {
                System.out.println(i + " " + j);
                result.add(matrix[i][j]);
            }
            if (result.size() == sum) {
                break;
            }
            System.out.println("++++++");
            for (i = n - starti - 1, j = m - startj - 2; j >= startj && n - starti - 1 > starti; j--) {
                System.out.println(i + " " + j);
                result.add(matrix[i][j]);
            }
            if (result.size() == sum) {
                break;
            }
            System.out.println("++++++");
            for (i = n - starti - 2, j = startj; i >= starti + 1 && m - startj - 1 > startj; i--) {
                System.out.println(i + " " + j);
                result.add(matrix[i][j]);
            }

            System.out.println("++++++");

            if (result.size() == sum) {
                break;
            }
            starti++;
            startj++;
        }
        return result;
    }

    public int[][] spiralOrder2(int n) {
        int [][] res = new int[n][n];
        int num =0;
        int i = 0;
        int j = 0;
        int starti = 0;
        while (true) {
            for (i = starti, j = starti; j < n - starti; j++) {
                if (res[i] == null) {
                    res[i] = new int[n];
                }
                res[i][j] = ++num;
            }
            if (num == n * n) {
                break;
            }
            for (i = starti + 1, j = n - starti - 1; i < n - starti; i++) {
                if (res[i] == null) {
                    res[i] = new int[n];
                }
                res[i][j] = ++num;
            }
            if (num == n * n) {
                break;
            }
            for (i = n - starti - 1, j = n - starti - 2; j >= starti && n - starti - 1 > starti; j--) {
                if (res[i] == null) {
                    res[i] = new int[n];
                }
                res[i][j] = ++num;
            }
            if (num == n * n) {
                break;
            }
            for (i = n - starti - 2, j = starti; i >= starti + 1 && n - starti - 1 > starti; i--) {
                if (res[i] == null) {
                    res[i] = new int[n];
                }
                res[i][j] = ++num;
            }
            if (num == n * n) {
                break;
            }
            starti++;
        }
            
        return res;
    }

    public static void main(String[] args) {

/*
        Assert.assertThat(Arrays.stream(new int[] {1,2,3,4,5,10,15,14,13,12,11,6,7,8,9}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{1, 2, 3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}})));

        Assert.assertThat(Arrays.stream(new int[] {1, 2, 3, 6, 9, 8, 7, 4, 5}).boxed().collect(Collectors.toList()),
            is(new SpiralOrder().spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));

        Assert.assertThat(Arrays.stream(new int[] {1,2,3,4,8,12,11,10,9,5,6,7}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})));

        Assert.assertThat(Arrays.stream(new int[] {1,2,3,6,9,12,11,10,7,4,5,8}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7,8,9},{10, 11, 12}})));

        Assert.assertThat(Arrays.stream(new int[] {2,5,8,-1,0,4}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{2, 5, 8}, {4, 0, -1}})));

        Assert.assertThat(Arrays.stream(new int[] {2, 5, 8}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{2, 5, 8}})));

        Assert.assertThat(Arrays.stream(new int[] {2, 5, 8}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{2}, {5}, {8}})));

        Assert.assertThat(Arrays.stream(new int[] {2}).boxed().collect(Collectors.toList()),
                is(new SpiralOrder().spiralOrder(new int[][] {{2}})));
*/

        System.out.println(Arrays.deepToString(new SpiralOrder().spiralOrder2(1)));
        System.out.println(Arrays.deepToString(new SpiralOrder().spiralOrder2(2)));
        System.out.println(Arrays.deepToString(new SpiralOrder().spiralOrder2(3)));
        System.out.println(Arrays.deepToString(new SpiralOrder().spiralOrder2(4)));



    }
}
