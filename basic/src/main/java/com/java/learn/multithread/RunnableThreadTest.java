package com.java.learn.multithread;

/**
 * @author hzliuzhujie
 * @date 2021-07-19
 **/
public class RunnableThreadTest implements Runnable {

    private int i;

    @Override
    public void run() {
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 2) {
                RunnableThreadTest rtt = new RunnableThreadTest();
                new Thread(rtt, "新线程1").start();
                new Thread(rtt, "新线程2").start();
            }
        }
    }
}
