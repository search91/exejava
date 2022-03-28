package com.java.learn.multithread;

import lombok.SneakyThrows;

/**
 * @author hzliuzhujie
 * @date 2022-01-25
 **/
public class ABCThread extends Thread {

    private final Thread thread;
    private final String zimu;

    public ABCThread(Thread thread, String zimu) {
        this.thread = thread;
        this.zimu = zimu;
    }

    @SneakyThrows
    @Override
    public void run() {
            if (thread != null) {
                thread.join();
            }
            System.out.println(zimu);
    }

    public static void main(String[] args) {
        while (true) {
        ABCThread thread1 = new ABCThread(null, "A");
        ABCThread thread2 = new ABCThread(thread1, "B");
        ABCThread thread3 = new ABCThread(thread2, "C");

            thread1.start();
            thread2.start();
            thread3.start();
        }


    }
}
