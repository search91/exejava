package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 建信02. 柱状图分析
 *
 * 某柱状图上共有 N 个柱形，数组 heights 中按照排列顺序记录了每个柱形的高度。假定任选 cnt
 * 个柱形可组成一个柱形组，请在所有可能的柱形组中，找出最大高度与最小高度的差值为最小的柱形组，按高度升序返回该柱形组。若存在多个柱形组满足条件，则返回第一个元素最小的柱形组。
 *
 * @author hzliuzhujie
 * @date 2021-10-29
 **/
public class AnalysisHistogram {
    public int[] analysisHistogram(int[] heights, int cnt) {
        if (heights == null || heights.length <= 1) {
            return heights;
        }
        int len = heights.length;
        Arrays.sort(heights);
        if (len <= cnt) {
            return heights;
        }

        int minDis = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i <= len - cnt; i++) {
            int dis = heights[cnt + i - 1] - heights[i];
            if (dis < minDis) {
                minDis = dis;
                minIndex = i;
            }
        }
        /*   System.out.println(minIndex);
        System.out.println(Arrays.toString(heights));*/
        return Arrays.copyOfRange(heights, minIndex, minIndex + cnt);
    }

    public static void main(String[] args) {
         // 1 2 3
        System.out.println(Arrays.toString(new AnalysisHistogram().analysisHistogram(new int[] {3, 2, 7, 6, 1, 8}, 3)));
        
        // 4 4 6 8
        System.out
            .println(Arrays.toString(new AnalysisHistogram().analysisHistogram(new int[] {4, 6, 1, 8, 4, 10}, 4)));

        // [5,6,7,7,7]
        System.out.println(
            Arrays.toString(new AnalysisHistogram().analysisHistogram(new int[] {2, 0, 4, 7, 7, 4, 7, 1, 6, 5}, 5)));
    }
}
