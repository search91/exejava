package com.java.learn.lock;

/**
 * 不可重入锁
 *
 * @author hzliuzhujie
 * @date 2021-07-21
 **/
public class UnReentraLock {
    private boolean isLocked = false;

    public synchronized void lock()
            throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
