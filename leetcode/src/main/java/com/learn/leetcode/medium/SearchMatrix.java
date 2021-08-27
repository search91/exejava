package com.learn.leetcode.medium;

/**
 * 74.搜索二维矩阵
 *
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 *
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 简单,这道就是二分查找，只是需要转换下横列的下标
 *
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int hengSize = matrix.length;
        int lieSize = matrix[0].length;
        int total = hengSize * lieSize;
        int left = 0, right = total - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int i = middle / lieSize;
            int j = middle % lieSize;
            if (matrix[i][j] > target) {
                right = middle - 1;
            } else if (matrix[i][j] == target) {
                return true;
            } else {
                left = middle + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(
            new SearchMatrix().searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));

        System.out.println(
            new SearchMatrix().searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));

        System.out.println(
                new SearchMatrix().searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 7));

    }
}
