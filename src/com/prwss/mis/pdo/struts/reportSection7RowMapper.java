package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reportSection7RowMapper implements RowMapper<reportSection7Modal> {

	@Override
	public reportSection7Modal mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub

		reportSection7Modal r6 = new reportSection7Modal();
	
		r6.setScheme(rs.getString("scheme"));
		r6.setVillage(rs.getString("village"));
		r6.setHouseholds(rs.getString("households"));
		r6.setSew_connection(rs.getString("sew_connection"));
		r6.setConnection_percent(rs.getString("connection_percent"));
		
	
		
		

		return r6;
	}

}
