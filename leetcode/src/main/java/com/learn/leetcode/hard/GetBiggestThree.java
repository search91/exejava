package com.learn.leetcode.hard;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 5757. 矩阵中最大的三个菱形和
 *
 * @author hzliuzhujie
 * @date 2021-05-29
 **/
public class GetBiggestThree {
    public int[] getBiggestThree(int[][] grid) {
      /*  nums1 = IntStream.of(nums1)          // 变为 IntStream
                .boxed()           // 变为 Stream<Integer>
                .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                .mapToInt(Integer::intValue)       // 变为 IntStream
                .toArray();        // 又变回 int[]
        nums2 = IntStream.of(nums2)          // 变为 IntStream
                .boxed()           // 变为 Stream<Integer>
                .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                .mapToInt(Integer::intValue)       // 变为 IntStream
                .toArray();        // 又变回 int[]*/

        int height = grid.length;
        int width = grid[0].length;
        int max = height < width ? (height + 1) / 2 : (width + 1) / 2;
        Set<Integer> sortSet = new TreeSet<>((o1, o2) -> {
            return o2 - o1;//降序排列
        });

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sortSet.add(grid[j][i]);
                int z = 1;
                while (j - z >= 0 && j + z <= height && i - z >= 0 && i + z <= width) {
                    System.out.println(j + " " + i + " " + z);
                    z++;
                }
            }
        }
        System.out.println(sortSet);
        for (int z = max; z >= 1; z--) {

        }

        return null;
    }

    public static void main(String[] args) {
        GetBiggestThree getBiggestThree = new GetBiggestThree();
        // 7
        //  System.out.println(Arrays.toString(getBiggestThree.getBiggestThree(new int[][]{{7, 7, 7}})));
        // 20,9,8
        System.out.println(Arrays.toString(getBiggestThree.getBiggestThree(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}})));

        /**
         * grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
         * 输出：[228,216,211]
         */
        System.out.println(Arrays.toString(getBiggestThree.getBiggestThree(new int[][]{
                {3, 4, 5, 1, 3},
                {3, 3, 4, 2, 3},
                {20, 30, 200, 40, 10},
                {1, 5, 5, 4, 1},
                {4, 3, 2, 2, 5}})));
    }

}
