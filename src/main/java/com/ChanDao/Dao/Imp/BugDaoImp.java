package com.ChanDao.Dao.Imp;

import com.ChanDao.Dao.BugDao;
import com.ChanDao.controller.BugMapper;
import com.ChanDao.model.Bug;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;

/**
 * Author ajiang
 */

public class BugDaoImp implements BugDao {

    private DataSource datasource;
    private JdbcTemplate jdbcTemplateObject;

    public void setdatasource(DataSource ds) {
        this.datasource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(datasource);
    }

    @Override
    public List<Bug> allbug(int product) {
        List<Bug> bugs = null;
        String sql = "SELECT * FROM zt_bug WHERE product=?";
        bugs = jdbcTemplateObject.query(sql,new Object[]{product}, new BugMapper());
        return bugs;
    }

    @Override
    public List<String> queryAssignedTo(int product) {
        List<String> assignedTos = null;
        String sql = "SELECT assignedTo FROM zt_bug WHERE product=? AND STATUS = \"active\" GROUP BY assignedTo";
        assignedTos = jdbcTemplateObject.queryForList(sql,new Object[]{product},String.class);
        return assignedTos;
    }

    @Override
    public List<Bug> queryAssignedToBugs(int product, String assignedTo) {
        List<Bug> bugs = null;
        String sql = "SELECT * FROM zt_bug WHERE product = ? AND STATUS = \"active\" AND assignedTo = ? ORDER BY assignedDate";
        bugs = jdbcTemplateObject.query(sql,new Object[]{product,assignedTo}, new BugMapper());
        return bugs;
    }

}
