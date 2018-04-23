package cn.edu.hust.logMonitor.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SQLPropertyParserTest {
    @Test
    public void getALL_RULE_SQL()
    {
        System.out.println(SQLPropertyParser.getAllRulesSql());
    }

    @Test
    public void testDate()
    {
        Date date=new Date();
        System.out.println(new Timestamp(date.getTime()));
        //SimpleDateFormat format=new SimpleDateFormat("");
    }
}
