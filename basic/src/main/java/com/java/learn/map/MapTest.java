/**
 *
 */
package com.java.learn.map;

import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapTest {
    public static void main(String[] args) {
        // 1.keySet 问题
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        map.put(1, new ArrayList<String>(Arrays.asList("a")));
        map.put(2, new ArrayList<String>(Arrays.asList("b")));
        map.put(3, new ArrayList<String>(Arrays.asList("c")));
        map.put(4, new ArrayList<String>(Arrays.asList("d")));
        Set<Integer> keys1 = map.keySet();
        keys1.remove(1); // keySet()返回此映射中所包含的键的 set 视图，key1-3指向同一个空间。
        // 只能删， 不能增加,删除后map也受影响

        // 2.map 中的add问题
        System.out.println(map);

        List<String> kk = map.get(1);
        if (kk == null) {

            kk = new ArrayList<String>(Arrays.asList("aa")); // kk原来没地址 ，kk指向new地址，必须put
        }
        System.out.println(map);

        List<String> kk2 = map.get(3);
        if (kk2 != null) {
            kk2.add("cc"); // kk2地址没变，不需要put
        }
        System.out.println(map);

        // 3.Set<Integer> -> Integer[] -> int[]
        Set<Integer> sizeSet = new TreeSet<Integer>();
        sizeSet.add(4);
        sizeSet.add(6);
        sizeSet.add(2);
        Integer[] sizes = new Integer[sizeSet.size()];
        sizeSet.toArray(sizes);

        int sizesInt[] = new int[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            sizesInt[i] = sizes[i].intValue();
        }

        // 4. java8
        Map<String, Integer> countMap = new HashMap<>(ImmutableMap.of("a", 1, "b", 2));
        System.out.println("map before:" + countMap);
        countMap.computeIfPresent("z", (key, value) -> value + 1);
        countMap.putIfAbsent("z", 1);

        countMap.computeIfPresent("a", (key, value) -> value + 1);
        countMap.putIfAbsent("a", 1);
        System.out.println("map after:" + countMap);
    }

}
