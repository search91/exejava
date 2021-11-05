package com.learn.leetcode.medium;

import java.util.*;

/**
 * 建信03. 地铁路线规划
 * 
 * @author hzliuzhujie
 * @date 2021-10-29
 **/
public class MetroRouteDesignI {

    public int[] metroRouteDesignI(int[][] lines, int start, int end) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                int num = lines[i][j];
                List<Integer> list = map.getOrDefault(num, new ArrayList<>());
                list.add(i);
                map.put(num, list);
            }
        }

        List<Integer> startLocs = map.get(start);
        List<Integer> endLocs = map.get(end);

        for (Integer startLoc : startLocs) {
            if (endLocs.contains(startLoc)) {

            }
        }
        System.out.println(map);
        return null;
    }

    public static void main(String[] args) {
        // 9,8,7,6,5,4,3,2,1
        System.out.println(Arrays.toString(new MetroRouteDesignI().metroRouteDesignI(
            new int[][] {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {12, 13, 2, 14, 8, 15}, {16, 1, 17, 10, 18}}, 9, 1)));

        // 1,2,3,4,8,7
        System.out.println(Arrays.toString(new MetroRouteDesignI().metroRouteDesignI(
            new int[][] {{1, 2, 3, 4, 5}, {2, 10, 14, 15, 16}, {10, 8, 12, 13}, {7, 8, 4, 9, 11}}, 1, 7)));
    }
}
