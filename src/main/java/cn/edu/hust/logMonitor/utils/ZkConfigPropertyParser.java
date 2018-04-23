package cn.edu.hust.logMonitor.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ZkConfigPropertyParser {
    private static String ZkHosts;
    private static String KafKaTopic;
    private static String ZKRoot;
    private static String ZkPath_ID;
    private static Properties prop=new Properties();
    private static InputStreamReader reader;
    private static Long Update_Rule_Interval;
    private static String SMTP_PROTOCOL;
    private static String SMTP_SERVER;
    private static String FROM_ADDRESS;
    private static String SMTP_USER;
    private static String SMTP_PWD;
    private static String COPY_TO_SEND;
    private static String LOG_ADMIN;
    static{
       InputStream is= ZkConfigPropertyParser.class.getClassLoader().getResourceAsStream("KafkaAndZk.properties");
       reader= new InputStreamReader(is);
        try {
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getZkHosts()
    {
        return prop.getProperty("ZkHosts");
    }

    public static String getKafKaTopic()
    {
        return prop.getProperty("KafKaTopic");
    }

    public static String getZKRoot()
    {
        return prop.getProperty("ZKRoot");
    }

    public static String getZkPath_ID()
    {
        return prop.getProperty("ZkPath_ID");
    }

    public static Long getUpdateRuleInterval() { return Long.parseLong(prop.getProperty("Update_Rule_Interval"))*1000*60;}

    public static String getSmtpProtocol(){ return prop.getProperty("SMTP_PROTOCOL");}

    public static String getFromAddress(){ return prop.getProperty("FROM_ADDRESS");}

    public static String getSmtpServer(){ return prop.getProperty("SMTP_SERVER");}

    public static String getSmtpUser(){ return prop.getProperty("SMTP_USER");}

    public static String getSmtpPwd(){ return prop.getProperty("SMTP_PWD");}

    public static String getCopyToSend(){ return prop.getProperty("COPY_TO_SEND");}

    public static String getLogAdmin(){ return prop.getProperty("LOG_ADMIN");}

}
