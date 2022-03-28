package com.java.learn.multithread;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hzliuzhujie
 * @date 2022-02-16
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>(20),
            new ThreadPoolExecutor.DiscardPolicy());

        System.out.println("---- before commit");
        System.out.println("activeCount:" + executor.getActiveCount());
        System.out.println("queueSize:" + executor.getQueue().size());

        System.out.println("---- commit ... ");

        for (int i = 0; i < 60; i++) {
            System.out.println();
            System.out.println("activeCount:" + executor.getActiveCount());
            System.out.println("queueSize:" + executor.getQueue().size());
            executor.execute(() -> {
                int random = new Random().nextInt(5);
                int time = 500 * random;
                try {
                    Thread.sleep(time);
                } catch (Exception e) {

                }
            });
        }

        System.out.println("----- committed");
        while (executor.getActiveCount() > 0) {
            System.out.println();
            System.out.println("activeCount:" + executor.getActiveCount());
            System.out.println("queueSize:" + executor.getQueue().size());
            try {
                Thread.sleep(800);
            } catch (Exception e) {

            }
        }
        System.out.println("---- activeCount is 0 ");
        System.out.println("activeCount:" + executor.getActiveCount());
        System.out.println("queueSize:" + executor.getQueue().size());

        executor.shutdown();
    }
}
