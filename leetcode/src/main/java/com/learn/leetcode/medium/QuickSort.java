package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 快排 时间复杂度 O(log2N) 最坏情况O(N2) 空间复杂度O(log2n)
 * 
 * @author hzliuzhujie
 * @date 2021-11-01
 **/
public class QuickSort {

    public void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pos = partition(array, left, right);
            quickSort(array, left, pos - 1);
            quickSort(array, pos + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int first = array[left];
        int originLeft = left;
        while (left < right) {
            while (left < right && array[right] >= first) {
                right--;
            }

            while (left < right && array[left] <= first) {
                left++;
            }

            if (left < right) {
                swap(array, left, right);
            }
        }
        array[originLeft] = array[left];
        array[left] = first;
        return left;
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) {
        int[] test = new int[] {10, 6, 7, 2, 3};
        new QuickSort().quickSort(test, 0, 4);
        System.out.println(Arrays.toString(test));
    }
}
