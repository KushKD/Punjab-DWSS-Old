package com.prwss.mis.masters.iecactivity.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class IecActivityForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5612938426832433038L;
	private String iecActivityCode;
	private String iecActivityName;
	private String iecActivityDescription;
	private String[] iecActivityCodes;
	
	public String getIecActivityCode() {
		return iecActivityCode;
	}
	public void setIecActivityCode(String iecActivityCode) {
		this.iecActivityCode = iecActivityCode;
	}
	public String getIecActivityName() {
		return iecActivityName;
	}
	public void setIecActivityName(String iecActivityName) {
		this.iecActivityName = iecActivityName;
	}
	public String getIecActivityDescription() {
		return iecActivityDescription;
	}
	public void setIecActivityDescription(String iecActivityDescription) {
		this.iecActivityDescription = iecActivityDescription;
	}
	public void setIecActivityCodes(String[] iecActivityCodes) {
		this.iecActivityCodes = iecActivityCodes;
	}
	public String[] getIecActivityCodes() {
		return iecActivityCodes;
	}
	
}
