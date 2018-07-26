package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class pmmtableRowMapper implements RowMapper<pmmtable> {

	@Override
	public pmmtable mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		pmmtable wscsm  = new pmmtable();
		wscsm.setDistrict_id(rs.getString("district_id"));
		wscsm.setPer_rural_households_as_survey(rs.getInt("per_rural_households_as_survey"));
		return wscsm;
	}

}
