package com.learn.leetcode.easy;

/**
 * 1678. 设计 Goal 解析器
 * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
 * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
 *
 * @author hzliuzhujie
 * @date 2021-03-21
 **/
public class InterpretGoal {
    public String interpret(String command) {
        char[] chars = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case 'G':
                    sb.append("G");
                    break;
                case '(':
                    if (chars[i + 1] == ')') {
                        sb.append("o");
                        i++;
                    } else {
                        sb.append("al");
                        i = i + 3;
                    }
                    break;
                default:
            }
        }
        return sb.toString();
   /*     command = command.replaceAll("\\(\\)", "o");
        command = command.replaceAll("\\(al\\)", "al");
        return command;*/
    }

    public static void main(String[] args) {
        System.out.println(new InterpretGoal().interpret("G()(al)"));
        System.out.println(new InterpretGoal().interpret("G()()()()(al)"));
        System.out.println(new InterpretGoal().interpret("(al)G(al)()()G"));
    }
}
