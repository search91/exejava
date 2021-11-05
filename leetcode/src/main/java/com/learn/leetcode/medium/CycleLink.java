package com.learn.leetcode.medium;

import com.learn.leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hzliuzhujie
 * @date 2021-09-29
 **/
public class CycleLink {

    /**
     * 141. 环形链表
     * 
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            if (fast == slow) {
                return true;
            }
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
        }
        return false;
    }

    /**
     * 142. 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     *
     * 链表中节点的数目范围在范围 [0, 10^4] 内
     * 
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();

        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            if (nodeSet.contains(fast)) {
                break;
            }
            nodeSet.add(fast);
            fast = fast.next;
            if (fast == null) {
                break;
            }
            if (nodeSet.contains(fast)) {
                break;
            }
            nodeSet.add(fast);
            if (fast == slow) {
                break;
            }
            fast = fast.next;
            if (fast == slow) {
                break;
            }
            slow = slow.next;
        }
        if (nodeSet.contains(fast)) {
            return fast;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = listNode.next.next;
        System.out.println(new CycleLink().hasCycle(listNode));

        System.out.println(new CycleLink().detectCycle(listNode).val);
    }
}
