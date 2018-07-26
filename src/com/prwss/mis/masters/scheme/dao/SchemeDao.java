package com.prwss.mis.masters.scheme.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.village.dao.PrwssVillageViewBeanWithMhOh;

public interface SchemeDao {

public List<SchemeBean> findScheme(SchemeBean schemeBean,List<String> statusList) throws DataAccessException;
	
	public boolean saveScheme(SchemeBean schemeBean) throws DataAccessException;
	
	public boolean saveOrUpdateScheme(SchemeBean schemeBean) throws DataAccessException;
	
//rohit	public List<SchemeBean> findAllData(SchemeBean schemeBean)throws DataAccessException;
	
	
}
