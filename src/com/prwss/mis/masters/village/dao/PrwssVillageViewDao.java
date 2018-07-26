package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PrwssVillageViewDao {
	
	public List<PrwssVillageViewBean> findPrwssVillageFromView(VillageBean villageBean) throws DataAccessException;

 

}
