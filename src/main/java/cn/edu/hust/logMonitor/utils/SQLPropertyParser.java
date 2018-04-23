package cn.edu.hust.logMonitor.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class SQLPropertyParser {
    private static String ALL_RULES_SQL;
    private static String ALL_APPS_SQL;
    private static String ALL_APPTYPE_SQL;
    private static String ALL_USER_SQL;
    private static String Insert_RuleRecord_SQL;
    private static Properties prop=new Properties();
    private static InputStreamReader reader;
    static {
        InputStream is= ZkConfigPropertyParser.class.getClassLoader().getResourceAsStream("app-sql.properties");
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

    public static String getAllRulesSql()
    {
        return prop.getProperty("ALL_RULES_SQL");
    }

    public static String getAllAppSQL() { return prop.getProperty("ALL_APPS_SQL"); }

    public static String getAllAppType() {
        return prop.getProperty("ALL_APPTYPE_SQL");
    }

    public static String getUserSQL() {
        return prop.getProperty("ALL_USER_SQL");
    }

    public static String getInsert_RuleRecord_SQL() {
        return prop.getProperty("Insert_RuleRecord_SQL");
    }
}
