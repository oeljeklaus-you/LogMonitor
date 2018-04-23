package cn.edu.hust.logMonitor.Dao;

import cn.edu.hust.logMonitor.bean.Apps;
import cn.edu.hust.logMonitor.dao.AppDao;
import cn.edu.hust.logMonitor.dao.impl.AppDaoImpl;
import cn.edu.hust.logMonitor.utils.DataSourceUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AppDaoTest {
    JdbcTemplate jdbcTemplate;
    AppDao dao;
    @Before
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        dao=new AppDaoImpl(jdbcTemplate);
    }

    @Test
    public void getAllApps()
    {
        List<Apps> apps=dao.getAllApps();
        for (Apps app:
             apps) {
            System.out.println(app);
        }
    }
}
