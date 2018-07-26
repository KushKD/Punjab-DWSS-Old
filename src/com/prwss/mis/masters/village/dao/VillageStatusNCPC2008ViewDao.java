package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageStatusNCPC2008ViewDao {

	public List<VillageStatusNCPCView2008Bean> find(VillageStatusNCPCView2008Bean bean) throws DataAccessException;
}
