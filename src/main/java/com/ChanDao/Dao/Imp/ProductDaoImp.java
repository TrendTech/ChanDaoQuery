package com.ChanDao.Dao.Imp;

import com.ChanDao.Dao.ProductDao;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


/**
 * Author ajiang
 * Created 2019/11/24 13:14
 */
public class ProductDaoImp implements ProductDao {

    private DataSource datasource;
    private JdbcTemplate jdbcTemplateObject;

    public void setdatasource(DataSource ds) {
        this.datasource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(datasource);
    }

    @Override
    public String queryProductName(int product) {
        String productName = null;
        String sql = "SELECT name FROM zt_product WHERE id = ?";
        productName = (String)jdbcTemplateObject.queryForObject(sql,new Object[]{product},String.class);
        return productName;
    }
}
