package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageViewDao {
	
	public List<VillageViewBean> findVillageFromView(VillageBean villageBean, List<String> statusList) throws DataAccessException;

}
