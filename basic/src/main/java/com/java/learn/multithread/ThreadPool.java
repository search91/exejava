package com.java.learn.multithread;
import java.util.Vector;
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
  
public class ThreadPool{   
    public static void main(String[] args){
    	ExecutorService executorService = Executors.newCachedThreadPool();   
    	//ExecutorService executorService = Executors.newFixedThreadPool(3);
    	//ExecutorService executorService = Executors.newSingleThreadExecutor();  
      Vector<Thread> threads = new Vector<Thread>();

        for (int i = 0; i < 10; i++){  
        	TestRunnable testRunnable = new TestRunnable(i);
            executorService.execute(testRunnable);   
            System.out.println("************* a" + i + " *************");  
            threads.add(testRunnable);
        } 
        System.out.println(999999);
//        for (Thread iThread : threads) {
//            try {
//				iThread.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}// 等待所有线程执行完毕
//        }
        System.out.println(888);
        executorService.shutdown();   
    }   
}   
  
class TestRunnable extends Thread {   
	private int num;
	public TestRunnable(int num) {
		this.num = num;
	}
    public void run(){  
    	long l = (long) (Math.random()*5000);
    	Thread.currentThread().isDaemon();
        System.out.println(Thread.currentThread().getName() + "线程被调用了。"+"[long]"+l+"[num]"+num);   
    	try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   
}  