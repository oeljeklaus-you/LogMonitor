package cn.edu.hust.logMonitor.dao.impl;

import cn.edu.hust.logMonitor.bean.Rule;
import cn.edu.hust.logMonitor.dao.RuleDao;
import cn.edu.hust.logMonitor.utils.SQLPropertyParser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RuleDaoImpl implements RuleDao {
    private JdbcTemplate jdbcTemplate;

    public RuleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rule> getAllRules()
    {
        String sql= SQLPropertyParser.getAllRulesSql();
        return this.jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<>(Rule.class));
    }
}
