package com.learn.leetcode.medium;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词指字母相同，但排列不同的字符串。
 * 1颗星
 *
 * @author hzliuzhujie
 * @date 2021-08-06
 **/
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            Arrays.sort(tmp);
            if (map.get(Arrays.toString(tmp)) == null) {
                map.put(Arrays.toString(tmp), res.size());
                List<String> list = new ArrayList<>();
                list.add(str);
                res.add(list);
            } else {
                List<String> list = res.get(map.get(Arrays.toString(tmp)));
                list.add(str);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"a"}));
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{""}));
    }
}
