package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class reportSection6RowMapper implements RowMapper<reportSection6Modal> {

	@Override
	public reportSection6Modal mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub

		reportSection6Modal r6 = new reportSection6Modal();
	
		r6.setAluminium(rs.getString("aluminium"));
		r6.setLead(rs.getString("lead"));
		r6.setSelenium(rs.getString("selenium"));
		r6.setChromium(rs.getString("chromium"));
		r6.setMercury(rs.getString("mercury"));
		r6.setArsenic(rs.getString("arsenic"));
		r6.setCadium(rs.getString("cadium"));
		r6.setNikel(rs.getString("nikel"));
		r6.setTds(rs.getString("tds"));
		r6.setFlouride(rs.getString("flouride"));
		r6.setNitrate(rs.getString("nitrate"));
		r6.setIron(rs.getString("iron"));
		r6.setFailed_phase(rs.getString("failed_phase"));
		r6.setUranium_safe(rs.getString("uranium_safe"));
		r6.setUranium_failed_revised(rs.getString("uranium_failed_revised"));
		r6.setUranimum_failed_aerb(rs.getString("uranimum_failed_aerb"));
		r6.setUranimum_failed_conc(rs.getString("uranimum_failed_conc"));
		r6.setIs_ro_functional(rs.getString("is_ro_functional"));
		r6.setNo_of_failed_parameters_1(rs.getString("no_of_failed_parameters_1"));
		r6.setNo_of_failed_parameters_2(rs.getString("no_of_failed_parameters_2"));
		
	
		
		

		return r6;
	}

}
