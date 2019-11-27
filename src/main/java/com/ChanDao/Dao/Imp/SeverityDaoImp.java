package com.ChanDao.Dao.Imp;

import com.ChanDao.Dao.SeverityDao;
import com.ChanDao.mapper.SeverityMapper;
import com.ChanDao.model.Severity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Author ajiang
 * Created 2019/11/24 10:16
 */
public class SeverityDaoImp implements SeverityDao {

    private DataSource datasource;
    private JdbcTemplate jdbcTemplateObject;

    public void setdatasource(DataSource ds) {
        this.datasource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(datasource);
    }

    @Override
    public List<Severity> queryBugSeverity(int product, String assignedTo) {
        List<Severity> severitySummay = null;
        String sql = "SELECT severity,COUNT(severity) FROM zt_bug WHERE product = ? AND STATUS = \"active\" AND assignedTo = ? GROUP BY severity";
        severitySummay= jdbcTemplateObject.query(sql,new Object[]{product,assignedTo}, new SeverityMapper());
        return severitySummay;
    }
}
