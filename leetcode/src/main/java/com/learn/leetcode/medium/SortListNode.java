package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 148. 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(nlog n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 感觉是快排？ 冒泡会超时，没做出
 *
 * @author hzliuzhujie
 * @date 2021-09-30
 **/
public class SortListNode {

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).val > list.get(j).val) {
                    ListNode tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (i + 1 <= list.size() - 1) {
                list.get(i).next = list.get(i + 1);
            } else {
                list.get(i).next = null;
            }
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        ListNode current = new SortListNode().sortList(ListNode.buildListNode(new int[] {1, 3, 5, 4, -1}));
        ListNode.printListNode(current);
    }
}
