package cn.edu.hust.logMonitor.bolt;

import cn.edu.hust.logMonitor.bean.AppMessage;
import cn.edu.hust.logMonitor.bean.RuleRecord;
import cn.edu.hust.logMonitor.exception.LogException;
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
import org.apache.storm.tuple.Values;
import scala.App;

import java.util.Map;

public class RuleBolt extends BaseRichBolt{
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
        //从上一个bolt中得到appMessage
        AppMessage appMessage=(AppMessage) input.getValue(0);
        //取出触犯规则的id
        int ruleId=input.getInteger(1);
        //发送邮件给管理这些应用的运维人员
        /**
         * 如果发送邮件正确，那么将会把发送的正确信息给下一个bolt
         * 如果没有合法的运维人员，发邮件给管理人员
         */
        //System.out.println("进入了RuleBolt");
        try {
            RuleRecord record=LogUtils.sendMessge(appMessage);
            record.setRuleId(ruleId);
            this.collector.emit(new Values(record));
            this.collector.ack(input);
        } catch (LogException e) {
            RuleRecord record=e.getRecord();
            record.setAppId(ruleId);
            System.out.println("出异常了");
            //在这里设置一个异常表，用于管理员日常查看
        }
        catch(Exception e)
        {
            System.out.println("其他错误");
            return;
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(("record")));
    }
}
