package cn.edu.hust.logMonitor.Dao;

import cn.edu.hust.logMonitor.bean.AppType;
import cn.edu.hust.logMonitor.dao.AppTypeDao;
import cn.edu.hust.logMonitor.dao.impl.AppTypeDaoImpl;
import cn.edu.hust.logMonitor.utils.DataSourceUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AppTypeDaoTest {
    JdbcTemplate jdbcTemplate;
    AppTypeDao dao;
    @Before
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        dao=new AppTypeDaoImpl(jdbcTemplate);
    }

    @Test
    public void getAllAppType()
    {
        List<AppType> appTypeList=dao.getAllAppType();
        for (AppType types:
                appTypeList) {
            System.out.println(types);
        }
    }
}
