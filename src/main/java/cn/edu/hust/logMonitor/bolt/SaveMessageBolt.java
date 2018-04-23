package cn.edu.hust.logMonitor.bolt;

import cn.edu.hust.logMonitor.bean.RuleRecord;
import cn.edu.hust.logMonitor.utils.LogUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IWindowedBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class SaveMessageBolt extends BaseRichBolt
{
    private Map stormConf;
    private TopologyContext context;
    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.stormConf=stormConf;
        this.context=context;
        this.collector=collector;
    }

    @Override
    public void execute(Tuple input) {
        RuleRecord record=(RuleRecord) input.getValue(0);
        LogUtils.save2DB(record);
        this.collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("success"));
    }
}