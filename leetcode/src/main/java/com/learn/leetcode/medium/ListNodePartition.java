package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

/**
 * 86. 分隔链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 思路：双指针，一个指向<，一个指向>，分别拆开链表，最后再合拢
 *
 * @author hzliuzhujie
 * @date 2021-08-30
 **/
public class ListNodePartition {

    public ListNode partition(ListNode head, int x) {
        ListNode pre = new ListNode(-1), pre1 = pre, pos = new ListNode(-1), pos1 = pos;
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                pre.next = current;
                pre = pre.next;
            } else {
                pos.next = current;
                pos = pos.next;
            }
            current = current.next;
        }
        pos.next = null;
        pre.next = pos1.next;
        return pre1.next;
    }

    public static void main(String[] args) {
        // [1,2,2,4,3,5]
        ListNode
            .printListNode(new ListNodePartition().partition(ListNode.buildListNode(new int[] {1, 4, 3, 2, 5, 2}), 3));

        // [1,2,2]
        ListNode.printListNode(new ListNodePartition().partition(ListNode.buildListNode(new int[] {1, 2, 2}), 3));

        // [5,6,7]
        ListNode.printListNode(new ListNodePartition().partition(ListNode.buildListNode(new int[] {5, 6, 7}), 3));

        // [1,2]
        ListNode.printListNode(new ListNodePartition().partition(ListNode.buildListNode(new int[] {2, 1}), 2));

    }

}
