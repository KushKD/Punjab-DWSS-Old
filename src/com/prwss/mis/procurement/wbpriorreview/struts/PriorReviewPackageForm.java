package com.prwss.mis.procurement.wbpriorreview.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

public class PriorReviewPackageForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5371452318985150835L;
	private String locationId;
	private String typeOfProcurement;
	private String planId;
	private long subPlanId;
	private BigDecimal estimatedPackageCost;
	private String packageId;
	private String postPriorStatus;
	private String wbNocDate;
	private String wbNumber;
	private String wbRemarks ;
	private String wbStatus ;
	private String wbContractAwardNocDate;
	private String wbBidDocNocDate;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getTypeOfProcurement() {
		return typeOfProcurement;
	}
	public void setTypeOfProcurement(String typeOfProcurement) {
		this.typeOfProcurement = typeOfProcurement;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public long getSubPlanId() {
		return subPlanId;
	}
	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}
	
	public BigDecimal getEstimatedPackageCost() {
		return estimatedPackageCost;
	}
	public void setEstimatedPackageCost(BigDecimal estimatedPackageCost) {
		this.estimatedPackageCost = estimatedPackageCost;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPostPriorStatus() {
		return postPriorStatus;
	}
	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}
	public String getWbNocDate() {
		return wbNocDate;
	}
	public void setWbNocDate(String wbNocDate) {
		this.wbNocDate = wbNocDate;
	}
	public String getWbNumber() {
		return wbNumber;
	}
	public void setWbNumber(String wbNumber) {
		this.wbNumber = wbNumber;
	}
	public String getWbRemarks() {
		return wbRemarks;
	}
	public void setWbRemarks(String wbRemarks) {
		this.wbRemarks = wbRemarks;
	}
	public String getWbStatus() {
		return wbStatus;
	}
	public void setWbStatus(String wbStatus) {
		this.wbStatus = wbStatus;
	}
	public void setWbContractAwardNocDate(String wbContractAwardNocDate) {
		this.wbContractAwardNocDate = wbContractAwardNocDate;
	}
	public String getWbContractAwardNocDate() {
		return wbContractAwardNocDate;
	}
	public void setWbBidDocNocDate(String wbBidDocNocDate) {
		this.wbBidDocNocDate = wbBidDocNocDate;
	}
	public String getWbBidDocNocDate() {
		return wbBidDocNocDate;
	}
	
	
}
