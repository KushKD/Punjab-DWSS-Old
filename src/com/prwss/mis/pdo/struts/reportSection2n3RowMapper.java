package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reportSection2n3RowMapper implements
		RowMapper<reportSection2n3Modal> {

	@Override
	public reportSection2n3Modal mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub

		reportSection2n3Modal wscsm = new reportSection2n3Modal();
		wscsm.setTot_sch(rs.getString("tot_sch"));

		wscsm.setAdmin_sch(rs.getString("admin_sch"));
		wscsm.setProc_sch(rs.getString("proc_sch"));
		wscsm.setComp_sch(rs.getString("comp_sch"));

		wscsm.setAdmin_sch_2a(rs.getString("admin_sch_2a"));
		wscsm.setProc_sch_2a(rs.getString("proc_sch_2a"));
		wscsm.setComp_sch_2a(rs.getString("comp_sch_2a"));

		wscsm.setAdmin_sch_1a(rs.getString("admin_sch_1a"));
		wscsm.setProc_sch_1a(rs.getString("proc_sch_1a"));
		wscsm.setComp_sch_1a(rs.getString("comp_sch_1a"));

		wscsm.setAdmin_sch_ndwp(rs.getString("admin_sch_nrdwp"));
		wscsm.setProc_sch_nrdwp(rs.getString("proc_sch_nrdwp"));
		wscsm.setComp_sch_nrdwp(rs.getString("comp_sch_nrdwp"));

		wscsm.setAdmin_sch_othr(rs.getString("admin_sch_othr"));
		wscsm.setProc_sch_othr(rs.getString("proc_sch_othr"));
		wscsm.setComp_sch_othr(rs.getString("comp_sch_othr"));

		wscsm.setAdmin_sch_3a(rs.getString("admin_sch_3a"));
		wscsm.setProc_sch_3a(rs.getString("proc_sch_3a"));
		wscsm.setComp_sch_3a(rs.getString("comp_sch_3a"));

		wscsm.setAdmin_sch_3b(rs.getString("admin_sch_3b"));
		wscsm.setProc_sch_3b(rs.getString("proc_sch_3b"));
		wscsm.setComp_sch_3b(rs.getString("comp_sch_3b"));

		wscsm.setAdmin_sch_othr3(rs.getString("admin_sch_othr3"));
		wscsm.setProc_sch_othr3(rs.getString("proc_sch_othr3"));
		wscsm.setComp_sch_othr3(rs.getString("comp_sch_othr3"));

		return wscsm;
	}

}
