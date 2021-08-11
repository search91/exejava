package com.learn.leetcode.easy;


/*
67. Add Binary
 * Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */
public class AddBinary {

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int alen = a.length();
        int blen = b.length();
        int maxlen = alen < blen ? blen : alen;
        int add = 0;
        for (int i = 1; i <= maxlen; i++) {
            int n = 0, m = 0;
            if (alen - i >= 0) {
                n = a.charAt(alen - i) - '0';
            }
            if (blen - i >= 0) {
                m = b.charAt(blen - i) - '0';
            }
            sb.append((n + m + add) % 2);
            add = (n + m + add) / 2;
        }
        if (add != 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "111";
        String b = "1";
        System.out.println(addBinary(a, b));
    }
}
