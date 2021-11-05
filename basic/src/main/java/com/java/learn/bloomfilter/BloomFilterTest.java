package com.java.learn.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;

/**
 * @author hzliuzhujie
 * @date 2021-10-09
 **/
public class BloomFilterTest {

    public static long used() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return (total - free);
    }

    public static void main(String[] args) {
        long k = used();
        HashSet<String> nameHash = new HashSet<>(10);
        for (int i = 0; i < 20000000; i++) {
            nameHash.add("aaaaa" + i + "@uu.com");
        }
        long m = used();
        System.out.println(m - k);
        k = m;
        BloomFilter<String> nameFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 1000000, 0.01);
        for (int i = 0; i < 20000000; i++) {
            nameFilter.put("aaaaa" + i + "@uu.com");
        }
        m = used();
        System.out.println(m - k);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            nameHash.contains("aaaaa" + i + "@uu.com");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            boolean filterRes = nameFilter.mightContain("aaaaa" + i + "@uu.com");
            /* if (filterRes != nameHash.contains("aaaaa" + i + "@uu.com")) {
                System.out.println("aaaaa" + i + "@uu.com");
            }*/
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
