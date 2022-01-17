package com.learn.leetcode.medium;

import com.learn.leetcode.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hzliuzhujie
 * @date 2021-11-05
 **/
public class BinaryTreeTraversalTest {
    private TreeNode treeNode = null;

    @Before
    public void init() {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        root.left = two;
        root.right = three;
        two.left = four;
        this.treeNode = root;
    }

    @Test
    public void preorderTraversal() {
        System.out.println(new BinaryTreeTraversal().preorderTraversal(treeNode));
    }

    @Test
    public void preorderTraversal2() {
        System.out.println(new BinaryTreeTraversal().preorderTraversal2(treeNode));
    }

    @Test
    public void preorderTraversal3() {
        System.out.println(new BinaryTreeTraversal().preorderTraversal3(treeNode));
    }

    @Test
    public void inorderTraversal() {
        System.out.println(new BinaryTreeTraversal().inorderTraversal(treeNode));
    }

    @Test
    public void inorderTraversal2() {
        System.out.println(new BinaryTreeTraversal().inorderTraversal2(treeNode));
    }

    @Test
    public void inorderTraversal3() {
        System.out.println(new BinaryTreeTraversal().inorderTraversal3(treeNode));
    }

    @Test
    public void postorderTraversal2() {
        System.out.println(new BinaryTreeTraversal().postorderTraversal2(treeNode));
    }

    @Test
    public void postorderTraversal3() {
        System.out.println(new BinaryTreeTraversal().postorderTraversal3(treeNode));
    }

    @Test
    public void levelOrder() {
        System.out.println(new BinaryTreeTraversal().levelOrder(treeNode));
    }
}