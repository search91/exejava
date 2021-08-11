package com.java.learn.blockQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueue {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BlockQueue.class);
	private final static int QUEUE_SIZE  = 1000000;
	private final static int CHECK_THREADS = 1000;
	
	
	 public static void main(String[] args) {
	        	String inputPath="D:\\daily_new_domain";
	            String outputPath="D:\\daily_new_domain_record";

	            BlockingQueue<String> checkQueue = new ArrayBlockingQueue<String>(QUEUE_SIZE);
	        	BlockingQueue<String> writeQueue = new ArrayBlockingQueue<String>(QUEUE_SIZE);

	            try {
	            	Thread readhread = new Thread(new Reader(checkQueue,inputPath));
	            	readhread.start();
	            	
	            	ArrayList<Thread> threads = new ArrayList<Thread>();
	            	for (int i=0; i<CHECK_THREADS; i++) {
		            	threads.add(new Thread( new Checker(checkQueue, writeQueue, i)));
		        		threads.get(i).setPriority(Thread.MAX_PRIORITY);
		        		threads.get(i).start();
	            	}

	            	readhread.join();
	            	Thread.sleep(60*1000);
	            	Thread writeThread = new Thread(new Writer(writeQueue,outputPath));
	            	writeThread.setPriority(Thread.MIN_PRIORITY);
	            	writeThread.start();
	            	for (int i=0; i<CHECK_THREADS; i++) {
		        		threads.get(i).join();
	            	}

	        		logger.info("退出");
	                
	                System.exit(0);
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	       
	        // System.out.println("exception finish " + DnsMXCheckServerManager.class);
	    }
}
	class Reader implements Runnable {
		private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Reader.class);
		private BlockingQueue<String> checkQueue;  
		private String domain;
		private String inputPath;
		
		public Reader(BlockingQueue<String> checkQueue, String inputPath) {
			this.checkQueue = checkQueue;
			this.inputPath = inputPath;
		}
		
		@Override
		public void run() {
			produce();
		}

		private void produce() {
			BufferedReader reader;
			String line=null;
			try {
				reader = new BufferedReader(new FileReader(inputPath));
			} catch (FileNotFoundException e) {
				logger.error("inputFile:{} not exist!",inputPath);
				return;
			}
			
			try {
				while ((line = reader.readLine()) != null) {
					String[] ss = line.split("\t");
					if (ss.length == 2) {
						domain = ss[0].trim();
						while (!checkQueue.offer(domain,1,TimeUnit.SECONDS)) {
							logger.warn("{} check queue full, size:{}",domain,checkQueue.size());
							Thread.sleep(100*1000); //增大检测线程获得锁的概率
						}
						logger.debug("read domain:{},鉴别队列放入：{}",domain,checkQueue.size());
					}
				}
				reader.close();
			} catch (IOException e) {
				logger.error("",e);
			} catch (InterruptedException e) {
				logger.error("",e);
			}
		}
	}
	
	class Checker implements Runnable {
		private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Checker.class);
		private BlockingQueue<String> checkQueue,writeQueue;
		private int threadId = -1;
		private String lineSeparator = System.getProperty("line.separator");
		
		public Checker() {}

		public Checker(BlockingQueue<String> checkQueue, BlockingQueue<String> writeQueue, int threadId) {
			this.checkQueue = checkQueue;
			this.writeQueue = writeQueue;
			this.threadId = threadId;
		}

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				try {
					String domain = checkQueue.poll(10, TimeUnit.SECONDS);
					if (domain!=null) {
						logger.info("thr:{} check domain:{},鉴别队列剩余：{}",threadId,domain,checkQueue.size());
						Thread.sleep(1*1000);
						writeQueue.put(domain+"\n");
					} else {
						logger.warn("check queue empty,thr:{} exit", threadId);
						
						break;
					}
					
					
				} catch (InterruptedException e) {
					logger.error("",e);
				} 
				
			}
		}
	}
	
	class Writer implements Runnable {
		private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Writer.class);
		private BlockingQueue<String> writeQueue;
		private String outputPath;  
		
		public Writer(BlockingQueue<String> writeQueue, String outputPath) {
			this.writeQueue = writeQueue;
			this.outputPath = outputPath;
		}
		
		private void init() {
			File f = new File(outputPath);
            if (f.exists()) {
                f.delete();
            }
		}

		@Override
		public void run() {
			init();
			consume();
		}

		private void consume() {
			while (true) {
				BufferedWriter writer;
				
				try {
					writer = new BufferedWriter(new FileWriter(outputPath, true));
				} catch (IOException e1) {
					logger.error("outputFile:{} not exist!", outputPath);
					return;
				}
				
				try {
					String content = writeQueue.poll(3,TimeUnit.SECONDS);
					if (content!=null) {
						logger.info("write res,写入队列剩余：{}",(writeQueue.size()));
						writer.write(content);
					} else {
						logger.warn("write queue empty");
						Thread.sleep(1000*10);
					}
					writer.close();
				} catch (InterruptedException e) {
					logger.error("",e);
				}  catch (IOException e) {
					logger.error("",e);
				}
			}
		}
	}
	
