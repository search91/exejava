package com.learn.leetcode.medium;

/**
 * 97 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和 s2 交错组成的。
 * 
 * @author hzliuzhujie
 * @date 2022-01-08
 **/
public class IsInterleave {

    public boolean isInterleave(String s1, String s2, String s3) {
        int size1 = s1.length();
        int size2 = s2.length();
        int size3 = s3.length();
        if (size1 + size2 != size3) {
            return false;
        }
        int i = 0, j = 0;
        char[] s11 = s1.toCharArray();
        char[] s22 = s2.toCharArray();

        return true;
    }
}
