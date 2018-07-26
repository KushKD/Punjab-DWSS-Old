package com.prwss.mis.masters.component.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ComponentDao {

	public ComponentBean loadComponent(String componentId) throws DataAccessException;

	public List<ComponentBean> findComponent(ComponentBean componentBean, List<String> statusList) throws DataAccessException;
	
	public List<ComponentBean> findComponentForRequestLoc(ComponentBean componentBean, List<String> statusList) throws DataAccessException;
	
	public List<ComponentBean> findComponent(List<String> componentIds) throws DataAccessException;

	public boolean saveComponent(ComponentBean componentBean) throws DataAccessException;

	public boolean updateComponent(ComponentBean componentBean)	throws DataAccessException;
	
	public boolean updateComponent(List<ComponentBean> componentBeans) throws DataAccessException;

	public Set<ComponentBean> getDistinctComponentCodes() throws DataAccessException;

}
