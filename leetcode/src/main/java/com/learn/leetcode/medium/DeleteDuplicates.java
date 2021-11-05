package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

/**
 *
 * 83. 删除排序链表中的重复元素
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 *
 *
 *
 * 82. 删除排序链表中的重复元素 II
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 * 
 * @author hzliuzhujie
 * @date 2021-08-30
 **/
public class DeleteDuplicates {

    public ListNode deleteDuplicates82(ListNode head) {
        ListNode start = new ListNode(1);
        start.next = head;
        ListNode current = head;
        ListNode pre = start;
        while (current != null) {
            ListNode next = current.next;
            boolean repli = false;
            while (next != null && next.val == current.val) {
                repli = true;
                next = next.next;
            }
            if (repli) {
                pre.next = next;
            } else {
                pre = current;
            }
            current = next;
        }
        return start.next;
    }

    public ListNode deleteDuplicates83(ListNode head) {
        ListNode start = head, current = head;
        while (current != null) {
            ListNode next = current.next;
            while (next != null && next.val == current.val) {
                next = next.next;
            }
            current.next = next;
            current = current.next;
        }
        return start;
    }

    public static void main(String[] args) {
        /*
        // []
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates83(ListNode.buildListNode(new int[] {})));
        // [1]
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates83(ListNode.buildListNode(new int[] {1})));
        // [1,2]
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates83(ListNode.buildListNode(new int[] {1, 1, 2})));
        // [1,2,3]
        ListNode.printListNode(
            new DeleteDuplicates().deleteDuplicates83(ListNode.buildListNode(new int[] {1, 1, 2, 3, 3})));
        // [1]
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates83(ListNode.buildListNode(new int[] {1, 1})));
        */

       /* // []
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates82(ListNode.buildListNode(new int[] {1, 1, 1})));

        // [1]
        ListNode.printListNode(new DeleteDuplicates().deleteDuplicates82(ListNode.buildListNode(new int[] {1})));

        // [1,3]
        ListNode
            .printListNode(new DeleteDuplicates().deleteDuplicates82(ListNode.buildListNode(new int[] {1, 2, 2, 3})));*/

        // [1,2,5]
        ListNode.printListNode(
            new DeleteDuplicates().deleteDuplicates82(ListNode.buildListNode(new int[] {1, 2, 3, 3, 4, 4, 5})));
    }

}
