package cn.edu.hust.logMonitor.dao.impl;

import cn.edu.hust.logMonitor.bean.AppType;
import cn.edu.hust.logMonitor.dao.AppTypeDao;
import cn.edu.hust.logMonitor.utils.SQLPropertyParser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AppTypeDaoImpl implements AppTypeDao {
    private JdbcTemplate jdbcTemplate;

    public AppTypeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppType> getAllAppType() {
        String sql= SQLPropertyParser.getAllAppType();
        return this.jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<>(AppType.class));
    }
}
