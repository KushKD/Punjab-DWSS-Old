package com.prwss.mis.masters.location.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.WaterConnection.struts.WaterConnectionBean;
import com.prwss.mis.login.dao.LoginUserBean;

public interface LocationDao {
	
	public Set<LocationBean> getLocationIds(String locationType) throws DataAccessException ;
	
	public Set<LocationBean> getChildLocationIds(String parentLocationId, String locationType) throws DataAccessException ;
	public Set<LocationBean> getChildLocationIdss(String subDivisionId) throws DataAccessException ;

	
	
	public List<LocationBean> getChildLocationListIds(String parentLocationId, String locationType) throws DataAccessException ;
	
	public boolean saveLocation(LocationBean locationBean) throws DataAccessException;
	public LocationBean getLocation(LocationBean locationBean) throws DataAccessException;
	
	public Set<LocationBean> getLocationIdOnTypeList(List<String> locationTypeList) throws DataAccessException;
	
	public Set<LocationBean> getLocationBeanOnLocationIdList(List<String> locationIds) throws DataAccessException;
	
	public List<LocationBean> getLocationBeanOnLocationIdList2(List<String> locationIds) throws DataAccessException;
	
	public Set<LocationBean> getChildLocationIds(String parentLocationId, List<String> locationType)
	throws DataAccessException;
	 
	public Set<LocationBean> getLocationNameById(String locationId) throws DataAccessException ;

	/**
	 * Water Connection
	 */
	public WaterConnectionBean getApplicationName(String applicationId) throws DataAccessException;
	public String getApplicationStatus(String applicationId) throws DataAccessException;
	public WaterConnectionBean getApplicationfromId(String applicationId) throws DataAccessException;
	public List<WaterConnectionBean> getApplications(String UserId) throws DataAccessException;
	public Boolean updateWaterConnectionData(String status, String appId , String comments , String snum) throws DataAccessException, SQLException;
	public String getApplicationStatusComments(String applicationId) throws DataAccessException;

	public Set<LoginUserBean> getUserName(List<String> userId) throws DataAccessException ;
	public List<String> getUserId(String locationId) throws DataAccessException ;

}
