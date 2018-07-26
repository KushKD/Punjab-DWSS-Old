package com.prwss.mis.masters.iecactivity.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface IECActivityDao {
	
	public List<IECActivityBean> findIECActivity(IECActivityBean iecActivityBean, List<String> statusList) throws DataAccessException;
	
	public List<IECActivityBean> findIECActivity(List<String> iecActivityIds) throws DataAccessException;
	
	public String saveIECActivity(IECActivityBean iecActivityBean) throws DataAccessException;

	public boolean updateIECActivity(IECActivityBean iecActivityBean)	throws DataAccessException;
	
	public boolean updateIECActivity(List<IECActivityBean> iecActivityBeans) throws DataAccessException;
	
	public Set<IECActivityBean> getDistinctIECActivityId() throws DataAccessException;

}
