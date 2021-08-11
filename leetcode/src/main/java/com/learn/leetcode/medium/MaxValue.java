package com.learn.leetcode.medium;

/**
 * 5773. 插入后的最大值 显示英文描述
 * 通过的用户数81
 * 尝试过的用户数142
 * 用户总通过次数81
 * 用户总提交次数182
 * 题目难度Medium
 * 给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
 * <p>
 * 你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值 ​​​​​​。但 不能 在负号的左边插入 x 。
 * <p>
 * 例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
 * 如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。
 * 返回插入操作后，用字符串表示的 n 的最大值。
 *
 * @author hzliuzhujie
 * @date 2021-05-30
 **/
public class MaxValue {

    public String maxValue(String n, int x) {
        boolean negi = false;
        if (n.charAt(0) == '-') {
            negi = true;
        }
        int i = 0;
        for (i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                if (x > num && !negi) {
                    break;
                }
                if (x < num && negi) {
                    break;
                }
            }
        }
        return n.substring(0, i) + x + n.substring(i);
    }

    public static void main(String[] args) {

        // 999
        System.out.println(new MaxValue().maxValue("99", 9));
        // -123
        System.out.println(new MaxValue().maxValue("-13", 2));

        // -123
        System.out.println(new MaxValue().maxValue("2142", 2));

        // -1432
        System.out.println(new MaxValue().maxValue("-432", 1));

        // -1323
        System.out.println(new MaxValue().maxValue("-132", 3));


    }
}
