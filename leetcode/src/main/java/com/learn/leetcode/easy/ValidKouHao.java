package com.learn.leetcode.easy;

import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * @author hzliuzhujie
 * @date 2021-03-10
 **/
public class ValidKouHao {

    // 40ms
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        int count = s.length() / 2;
        for (int i = 0; i < count; i++) {
            s = s.replace("()", "");
            if (s.length() == 0) {
                return true;
            }
            s = s.replace("{}", "");
            if (s.length() == 0) {
                return true;
            }
            s = s.replace("[]", "");
            if (s.length() == 0) {
                return true;
            }
        }
        return s.length() == 0;
    }

    // 1ms
    public boolean isValid2(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        int count = s.length() / 2;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char tmp = stack.pop();
                if (c == ')' && tmp != '(') {
                    return false;
                }
                if (c == ']' && tmp != '[') {
                    return false;
                }
                if (c == '}' && tmp != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidKouHao validKouHao = new ValidKouHao();
        System.out.println(validKouHao.isValid("()[]{}"));
        System.out.println(validKouHao.isValid("([)]"));
        System.out.println(validKouHao.isValid("{[]}"));

        System.out.println(validKouHao.isValid2("()[]{}"));
        System.out.println(validKouHao.isValid2("([)]"));
        System.out.println(validKouHao.isValid2("{[]}"));
    }
}
