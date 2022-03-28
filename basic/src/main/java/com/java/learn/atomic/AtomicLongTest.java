package com.java.learn.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author hzliuzhujie
 * @date 2022-02-18
 * @description 压测AtomicLong的原子操作性能
 **/
public class AtomicLongTest implements Runnable {

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            atomicLong.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(30);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            es.submit(new AtomicLongTest());
        }
        es.shutdown();
        // 保证任务全部执行完
        while (!es.isTerminated()) {
        }
        long end = System.currentTimeMillis();
        System.out.println("AtomicLong add 耗时=" + (end - start));
        System.out.println("AtomicLong add result=" + atomicLong.get());
    }
}