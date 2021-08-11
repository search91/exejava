package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import java.util.Deque;
import java.util.LinkedList;

import static com.learn.leetcode.ListNode.printListNode;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * @author hzliuzhujie
 * @date 2021-03-18
 **/
public class ReverseBetweenLink {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode start = head;
        int i = 1;
        Deque<Integer> deque = new LinkedList<>();
        ListNode breakNode = new ListNode(0);
        breakNode.next = head;
        while (head != null) {
            int val = head.val;
            if (i >= left && i <= right) {
                deque.push(val);
            } else {
                if (i == left - 1) {
                    breakNode = head;
                }
                if (i == right + 1) {
                    int j = 0;
                    while (!deque.isEmpty()) {
                        j++;
                        ListNode newNode = new ListNode(deque.pollFirst());
                        if (left == 1 && j == 1) {
                            start = newNode;
                        }
                        breakNode.next = newNode;
                        breakNode = breakNode.next;
                    }
                    breakNode.next = head;
                }
            }
            head = head.next;
            i++;
        }
        int j = 0;
        if (!deque.isEmpty()) {
            while (!deque.isEmpty()) {
                j++;
                ListNode newNode = new ListNode(deque.pollFirst());
                if (left == 1 && j == 1) {
                    start = newNode;
                }
                breakNode.next = newNode;
                breakNode = breakNode.next;
            }
            breakNode.next = head;
        }
        return start;
    }

    public static void main(String[] args) {
        ReverseBetweenLink reverseBetweenLink = new ReverseBetweenLink();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode newListNode = reverseBetweenLink.reverseBetween(listNode, 2, 4);
        printListNode(newListNode);

        listNode = new ListNode(5);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
        newListNode = reverseBetweenLink.reverseBetween(listNode, 1, 2);
        printListNode(newListNode);
    }

}
