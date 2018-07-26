package com.prwss.mis.masters.village.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface VillageDao {
	
	public Set<VillageBean> getVillageIds(String blockId) throws DataAccessException;
	
	public List<VillageBean> findVillage(VillageBean villageBean, List<String> statusList) throws DataAccessException;

	public List<VillageBean> findVillage1(VillageBean villageBean, List<String> statusList) throws DataAccessException;

	
	public String saveVillage(VillageBean villageBean) throws DataAccessException;

	public boolean updateVillage(VillageBean villageBeans)	throws DataAccessException;
	
	public List<VillageBean> getVillageOnHabitationType(String habitationType, String blockId) throws DataAccessException;
	
}
