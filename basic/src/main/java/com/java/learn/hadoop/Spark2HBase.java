/**
 * 
 */
package com.java.learn.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapred.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.rdd.RDD;

import scala.Tuple2;

//http://codereview.stackexchange.com/questions/56641/producing-a-sorted-wordcount-with-spark

//export HADOOP_CONF_DIR=/etc/hadoop/conf
//java -cp ./target/hbase-spark-playground-1.0-SNAPSHOT.jar spark.examples.SparkHBase local[2]
//
//hadoop fs -put README.md
//spark-submit --class spark.examples.SparkHBase --master local[8]  ./target/hbase-spark-playground-1.0-SNAPSHOT.jar
//FIXME: La ejecucción no comienza y da el siguiente error:
//       WARN hdfs.BlockReaderLocal: The short-circuit local reads feature cannot be used because libhadoop cannot be loaded.

// spark-submit --verbose --class spark.examples.SparkToHBase --master yarn-client  --num-executors 1 --driver-memory 1G  --executor-memory 1G  --executor-cores 1  --driver-class-path /usr/lib/hbase/lib/hbase-protocol-0.98.0.2.1.1.0-385-hadoop2.jar  ./target/hbase-spark-playground-1.0-SNAPSHOT.jar yarn-client 

//spark-submit --class spark.examples.SparkHBase --master yarn-cluster  --executor-memory 200m --num-executors 1 ./target/hbase-spark-playground-1.0-SNAPSHOT.jar yarn-cluster
//FIXME: el submit se ejecuta y lee y guarda en HBase correctamente. Sin embargo la tarea Spark termina como Failed en distributedFinalState:.
//Viendo los logs se puede observar que hay un problema con el DNS
//ERROR mapreduce.TableInputFormatBase: Cannot resolve the host name for sandbox.hortonworks.com/192.168.149.133 because of javax.naming.NameNotFoundException: DNS name not found [response code 3]; remaining name '133.149.168.192.in-addr.arpa'

public class Spark2HBase {
	
	private final static String tableName = "t_userinfo";
	private final static String columnFamily = "active";

	private static JavaSparkContext jsc;
	public static void main(String[] args) throws Exception{
		String master = "local[*]";
		jsc = new JavaSparkContext(master, "get HBase data");
		Configuration conf = HBaseConfiguration.create();
		//conf.addResource(new Path("file://E:/project/java/exeJava/src/main/java/hbase-site.xml"));
		conf.set(TableInputFormat.INPUT_TABLE, tableName);
		
		// new Hadoop API configuration
		Job newAPIJobConfiguration = Job.getInstance(conf);
		newAPIJobConfiguration.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, tableName);
		newAPIJobConfiguration.setOutputFormatClass(org.apache.hadoop.hbase.mapreduce.TableOutputFormat.class);
		
		// old Hadoop API configuration
		JobConf oldAPIJobConfiguration = new JobConf(conf, Spark2HBase.class);
		oldAPIJobConfiguration.setOutputFormat(TableOutputFormat.class);
		oldAPIJobConfiguration.set(TableOutputFormat.OUTPUT_TABLE, tableName);
		
		
		
		HBaseAdmin hBaseAdmin = null;
		try {
			hBaseAdmin = new HBaseAdmin(conf);
			if (hBaseAdmin.isTableAvailable(tableName)) {
				System.out.println("Table " + tableName + " is available.");
			}
			else {
				System.out.println("Table " + tableName + " is not available.");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			hBaseAdmin.close();
		}

		
		System.out.println("-----------------------------------------------");
		//readTable(conf, mode);
		readTableJava(conf, master);
		System.out.println("-----------------------------------------------");
		//writeRowOldHadoopAPI(oldAPIJobConfiguration,mode);
		writeRowNewHadoopAPI(newAPIJobConfiguration.getConfiguration(),master);
		System.out.println("-----------------------------------------------");
		readTableJava(conf, master);
		System.out.println("-----------------------------------------------");
		//System.exit(0);
		jsc.stop();

	}



	private static void readTable(Configuration conf, String mode) {
		SparkContext jsc = new SparkContext(mode, "get HBase data");
		RDD<Tuple2<ImmutableBytesWritable, Result>> hBaseRDD = jsc.newAPIHadoopRDD(
						conf,
						TableInputFormat.class,
						org.apache.hadoop.hbase.io.ImmutableBytesWritable.class,
						org.apache.hadoop.hbase.client.Result.class);
		long count = hBaseRDD.count();
		System.out.println("Number of register in hbase table: " + count);
		jsc.stop();
	}
	
	
	private static void readTableJava(Configuration conf, String mode) {
		
		JavaPairRDD<ImmutableBytesWritable, Result> hBaseRDD = jsc.newAPIHadoopRDD(
						conf,
						TableInputFormat.class,
						org.apache.hadoop.hbase.io.ImmutableBytesWritable.class,
						org.apache.hadoop.hbase.client.Result.class);
		long count = hBaseRDD.count();
		System.out.println("Number of register in hbase table: " + count);
//		jsc.stop();
	}
	
	private static void writeRowOldHadoopAPI(JobConf conf, String mode) {
		//JavaSparkContext sparkContext = new JavaSparkContext(mode, "write data to HBase");
		//FIXME: mirar como quitar la carga de un texto arbitrario para crear un JavaRDD
		JavaRDD<String> records = jsc.textFile("file:///E:/project/java/README.md",1);
		
	    JavaPairRDD<ImmutableBytesWritable, Put> hbasePuts = records.mapToPair(new PairFunction<String, ImmutableBytesWritable, Put>() {
	    	@Override
			public Tuple2<ImmutableBytesWritable, Put> call(String t) throws Exception {
				Put put = new Put(Bytes.toBytes("lzj"));
				put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes("test"), Bytes.toBytes("lzjtest"));

				return new Tuple2<ImmutableBytesWritable, Put>(new ImmutableBytesWritable(), put);
			}
	      });
		hbasePuts.saveAsHadoopDataset(conf);
		//sparkContext.stop();

	}
	
	private static void writeRowNewHadoopAPI(Configuration conf, String mode) {
		
		//JavaSparkContext sparkContext = new JavaSparkContext(mode, "write data to HBase");
		JavaRDD<String> records = jsc.textFile("file:///E:/project/java/README.md",1);
		//FIXME: mirar como quitar la carga de un texto arbitrario para crear un JavaRDD
	    JavaPairRDD<ImmutableBytesWritable, Put> hbasePuts = records.mapToPair(new PairFunction<String, ImmutableBytesWritable, Put>() {
	    	@Override
			public Tuple2<ImmutableBytesWritable, Put> call(String t) throws Exception {
				Put put = new Put(Bytes.toBytes("lzj"));
				put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes("test"), Bytes.toBytes("lzjtest"));

				return new Tuple2<ImmutableBytesWritable, Put>(new ImmutableBytesWritable(), put);
			}
	      });
	    
		hbasePuts.saveAsNewAPIHadoopDataset(conf);
		//sparkContext.stop();

	}
}