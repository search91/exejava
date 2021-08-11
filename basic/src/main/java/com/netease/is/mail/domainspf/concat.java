package com.netease.is.mail.domainspf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

/*
  
hive> select * from concat_test;
1       good
2       other
1       nice
1       hello

hive> select a,concat(b,',') from concat_test group by a;
OK
1       good,nice,hello
2       other
 */

/*
 * UDAF函数类需要继承UDAF类
 * 内部类Evaluator实UDAFEvaluator接口,实现 init、iterate、terminatePartial、merge、terminate函数。
 */
public class concat extends UDAF {
	public static class concatUDAFEvaluator implements UDAFEvaluator {
		public static class PartialResult{
            String result = "";
            String delimiter = null;
            
        }
		private PartialResult partial = new PartialResult();
		
		/* init函数类似于构造函数，用于UDAF的初始化 */
        public void init() {
            partial.result ="";
        }
        
        /* iterate接收传入的参数，并进行内部的轮转。其返回类型为boolean */
        public boolean iterate(String value ,String deli){
            if(value == null || "null".equalsIgnoreCase(value)){
                return true;
            }
            if(partial.delimiter == null){
                partial.delimiter = deli;
            }
            if(partial.result.length()>0){
                partial.result = partial.result.concat(partial.delimiter);//拼接
            } 
            partial.result = partial.result.concat(value);//拼接
            return true;
        }
        
        /*
         * terminatePartial无参数，其为iterate函数轮转结束后，返回轮转数据， 
         * terminatePartial类似于hadoop的Combiner
         */
        public PartialResult terminatePartial(){
            return partial;
        }
        
        /* merge接收terminatePartial的返回结果，进行数据merge操作，其返回类型为boolean*/
        public boolean merge(PartialResult other){
            if(other == null ){
                return true;
            }
            if (partial.delimiter == null) {
                partial.delimiter = other.result;
                partial.result = other.result;
            }else{
                if (partial.result.length()>0) {
                    partial.result = partial.result.concat(partial.delimiter);
                }
                partial.result = partial.result.concat(other.result);
            }
            return true;
        }
        
        /* terminate返回最终的聚集函数结果 */
        public String terminate(){
            if(partial==null || partial.result.length()==0){
                return null;
            }
            return partial.result;
        }
        
    }
	}