package com.learn.leetcode.medium;


import com.learn.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 不会做 五颗星
 *
 * @author hzliuzhujie
 * @date 2021-08-10
 **/
public class WidthOfBinaryTree {

    class AnnotatedNode {
        TreeNode node;
        int depth, pos;

        AnnotatedNode(TreeNode n, int d, int p) {
            node = n;
            depth = d;
            pos = p;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.add(new AnnotatedNode(root, 0, 0));
        int curDepth = 0, left = 0, ans = 0;
        while (!queue.isEmpty()) {
            AnnotatedNode a = queue.poll();
            if (a.node != null) {
                System.out.println(a.node.left + " " + (a.depth + 1) + " " + (a.pos * 2));
                System.out.println(a.node.right + " " + (a.depth + 1) + " " + (a.pos * 2 + 1));
                queue.add(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
                queue.add(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
                // 每次升级都重新计算最左边的位置
                if (curDepth != a.depth) {
                    System.out.println(curDepth + " " + a.depth + " " + a.pos);
                    curDepth = a.depth;
                    left = a.pos;

                }
                System.out.println("pp:" + (a.pos - left + 1));
                ans = Math.max(ans, a.pos - left + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        /**
         *           1
         *          / \
         *         3   2
         *        /     \
         *       5       9
         *      /         \
         *     6           7
         */
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(5);
        treeNode.left.left.left = new TreeNode(6);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(9);
        treeNode.right.right.right = new TreeNode(7);
        System.out.println(new WidthOfBinaryTree().widthOfBinaryTree(treeNode));

        /**
         *       1
         *        \
         *         3
         *        / \
         *       5   4
         */
        treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);
        System.out.println(new WidthOfBinaryTree().widthOfBinaryTree(treeNode));
    }
}
