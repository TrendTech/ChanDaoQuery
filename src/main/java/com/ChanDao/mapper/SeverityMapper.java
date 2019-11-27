package com.ChanDao.mapper;


import com.ChanDao.model.Bug;
import com.ChanDao.model.Severity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author ajiang
 */

public class SeverityMapper implements RowMapper<Severity> {

	public Severity mapRow(ResultSet rs, int rownum) throws SQLException {
		Severity severity = new Severity();
		severity.setSeverity(rs.getInt(1));
		severity.setSummary(rs.getInt(2));
		return severity;
	}
}