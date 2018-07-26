package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageSchemeViewDao {
	public List<VillageSchemeViewBean> findVillageSchemeFromView(VillageSchemeViewBean villageSchemeViewBean, List<String> statusList) throws DataAccessException;
}
