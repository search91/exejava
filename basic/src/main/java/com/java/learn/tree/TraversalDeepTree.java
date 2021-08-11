package com.java.learn.tree;

import java.util.LinkedList;

/**
 * @author hzliuzhujie
 * @date 2021-07-29
 **/
public class TraversalDeepTree {

    // 定义节点
    class Node {
        int val;
        TraversalDeepTree.Node left;
        TraversalDeepTree.Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //递归实现1
    public int findDeep(Node root) {
        int deep = 0;

        if (root != null) {
            int lchilddeep = findDeep(root.left);
            int rchilddeep = findDeep(root.right);
            deep = lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;
        }

        return deep;
    }


    //递归实现2
    public int findDeep1(Node root) {
        if (root == null) {
            return 0;
        } else {
            //求左子树的深度
            int lchilddeep = findDeep1(root.left);
            //求右子树的深度
            int rchilddeep = findDeep1(root.left);

            //左子树和右子树深度较大的那个加一等于整个树的深度
            return lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;
        }
    }

    //非递归实现
    public int findDeep2(Node root) {
        if (root == null) {
            return 0;
        }

        Node current = null;
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        int cur, last;
        int level = 0;
        while (!queue.isEmpty()) {
            //记录本层已经遍历的节点个数
            cur = 0;

            //当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
            last = queue.size();

            //当还没有遍历到本层最后一个节点时循环
            while (cur < last) {
                current = queue.poll();//出队一个元素
                cur++;
                //把当前节点的左右节点入队（如果存在的话）
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;//每遍历完一层level+1
        }
        return level;
    }

}
