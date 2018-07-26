/**
 * 
 */
package com.prwss.mis.finance.accountschart.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author PJHA
 *
 */
public class AccountsChartForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8813762135029002048L;
	
	
	private String codeHeadId;
	private String codeHeadIdDescription;
	private String accountType;
	private String accountNature;
	
	private String majorHeadId;
	private String minorHeadId;
	private String subHeadId;
	
	
	
	public String getAccountNature() {
		return accountNature;
	}
	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}
	public String getCodeHeadIdDescription() {
		return codeHeadIdDescription;
	}
	public void setCodeHeadIdDescription(String codeHeadIdDescription) {
		this.codeHeadIdDescription = codeHeadIdDescription;
	}
	public String getCodeHeadId() {
		return codeHeadId;
	}
	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getMajorHeadId() {
		return majorHeadId;
	}
	public void setMajorHeadId(String majorHeadId) {
		this.majorHeadId = majorHeadId;
	}
	public String getMinorHeadId() {
		return minorHeadId;
	}
	public void setMinorHeadId(String minorHeadId) {
		this.minorHeadId = minorHeadId;
	}
	public String getSubHeadId() {
		return subHeadId;
	}
	public void setSubHeadId(String subHeadId) {
		this.subHeadId = subHeadId;
	}
	

}
