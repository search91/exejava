package com.java.learn.atomic;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author hzliuzhujie
 * @date 2022-02-18
 * @description 压测LongAdder的原子操作性能
 **/
public class LongAdderTest implements Runnable {

    private static LongAdder longAdder = new LongAdder();

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            longAdder.increment();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(30);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            es.submit(new LongAdderTest());
        }
        es.shutdown();
        // 保证任务全部执行完
        while (!es.isTerminated()) {
        }
        long end = System.currentTimeMillis();
        System.out.println("LongAdder add 耗时=" + (end - start));
        System.out.println("LongAdder add result=" + longAdder.sum());
    }
}