package com.prwss.mis.master.role.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.dao.RoleBean;
import com.prwss.mis.admin.dao.RoleDao;
import com.prwss.mis.admin.struts.RoleMasterForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.LoginUserLocationBOImpl;
import com.prwss.mis.login.dao.LoginUserPermissionBean;
import com.prwss.mis.login.dao.LoginUserPermissionDao;

public class RoleMasterBOImpl implements RoleMasterBO{

private Logger log = Logger.getLogger(RoleMasterBOImpl.class);
	
	private RoleDao roleDao;
	private LoginUserPermissionDao loginUserPermissionDao;
	
	public void setLoginUserPermissionDao(
			LoginUserPermissionDao loginUserPermissionDao) {
		this.loginUserPermissionDao = loginUserPermissionDao;
	}

	@Override
	public List<RoleBean> findRole(RoleMasterForm roleForm, List<String> statusList) throws MISException {
		List<RoleBean> roleList;
		try {
			System.out.println("----------RoleBO"+roleForm.getRoleId());
			RoleBean roleBean = new RoleBean();
			roleBean.setRoleId(roleForm.getRoleId());
			roleBean.setRoleName(roleForm.getRoleName());
			roleBean.setRoleDesc(roleForm.getRoleDesc());
			roleList = roleDao.findRole(roleBean, statusList);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			throw new MISException(e);
		}
		
		return roleList;
	}

	@Override
	public boolean saveRole(RoleMasterForm roleForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<RoleBean> roleBeans = null;
		RoleBean roleBean = new RoleBean();
		try {
			
			roleBean.setRoleId(roleForm.getRoleId());
			//User entered Value
			String roleName = roleForm.getRoleName();
			roleBeans = roleDao.findRole(roleBean, null);
			if(!MisUtility.ifEmpty(roleBeans)){					
				StringBuffer message = new StringBuffer();
					message.append(" Component code ").append(roleForm.getRoleId());
					if(roleName.equalsIgnoreCase(roleBeans.get(0).getRoleName())){
						message.append(" and Component Name ").append(roleName);
					}
				log.debug("Duplicate Entry for "+roleForm.getRoleId()+" and "+ roleForm.getRoleName());
				log.debug("Component Already exist \n"+roleBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			roleBean.setRoleName(roleForm.getRoleName());
			roleBean.setRoleDesc(roleForm.getRoleDesc());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			roleBean.setMisAuditBean(misAuditBean);
			
			result = roleDao.saveRole(roleBean);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}


	@Override
	public boolean updateRole(RoleMasterForm roleForm, MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean result = false;
		try {
			RoleBean roleBean = new RoleBean();
			roleBean.setRoleId(roleForm.getRoleId());
//			System.out.println("roleBean after change\t"+roleBean);
			roleBean.setRoleId(roleForm.getRoleId());
			roleBean.setRoleName(roleForm.getRoleName());
			roleBean.setRoleDesc(roleForm.getRoleDesc());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			roleBean.setMisAuditBean(misAuditBean);
//			System.out.println("before updating\t\n"+roleBean);
			result = roleDao.updateRole(roleBean);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}


	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public boolean deleteRole(RoleMasterForm roleForm,	MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		RoleBean bean = new RoleBean();
		bean.setRoleId(roleForm.getRoleId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		List<RoleBean> roleBeans = roleDao.findRole(bean, statusList);
		
		RoleBean roleBean = new RoleBean();
		try {
			MISAuditBean misAuditBean = null;
			for (RoleBean roleBean1 : roleBeans) {
			roleBean.setRoleId(roleBean1.getRoleId());
			roleBean.setRoleDesc(roleBean1.getRoleDesc());
			roleBean.setRoleName(roleBean1.getRoleName());
				misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				roleBean1.setMisAuditBean(misAuditBean);
			}
			result = roleDao.updateRole(roleBean);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postRole(RoleMasterForm roleForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<RoleBean> roleBeans = roleDao.findComponent(Arrays.asList(roleForm.getRoleIds()));
		List<LoginUserPermissionBean> userPermissionBeans = new ArrayList<LoginUserPermissionBean>();
		LoginUserPermissionBean userPermissionBean = new LoginUserPermissionBean();
		RoleBean roleBean = new RoleBean();
		roleBean.setRoleId(roleForm.getRoleId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			RoleBean roleBean2 = roleDao.findRole(roleBean, statusList).get(0);
//			MISAuditBean misAuditBean = null;
//			for (ComponentBean roleBean : roleBeans) {
//				misAuditBean = new MISAuditBean();
//				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
//				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
//				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
//				roleBean.setMisAuditBean(misAuditBean);
//			}
			
			MISAuditBean misAuditBean = roleBean2.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			roleBean2.setMisAuditBean(misAuditBean);
			result = roleDao.updateRole(roleBean2);
			
			userPermissionBean.setRoleId(roleForm.getRoleId());
			userPermissionBean.setMenuId("RPT001");
			userPermissionBean.setMenuName("Work Program");
			userPermissionBean.setMisAuditBean(misAuditBean);
			userPermissionBean.setCanAdd(false);
			userPermissionBean.setCanDelete(false);
			userPermissionBeans.add(userPermissionBean);
			loginUserPermissionDao.saveUserSpecificMenus(userPermissionBeans);
			
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	
}
