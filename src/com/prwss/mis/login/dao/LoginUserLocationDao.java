package com.prwss.mis.login.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LoginUserLocationDao {
	
	public List<String> getLoginUserLocations(String userId) throws DataAccessException;
	
	public boolean saveOrUpdateLoginUserLocations(Collection<LoginUserLocationBean> loginUserLocationBeans) throws DataAccessException;
	public List<LoginUserLocationBean> findUserLocation(LoginUserLocationBean userLocationBean,List<String> statusList) throws DataAccessException;
}
