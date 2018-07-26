/**
 * 
 */
package com.prwss.mis.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.admin.UserAdminBean;
import com.prwss.mis.admin.struts.UserMasterForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserDao;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.login.dao.LoginUserLocationDao;


/**
 * @author vgadiraju
 *
 */
public class LoginBOImpl extends LoginUserPermissionBOImpl implements LoginBO {
	private Logger log = Logger.getLogger(LoginBOImpl.class);
	private LoginUserDao loginUserDao;

	//private LoginUserLocationDao loginUserLocationDao;

//	private LoginUserLocationDao loginUserLocationDao;

	
	
    
	public void setLoginUserLocationDao(LoginUserLocationDao loginUserLocationDao) {
		this.loginUserLocationDao = loginUserLocationDao;
	}


	public void setLoginUserDao(LoginUserDao loginUserDao) {
		this.loginUserDao = loginUserDao;
	}


	@Override
	public LoginUserBean verifyLogin(String userName, String password) throws MISException {
		LoginUserBean loginUserBean;
		try {
			loginUserBean = loginUserDao.getUserDetails(userName.trim());
			if(!MisUtility.ifEmpty(loginUserBean))
				throw new MISException("User ID doesnt exist");
			
			if(!password.trim().equals(loginUserBean.getUserPassword())){
				throw new MISException("Invalid password for UserId\t"+userName);
			}
		} catch (DataAccessException e) {
			System.out.println("LoginBO DataAccesException !!!!!!----------------!!!!!!");
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			System.out.println("LoginBO Exception"+ e.getMessage());
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return loginUserBean;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public UserAdminBean findUserMaster(UserMasterForm userMasterForm,
			List<String> statusList) throws MISException {
		UserAdminBean userAdminBean =new UserAdminBean();
		List<LoginUserBean> loginUserBeans  = new ArrayList<LoginUserBean>();
		List <LoginUserLocationBean> userLocationsBeans = new ArrayList<LoginUserLocationBean>();
		try {
			LoginUserBean  loginUserBean =new LoginUserBean();
			System.out.println("loginUserBean-------->"+loginUserBean.toString());
//			loginUserBean.setoEmployeeId(userMasterForm.getEmployeeId());
			loginUserBean.setUserId(userMasterForm.getUserId());
//			loginUserBean.setRoleId(userMasterForm.getRoleId());
//			loginUserBean.setUserTelephone(userMasterForm.getUserMobile());
			loginUserBeans = loginUserDao.findLoginUser(loginUserBean, statusList);
			LoginUserLocationBean loginUserLocationBean = new LoginUserLocationBean();
			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			if(!MisUtility.ifEmpty(loginUserBeans)){
				userLocationsBeans= loginUserLocationDao.findUserLocation(loginUserLocationBean, statusList);
			}
			userAdminBean.setLoginUserBeans(loginUserBeans);
			userAdminBean.setUserLocationBeans(userLocationsBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		
		return userAdminBean;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveUserMaster(UserMasterForm userMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			
			LoginUserBean loginUserBean = populateUserMasterBean(userMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			loginUserBean.setMisAuditBean(misAuditBean);

			status = loginUserDao.saveLoginBean(loginUserBean);
			 
			if(status){
				List<LoginUserLocationBean> loginUserLocationBeans = populateUserLocationsBean(userMasterForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(loginUserLocationBeans)){
					boolean userLocationsStatus = loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans);
					if(!userLocationsStatus){
						log.error(loginUserLocationBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update User Locations details");
					}
				}

				
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		
		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateUserMaster(UserMasterForm userMasterForm,
			MISSessionBean misSessionBean) throws MISException {
boolean status = false;
		
try {
		
	LoginUserBean loginUserBean = populateUserMasterBean(userMasterForm);
	MISAuditBean misAuditBean = new MISAuditBean();
	misAuditBean.setEntBy(misSessionBean.getEnteredBy());
	misAuditBean.setEntDate(misSessionBean.getEnteredDate());
	misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
	loginUserBean.setMisAuditBean(misAuditBean);

	status = loginUserDao.updateLoginBean(loginUserBean);
	 
	if(status){
		List<LoginUserLocationBean> loginUserLocationBeans = populateUserLocationsBean(userMasterForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
		if(!MisUtility.ifEmpty(loginUserLocationBeans)){
			boolean userLocationsStatus = loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans);
			if(!userLocationsStatus){
				log.error(loginUserLocationBeans);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update User Locations details");
			}
		}

		
	}

} catch (DataAccessException e) {
	log.error(e.getLocalizedMessage(),e);
	throw new MISException(e);
} catch (Exception e) {
	log.error(e.getLocalizedMessage(),e);
	throw new MISException(e);
}


return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteUserMaster(UserMasterForm userMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			LoginUserBean loginUserBean = populateUserMasterBean(userMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			loginUserBean.setMisAuditBean(misAuditBean);
			status = loginUserDao.updateLoginBean(loginUserBean);
			LoginUserLocationBean  loginUserLocationBean = new LoginUserLocationBean();
			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			
				if(status){
					List<LoginUserLocationBean> loginUserLocationBeans = loginUserLocationDao.findUserLocation(loginUserLocationBean, statusList);
					for (LoginUserLocationBean loginUserLocationBean2 : loginUserLocationBeans) {
						
				
				
						misAuditBean = loginUserLocationBean2.getMisAuditBean();
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						loginUserLocationBean2.setMisAuditBean(misAuditBean);
					}
					
					if(!MisUtility.ifEmpty(loginUserLocationBeans)){
						if(!loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans)){
							throw new MISException(MISExceptionCodes.MIS003, "User Locations Details  not deleted for the User Id : "+userMasterForm.getUserId());
						}
					}
					
				}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postUserMaster(UserMasterForm userMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
			LoginUserBean loginUserBean = new LoginUserBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			loginUserBean.setUserId(userMasterForm.getUserId());
			loginUserBean = loginUserDao.findLoginUser(loginUserBean, statusList).get(0);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = loginUserBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			loginUserBean.setMisAuditBean(misAuditBean);
						
			status = loginUserDao.updateLoginBean(loginUserBean);
			
			LoginUserLocationBean  loginUserLocationBean = new LoginUserLocationBean();
			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			
				if(status){
					List<LoginUserLocationBean> loginUserLocationBeans = loginUserLocationDao.findUserLocation(loginUserLocationBean, statusList);
					for (LoginUserLocationBean loginUserLocationBean2 : loginUserLocationBeans) {
						
			
				
						misAuditBean = loginUserLocationBean2.getMisAuditBean();
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate((misSessionBean.getEnteredDate()));
						loginUserLocationBean2.setMisAuditBean(misAuditBean);
						
						
						
					
					}
					
					if(!MisUtility.ifEmpty(loginUserLocationBeans)){
						if(!loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans)){
							throw new MISException(MISExceptionCodes.MIS003, "User Locations Details  not approved for the User Id : "+userMasterForm.getUserId());
						}
					}
					
				}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
private LoginUserBean populateUserMasterBean(UserMasterForm userMasterForm){
		
	LoginUserBean loginUserBean = new LoginUserBean();
		try {
			loginUserBean.setUserId(userMasterForm.getUserId());
			loginUserBean.setUserName(userMasterForm.getUserName());
			loginUserBean.setUserPassword(userMasterForm.getUserPassword());
			
			//userMasterBean.setLastLoginOn(userMasterForm.getu);
			loginUserBean.setGender(userMasterForm.getGender());
			loginUserBean.setUserEmail(userMasterForm.getUserEmail());
			loginUserBean.setUserTelephone(userMasterForm.getUserMobile());
			loginUserBean.setUserAddress1(userMasterForm.getUserAddress1());
			loginUserBean.setUserAddress2(userMasterForm.getUserAddress2());
			loginUserBean.setUserAddress3(userMasterForm.getUserAddress3());
			loginUserBean.setRoleId(userMasterForm.getRoleId());
			loginUserBean.setEmployeeId(userMasterForm.getEmployeeId());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return loginUserBean;
	}

@SuppressWarnings("unchecked")
private List<LoginUserLocationBean> populateUserLocationsBean(UserMasterForm userMasterForm, MISSessionBean misSessionBean, String status){
	List <LoginUserLocationBean> loginUserLocationBeans = new ArrayList<LoginUserLocationBean>();
	MISAuditBean misAuditBean = new MISAuditBean();
	if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
	} else{
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
	}
	misAuditBean.setStatus(status);
	
	
	
	Collection<LoginUserLocationBean> addedLoginUserLocationBeans = userMasterForm.getUserLocationGrid().getAddedData();
	if(!MisUtility.ifEmpty(addedLoginUserLocationBeans)){
		for (LoginUserLocationBean loginUserLocationBean : addedLoginUserLocationBeans) {		

			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			loginUserLocationBean.setMisAuditBean(misAuditBean);
			loginUserLocationBeans.add(loginUserLocationBean);
		}
	}
	
	Collection<LoginUserLocationBean>  modifiedLoginUserLocationBeans = userMasterForm.getUserLocationGrid().getModifiedData();
	if(!MisUtility.ifEmpty(modifiedLoginUserLocationBeans)){
		for (LoginUserLocationBean loginUserLocationBean : modifiedLoginUserLocationBeans) {			
	

			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			loginUserLocationBean.setMisAuditBean(misAuditBean);
			loginUserLocationBeans.add(loginUserLocationBean);
		}
	}
	
	Collection<LoginUserLocationBean> deletedLoginUserLocationBeans = userMasterForm.getUserLocationGrid().getDeletedData();
	if(!MisUtility.ifEmpty(deletedLoginUserLocationBeans)){
		for (LoginUserLocationBean loginUserLocationBean : deletedLoginUserLocationBeans) {
			
		
			loginUserLocationBean.setUserId(userMasterForm.getUserId());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			loginUserLocationBean.setMisAuditBean(misAuditBean);
			loginUserLocationBeans.add(loginUserLocationBean);
		}
	}
	
	return loginUserLocationBeans;
}


@Override
public List<LoginUserBean> findUserLogin(String userId) throws MISException {
	List<LoginUserBean> loginUserBeans;
	try {
		LoginUserBean loginUserBean= new LoginUserBean();
		
		loginUserBean.setUserId(userId);
		
		loginUserBeans = loginUserDao.findLoginUser(loginUserBean, null);		
		
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	
	return loginUserBeans;
}


@Override
public boolean saveLoginUser(LoginUserBean loginUserBean) throws MISException {
	try {
		
		loginUserDao.updateLoginBean(loginUserBean);	
		
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	
	
	return true;
}



}
