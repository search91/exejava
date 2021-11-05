package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 * 五星，比较巧妙的题，镜像反射法，每次只改一个数字
 *
 * https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
 *
 * @author hzliuzhujie
 * @date 2021-08-30
 **/
public class GrayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> gray = new ArrayList<Integer>();
        gray.add(0); // 初始化 n = 0 的解
        for (int i = 0; i < n; i++) {
            // 要加的数
            // 1 10 100 1000 ...等于是最前那位置为1
            int add = 1 << i;
            // 倒序遍历，并且加上一个值添加到结果中
            for (int j = gray.size() - 1; j >= 0; j--) {
                gray.add(gray.get(j) + add);
            }
        }
        return gray;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(2));
        System.out.println(new GrayCode().grayCode(5));
    }

}
