package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PrwssVillageAllHabitationDao 
{
	public List<PrwssVillageAllHabitationBean> findPrwssAllHabitationView(VillageBean villageBean) throws DataAccessException;
}
