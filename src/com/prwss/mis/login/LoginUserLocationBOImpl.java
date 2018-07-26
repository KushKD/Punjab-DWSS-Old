package com.prwss.mis.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.login.dao.LoginUserLocationDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public abstract class LoginUserLocationBOImpl implements LoginBO {
	
	private Logger log = Logger.getLogger(LoginUserLocationBOImpl.class);
	protected LoginUserLocationDao loginUserLocationDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setLoginUserLocationDao(LoginUserLocationDao loginUserLocationDao) {
		this.loginUserLocationDao = loginUserLocationDao;
	}

	public Set<LocationBean> getUserLocations(String userId) throws MISException{
		
		Set<LocationBean> locationBeans = null;
		List<String> loginUserLocationBeans = null;
		
		try {
			loginUserLocationBeans = loginUserLocationDao.getLoginUserLocations(userId);
			if(!MisUtility.ifEmpty(loginUserLocationBeans)){
				locationBeans = locationDao.getLocationBeanOnLocationIdList(loginUserLocationBeans);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return locationBeans;
	}
	
	@Override
	public boolean saveUserLocations(String userId, Set<String> locationIds) throws MISException {
		boolean status = false;
		if(!MisUtility.ifEmpty(locationIds)){
			List<LoginUserLocationBean> loginUserLocationBeans = new ArrayList<LoginUserLocationBean>();
			LoginUserLocationBean loginUserLocationBean = null;
			for (String locationId : locationIds) {
				loginUserLocationBean = new LoginUserLocationBean();
				loginUserLocationBean.setLocationId(locationId);
				loginUserLocationBean.setUserId(userId);
				//loginUserLocationBean.setRowActive(MISConstants.MIS_USER_LOCATION_ACTIVE);
				loginUserLocationBeans.add(loginUserLocationBean);
				
			}
			status = loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans);
		}
		return status;
	}
}
