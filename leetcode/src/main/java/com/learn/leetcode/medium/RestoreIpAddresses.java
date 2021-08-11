package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 * @author hzliuzhujie
 * @date 2021-06-28
 **/
public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        Stack<String> stack = new Stack<>();
        stack.push(s);
        int i = 0;
        while (i < 3) {
            i++;
            stack = restoreIpAddresses(stack, i);
        }
        return new ArrayList<>(stack);
    }

    private Stack<String> restoreIpAddresses(Stack<String> stack, int time) {
        Stack<String> res = new Stack<>();
        while (!stack.isEmpty()) {
            String s = stack.pop();
            int start = s.lastIndexOf(".");
            for (int i = 0; i <= 3; i++) {
                if (start + 1 + i + 1 >= s.length()) {
                    continue;
                }
                String pre = s.substring(start + 1, start + 1 + i + 1);
                if (start + 1 + i + 1 >= s.length()) {
                    continue;
                }
                String postfix = s.substring(start + 1 + i + 1);
                if (pre.length() > 3) {
                    continue;
                }
                if (postfix.length() > 3 * (4 - time)) {
                    continue;
                }
                if (pre.length() >= 2 && pre.startsWith("0")) {
                    continue;
                }
                if (Integer.parseInt(pre) <= 255) {
                    if (time == 3) {
                        if (!(postfix.length() <= 3 && Integer.parseInt(postfix) <= 255)) {
                            continue;
                        }
                        if (postfix.length() >= 2 && postfix.startsWith("0")) {
                            continue;
                        }
                    }
                    res.push(s.substring(0, start + 1) + pre + "." + postfix);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        /**
         * 输入：s = "25525511135"
         * 输出：["255.255.11.135","255.255.111.35"]
         */
        System.out.println(restoreIpAddresses.restoreIpAddresses("25525511135"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("0000"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("1111"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("010010"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("101023"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("0279245587303"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("11111111111111111111111111111111111111111111111"));

        System.out.println(restoreIpAddresses.restoreIpAddresses("25525511135"));
    }
}
