package com.learn.leetcode.medium;

import java.util.*;

/**
 * @author hzliuzhujie
 * @date 2021-03-31
 **/
public class LetterCombinations {

    static Map<Character, String[]> letter2Num = new HashMap<>();

    static {
        letter2Num.put('2', new String[]{"a", "b", "c"});
        letter2Num.put('3', new String[]{"d", "e", "f"});
        letter2Num.put('4', new String[]{"g", "h", "i"});
        letter2Num.put('5', new String[]{"j", "k", "l"});
        letter2Num.put('6', new String[]{"m", "n", "o"});
        letter2Num.put('7', new String[]{"p", "q", "r", "s"});
        letter2Num.put('8', new String[]{"t", "u", "v"});
        letter2Num.put('9', new String[]{"w", "x", "y", "z"});

    }

    public List<String> letterCombinations(String digits) {
        List<String> newList = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            String[] letters = letter2Num.get(c);
            if (newList.isEmpty()) {
                newList = Arrays.asList(letters.clone());
            } else {
                newList = pushLetter(letters, newList);
            }
        }
        return newList;
    }

    List<String> pushLetter(String[] letters, List<String> old) {
        List<String> newList = new ArrayList<>();
        for (String str : old) {
            for (String c : letters) {
                newList.add(str + c);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        // []
        System.out.println(new LetterCombinations().letterCombinations(""));
        // 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(new LetterCombinations().letterCombinations("23"));

        System.out.println(new LetterCombinations().letterCombinations("234"));
    }
}
