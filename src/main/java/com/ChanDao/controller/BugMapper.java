package com.ChanDao.controller;


import com.ChanDao.model.Bug;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Author ajiang
 */

public class BugMapper implements RowMapper<Bug> {

	public Bug mapRow(ResultSet rs, int rownum) throws SQLException {
		Bug bug = new Bug();
		bug.setProduct(rs.getString("product"));
		bug.setProject(rs.getString("project"));
		bug.setTitle(rs.getString("title"));
		bug.setSeverity(rs.getInt("severity"));
		bug.setPri(rs.getInt("pri"));
		bug.setStatus(rs.getString("status"));
		bug.setConfirmed(rs.getInt("confirmed"));
		bug.setActivatedCount(rs.getInt("activatedCount"));
		bug.setOpenedDate(rs.getTimestamp("openedDate"));
		bug.setAssignedDate(rs.getTimestamp("assignedDate"));
		bug.setAssignedTo(rs.getString("assignedTo"));
		return bug;
	}
}