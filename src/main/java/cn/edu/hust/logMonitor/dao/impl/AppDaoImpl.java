package cn.edu.hust.logMonitor.dao.impl;

import cn.edu.hust.logMonitor.bean.Apps;
import cn.edu.hust.logMonitor.dao.AppDao;
import cn.edu.hust.logMonitor.utils.SQLPropertyParser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AppDaoImpl implements AppDao {
    private JdbcTemplate jdbcTemplate;

    public AppDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Apps> getAllApps() {
        String sql= SQLPropertyParser.getAllAppSQL();
        return this.jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<Apps>(Apps.class));
    }
}
