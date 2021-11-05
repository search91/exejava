package com.learn.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 *
 * 119. 杨辉三角2
 * 
 * @author hzliuzhujie
 * @date 2021-09-02
 **/
public class Yanghui {

     // 118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            int num = i + 1;
            for (int j = 0; j < num; j++) {
                if (j == 0 || j == num - 1) {
                    list.add(1);
                } else {
                    list.add(result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
                }
            }
            result.add(list);
        }
        return result;
    }

    // 119
    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex+1).get(rowIndex);
    }

    public static void main(String[] args) {
        System.out.println(new Yanghui().generate(5));

        System.out.println(new Yanghui().getRow(3));
    }

}
