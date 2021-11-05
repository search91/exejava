package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzliuzhujie
 * @date 2021-09-30
 **/
public class ReorderList {

    /**
     * 143. 重排链表
     *
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     *
     * L0 → L1 → … → Ln-1 → Ln 请将其重新排列后变为：
     *
     * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
     *
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 思路：断开成两个链表；这个没实现，还是用list辅助的方式吧。
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);
        new ReorderList().reorderList(listNode);
        ListNode.printListNode(listNode);
    }

}
