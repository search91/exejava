package com.learn.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 137. 只出现一次的数字 II
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * @author hzliuzhujie
 * @date 2021-03-30
 **/
public class SingleNumber {

    // 8ms
    public int singleNumber(int[] nums) {
        int total = 0;
        int doubleTotal = 0;
        Set<Integer> collect = new HashSet<>();
        for (int tmp : nums) {
            total += tmp;
            if (collect.add(tmp)) {
                doubleTotal += tmp;
            }
        }
        return doubleTotal * 2 - total;
    }

    /**
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * 任何数于0异或为任何数 0 ^ n => n
     * 相同的数异或为0: n ^ n => 0
     * var a = [2,3,2,4,4]
     * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
     */
    // 1ms
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
     * 创建算法使得3次的能消除，类似2次的亦或
     * @param nums
     * @return
     */
    public int singleNumberII(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumber.singleNumber(nums));
    }
}
