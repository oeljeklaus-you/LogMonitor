package cn.edu.hust.logMonitor.dao.impl;

import cn.edu.hust.logMonitor.bean.User;
import cn.edu.hust.logMonitor.dao.UserDao;
import cn.edu.hust.logMonitor.utils.SQLPropertyParser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUser() {
        String sql= SQLPropertyParser.getUserSQL();
        return this.jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<User>(User.class));
    }
}
