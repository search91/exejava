package com.learn.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;


/**
 * ${DESCRIPTION}
 *
 * @author hzliuzhujie
 * @date 2019-06-28
 **/
public class MainTopology {
    public void runLocal(int waitSeconds) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("sentenceSpout", new SentenceSpout(), 1);
        builder.setBolt("splitBolt", new SplitBolt(), 1).shuffleGrouping("sentenceSpout");
        builder.setBolt("countBolt", new CountBolt(), 1).shuffleGrouping("splitBolt");

        Config config = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("word_count", config, builder.createTopology());

        try {
            Thread.sleep(waitSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cluster.killTopology("word_count");
        cluster.shutdown();
    }

    public static void main(String[] args) throws Exception {
        MainTopology topology = new MainTopology();
        topology.runLocal(60);
    }
}