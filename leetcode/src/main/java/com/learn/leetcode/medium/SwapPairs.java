package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import static com.learn.leetcode.ListNode.printListNode;

/**
 * 24. 两两交换链表中的节点
 * （还是简单的）
 *
 * @author hzliuzhujie
 * @date 2021-04-07
 **/
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null && pre.next.next != null) {
            ListNode current = pre.next;
            ListNode next = current.next;
            current.next = next.next;
            next.next = current;
            pre.next = next;
            pre = current;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        SwapPairs swapPairs = new SwapPairs();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        printListNode(swapPairs.swapPairs(listNode));
    }

}
