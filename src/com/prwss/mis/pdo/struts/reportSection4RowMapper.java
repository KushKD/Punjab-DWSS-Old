package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reportSection4RowMapper implements RowMapper<reportSection4Modal> {

	@Override
	public reportSection4Modal mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub

		reportSection4Modal wscsm = new reportSection4Modal();
		wscsm.setGpwsc_sch(rs.getString("gpwsc_sch"));

		wscsm.setGpwsc_vill(rs.getString("gpwsc_vill"));
		wscsm.setDwss_sch(rs.getString("dwss_sch"));
		wscsm.setDwss_vill(rs.getString("dwss_vill"));

		wscsm.setConn_vill(rs.getString("conn_vill"));
		wscsm.setHrs_vill(rs.getString("hrs_vill"));
		wscsm.setLpcd_vill(rs.getString("lpcd_vill"));


		return wscsm;
	}

}
