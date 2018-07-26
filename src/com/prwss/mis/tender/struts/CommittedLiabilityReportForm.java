package com.prwss.mis.tender.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class CommittedLiabilityReportForm extends ValidatorForm{
	private static final long serialVersionUID = 4971739949912412509L;
	
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String selectLocation="A";
	private String locationId;
	private String selectContract="S";
	private String contractId;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="A";
	
	
	public String getSelectLocation() {
		return selectLocation;
	}
	public void setSelectLocation(String selectLocation) {
		this.selectLocation = selectLocation;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getSelectContract() {
		return selectContract;
	}
	public void setSelectContract(String selectContract) {
		this.selectContract = selectContract;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		if(fromDate==null)
			this.fromDate=this.currentDate;
		else
			this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		if(toDate==null)
			this.toDate=this.currentDate;
		else
			this.toDate = toDate;		
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	@Override
	public String toString() {
		return "CommittedLiabilityReportForm [contractid=" + contractId
				+ ", selectContract=" + selectContract + ", selectLocation=" + selectLocation
				+ ", locationId=" + locationId + ", fromDate="
				+ fromDate + ", toDate="
				+ toDate + ", approvalStatus=" + approvalStatus+ "]";
	}
}
