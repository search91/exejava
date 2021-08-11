package com.learn.leetcode.medium;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 很简单
 *
 * @author hzliuzhujie
 * @date 2021-08-06
 **/
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))) {
                stack.push(Integer.parseInt(token));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                switch (token) {
                    case "+":
                        a = a + b;
                        break;
                    case "-":
                        a = b - a;
                        break;
                    case "*":
                        a = a * b;
                        break;
                    default:
                        a = b / a;
                        break;
                }
                stack.push(a);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        // 9
        System.out.println(new EvalRPN().evalRPN(new String[]{"2", "1", "+", "3", "*"}));

        // 9
        System.out.println(new EvalRPN().evalRPN(new String[]{"4", "3", "-"}));

        // 6
        System.out.println(new EvalRPN().evalRPN(new String[]{"4", "13", "5", "/", "+"}));

        // 22
        System.out.println(new EvalRPN().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));


    }
}
