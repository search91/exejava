package com.learn.leetcode;

import java.util.List;

/**
 * @author hzliuzhujie
 * @date 2021-04-02
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildListNode(List<Integer> list) {
        ListNode root = new ListNode(-1);
        ListNode current = root;
        for (int val : list) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return root.next;
    }

    public static ListNode buildListNode(  int[] list) {
        ListNode root = new ListNode(-1);
        ListNode current = root;
        for (int val : list) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return root.next;
    }

    public static void printListNode(ListNode listNode) {
        System.out.println("\n===========");
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
    }
}
