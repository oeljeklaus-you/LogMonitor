package cn.edu.hust.logMonitor.dao.impl;

import cn.edu.hust.logMonitor.bean.RuleRecord;
import cn.edu.hust.logMonitor.dao.RuleRecordDao;
import cn.edu.hust.logMonitor.utils.SQLPropertyParser;
import org.springframework.jdbc.core.JdbcTemplate;

public class RuleRecordDaoImpl implements RuleRecordDao {
    private JdbcTemplate jdbcTemplate;

    public RuleRecordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(RuleRecord record) {
        String sql= SQLPropertyParser.getInsert_RuleRecord_SQL();
        Object[] args=new Object[]{record.getAppId(),record.getRuleId(),record.getIsEmail(),record.getIsPhone(),record.getIsColse(),record.getNoticeInfo(),record.getCreateDate(),record.getUpdateDate()};
        this.jdbcTemplate.update(sql,args);
    }
}
