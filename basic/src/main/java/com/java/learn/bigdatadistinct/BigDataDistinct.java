/**
 * 
 */
package com.java.learn.bigdatadistinct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.Reader;

import java.io.Writer;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

public class BigDataDistinct {

	// 内存监控

	final static Runtime currRuntime = Runtime.getRuntime();

	// 最小的空闲空间，额，可以用来 智能控制，- -考虑到GC ，暂时没用

	final static long MEMERY_LIMIT = 1024 * 1024 * 3;

	// 内存限制，我内存最多容纳的文件大小

	static final long FILE_LIMIT_SIZE = 1024 * 1024 * 20;

	// 文件写入缓冲区 ，我默认1M

	static final int CACHE_SIZE = 1024 * 1024;

	// 默认文件后缀

	static final String FILE_SUFFIX = "_tmp";

	// 临时分割的文件目录，可以删除~。~

	static final String FILE_PREFIX = "tmp/";

	// 汇总的文件名

	static final String REQUST_FILE_NAME = "resultFile";

	// 存放大文件 引用，以及分割位置

	static List<ChildFile> bigChildFiles = new ArrayList<ChildFile>();

	// 存放小文件的，驱除重复数据

	static Map<String, String> fileLinesMap = new HashMap<String, String>(10000);

	public static void main(String[] args) {

		long begin = System.currentTimeMillis();

		new BigDataDistinct().partFile(new File("userwhitelist.log.2017-02-06"),

		Integer.MAX_VALUE, Integer.MIN_VALUE);

		long result = System.currentTimeMillis() - begin;

		System.out.println("除去重复时间为：" + result + " 毫秒");

	}

	// 按hashCode 范围分割

	public void partFile(File origFile, long maxNum, long minNum) {
		int count=0;

		String line = null;

		long hashCode = 0;

		long max_left_hashCode = 0;

		long min_left_hashCode = 0;

		long max_right_hashCode = 0;

		long min_right_hashCode = 0;

		BufferedWriter rightWriter = null;

		BufferedWriter leftWriter = null;

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(origFile));

			long midNum = (maxNum + minNum) / 2;

			// 以文件hashCode 范围作为子文件名

			File leftFile = new File(FILE_PREFIX + minNum + "_" + midNum
					+ FILE_SUFFIX);

			File rightFile = new File(FILE_PREFIX + midNum + "_" + maxNum
					+ FILE_SUFFIX);

			leftWriter = new BufferedWriter(new FileWriter(leftFile),
					CACHE_SIZE);

			rightWriter = new BufferedWriter(new FileWriter(rightFile),
					CACHE_SIZE);

			ChildFile leftChild = new ChildFile(leftFile);

			ChildFile rightChild = new ChildFile(rightFile);

			// 字符串 组合写入也行

			// StringBuilder leftStr = new StringBuilder(100000);

			// StringBuilder rightStr = new StringBuilder(100000);

			// hashCode 的范围作为分割线

			while ((line = reader.readLine()) != null) {
				count++;
				String[] lines = line.split(";;");
				if (lines.length <2) {
					System.out.println("[]" + line);
					continue;
				}
				String line1 = (lines[0] + ";;"+ lines[1]).toLowerCase();
				
				
				if (count%10000 ==0) {
					System.out.println(count);
				}

				hashCode = line1.hashCode();

				if (hashCode > midNum) {

					if (max_right_hashCode < hashCode
							|| max_right_hashCode == 0) {

						max_right_hashCode = hashCode;

					} else if (min_right_hashCode > hashCode
							|| min_right_hashCode == 0) {

						min_right_hashCode = hashCode;

					}

					// 按行写入缓存

					writeToFile(rightWriter, line1);

				} else {

					if (max_left_hashCode < hashCode || max_left_hashCode == 0) {

						max_left_hashCode = hashCode;

					} else if (min_left_hashCode > hashCode
							|| min_left_hashCode == 0) {

						min_left_hashCode = hashCode;

					}

					writeToFile(leftWriter, line1);

				}

			}

			// 保存子文件信息

			leftChild.setHashCode(min_left_hashCode, max_left_hashCode);

