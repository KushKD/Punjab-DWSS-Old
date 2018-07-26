package com.prwss.mis.admin.dao;


import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.login.dao.LoginUserPermissionBean;
import com.prwss.mis.masters.component.dao.ComponentBean;

public interface RoleDao {
	
	public Set<RoleBean> getRoleIds() throws DataAccessException ;
	
	public List<RoleBean> findRole(RoleBean roleBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveRole(RoleBean roleBean) throws DataAccessException;

	public boolean updateRole(RoleBean roleBean)	throws DataAccessException;
	

}
