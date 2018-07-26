/**
 * 
 */
package com.prwss.mis.login;

import org.apache.struts.validator.ValidatorForm;


/**
 * @author vgadiraju
 *
 */
public class LoginForm extends ValidatorForm {
	
	/**
	 * Initial Version
	 */
	
	private static final long serialVersionUID = -4697683491276854384L;
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.trim();
	}
	
}
