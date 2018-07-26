/**
 * 
 */
package com.prwss.mis.login;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.prwss.mis.admin.UserAdminBean;
import com.prwss.mis.admin.struts.UserMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserPermissionBean;
import com.prwss.mis.masters.location.dao.LocationBean;

/**
 * @author vgadiraju
 *
 */
public interface LoginBO {
	
	public LoginUserBean verifyLogin(String userId, String password) throws MISException;
	
	public List<LoginUserBean> findUserLogin(String userId) throws MISException;
	
	public Map<String,String> getUserMenuPermission(String userId) throws MISException;
	public boolean saveLoginUser(LoginUserBean loginUserBean) throws MISException;
	
	public Set<LocationBean> getUserLocations(String userId) throws MISException;
	
	public boolean saveUserLocations(String userId, Set<String> locationIds) throws MISException;
	
	public boolean saveUserPermission(Set<LoginUserPermissionBean> loginUserPermissionBeans) throws MISException;

	public UserAdminBean findUserMaster(UserMasterForm userMasterForm, List<String> statusList) throws MISException;
	
	public boolean saveUserMaster(UserMasterForm  userMasterForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateUserMaster(UserMasterForm  userMasterForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteUserMaster(UserMasterForm  userMasterForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postUserMaster(UserMasterForm  userMasterForm,  MISSessionBean misSessionBean) throws MISException;

//	public List<LoginUserPermissionBean> populateRolePermissionBean(RolePermissionForm rolePermissionForm) throws MISException;
	
}
