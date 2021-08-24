package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

/**
 * 61. 旋转链表 你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
 *
 * @author hzliuzhujie
 * @date 2021-08-11
 *
 * 感觉比较简单,注意特殊用例
 **/
public class RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        ListNode root = head, current = null, pre = null;
        if (k == 0) {
            return root;
        }
        while (head != null) {
            size++;
            head = head.next;
        }
        if (size == 0) {
            return root;
        }
        k = k % size;
        if (k == 0) {
            return root;
        }
        head = root;
        int i = 0;
        while (head.next != null && k < size) {
            pre = head;
            head = head.next;
            i++;
            if (i == size - k) {
                current = head;
                pre.next = null;
            }
        }
        head.next = root;
        return current;
    }

    public static void main(String[] args) {
        /**
         * 输入：head = [1,2,3,4,5], k = 2 输出：[4,5,1,2,3]
         */
        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {1, 2, 3, 4, 5}), 2));

        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {1, 2, 3, 4, 5}), 5));

        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {1, 2, 3, 4, 5}), 6));

        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {1, 2, 3, 4, 5}), 1));

        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {1, 2}), 1));

        ListNode.printListNode(new RotateRight().rotateRight(ListNode.buildListNode(new int[] {}), 1));
    }

}
