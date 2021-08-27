package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为intervals[i] = [starti, endi]。
 *
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * ps：发现思路有的要调试很久，好菜
 *
 * 57. 插入区间 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * ps: 跟56差不多，就是已经有序了,做起来太难，最后套56.
 *
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

    // 57. 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0) {
            return intervals;
        }
        int size = intervals.length;
        int[][] other = new int[size + 1][];
        for (int i = 0; i < intervals.length; i++) {
            other[i] = intervals[i];
        }
        other[size] = newInterval;
        return merge(other);
    }

    public static void main(String[] args) {
        // [1,5]
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

        // [[2,4],[5,5]]
        System.out
            .println(Arrays.deepToString(new Merge().merge(new int[][] {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}})));

        // [[1,2],[3,10],[12,16]]
        System.out.println(Arrays.deepToString(
            new Merge().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})));

        // [1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16]
        System.out.println(Arrays.deepToString(
            new Merge().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {3, 4})));

        System.out.println(Arrays.deepToString(
            new Merge().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {17, 19})));

        System.out.println(
            Arrays.deepToString(new Merge().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {12, 16}}, new int[] {8, 10})));

        // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(new Merge().insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5})));

        System.out
            .println(Arrays.deepToString(new Merge().insert(new int[][] {{1, 3}, {6, 9}, {10, 13}}, new int[] {2, 5})));

        // [[1,2],[3,10],[12,16]]
        System.out.println(Arrays.deepToString(
            new Merge().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})));

        // [[0,5]]
        System.out.println(Arrays.deepToString(new Merge().insert(new int[][] {{1, 5}}, new int[] {0, 3})));

    }
}
