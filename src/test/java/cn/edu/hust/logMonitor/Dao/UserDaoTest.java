package cn.edu.hust.logMonitor.Dao;

import cn.edu.hust.logMonitor.bean.User;
import cn.edu.hust.logMonitor.dao.UserDao;
import cn.edu.hust.logMonitor.dao.impl.UserDaoImpl;
import cn.edu.hust.logMonitor.utils.DataSourceUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoTest {
    JdbcTemplate jdbcTemplate;
    UserDao dao;
    @Before
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        dao=new UserDaoImpl(jdbcTemplate);
    }

    @Test
    public void getAllUsers()
    {
        List<User> users=dao.getAllUser();
        for (User user:
             users) {
            System.out.println(user);
        }
    }
}
