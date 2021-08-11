package com.learn.leetcode.hard;

import com.learn.leetcode.ListNode;
import com.learn.leetcode.easy.MergeTwoLists;

import static com.learn.leetcode.ListNode.printListNode;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author hzliuzhujie
 * @date 2021-04-06
 **/
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();
        ListNode listNode1 = new ListNode(-9);
        listNode1.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(7);
        ListNode[] list = new ListNode[]{listNode1, listNode2};
        printListNode(mergeKLists.mergeKLists(list));
    }
}
