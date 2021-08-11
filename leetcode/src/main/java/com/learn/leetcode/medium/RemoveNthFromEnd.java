package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import java.util.List;
import java.util.Stack;

import static com.learn.leetcode.ListNode.printListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * @author hzliuzhujie
 * @date 2021-04-02
 **/
public class RemoveNthFromEnd {

    // 1 ms 提交中击败了 18.84%
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode start = new ListNode(1);
        start.next = head;
        head = start;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode current = null;
        for (int i = 0; i <= n; i++) {
            current = stack.pop();
        }
        if (current.next != null) {
            current.next = current.next.next;
        } else {
            current.next = null;
        }
        return start.next;
    }

    public static void main(String[] args) {
        //  输入：head = [1, 2, 3, 4, 5],n = 2
        //        输出：[1, 2, 3, 5]

        // head = [1],n = 1
        //        输出：[]

        //  输入：head = [1, 2],n = 1
        //        输出：[1]
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode result = removeNthFromEnd.removeNthFromEnd(listNode, 2);
        printListNode(result);

        listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        result = removeNthFromEnd.removeNthFromEnd(listNode, 1);
        printListNode(result);

        listNode = new ListNode(1);
        result = removeNthFromEnd.removeNthFromEnd(listNode, 1);
        printListNode(result);
    }


}
