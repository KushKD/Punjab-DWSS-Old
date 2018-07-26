package com.prwss.mis.login.changepassword.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class ChangePasswordForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8167359759684553754L;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String userName;
	private boolean submitForm;
	
	
	public boolean getSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "ChangePasswordForm [oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + ", userName=" + userName + ", submitForm="
				+ submitForm + "]";
	}
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode","d__mode");
		if(this.submitForm)
		{
		ActionErrors errors = super.validate(mapping, request);
		this.setSubmitForm(false);
		return errors;
		}
		else
		{
		return null;
		}

	}
	
}
