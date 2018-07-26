package com.prwss.mis.login;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.dao.MenuBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.login.dao.LoginUserPermissionBean;
import com.prwss.mis.login.dao.LoginUserPermissionDao;

public abstract class LoginUserPermissionBOImpl extends LoginUserLocationBOImpl implements LoginBO {
	
	private Logger log = Logger.getLogger(LoginUserPermissionBOImpl.class);
	
	protected LoginUserPermissionDao loginUserPermissionDao;

	public void setLoginUserPermissionDao(LoginUserPermissionDao loginUserPermissionDao) {
		this.loginUserPermissionDao = loginUserPermissionDao;
	}

	@Override
	public Map<String,String> getUserMenuPermission(String roleId) throws MISException {
		List<LoginUserPermissionBean> loginUserPermissionBeans = null;
		Map<String,String> menuToolbarStringMap = new TreeMap<String, String>(); 
		StringBuffer menuStatus = null;
		try {
			loginUserPermissionBeans = loginUserPermissionDao.getUserSpecificMenus(roleId);
			Iterator<LoginUserPermissionBean> loginUserPermissinBeansIterator = loginUserPermissionBeans.iterator();
			LoginUserPermissionBean loginUserPermissionBean = null;
			while(loginUserPermissinBeansIterator.hasNext()){
				menuStatus = new StringBuffer();
				
				loginUserPermissionBean = loginUserPermissinBeansIterator.next();
				MenuBean menuBean=loginUserPermissionBean.getMenuBean();
				menuStatus.append((loginUserPermissionBean.isCanInquire()&& menuBean.isCanInquire())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanAdd()&& menuBean.isCanAdd())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanModify()&& menuBean.isCanModify())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanDelete()&& menuBean.isCanDelete())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanPost()&& menuBean.isCanPost())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanView()&& menuBean.isCanView())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanFile()&& menuBean.isCanFile())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanPrint()&& menuBean.isCanPrint())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanEmail()&& menuBean.isCanEmail())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanExport()&& menuBean.isCanExport())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanGraph()&& menuBean.isCanGraph())? "t" : "f");
				menuStatus.append((loginUserPermissionBean.isCanRepost()&& menuBean.isCanRepost())? "t" : "f");
				//System.out.println("loginUserPermissionBean.isCanRepost(): "+loginUserPermissionBean.isCanRepost());
				//System.out.println("menuBean.isCanRepost(): "+menuBean.isCanRepost());
				if(menuStatus.toString().contains("t")){
					//System.out.println("status of op :-"+menuStatus.toString());
					menuToolbarStringMap.put(loginUserPermissionBean.getMenuId(), menuStatus.toString());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return menuToolbarStringMap;
	}
	
	@Override
	public boolean saveUserPermission(Set<LoginUserPermissionBean> loginUserPermissionBeans) throws MISException {
		boolean status=false;
		
		try {
			status = loginUserPermissionDao.saveUserSpecificMenus(loginUserPermissionBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}  catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}


}
