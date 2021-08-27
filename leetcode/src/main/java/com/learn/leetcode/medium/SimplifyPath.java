package com.learn.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 71. 简化路径 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式： 始终以斜杠 '/' 开头。 两个目录名之间必须只有一个斜杠 '/' 。 最后一个目录名（如果存在）不能 以 '/' 结尾。 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含
 * '.' 或 '..'）。 返回简化后得到的 规范路径 。
 *
 * 示例1：
 * 
 * 输入：path="/home/"输出："/home" 解释：注意，最后一个目录名后面没有斜杠。
 * 
 * 示例 2：
 *
 * 输入：path="/../"输出："/" 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * 
 * 示例 3：
 *
 * 输入：path="/home//foo/" 输出："/home/foo"解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 
 * 示例 4：
 *
 * 输入：path="/a/./b/../../c/" 输出："/c"
 *
 * 简单，堆栈的思想，最后串起来/
 * 注意下deque的用法，不要用offer poll，会有歧义
 * 将元素添加到队尾或队首：addLast()/offerLast()/addFirst()/offerFirst()；
 *
 * 从队首／队尾获取元素并删除：removeFirst()/pollFirst()/removeLast()/pollLast()；
 *
 * 从队首／队尾获取元素但不删除：getFirst()/peekFirst()/getLast()/peekLast()；
 *
 * 
 * @author hzliuzhujie
 * @date 2021-08-25
 **/
public class SimplifyPath {

    public String simplifyPath(String path) {
        Deque<String> deque = new LinkedList<>();
        for (String single : path.split("/")) {
            single = single.trim();
            if (single.length() == 0 || single.equals(".")) {
                continue;
            }
            if (single.equals("..")) {
                deque.pollLast();
            } else {
                deque.offerLast(single);
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/home/"));
        System.out.println(new SimplifyPath().simplifyPath("/../"));
        System.out.println(new SimplifyPath().simplifyPath("/home//foo/"));
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/"));
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/d/./e/../"));
    }

}
