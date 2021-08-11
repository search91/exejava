package com.learn.leetcode.easy;

import com.learn.leetcode.ListNode;

import static com.learn.leetcode.ListNode.printListNode;

/**
 * 21. 合并两个有序链表
 *
 * @author hzliuzhujie
 * @date 2021-04-02
 **/
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode start = listNode;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 > v2) {
                listNode.next = new ListNode(v2);
                l2 = l2.next;
            } else {
                listNode.next = new ListNode(v1);
                l1 = l1.next;
            }
            listNode = listNode.next;
        }
        if (l1 != null) {
            listNode.next = l1;
        } else if (l2 != null) {
            listNode.next = l2;
        }
        return start.next;
    }

    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode listNode1 = new ListNode(-9);
        listNode1.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(7);
        printListNode(mergeTwoLists.mergeTwoLists(listNode1, listNode2));
    }
}
