package com.java.learn.hadoop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.*;

public class HBase2Spark implements Serializable {
    public static void main(String[] args) throws Exception {
        System.setProperty("SPARK_LOCAL_HOSTNAME", "dir");
        System.setProperty("HADOOP_CONF_DIR", "");
        System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        SparkConf sparkConf = new SparkConf().setAppName("lzj")
                .set("spark.shuffle.service.enabled", "false")
                .set("spark.dynamicAllocation.enabled", "false")
                .set("spark.cores.max", "2")
                .set("spark.executor.memory", "2g")
                .set("spark.driver.memory", "2g")
                .setMaster("spark://dm8.space.163.org:7077");//spark://dm8.space.163.org:7077
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        Configuration hbaseConf = HBaseConfiguration.create();
        hbaseConf.set(TableInputFormat.INPUT_TABLE, "t_phone");

        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("active"), Bytes.toBytes("mail_UserInfo"));
        ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
        String ScanToString = Base64.encodeBytes(proto.toByteArray());
        hbaseConf.set(TableInputFormat.SCAN, ScanToString);

        // hbaseConf.set(TableInputFormat.SCAN_COLUMN_FAMILY, "mail_UserInfo");

        JavaPairRDD<ImmutableBytesWritable, Result> hBaseRDD = jsc.newAPIHadoopRDD(hbaseConf,
                TableInputFormat.class,
                ImmutableBytesWritable.class,
                Result.class);

        hBaseRDD.cache();
        System.out.println(hBaseRDD);
        System.out.println("count: " + hBaseRDD.count());
        List<Tuple2<ImmutableBytesWritable, Result>> output = hBaseRDD.collect();
        for (Tuple2<ImmutableBytesWritable, Result> tuple : output) {
            System.out.println(tuple._1 + ":" + tuple._2);
        }

        /**
         * 例如数据是：name：gaoyue age：28
         * 方法一：map,我们可以看到数据的每一行在map之后产生了一个数组，那么rdd存储的是一个数组的集合
         * rdd存储的状态是Array[Array[String]] = Array(Array(name, gaoyue), Array(age, 28))
         * Array[String] = Array(name, gaoyue, age, 28)
         */

        JavaRDD<String> mapRdd = hBaseRDD.map(new Function<Tuple2<ImmutableBytesWritable, Result>, String>() {
            @Override
            public String call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
                String value = Bytes.toString(t._2.getValue(Bytes.toBytes("active"), Bytes.toBytes("mail_UserInfo")));
                return value;
            }
        });
        System.out.println("mapRdd##" + mapRdd.collect());

        /**
         * 方法二：flatMap 
         * 操作1：同map函数一样：对每一条输入进行指定的操作，然后为每一条输入返回一个对象 
         * 操作2：最后将所有对象合并为一个对象 
         */
        JavaRDD<String> flatMapRdd = hBaseRDD.flatMap(new FlatMapFunction<Tuple2<ImmutableBytesWritable, Result>, String>() {
            @Override
            public Iterator<String> call(Tuple2<ImmutableBytesWritable, Result> t) {
                String value = Bytes.toString(t._2.getValue(Bytes.toBytes("active"), Bytes.toBytes("mail_UserInfo")));
                return (Iterator<String>) Arrays.asList(value);
            }
        });
        System.out.println("flatMapRdd##" + flatMapRdd.collect());

        /**
         * 方法三：  mapPartition 
         *rdd的mapPartitions是map的一个变种，它们都可进行分区的并行处理。两者的主要区别是调用的粒度不一样： 
         * map的输入变换函数是应用于RDD中每个元素，而mapPartitions的输入函数是应用于每个分区。也就是把每个分区中的内容作为整体来处理的。 
         *
         */
        JavaRDD<String> mapPartitionsRdd = hBaseRDD.mapPartitions(new FlatMapFunction<Iterator<Tuple2<ImmutableBytesWritable, Result>>, String>() {
            ArrayList<String> results = new ArrayList<>();

            @Override
            public Iterator<String> call(Iterator<Tuple2<ImmutableBytesWritable, Result>> iterator) {
                while (iterator.hasNext()) {
                    String value = Bytes.toString(iterator.next()._2.getValue(Bytes.toBytes("active"), Bytes.toBytes("mail_UserInfo")));
                    results.add(value);
                }
                return (Iterator<String>) results;
            }
        });
        System.out.println("mapPartitionsRdd##" + mapPartitionsRdd.collect());

        /**
         * 方法四：mapToPair 
         * 操作1：同map函数一样：对每一条输入进行指定的操作，然后为每一条输入返回一个key－value对象 
         *
         */
        JavaPairRDD<String, Integer> mapPairRdd = hBaseRDD.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable, Result>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<ImmutableBytesWritable, Result> hbaseTuple) throws Exception {
                byte[] o = hbaseTuple._2().getValue(Bytes.toBytes("active"), Bytes.toBytes("mail_UserInfo"));
                if (o != null) {
                    String jsonStr = Bytes.toString(o);
                    if (jsonStr.contains("fullname")) {
                        Map<String, String> userMap = JSON.parseObject(jsonStr.replaceAll("=", "\\:"), new TypeReference<Map<String, String>>() {
                        });
                        Map<String, String> userMap2 = (Map<String, String>) JSON.parse(jsonStr.replaceAll("=", "\\:"));
                        String fullName = userMap.get("fullname");
                        if (!fullName.isEmpty()) {
                            return new Tuple2<String, Integer>(fullName, 1);
                        }
                    }
                }
                return new Tuple2<String, Integer>("", 1);
            }
        });
        System.out.println("mapPairRdd##" + mapPairRdd.collect());

        /**
         * 方法五：flatMapToPair 
         * 操作1：同map函数一样：对每一条输入进行指定的操作，然后为每一条输入返回一个key－value对象 
         * 操作2：最后将所有key－value对象合并为一个对象 Iterable<Tuple2<String, String>> 
         *
         */

        // reduce
        JavaPairRDD<String, Integer> reducePairRdd = mapPairRdd.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(final Integer value0, final Integer value1) {
                return Integer.valueOf(value0.intValue() + value1.intValue());
            }
        });
        System.out.println("reducePairRdd##" + reducePairRdd.collect());

        JavaRDD<Tuple2<ImmutableBytesWritable, Result>> rdd = JavaRDD.fromRDD(JavaPairRDD.toRDD(hBaseRDD), hBaseRDD.classTag());
        System.out.println(rdd.collect());
        jsc.stop();
    }
}