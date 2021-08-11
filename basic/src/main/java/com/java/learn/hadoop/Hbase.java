/**
 * 
 */
package com.java.learn.hadoop;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;


public class Hbase {

	private static final String TABLE_NAME = "lzj";
	private static final String CF_DEFAULT = "info";
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Hbase.class);


	public static void main(String[] args) throws IOException {
		Configuration config = HBaseConfiguration.create();

		/*
		 * 默认会从项目编译后的calss文件的根目录寻找文件，可以不指定 添加资源文件有以下几种方式：
		 * config.addResource(input); InputStream input =
		 * HBaseOper.class.getResourceAsStream("/core-site.xml");
		 * config.addResource(input);
		 * 
		 * config.addResource("D:\\hbase\\conf\\hbase-site.xml"); Path可以为绝对路径
		 */
		try {
/*			config.set("hbase.zookeeper.quorum", "centos7,centos");
			config.set("zookeeper.znode.parent", "/hbase");*/
			Connection connection = ConnectionFactory.createConnection(config);
			Admin admin = connection.getAdmin();
			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT).setCompressionType(Algorithm.NONE));

			// 删建表
			if (admin.tableExists(table.getTableName())) {
				logger.info("table is exist!");
				admin.disableTable(table.getTableName());
				admin.deleteTable(table.getTableName());
			} else {
				logger.info("table is not exist!");
			}
			admin.createTable(table);

			// 插入数据
			
			// 删除数据
			
			// 查询数据
			HTable hTable = new HTable(config, TABLE_NAME);
	        Get get = new Get("susan".getBytes());
	        Result result = hTable.get(get);
	        logger.info("rowkey为:" + "susan");
	        HashMap<String, String> hm = new HashMap<String, String>();
	        for (KeyValue kv: result.raw()) {
	            hm.put(new String(kv.getFamily()) , new String(kv.getValue()));
	            logger.info("rowkey:" + new String(kv.getKey()));
	            logger.info("columnFamily:" + new String(kv.getFamily()) +"===column:" + new String(kv.getQualifier()) + "===getValue:" + new String(kv.getValue()));
	        }
	        logger.info(hm.size()+hm.toString());
			
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}