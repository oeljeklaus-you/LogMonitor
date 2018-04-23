package cn.edu.hust.logMonitor;

import cn.edu.hust.logMonitor.bolt.FilterBolt;
import cn.edu.hust.logMonitor.bolt.RuleBolt;
import cn.edu.hust.logMonitor.bolt.SaveMessageBolt;
import cn.edu.hust.logMonitor.utils.ZkConfigPropertyParser;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.TopologyBuilder;

public class LogMonitor {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder=new TopologyBuilder();
        BrokerHosts hosts=new ZkHosts(ZkConfigPropertyParser.getZkHosts());
        SpoutConfig conf=new SpoutConfig(hosts, ZkConfigPropertyParser.getKafKaTopic(), ZkConfigPropertyParser.getZKRoot(), ZkConfigPropertyParser.getZkPath_ID());
        builder.setSpout("kafkaSpout",new KafkaSpout(conf),1);
        builder.setBolt("FilterBolt",new FilterBolt(),1).shuffleGrouping("kafkaSpout");
        builder.setBolt("RuleBolt",new RuleBolt(),1).shuffleGrouping("FilterBolt");
        builder.setBolt("SaveMessage",new SaveMessageBolt(),1).shuffleGrouping("RuleBolt");

        Config config=new Config();
        config.setNumWorkers(3);
        if(args.length>0)
        {
            //StormSubmitter submitter=new StormSubmitter();
            StormSubmitter.submitTopology(args[0],config,builder.createTopology());
        }
        else{
            LocalCluster cluster=new LocalCluster();
            cluster.submitTopology("log",config,builder.createTopology());
        }

    }


}
