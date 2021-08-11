package com.learn.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 12. 整数转罗马数字
 * 13. 罗马转数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V(5) 和 X(10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M 1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * <p>
 * 输入:3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "IV"
 * <p>
 * 输入: 9
 * 输出: "IX"
 * 示例 :
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 提示：
 * <p>
 * 1 <= num <= 3999
 * <p>
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 *
 * @author hzliuzhujie
 * @date 2021-03-03
 **/
public class IntToRoman {

    static public Map<Integer, String> convertMap = new LinkedHashMap<>();

    static {
        convertMap.put(1000, "M");
        convertMap.put(900, "CM");
        convertMap.put(500, "D");
        convertMap.put(400, "CD");
        convertMap.put(100, "C");
        convertMap.put(90, "XC");
        convertMap.put(50, "L");
        convertMap.put(40, "XL");
        convertMap.put(10, "X");
        convertMap.put(9, "IX");
        convertMap.put(5, "V");
        convertMap.put(4, "IV");
        convertMap.put(1, "I");
    }

    static public Map<String, Integer> convert2Map = new LinkedHashMap<>();

    static {
        convert2Map.put("M", 1000);
        convert2Map.put("CM", 900);
        convert2Map.put("D", 500);
        convert2Map.put("CD", 400);
        convert2Map.put("C", 100);
        convert2Map.put("XC", 90);
        convert2Map.put("L", 50);
        convert2Map.put("XL", 40);
        convert2Map.put("X", 10);
        convert2Map.put("IX", 9);
        convert2Map.put("V", 5);
        convert2Map.put("IV", 4);
        convert2Map.put("I", 1);
    }

    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int base : convertMap.keySet()) {
            int single = num / base;
            if (single >= 1) {
                stringBuilder.append(convertSingle(single, convertMap.get(base)));
                num = num % base;
            }
        }
        return stringBuilder.toString();
    }

    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 2 <= s.length()) {
                Integer num = convert2Map.get(s.substring(i, i + 2));
                if (num != null) {
                    result += num;
                    i = i + 2;
                } else {
                    result += convert2Map.get(s.substring(i, i + 1));
                    i = i + 1;
                }
            } else {
                result += convert2Map.get(s.substring(i, i + 1));
                i = i + 1;
            }
        }
        return result;
    }

    public String convertSingle(int num, String base) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(base);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
/*
        // MCMXCIV
        System.out.println(intToRoman.intToRoman(1994));

        System.out.println(intToRoman.intToRoman(2994));

        // LVIII
        System.out.println(intToRoman.intToRoman(58));
        // IX
        System.out.println(intToRoman.intToRoman(9));
        // IV
        System.out.println(intToRoman.intToRoman(4));
        // III
        System.out.println(intToRoman.intToRoman(3));
*/

        System.out.println(intToRoman.romanToInt("III"));
        System.out.println(intToRoman.romanToInt("LVIII"));
        System.out.println(intToRoman.romanToInt("MCMXCIV"));

    }
}
