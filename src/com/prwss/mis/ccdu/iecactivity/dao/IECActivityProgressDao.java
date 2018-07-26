package com.prwss.mis.ccdu.iecactivity.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface IECActivityProgressDao {
	
	public List<IECActivityProgressBean> findCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean, List<String> statusList) throws DataAccessException;
	
	public long saveCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean) throws DataAccessException;

	public boolean updateCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean)	throws DataAccessException;
	
	public Set<IECActivityProgressBean> getCBIECActivityProgressIds(String villageId) throws DataAccessException;

}