			rightChild.setHashCode(min_right_hashCode, max_right_hashCode);

			closeWriter(rightWriter);

			closeWriter(leftWriter);

			closeReader(reader);

			// 删除原始文件，保留最原始的文件

			if (!origFile.getName().equals("userwhitelist.log.2017-02-06")) {

				origFile.delete();

			}

			// 分析子文件信息，是否写入或者迭代

			analyseChildFile(rightChild, leftChild);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println(count);
	}

	// 分析子文件信息

	public void analyseChildFile(ChildFile rightChild, ChildFile leftChild) {

		// 将分割后 还是大于内存的文件保存 继续分割

		File rightFile = rightChild.getChildFile();

		if (isSurpassFileSize(rightFile)) {

			bigChildFiles.add(rightChild);

		} else if (rightFile.length() > 0) {

			orderAndWriteToFiles(rightFile);

		}

		File leftFile = leftChild.getChildFile();

		if (isSurpassFileSize(leftFile)) {

			bigChildFiles.add(leftChild);

		} else if (leftFile.length() > 0) {

			orderAndWriteToFiles(leftFile);

		}

		// 未超出直接内存排序，写入文件，超出继续分割，从末尾开始，不易栈深度溢出

		if (bigChildFiles.size() > 0) {

			ChildFile e = bigChildFiles.get(bigChildFiles.size() - 1);

			bigChildFiles.remove(e);

			// 迭代分割

			partFile(e.getChildFile(), e.getMaxHashCode(), e.getMinHashCode());

		}

	}

	// 将小文件读到内存排序除重复

	public void orderAndWriteToFiles(File file) {

		BufferedReader reader = null;

		String line = null;

		BufferedWriter totalWriter = null;

		StringBuilder sb = new StringBuilder(1000000);

		try {

			totalWriter = new BufferedWriter(new FileWriter(REQUST_FILE_NAME,true), CACHE_SIZE);

			reader = new BufferedReader(new FileReader(file));

			while ((line = reader.readLine()) != null) {

				if (!fileLinesMap.containsKey(line)) {

					fileLinesMap.put(line, null);

					sb.append(line + "\r\n");

					// totalWriter.write(line+"\r\n");

				}

			}

			totalWriter.write(sb.toString());

			fileLinesMap.clear();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			closeReader(reader);

			closeWriter(totalWriter);

			// 删除子文件

			file.delete();

		}

	}

	// 判断该文件是否超过 内存限制

	public boolean isSurpassFileSize(File file) {

		return FILE_LIMIT_SIZE < file.length();

	}

	// 将数据写入文件

	public void writeToFile(BufferedWriter writer, String writeInfo) {

		try {

			writer.write(writeInfo + "\r\n");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	// 关闭流

	public void closeReader(Reader reader) {

		if (reader != null) {

			try {

				reader.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	// 关闭流

	public void closeWriter(Writer writer) {

		if (writer != null) {

			try {

				writer.flush();

				writer.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	// 内部类，记录子文件信息

	class ChildFile {

		// 文件 和 内容 hash 分布

		File childFile;

		long maxHashCode;

		long minHashCode;

		public ChildFile(File childFile) {

			this.childFile = childFile;

		}

		public ChildFile(File childFile, long maxHashCode, long minHashCode) {

			super();

			this.childFile = childFile;

			this.maxHashCode = maxHashCode;

			this.minHashCode = minHashCode;

		}

		public File getChildFile() {

			return childFile;

		}

		public void setChildFile(File childFile) {

			this.childFile = childFile;

		}

		public long getMaxHashCode() {

			return maxHashCode;

		}

		public void setMaxHashCode(long maxHashCode) {

			this.maxHashCode = maxHashCode;

		}

		public long getMinHashCode() {

			return minHashCode;

		}

		public void setMinHashCode(long minHashCode) {

			this.minHashCode = minHashCode;

		}

		public void setHashCode(long minHashCode, long maxHashCode) {

			this.setMaxHashCode(maxHashCode);

			this.setMinHashCode(minHashCode);

		}

	}

}
