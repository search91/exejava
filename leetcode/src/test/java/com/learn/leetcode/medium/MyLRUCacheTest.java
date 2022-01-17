package com.learn.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hzliuzhujie
 * @date 2022-01-08
 **/
public class MyLRUCacheTest {

    @Test
    public void get() {}

    @Test
    public void put() {
        MyLRUCache myLruCache = new MyLRUCache(2);
        myLruCache.put(1, 11);
        myLruCache.put(2, 22);
        System.out.println(myLruCache.getList());
        System.out.println(myLruCache.get(1));
        System.out.println(myLruCache.get(2));
        myLruCache.put(3, 33);
        System.out.println(myLruCache.getList());
        System.out.println(myLruCache.get(1));
        System.out.println(myLruCache.get(2));
        System.out.println(myLruCache.get(3));
        myLruCache.put(4, 44);
        System.out.println(myLruCache.get(4));
    }
}