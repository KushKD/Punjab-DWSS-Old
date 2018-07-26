package com.prwss.mis.daktask.outwarddak.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface OutwardDakDao {
	public List<OutwardDakBean> getOutwardDakBeans(OutwardDakBean outwardDakBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateOutwardDakBean(OutwardDakBean outwardDakBean) throws DataAccessException;
	
	public boolean save(OutwardDakBean outwardDakBean) throws DataAccessException;

}
