/**
 * 
 */
package com.java.learn.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hzliuzhujie
 * 
 */
public class Yield implements Runnable {
	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			exec.execute(new Yield());
		}
		System.out.println(exec.isTerminated());
		System.out.println("finish");
	}

	@Override
	public void run() {
		System.out.println("1");
		Thread.yield();
		System.out.println("2");

	}

}
