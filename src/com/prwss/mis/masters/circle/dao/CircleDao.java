package com.prwss.mis.masters.circle.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface CircleDao {
	
	public  List<CircleBean> findCircle(CircleBean circleBean, List<String> statusList) throws DataAccessException;
	public  List<CircleBean> findCircle(List<String> circleIdList) throws DataAccessException;
	public boolean saveCircle(CircleBean circleBean) throws DataAccessException;
	public boolean updateCircle(CircleBean circleBean) throws DataAccessException;
	/*public Set<CircleBean> getDistinctCircleCodes() throws DataAccessException;
	*/public boolean updateCircle(List<CircleBean> circleBeans) throws DataAccessException;
	public  Set<CircleBean> getDistinctCircleCodes(List<String> statusList)
			throws DataAccessException;

}
