package com.prwss.mis.pdo.struts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WaterSupplyCoverageStatusModelRowmapper implements RowMapper<WaterSupplyCoverageStatusModel> {

	@Override
	public WaterSupplyCoverageStatusModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		WaterSupplyCoverageStatusModel wscsm  = new WaterSupplyCoverageStatusModel();
		
		wscsm.setTotalVillage(rs.getInt("total_village"));
		wscsm.setPopulation2011(rs.getInt("population_2011"));
		wscsm.setHousehold2011(rs.getInt("households_2011"));
		wscsm.setVillageBechirag(rs.getInt("village_bechirag"));
		wscsm.setOwn(rs.getInt("village_own"));
		wscsm.setFcPws(rs.getInt("fc_pws"));
		wscsm.setFcHP(rs.getInt("fc_hp"));
		wscsm.setFcOwn(rs.getInt("fc_own"));
		wscsm.setPcPws(rs.getInt("pc_pws"));
		wscsm.setPcHp(rs.getInt("pc_hp"));
		wscsm.setPcOwn(rs.getInt("pc_own"));
		wscsm.setNcPws(rs.getInt("nc_pws"));
		wscsm.setPipedSCH(rs.getInt("piped_sch"));
		wscsm.setPipedVillage(rs.getInt("piped_village"));
		wscsm.setHpSch(rs.getInt("hp_sch"));
		wscsm.setHpVillage(rs.getInt("hp_village"));
		wscsm.setPwsConnection(rs.getInt("pws_connection"));
		
		return wscsm;
	}

}
