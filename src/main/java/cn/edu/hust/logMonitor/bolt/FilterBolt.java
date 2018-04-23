package cn.edu.hust.logMonitor.bolt;

import cn.edu.hust.logMonitor.bean.AppMessage;
import cn.edu.hust.logMonitor.utils.LogUtils;
import cn.edu.hust.logMonitor.utils.MessageUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class FilterBolt extends BaseRichBolt{
    private Map stormConf;
    private TopologyContext context;
    private OutputCollector collector;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.stormConf=stormConf;
        this.context=context;
        this.collector=collector;
    }

    /**1$$$$$error: Caused by: java.lang.NoClassDefFoundError: com/starit/gejie/dao/SysNameDao
     * 这个消息是由应用端，也就是javaee或者安卓端产生，第一个表示应用的类型，
     * 第二个表示消息的具体内容
     */
    @Override
    public void execute(Tuple input) {
        String message=new String((byte[])input.getValue(0));

        //将spout采集的数据转化为对象
        AppMessage appMessage=MessageUtils.str2Object(message);
        //System.out.println("获取的信息:"+message+"---"+appMessage.getContent());
        if(appMessage==null) return;
        //判断是否触发规则
        Map<Boolean,Integer> flag=LogUtils.IsTriggerRules(appMessage);
        //如果没有触发规则，那么久不做任何处理
        if(flag.get(false)!=null) return;
        collector.emit(new Values(appMessage,flag.get(true)));
        //这里进行ack确认，因为KafkaSpout实现了ack-fail机制
        this.collector.ack(input);
        //定时更新规则库
        LogUtils.scheduleRules();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("appMessage", "ruleId"));
    }
}
