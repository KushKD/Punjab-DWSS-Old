package com.prwss.mis.masters.activity.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ActivityDao {
	
//	public boolean deleteActivity(ActivityBean activityBean)	throws DataAccessException;

	public List<ActivityBean> findActivity(ActivityBean activityBean, List<String> statusList) throws DataAccessException;
	
	public List<ActivityBean> findActivity(List<String> activityIds) throws DataAccessException;

	public boolean saveActivity(ActivityBean activityBean) throws DataAccessException;

	public boolean updateActivity(ActivityBean activityBean)	throws DataAccessException;
	
	public boolean updateActivity(List<ActivityBean> activityBeans) throws DataAccessException;

//	public Set<ActivityBean> getDistinctActivityCodes() throws DataAccessException;

	
}
