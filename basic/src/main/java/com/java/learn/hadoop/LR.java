package com.java.learn.hadoop;

import java.util.regex.Pattern;  

import org.apache.spark.SparkConf;  
import org.apache.spark.api.java.JavaRDD;  
import org.apache.spark.api.java.JavaSparkContext;  
import org.apache.spark.api.java.function.Function;  
  
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD;  
import org.apache.spark.mllib.classification.LogisticRegressionModel;  
import org.apache.spark.mllib.linalg.Vectors;  
import org.apache.spark.mllib.regression.LabeledPoint;  
import org.apache.spark.mllib.linalg.DenseVector;  

/** 
 * Logistic regression based classification using ML Lib. 
 **/

public final class LR {  
	static class ParsePoint implements Function<String, LabeledPoint> {
	private static final Pattern COMMA = Pattern.compile(",");  
	private static final Pattern SPACE = Pattern.compile(" ");  
	  
    @Override  
    public LabeledPoint call(String line) {  
      String[] parts = COMMA.split(line);  
      double y = Double.parseDouble(parts[0]);  
      String[] tok = SPACE.split(parts[1]);  
      double[] x = new double[tok.length];  
      for (int i = 0; i < tok.length; ++i) {  
        x[i] = Double.parseDouble(tok[i]);  
      }  
      return new LabeledPoint(y, Vectors.dense(x));  
    }  
  }  
  
  public static void main(String[] args) {
	  System.setProperty("spark.serializer","org.apache.spark.serializer.KryoSerializer");
	  SparkConf sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local[*]");//spark://dm8.space.163.org:7077
	  JavaSparkContext jsc = new JavaSparkContext(sparkConf);
	  JavaRDD<String> lines = jsc.textFile("file:///E:/project/java/exeJava/src/main/java/com/java/test/hadoop/lr_training.txt");
	  System.out.println(lines.collect());
	  JavaRDD<LabeledPoint> points = lines.map(new ParsePoint()).cache();
	  System.out.println(points.collect());
	  double stepSize = 0.2;
	  int iterations = 100;  
  
    // Another way to configure LogisticRegression  
    //  
    // LogisticRegressionWithSGD lr = new LogisticRegressionWithSGD();  
    // lr.optimizer().setNumIterations(iterations)  
    //               .setStepSize(stepSize)  
    //               .setMiniBatchFraction(1.0);  
    // lr.setIntercept(true);  
    // LogisticRegressionModel model = lr.train(points.rdd());  
  
    LogisticRegressionModel model = LogisticRegressionWithSGD.train(points.rdd(), iterations, stepSize);  
  
    System.out.print("Final w: " + model.weights() + "and intercept is " + model.intercept() + "\n");  
    double[] point = new double[2];  
    point[0] = 8;  
    point[1] = 8;  
    double label = model.predictPoint(new DenseVector(point), model.weights(), model.intercept());  
    System.out.print("label for [" + point[0] + " "  + point[1] + "] is " + label + "\n");  
    
    
 // JavaRDD<Vector> points = rdd.map(new ParsePoint());
    /*
     KMeansModel model = KMeans.train(flatMapRdd.rdd(), k, iterations, runs, KMeans.K_MEANS_PARALLEL());

     System.out.println("Cluster centers:");
     for (Vector center : model.clusterCenters()) {
        System.out.println(" " + center);
     }
     double cost = model.computeCost(points.rdd());
     System.out.println("Cost: " + cost);*/
    
    jsc.stop();  
  }  
}  