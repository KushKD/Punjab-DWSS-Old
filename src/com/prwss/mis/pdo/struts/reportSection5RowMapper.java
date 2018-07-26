package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reportSection5RowMapper  implements RowMapper<reportSection5Modal> {

	@Override
	public reportSection5Modal mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub

		reportSection5Modal wscsm = new reportSection5Modal();
	
		wscsm.setRoplant(rs.getString("roplant"));
		wscsm.setIs_ro_functional(rs.getString("is_ro_functional"));


		return wscsm;
	}

}