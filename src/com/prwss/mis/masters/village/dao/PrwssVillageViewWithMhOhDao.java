package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PrwssVillageViewWithMhOhDao {

	public List<PrwssVillageViewBeanWithMhOh> find3016Villages(String villageId)throws DataAccessException;
}
