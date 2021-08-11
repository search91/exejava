package com.learn.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 * <p>
 * Deque last  first 需要学习下
 *
 * @author hzliuzhujie
 * @date 2021-03-11
 **/
public class Calculate2 {

    public int calculate(String s) {
        Deque<Integer> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        char symbol = '+';
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length || sb.length() > 0; i++) {
            char c = '+';
            if (i < chars.length) {
                c = chars[i];
            }
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (c != ' ') {
                // 运算符
                switch (symbol) {
                    case '+':
                        deque.push(Integer.parseInt(sb.toString()));
                        break;
                    case '-':
                        deque.push(-Integer.parseInt(sb.toString()));
                        break;
                    case '*':
                        int left = deque.pollFirst();
                        int right = Integer.parseInt(sb.toString());
                        deque.push(left * right);
                        break;
                    case '/':
                        left = deque.pollFirst();
                        right = Integer.parseInt(sb.toString());
                        deque.push(left / right);
                        break;
                    default:
                        break;
                }
                sb = new StringBuilder();
                symbol = c;
            }
        }
        int sum = 0;
        for (Integer x : deque) {
            sum = sum + x;
        }
        return sum;
    }

    public static void main(String[] args) {
        Calculate2 calculate2 = new Calculate2();
        // 27
        System.out.println(calculate2.calculate("3+ 4*2*3"));

        // 1
        System.out.println(calculate2.calculate(" 3/2 "));

        // 5
        System.out.println(calculate2.calculate(" 3+2 "));

        // 12
        System.out.println(calculate2.calculate(" 3* 4 "));

        // 1
        System.out.println(calculate2.calculate("1-1+1"));

        // -1
        System.out.println(calculate2.calculate("4/3-2"));

        // 3
        System.out.println(calculate2.calculate("4/3+2"));

        // 5
        System.out.println(calculate2.calculate(" 3+5 / 2 "));

        // 10
        System.out.println(calculate2.calculate(" 6+3 / 3/1*3 +1"));
    }

}


