package com.java.learn.hadoop;


import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCountMR {
	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		// Configuration 类: 读取hadoop的配置文件，如 site-core.xml...;
		// 也可以用set方法重新设置(会覆盖): conf.set("fs.defaultFS","hdfs://master:9000")
		Configuration conf = new Configuration();

		// 将命令行中的参数自动设置到变量conf中
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();

		if (otherArgs.length != 2) {
			System.err.println("Usage: wordcount <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "word count2"); // 新建一个job,传入配置信息
		job.setJarByClass(WordCountMR.class); // 设置主类
		job.setMapperClass(TokenizerMapper.class); // 设置Mapper类
		job.setReducerClass(IntSumReducer.class); // 设置Reducer类
		job.setOutputKeyClass(Text.class); // 设置输出类型
		job.setOutputValueClass(IntWritable.class); // 设置输出类型
		FileInputFormat.addInputPath(job, new Path(otherArgs[0])); // 设置输入文件
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1])); // 设置输出文件

		boolean flag = job.waitForCompletion(true);
		System.out.println("SUCCEED!" + flag); // 任务完成提示
		System.exit(flag ? 0 : 1);
		System.out.println();

	}
}