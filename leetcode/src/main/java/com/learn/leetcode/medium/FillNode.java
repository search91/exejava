package com.learn.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针 （完全二叉树）
 *
 * 117. 填充每个节点的下一个右侧节点指针 II （非完全二叉树）
 *
 * 3星 感觉不难，需要细心
 * 
 * @author hzliuzhujie
 * @date 2021-09-02
 **/
public class FillNode {

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        public Node() {}

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    // 116. 完全二叉树填充每个节点的下一个右侧节点指针
    // 思路是一层层往下连接next
    public Node connect(Node root) {
        Node current = root, parent = root;
        while (current != null) {
            current = current.left;
            if (current == null) {
                break;
            }
            Node current1 = current;
            Node parent1 = parent;
            int i = 0;
            while (parent1 != null) {
                if (i != 0) {
                    current1.next = parent1.left;
                    current1 = current1.next;
                }
                current1.next = parent1.right;
                current1 = current1.next;
                i++;
                parent1 = parent1.next;
            }
            current1.next = null;
            parent = current;
        }
        return root;
    }

    /* 117. 非完全二叉树填充每个节点的下一个右侧节点指针
    感觉比116难，要考虑左子树为空的情况
    */
    public Node connect2(Node root) {
        Node current = root, parent = root;
        while (current != null) {
            Node current2 = current;
            while (parent != null) {
                if (parent.left != null) {
                    current = parent.left;
                    break;
                }
                if (parent.right != null) {
                    current = parent.right;
                    break;
                }
                parent = parent.next;
            }
            if (current==current2) {
                break;
            }
            Node current1 = current;
            Node parent1 = parent;
            int i = 0;
            while (parent1 != null) {
                if (i != 0 && parent1.left != null) {
                    current1.next = parent1.left;
                    current1 = current1.next;
                }
                if (parent1.right != null) {
                    current1.next = parent1.right;
                    current1 = current1.next;
                }
                i++;
                parent1 = parent1.next;
            }
            current1.next = null;
            parent = current;
        }
        return root;
    }

    // 117. 官方解答-解法：层次遍历  自己的解是解法二
    public Node connect22(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node2 = new Node(node4, node5, 2);

        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node3 = new Node(node6, node7, 3);

        Node node1 = new Node(node2, node3, 1);
        Node res = new FillNode().connect(node1);

        /**
         *         1
         *       /  \
         *     2     3
         *   /  \     \
         * 4     5     7
         */
/*
        node3 = new Node(null, node7, 3);
        node1 = new Node(node2, node3, 1);
        Node res2 = new FillNode().connect2(node1);
        System.out.println(res2);
*/


        /**
         *         3
         *       /  \
         *     9     20
         *         /  \
         *       15    7
         */
        Node node15 = new Node(15);
        node7 = new Node(7);
        Node node20 = new Node(node15, node7, 20);
        Node node9 = new Node(9);
        node3 = new Node(node9, node20, 3);

        Node res3 = new FillNode().connect2(node3);
        System.out.println(res3);
    }
}
