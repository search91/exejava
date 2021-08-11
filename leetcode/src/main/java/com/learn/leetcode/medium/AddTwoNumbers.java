package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

/**
 * 2. 两数相加
 *
 * @author hzliuzhujie
 * @date 2021-03-01
 **/
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int c = 0;
        int more = 0;
        while (l1 != null || l2 != null || c != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + c;
            c = sum / 10;
            more = sum % 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

            ListNode sumNode = new ListNode(more);
            cursor.next = sumNode;
            cursor = sumNode;
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        // 708
        ListNode newListNode = new AddTwoNumbers().addTwoNumbers(l1, l2);
        ListNode.printListNode(newListNode);

        l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(9);

        l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(9);
        // 7 0 4 0 1
        newListNode = new AddTwoNumbers().addTwoNumbers(l1, l2);
        ListNode.printListNode(newListNode);
    }

}
