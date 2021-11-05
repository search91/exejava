package com.java.learn.multithread;

import com.java.learn.kk.String;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 写一个死锁例子
 * 
 * @author hzliuzhujie
 * @date 2021-09-27
 **/
public class DeadLock {

    public static Object resourceA = new Object();
    public static Object resourceB = new Object();

    public static final Semaphore a1 = new Semaphore(1);
    public static final Semaphore a2 = new Semaphore(1);

    /**
     * 解决死锁问题的方法是：一种是用synchronized，一种是用Lock显式锁实现。
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new LockA()).start();
        new Thread(new LockB()).start();

        new Thread(new LockAa()).start();
        new Thread(new LockBb()).start();
    }

    static class LockA implements Runnable {
        @Override
        public void run() {
            synchronized (DeadLock.resourceA) {
                System.out.println(new Date().toString() + " LockA 开始执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date().toString() + " LockA 锁住 obj1");
                synchronized (DeadLock.resourceB) {
                    System.out.println(new Date().toString() + " LockA 锁住 obj2");
                }
                System.out.printf("[INFO]: %s has done" + System.lineSeparator(), Thread.currentThread().getName());
            }
        }
    };

    static class LockB implements Runnable {
        @Override
        public void run() {
            synchronized (DeadLock.resourceB) {
                System.out.println(new Date().toString() + " LockB 开始执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date().toString() + " LockB 锁住 obj2");
                synchronized (DeadLock.resourceA) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj1");
                }
                System.out.printf("[INFO]: %s has done" + System.lineSeparator(), Thread.currentThread().getName());
            }
        }
    };

    static class LockAa implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockAa 开始执行");
                while (true) {
                    if (DeadLock.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockAa 锁住 obj1");
                        if (DeadLock.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockAa 锁住 obj2");
                            Thread.sleep(60 * 1000); // do something
                        } else {
                            System.out.println(new Date().toString() + "LockAa 锁 obj2 失败");
                        }
                    } else {
                        System.out.println(new Date().toString() + " LockAa 锁 obj1 失败");
                    }
                    DeadLock.a1.release(); // 释放
                    DeadLock.a2.release();
                    Thread.sleep(1000); // 马上进行尝试，现实情况下do something是不确定的
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class LockBb implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockBb 开始执行");
                while (true) {
                    if (DeadLock.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockBb 锁住 obj2");
                        if (DeadLock.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockBb 锁住 obj1");
                            Thread.sleep(60 * 1000); // do something
                        } else {
                            System.out.println(new Date().toString() + "LockBb 锁 obj1 失败");
                        }
                    } else {
                        System.out.println(new Date().toString() + " LockBb 锁 obj2 失败");
                    }
                    DeadLock.a1.release(); // 释放
                    DeadLock.a2.release();
                    Thread.sleep(10 * 1000); // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
