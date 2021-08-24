package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间 以数组 intervals 表示若干个区间的集合，其中单个区间为intervals[i] = [starti, endi]。
 *
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 发现思路有的要调试很久，好菜
 * @author hzliuzhujie
 * @date 2021-08-24
 **/
public class Merge {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 根据左端从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        int size = intervals.length;
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int min = intervals[i][0];
            int max = intervals[i][1];
            for (int j = i + 1; j < size; j++) {
                // 有重叠可覆盖
                if (intervals[j][1] >= max && intervals[j][0] <= max) {
                    max = intervals[j][1];
                    i = j;
                } else if (intervals[j][0] >= min && intervals[j][1] <= max) {
                    i = j;
                } else if (intervals[j][0] > intervals[i][1]) {
                    break;
                }
            }
            merged.add(new int[] {min, max});
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        // 1,5
        System.out.println(Arrays.deepToString(new Merge().merge(new int[][] {{1, 4}, {4, 5}})));

        // [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(new Merge().merge(new int[][] {{2, 6}, {1, 3}, {8, 10}, {15, 18}})));

        // [[1,4]]
        System.out.println(Arrays.deepToString(new Merge().merge(new int[][] {{1, 4}, {1, 4}})));

        // [[1,4],[2,3]]
        System.out.println(Arrays.deepToString(new Merge().merge(new int[][] {{1, 4}, {2, 3}})));

        // [[2,4],[5,5]]
        System.out
            .println(Arrays.deepToString(new Merge().merge(new int[][] {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}})));
    }
}
