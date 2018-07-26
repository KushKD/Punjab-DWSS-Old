package com.prwss.mis.masters.scheme.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CurrentFcVillageStatusDao {
	public List<CurrentFcVillageStatusBean> getVillageStatus(String villageId) throws DataAccessException;

}
