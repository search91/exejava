package com.learn.leetcode.easy;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 66.加一 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 简单，注意：
 *
 * 翻转语法
 *
 * Collections.reverse(list);
 *
 * list转 array语法
 *
 * list.stream().mapToInt(i->i).toArray();
 *
 * list.toArray(new String[list.size])
 *
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int size = digits.length;
        List<Integer> list = new ArrayList<>();
        int num = 1;
        for (int i = size - 1; i >= 0; i--) {
            int tmp = digits[i] + num;
            list.add(tmp % 10);
            num = tmp / 10;
        }
        Collections.reverse(list);
        if (num != 0) {
            list.add(0, num);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 在原地改
     * 
     * @param digits
     * @return
     */
    public int[] plusOne1(int[] digits) {
        int size = digits.length;
        int num = 1;
        for (int i = size - 1; i >= 0; i--) {
            digits[i] = digits[i] + num;
            num = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (num > 0) {
            int[] newDigit = new int[size + 1];
            newDigit[0] = num;
            System.arraycopy(digits, 0, newDigit, 1, size);
            return newDigit;
        } else {
            return digits;
        }
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[] {1, 2, 4}, new PlusOne().plusOne1(new int[] {1, 2, 3}));
        Assert.assertArrayEquals(new int[] {4, 3, 2, 2}, new PlusOne().plusOne1(new int[] {4, 3, 2, 1}));
        Assert.assertArrayEquals(new int[] {1, 0, 0, 0, 0}, new PlusOne().plusOne1(new int[] {9, 9, 9, 9}));
        Assert.assertArrayEquals(new int[] {1}, new PlusOne().plusOne1(new int[] {0}));
    }
}
