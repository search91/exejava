package com.learn.leetcode.easy;

import com.learn.leetcode.ListNode;

/**
 * 建信01. 间隔删除链表结点
 * 
 * @author hzliuzhujie
 * @date 2021-10-29
 **/
public class DeleteListNode {

    public ListNode deleteListNode(ListNode head) {
        int i = 0;
        ListNode head1 = head;
        ListNode pre = head;
        while (head != null) {
            pre = head;
            head = head.next;
            if (i % 2 == 0 && head != null && head.next != null) {
                pre.next = head.next;
            } else if (i % 2 == 0 && (head == null || head.next == null)) {
                pre.next = null;
            }
        }
        return head1;
    }

    public static void main(String[] args) {
        ListNode.printListNode(new DeleteListNode().deleteListNode(ListNode.buildListNode(new int[] {1})));
        ListNode.printListNode(new DeleteListNode().deleteListNode(ListNode.buildListNode(new int[] {1, 2})));
        ListNode.printListNode(new DeleteListNode().deleteListNode(ListNode.buildListNode(new int[] {1, 2, 3, 4})));
        ListNode.printListNode(new DeleteListNode().deleteListNode(ListNode.buildListNode(new int[] {1, 2, 3, 4, 5})));
    }
}
