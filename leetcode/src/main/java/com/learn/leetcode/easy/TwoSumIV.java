package com.learn.leetcode.easy;

/*
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
 */
public class TwoSumIV {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        if (root.right != null) {
            rightNode = root.right;
            leftNode = root;
        } else {
            rightNode = root;
            leftNode = root.left;
        }
        while (leftNode != null && rightNode != null) {
            if (rightNode.val + leftNode.val == k) {
                return true;
            } else if (rightNode.val + leftNode.val > k) {
                leftNode = leftNode.left;
            } else {
            }
        }
        return false;
    }
}
