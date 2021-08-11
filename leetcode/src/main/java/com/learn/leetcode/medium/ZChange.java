package com.learn.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. z字变换
 *
 * @author hzliuzhujie
 * @date 2021-02-26
 **/
public class ZChange {

    // 7 ms
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1 || s.length() <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        // 2n - 2 每趟个数
        int eachNum = numRows * 2 - 2;
        // 趟数
        int n = length / eachNum;
        int leave = length % eachNum;
        if (leave != 0) {
            n++;
        }

        //  每趟个数:2n-2；斜坡:2n-2-i 且 >= numRows 趟数：j
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < n; j++) {
                int index = i + j * eachNum;
                if (index >= length) {
                    continue;
                }
                sb.append(s.charAt(index));
                if (i != 0 && i != numRows - 1) {
                    index = (j + 1) * eachNum - i;
                    if (index < numRows || index >= length) {
                        continue;
                    }
                    sb.append(s.charAt(index));
                }
            }
        }
        return sb.toString();
    }

     // 以前做的 3ms
    public static String convert2(String s, int numRows) {
        List<StringBuilder> numList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            numList.add(new StringBuilder());
        }
        StringBuilder sb = new StringBuilder();

        if (s == null || s.length() <= numRows || numRows <= 1) {
            return s;
        }

        int flag = numRows * 2 - 2;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int mod = i % flag;
            if (mod < numRows) { // 锯齿中竖的部分
                numList.get(mod).append(c);
            } else { // 锯齿中斜的部分,需要倒序
                numList.get(flag - mod).append(c);
            }
        }

        for (StringBuilder num : numList) {
            sb.append(num);
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        /**
         *  输入：s = "PAYPALISHIRING", numRows = 3
         *  输出："PAHNAPLSIIGYIR"
         */
        ZChange zChange = new ZChange();
        // PAHNAPLSIIGYIR
        System.out.println(zChange.convert("PAYPALISHIRING", 3));

        // PINALSIGYAHRPI
        System.out.println(zChange.convert("PAYPALISHIRING", 4));

        // AB
        System.out.println(zChange.convert("AB", 2));

        // "ACBD"
        System.out.println(zChange.convert("ABCD", 2));

        // "ACB"
        System.out.println(zChange.convert("ABC", 2));

    }

}
