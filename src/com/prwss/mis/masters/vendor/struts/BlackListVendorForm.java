package com.prwss.mis.masters.vendor.struts;

import org.apache.struts.validator.ValidatorForm;

public class BlackListVendorForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1419155707224338657L;
	private String vendorId;
	private String vendorName;
	private String reason;
	private String blackListFrom;
	private String blackListTo;
	
	
	
	
	public String getVendorId() {
		return vendorId;
	}




	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}




	public String getVendorName() {
		return vendorName;
	}




	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}




	public String getReason() {
		return reason;
	}




	public void setReason(String reason) {
		this.reason = reason;
	}




	public String getBlackListFrom() {
		return blackListFrom;
	}




	public void setBlackListFrom(String blackListFrom) {
		this.blackListFrom = blackListFrom;
	}




	public String getBlackListTo() {
		return blackListTo;
	}




	public void setBlackListTo(String blackListTo) {
		this.blackListTo = blackListTo;
	}




	@Override
	public String toString() {
		return "BlackListVendorForm [vendorId=" + vendorId+ ", vendorName="
				+ vendorName + ", reason=" + reason+ ", blackListFrom="
				+ blackListFrom + ", blackListTo=" + blackListTo + "]";
	}
	
}
