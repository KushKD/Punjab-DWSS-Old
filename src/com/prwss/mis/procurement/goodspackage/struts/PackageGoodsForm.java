package com.prwss.mis.procurement.goodspackage.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageGoodsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005259226776584003L;
	private String packageId;
	private String locationId;
	private String planId;
	private long subPlanId;
	private long numberOfUnit;
	private double unitCost;
	private String schemeId;
	private String packageDescription;
	private String methodOfProcurement;
	private String designInvestigationDate;
	private String estPreparedSanctionDate;
	private String prepBidDocDate;
	private String bankNocBidDate;
	private String bidInvitationDate;
	private String bidOpeningDate;
	private String conAwardDecideDate;
	private String bankNocConAwardDate;
	private String conCompletionDate ;
	private String conSignDate;
	private String postPriorStatus;
	private Datagrid packageComponentsDatagrid;
	private BigDecimal estimatePackageCost;
	private String wingId;
	
	public String getPostPriorStatus() {
		return postPriorStatus;
	}
	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}
	
	public BigDecimal getEstimatePackageCost() {
		return estimatePackageCost;
	}
	public void setEstimatePackageCost(BigDecimal estimatePackageCost) {
		this.estimatePackageCost = estimatePackageCost;
	}
	public Datagrid getPackageComponentsDatagrid() {
		return packageComponentsDatagrid;
	}
	public void setPackageComponentsDatagrid(Datagrid packageComponentsDatagrid) {
		this.packageComponentsDatagrid = packageComponentsDatagrid;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	public long getNumberOfUnit() {
		return numberOfUnit;
	}
	public void setNumberOfUnit(long numberOfUnit) {
		this.numberOfUnit = numberOfUnit;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public String getMethodOfProcurement() {
		return methodOfProcurement;
	}
	public void setMethodOfProcurement(String methodOfProcurement) {
		this.methodOfProcurement = methodOfProcurement;
	}
	public String getDesignInvestigationDate() {
		return designInvestigationDate;
	}
	public void setDesignInvestigationDate(String designInvestigationDate) {
		this.designInvestigationDate = designInvestigationDate;
	}
	public String getEstPreparedSanctionDate() {
		return estPreparedSanctionDate;
	}
	public void setEstPreparedSanctionDate(String estPreparedSanctionDate) {
		this.estPreparedSanctionDate = estPreparedSanctionDate;
	}
	public String getPrepBidDocDate() {
		return prepBidDocDate;
	}
	public void setPrepBidDocDate(String prepBidDocDate) {
		this.prepBidDocDate = prepBidDocDate;
	}
	public String getBankNocBidDate() {
		return bankNocBidDate;
	}
	public void setBankNocBidDate(String bankNocBidDate) {
		this.bankNocBidDate = bankNocBidDate;
	}
	public String getBidInvitationDate() {
		return bidInvitationDate;
	}
	public void setBidInvitationDate(String bidInvitationDate) {
		this.bidInvitationDate = bidInvitationDate;
	}
	public String getBidOpeningDate() {
		return bidOpeningDate;
	}
	public void setBidOpeningDate(String bidOpeningDate) {
		this.bidOpeningDate = bidOpeningDate;
	}
	public String getConAwardDecideDate() {
		return conAwardDecideDate;
	}
	public void setConAwardDecideDate(String conAwardDecideDate) {
		this.conAwardDecideDate = conAwardDecideDate;
	}
	public String getBankNocConAwardDate() {
		return bankNocConAwardDate;
	}
	public void setBankNocConAwardDate(String bankNocConAwardDate) {
		this.bankNocConAwardDate = bankNocConAwardDate;
	}
	public String getConCompletionDate() {
		return conCompletionDate;
	}
	public void setConCompletionDate(String conCompletionDate) {
		this.conCompletionDate = conCompletionDate;
	}
	public String getConSignDate() {
		return conSignDate;
	}
	public void setConSignDate(String conSignDate) {
		this.conSignDate = conSignDate;
	}
	public void setWingId(String wingId) {
		this.wingId = wingId;
	}
	public String getWingId() {
		return wingId;
	}
	
	
}
