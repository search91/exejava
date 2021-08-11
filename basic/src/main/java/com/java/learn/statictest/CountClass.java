package com.java.learn.statictest;

public class CountClass {
    private int count = 0;
    private static int staticCount = 0;

    public int getCount() {
        return count;
    }

    public static int getStaticCount() {
        return staticCount;
    }

    public void addCount() {
        count++;
    }
    
    public void addStaticCount() {
        staticCount++;
    }

}
