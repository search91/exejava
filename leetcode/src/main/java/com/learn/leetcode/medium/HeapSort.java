package com.learn.leetcode.medium;

import java.util.Arrays;

/**
 * 堆排序
 * 
 * @author hzliuzhujie
 * @date 2022-01-17
 **/
public class HeapSort {

    // 从小到大 大顶堆
    public void heapSort(int[] arr) {
        int length = arr.length;

        for (int i = length / 2; i >= 0; i--) {
            toMaxHeap(arr, i, length);
        }

        for (int j = length - 1; j > 0; j--) {
            swap(arr, 0, j);
            toMaxHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组 转化成 大顶堆，父节点都比孩子节点大
     * 
     * 将一个数组 转化成 大顶堆 (根节点一定是比 左右子节点都大的）
     * 
     * 规则是 arr[i].left = arr[2*i+1]

     * arr[i].right = arr[2*i+2]
     *
     * arr[i].super = arr[i/2]
     *
     * @param arr
     *            数组
     * @param pos
     *            与左右子节点判断大小的根节点
     * @param length
     *            数组的总长度
     */
    public void toMaxHeap(int[] arr, int pos, int length) {
        // 自动向下调整
        // 主要循环判断左右子节点，由于不能只判断一层，那么就需要循环。知道当前节点的所有子节点，都比自己小为止
        // 从左节点开始判断（i*2+1），判断完之后，需要以 k 位置为根节点继续判断，即k*2+1。
        // 从左孩子节点开始
        int child = pos * 2 + 1;

        // 需要判断左、右孩子都存在,且右孩子比左孩子大，那么就直接后续用child+1 来计算
        if (child + 1 < length && arr[child + 1] > arr[child]) {
            child++;
        }
        if (child < length && arr[pos] < arr[child]) {
            swap(arr, pos, child);
            toMaxHeap(arr, child, length);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {5, 8, 9, 1};
        HeapSort heapSort = new HeapSort();
        heapSort.toMaxHeap(array, 0, array.length);
        System.out.println(Arrays.toString(array));
        heapSort.heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
