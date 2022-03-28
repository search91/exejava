package com.learn.leetcode.medium;

import com.learn.leetcode.TreeNode;

import java.util.*;

/**
 * 二叉树遍历
 * 
 * @author hzliuzhujie
 * @date 2021-11-03
 **/
public class BinaryTreeTraversal {

    // 先序遍历递归版本（深度遍历）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        helper(root, list);
        return list;
    }

    // 中序 和 后续就是调换下 list.add(root.val); 顺序
    public void helper(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) {
            helper(root.left, list);
        }
        if (root.right != null) {
            helper(root.right, list);
        }
    }

    // 中序遍历递归版本
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        helperIn(root, list);
        return list;
    }

    public void helperIn(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            helperIn(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            helperIn(root.right, list);
        }
    }

    // 先序非递归版本1
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return list;
    }

    // 中序非递归版本1
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            while (root.left != null) {
                if (map.containsKey(root.left)) {
                    break;
                }
                stack.push(root.left);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            map.put(root, 1);
            if (root.right != null) {
                stack.push(root.right);
            }

        }
        return list;
    }

    // 后序非递归版本1
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (root.left == null && root.right == null) {// 不含左右结点时，出栈
                root = stack.pop();
                list.add(root.val);
                map.put(root, 1);
            } else if ((root.left != null && root.right == null && map.containsKey(root.left))
                    || (root.right != null && root.left == null && map.containsKey(root.right)) || (root.left != null
                    && root.right != null && map.containsKey(root.left) && map.containsKey(root.right))) {// 包含子节点，但是子节点被访问过，出栈
                root = stack.pop();
                list.add(root.val);
                map.put(root, 1);
            } else {
                while (root.left != null) {
                    if (map.containsKey(root.left)) {
                        break;
                    }
                    stack.push(root.left);
                    root = root.left;
                }
                if (root.right != null) {
                    if (map.containsKey(root.right)) {
                        break;
                    }
                    stack.push(root.right);
                }
            }
        }
        return list;
    }

    // 先序非递归版本2
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                // 插入到头
                stack.push(node);
                list.add(node.val);
                node = node.left;
            } else {
                // 从头取，其实是先进后出
                node = stack.pop();
                node = node.right;
            }
        }
        return list;
    }

    // 中序非递归版本2
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    // 后序非递归版本2
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                list.add(0, node.val);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        return list;
    }

    // 层次遍历 bfs
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int num = 0;
        List<Integer> tempList = new ArrayList<>();
        list.add(tempList);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                list.get(num).add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            } else {
                if (!queue.isEmpty()) {
                    num++;
                    tempList = new ArrayList<>();
                    list.add(tempList);
                    queue.add(null);
                }
            }
        }
        return list;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root==null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        res.add(new ArrayList<>());
        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode take = queue.poll();
            if (take!=null) {
                res.get(level).add(take.val);
                if (take.left != null) {
                    queue.add(take.left);
                }
                if (take.right != null) {
                    queue.add(take.right);
                }
            } else {
                if (!queue.isEmpty()) {
                    // 这一层的结束
                    List<Integer> single1 = new ArrayList<>();
                    res.add(single1);
                    level++;
                    queue.add(null);
                }
            }
        }
        return res;
    }

}
