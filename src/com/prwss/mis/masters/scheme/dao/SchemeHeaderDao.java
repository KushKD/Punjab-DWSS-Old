package com.prwss.mis.masters.scheme.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.scheme.struts.SchemeVillagesBean;

public interface SchemeHeaderDao {
public  List<SchemeHeaderBean>  findSchemeHeader(SchemeHeaderBean schemeHeaderBean, List<String> statusList ) throws DataAccessException;	
	public boolean saveSchemeHeader(SchemeHeaderBean schemeHeaderBean) throws DataAccessException;
	public boolean saveOrUpdateSchemeHeader(SchemeHeaderBean schemeHeaderBean) throws DataAccessException;
	public boolean updateSchemeHeader(SchemeHeaderBean schemeHeaderBean)
			throws DataAccessException;

	public List<SchemeVillagesBean> getSchemevillages(SchemeVillagesBean schemeVillagesBean) throws DataAccessException;
	
	
}


