package com.prwss.mis.admin.divisional.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface SubDivisionalDao {
	public List<SubDivisionalBean> findSubDivisional(SubDivisionalBean subdivisionalBean, List<String> statusList) throws DataAccessException;
	
	public List<SubDivisionalBean> findSubDivisional(List<String> subdivisionalIds) throws DataAccessException;

	public boolean saveSubDivisional(SubDivisionalBean subdivisionalBean) throws DataAccessException;

	public boolean updateSubDivisional(SubDivisionalBean subdivisionalBean)	throws DataAccessException;
	
	public boolean updateSubDivisional(List<SubDivisionalBean> subdivisionalBeans) throws DataAccessException;

	public Set<SubDivisionalBean> getDistinctSubDivisionalCodes() throws DataAccessException;

}
