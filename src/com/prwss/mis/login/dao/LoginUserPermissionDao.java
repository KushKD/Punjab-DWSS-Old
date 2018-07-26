package com.prwss.mis.login.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LoginUserPermissionDao {
	
	public List<LoginUserPermissionBean> getUserSpecificMenus(String roleId) throws DataAccessException;
	
	public boolean saveUserSpecificMenus(Collection<LoginUserPermissionBean> loginUserPermissionBeans) throws DataAccessException;

}
