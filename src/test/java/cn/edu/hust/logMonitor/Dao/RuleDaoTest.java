package cn.edu.hust.logMonitor.Dao;

import cn.edu.hust.logMonitor.bean.Rule;
import cn.edu.hust.logMonitor.dao.RuleDao;
import cn.edu.hust.logMonitor.dao.impl.RuleDaoImpl;
import cn.edu.hust.logMonitor.utils.DataSourceUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RuleDaoTest {
    JdbcTemplate jdbcTemplate;
    RuleDao dao;
    @Before
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        dao= new RuleDaoImpl(jdbcTemplate);
    }
    @Test
    public void getAllRules()
    {
        List<Rule> rules=dao.getAllRules();
        for (Rule rule:
             rules) {
            System.out.println(rule);
        }
    }


}
