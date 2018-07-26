package com.prwss.mis.daktask.inwarddak.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface InwardDakDao {
	
public List<InwardDakBean> getInwardDakBeans(InwardDakBean inwardDakBean, List<String> statusList) throws DataAccessException;
	
public boolean saveOrUpdateInwardDakBean(InwardDakBean inwardDakBean) throws DataAccessException;

}
