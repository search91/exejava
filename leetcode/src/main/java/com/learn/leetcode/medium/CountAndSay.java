package com.learn.leetcode.medium;

import org.junit.Assert;

/**
 * 38. 外观数列
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *
 * @author hzliuzhujie
 * @date 2021-06-22
 **/
public class CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 1;
        String s = "1";
        while (i < n) {
            int lianxu = 1;
            char lastCha = s.charAt(0);
            StringBuilder tmps = new StringBuilder();
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) != lastCha) {
                    tmps.append(lianxu).append(lastCha);
                    lastCha = s.charAt(j);
                    lianxu = 1;
                } else {
                    lianxu++;
                }
            }
            s = tmps.append(lianxu).append(lastCha).toString();
            i++;
        }
        return s;
    }

    public static void main(String[] args) {
        Assert.assertEquals("1", new CountAndSay().countAndSay(1));
        Assert.assertEquals("11", new CountAndSay().countAndSay(2));
        Assert.assertEquals("21", new CountAndSay().countAndSay(3));
        Assert.assertEquals("13112221", new CountAndSay().countAndSay(7));
        Assert.assertEquals("1113213211", new CountAndSay().countAndSay(8));
    }
}
