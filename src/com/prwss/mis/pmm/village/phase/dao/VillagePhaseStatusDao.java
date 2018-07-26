package com.prwss.mis.pmm.village.phase.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillagePhaseStatusDao {
	
	public List<VillagePhaseStatusBean> findVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean) throws DataAccessException;

	public boolean updateVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean)	throws DataAccessException;


}
