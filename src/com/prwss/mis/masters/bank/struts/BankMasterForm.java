package com.prwss.mis.masters.bank.struts;

import org.apache.struts.validator.ValidatorForm;

public class BankMasterForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6932082467431004620L;
	
	private long bankId;
	private String bankName;
	private String bankBranch;
	private String bankAddress;
	private String locationId;
	private long bankIds[];
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public long[] getBankIds() {
		return bankIds;
	}
	public void setBankIds(long[] bankIds) {
		this.bankIds = bankIds;
	}
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	
}
