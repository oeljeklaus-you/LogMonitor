package cn.edu.hust.logMonitor.utils;

import cn.edu.hust.logMonitor.bean.*;
import cn.edu.hust.logMonitor.dao.*;
import cn.edu.hust.logMonitor.dao.impl.*;
import cn.edu.hust.logMonitor.exception.LogException;
import cn.edu.hust.logMonitor.mail.MailInfo;
import cn.edu.hust.logMonitor.mail.MessageSender;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogUtils {
    private static List<Rule> rules;
    private static List<AppType> appTypes;
    private static List<User> users;
    private static List<Apps> apps;
    private static Map<Integer,List<User>> userMap;
    private static Map<Integer,Apps> appsMap;
    private static Map<Integer,AppType> appTypesMap;
    private static JdbcTemplate jdbcTemplate;
    private static RuleDao ruleDao;
    private static AppTypeDao appTypeDao;
    private static UserDao userDao;
    private static AppDao appDao;
    private static RuleRecordDao ruleRecordDao;
    private static final Logger logger = Logger.getLogger(LogUtils.class);
    //上一次调度的时间
    private static Date schedulorTime;
    //这里次调度的时间
    private static Date nextSchedulorTime;
    //在类加载的时候就在数据库中读取规则
    static
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        //创建对应的dao
        ruleDao=new RuleDaoImpl(jdbcTemplate);
        appTypeDao=new AppTypeDaoImpl(jdbcTemplate);
        appDao=new AppDaoImpl(jdbcTemplate);
        userDao=new UserDaoImpl(jdbcTemplate);
        ruleRecordDao=new RuleRecordDaoImpl(jdbcTemplate);
        //载入对应的规则
        rules=ruleDao.getAllRules();
        appTypes=appTypeDao.getAllAppType();
        users=userDao.getAllUser();
        apps=appDao.getAllApps();

        getUsers();
        getApps();
        getAppTypes();
    }

    /**
     * 将有效的规则加载到内存后，以appId为key，List<rule>为value存储
     * 这样就比较判断规则
     * @return
     */
    public static Map<Integer,List<Rule>> getRules()
    {
        Map<Integer,List<Rule>> ruleMap=new HashMap<>();
        for (Rule rule:
             rules) {
            if(ruleMap.get(rule.getAppId())==null)
            {
                List<Rule> tmpRules=new LinkedList<>();
                tmpRules.add(rule);
                ruleMap.put(rule.getAppId(),tmpRules);
            }
            else
            {
                List<Rule> ruleList=ruleMap.get(rule.getAppId());
                ruleList.add(rule);
                ruleMap.put(rule.getAppId(),ruleList);
            }
        }
        return ruleMap;
    }

    /**
     * 将有效的规则加载到内存后，以appId为key，List<User>为value存储
     * 这样就比较判断规则
     * @return
     */
    public static void getUsers()
    {
        userMap=new HashMap<>();
        for (User user:
                users) {
            if(userMap.get(user.getAppId())==null)
            {
                List<User> tmpUsers=new LinkedList<>();
                tmpUsers.add(user);
                userMap.put(user.getAppId(),tmpUsers);
            }
            else
            {
                List<User> userList=userMap.get(user.getAppId());
                userList.add(user);
                userMap.put(user.getAppId(),userList);
            }
        }

    }

    /***
     * 将有效的规则加载到内存后，以appId为key,Apps为value存储
     * @return
     */
    public static void getApps()
    {
        appsMap=new HashMap();
        for(Apps app:apps)
        {
            appsMap.put(app.getId(),app);
        }

    }

    /***
     * 将有效的规则加载到内存后，以appId为key，AppType为value存储
     * 这里一个app只能和一个AppType对应
     * @return
     */
    public static void getAppTypes()
    {
        appTypesMap=new HashMap();
        for(AppType appType:appTypes)
        {
                appTypesMap.put(appType.getId(),appType);
        }
    }

    /**
     * 判断消息是否触发了规则库内部的规则
     * @param message
     * @return
     */
    public  static Map<Boolean,Integer> IsTriggerRules(AppMessage message)
    {
        Map<Boolean,Integer> maps=new HashMap<>();
        if(getRules()==null||message==null)
        {
            maps.put(false,1);
            return maps;
        }
        List<Rule> rules=getRules().get(message.getId());
        if(rules==null)
        {
            maps.put(false,1);
            return maps;
        }
        for (Rule rule:
             rules) {
            if(message.getContent().contains(rule.getKeyword()))
            {
                maps.put(true,rule.getId());
                return maps;
            }
        }
        maps.put(false,1);
        return maps;
    }



    /***
     *
     * 定时更新规则库
     */
    public static synchronized void scheduleRules() {
        if(schedulorTime==null)
        {
            schedulorTime=new Date();
            nextSchedulorTime=new Date();
            return;
        }
        Long time=ZkConfigPropertyParser.getUpdateRuleInterval();
        //如果两次的时间间隔小于指定时间，那么就更新这一次时间
        if(nextSchedulorTime.getTime()-schedulorTime.getTime()<time)
        {
            nextSchedulorTime=new Date();
        }
        else
        {

            rules=ruleDao.getAllRules();
            getRules();
            users=userDao.getAllUser();
            getUsers();
            apps=appDao.getAllApps();
            getApps();
            appTypes=appTypeDao.getAllAppType();
            getAppTypes();
            schedulorTime=new Date();
            nextSchedulorTime=new Date();

            logger.info("【 规则库更新完成，成功时间： " + System.currentTimeMillis() + " 】");
        }
    }

    /**
     * 发送邮件，这里只允许一个线程发送邮件
     * @param appMessage
     * @throws LogException
     */
    public static synchronized RuleRecord sendMessge(AppMessage appMessage) throws LogException {
        //这里new出一个新对象，
        RuleRecord record=new RuleRecord();
        //1.得到消息对应的应用编号
        int appId=appMessage.getId();
        Apps apps=appsMap.get(appId);
        //设置对应的告警信息
        record.setAppId(appId);
        record.setNoticeInfo(appMessage.getContent());
        record.setCreateDate(new Timestamp(new Date().getTime()));
        record.setUpdateDate(new Timestamp(new Date().getTime()));
        //2.获得应用对应的类型
        AppType appType=appTypesMap.get(appId);

        //3.获得处理这一类应用的运维人员的信息
        List<User> users=userMap.get(appId);
        if(users==null) throw new LogException("应用名字为"+appType.getName()+",没有对应的运维人员，请即使添加",record);

        //4.组装异常信息的内容
        /**
         * 1.构建接受者邮件
         * 2.构建抄送者邮件
         * 3.构建邮件主题
         */
        List<String> toAddress=new LinkedList();
        for (User user:users)
        {
            toAddress.add(user.getEmail());
        }
        //记录异常发送的时间
        //SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        String time=new Timestamp(new Date().getTime()).toString();
        List<String> ccAddress=Arrays.asList(ZkConfigPropertyParser.getCopyToSend().split(","));
        String mailSubject="应用为"+apps.getName()+"发生了"+appMessage.getContent();
        String mailContent="应用类型为"+appType.getName()+":应用的名称为"+apps.getName()
                +",具体的描述为"+apps.getDesc()+"在"+time+",发生了如下的异常"+appMessage.getContent() ;
        //5.发送邮件给运维人员
        MailInfo mail=new MailInfo(mailSubject,mailContent,toAddress,ccAddress);
        MessageSender.sendMail(mail);
        record.setIsEmail(1);
        record.setIsPhone(1);
        return record;
    }

    public static void save2DB(RuleRecord record) {
        ruleRecordDao.insert(record);
    }
}
