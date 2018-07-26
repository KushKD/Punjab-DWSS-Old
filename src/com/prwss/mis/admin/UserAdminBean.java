/**
 * 
 */
package com.prwss.mis.admin;

import java.io.Serializable;
import java.util.List;

import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

/**
 * @author pjha
 *
 */
public class UserAdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8205947660618268359L;
	
	
	private List<LoginUserBean> loginUserBeans = null;
	
	private List<LoginUserLocationBean> userLocationBeans=null;

	public List<LoginUserBean> getLoginUserBeans() {
		return loginUserBeans;
	}

	public void setLoginUserBeans(List<LoginUserBean> loginUserBeans) {
		this.loginUserBeans = loginUserBeans;
	}

	public List<LoginUserLocationBean> getUserLocationBeans() {
		return userLocationBeans;
	}

	public void setUserLocationBeans(List<LoginUserLocationBean> userLocationBeans) {
		this.userLocationBeans = userLocationBeans;
	}

	@Override
	public String toString() {
		return "UserAdminBean [loginUserBeans=" + loginUserBeans
				+ ", userLocationBeans=" + userLocationBeans + "]";
	}

	
	
	

}
