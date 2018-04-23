package cn.edu.hust.logMonitor.Dao;

import cn.edu.hust.logMonitor.bean.RuleRecord;
import cn.edu.hust.logMonitor.dao.RuleRecordDao;
import cn.edu.hust.logMonitor.dao.impl.RuleRecordDaoImpl;
import cn.edu.hust.logMonitor.utils.DataSourceUtils;
import cn.edu.hust.logMonitor.utils.LogUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Date;

public class RuleRecordDaoTest {
    JdbcTemplate jdbcTemplate;
    RuleRecordDao dao;
    @Before
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        dao= new RuleRecordDaoImpl(jdbcTemplate);
    }
    @Test
    public void getAllRuleRecord()
    {
        RuleRecord record=new RuleRecord();
        record.setAppId(1);
        record.setRuleId(1);
        record.setIsColse(1);
        record.setIsEmail(1);
        record.setIsPhone(1);
        record.setCreateDate(new Timestamp(new Date().getTime()));
        record.setUpdateDate(new Timestamp(new Date().getTime()));
        record.setNoticeInfo("触发了error");
        LogUtils.save2DB(record);
    }
}
