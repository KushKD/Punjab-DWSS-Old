package com.prwss.mis.procurement.servicespackage.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageServiceForm extends ValidatorForm {

	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 8131778047020178494L;
	private String packageId;
	private String locationId;
	private String planId;
	private String schemeId;
	private long subPlanId;
	private String packageDescription;
	private String methodOfSelection;
	private BigDecimal estimatePackageCost;
	private String dateAdvertisingShortlisting;
	private String rfpDraftBankDate;
	private String bankNocForTorDate;
	private String bankNocForShortlistDate;
	private String bankNocForRfpDate;
	private String rfpIssuedDate;
	private String torShortlistFinalizedDate;
	private String bankNocTechnicalDate;
	private String bankNocCombinedDate;
	private String evaluationFinalTechnicalDate;
	private String evaluationFinalCombinedDate;
	private String nocBankDraftDate;
	private String evaluationFinalDraftDate;
	private String bankNocFinalContractDate;
	private String evaluationFinalContractDate;
	private String proposalReciptDate;
	private String serviceCompletionDate;
	private String postPriorStatus;
	private Datagrid packageComponentsDatagrid;
	private String wingId;
	private String financialProposalOpeningDate;
	private String proposalReciptDateFinancial;
	private String projectCode;
	
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getPostPriorStatus() {
		return postPriorStatus;
	}
	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}
	public Datagrid getPackageComponentsDatagrid() {
		return packageComponentsDatagrid;
	}
	public void setPackageComponentsDatagrid(Datagrid packageComponentsDatagrid) {
		this.packageComponentsDatagrid = packageComponentsDatagrid;
	}
	public String getEvaluationFinalCombinedDate() {
		return evaluationFinalCombinedDate;
	}
	public void setEvaluationFinalCombinedDate(String evaluationFinalCombinedDate) {
		this.evaluationFinalCombinedDate = evaluationFinalCombinedDate;
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
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public String getMethodOfSelection() {
		return methodOfSelection;
	}
	public void setMethodOfSelection(String methodOfSelection) {
		this.methodOfSelection = methodOfSelection;
	}
	
	public BigDecimal getEstimatePackageCost() {
		return estimatePackageCost;
	}
	public void setEstimatePackageCost(BigDecimal estimatePackageCost) {
		this.estimatePackageCost = estimatePackageCost;
	}
	public String getDateAdvertisingShortlisting() {
		return dateAdvertisingShortlisting;
	}
	public void setDateAdvertisingShortlisting(String dateAdvertisingShortlisting) {
		this.dateAdvertisingShortlisting = dateAdvertisingShortlisting;
	}
	public String getRfpDraftBankDate() {
		return rfpDraftBankDate;
	}
	public void setRfpDraftBankDate(String rfpDraftBankDate) {
		this.rfpDraftBankDate = rfpDraftBankDate;
	}
	
	public String getBankNocForTorDate() {
		return bankNocForTorDate;
	}
	public void setBankNocForTorDate(String bankNocForTorDate) {
		this.bankNocForTorDate = bankNocForTorDate;
	}
	public String getBankNocForShortlistDate() {
		return bankNocForShortlistDate;
	}
	public void setBankNocForShortlistDate(String bankNocForShortlistDate) {
		this.bankNocForShortlistDate = bankNocForShortlistDate;
	}
	public String getBankNocForRfpDate() {
		return bankNocForRfpDate;
	}
	public void setBankNocForRfpDate(String bankNocForRfpDate) {
		this.bankNocForRfpDate = bankNocForRfpDate;
	}
	public String getRfpIssuedDate() {
		return rfpIssuedDate;
	}
	public void setRfpIssuedDate(String rfpIssuedDate) {
		this.rfpIssuedDate = rfpIssuedDate;
	}
	public String getTorShortlistFinalizedDate() {
		return torShortlistFinalizedDate;
	}
	public void setTorShortlistFinalizedDate(String torShortlistFinalizedDate) {
		this.torShortlistFinalizedDate = torShortlistFinalizedDate;
	}
	public String getBankNocTechnicalDate() {
		return bankNocTechnicalDate;
	}
	public void setBankNocTechnicalDate(String bankNocTechnicalDate) {
		this.bankNocTechnicalDate = bankNocTechnicalDate;
	}
	public String getBankNocCombinedDate() {
		return bankNocCombinedDate;
	}
	public void setBankNocCombinedDate(String bankNocCombinedDate) {
		this.bankNocCombinedDate = bankNocCombinedDate;
	}
	public String getEvaluationFinalTechnicalDate() {
		return evaluationFinalTechnicalDate;
	}
	public void setEvaluationFinalTechnicalDate(String evaluationFinalTechnicalDate) {
		this.evaluationFinalTechnicalDate = evaluationFinalTechnicalDate;
	}
	public String getNocBankDraftDate() {
		return nocBankDraftDate;
	}
	public void setNocBankDraftDate(String nocBankDraftDate) {
		this.nocBankDraftDate = nocBankDraftDate;
	}
	public String getEvaluationFinalDraftDate() {
		return evaluationFinalDraftDate;
	}
	public void setEvaluationFinalDraftDate(String evaluationFinalDraftDate) {
		this.evaluationFinalDraftDate = evaluationFinalDraftDate;
	}
	public String getBankNocFinalContractDate() {
		return bankNocFinalContractDate;
	}
	public void setBankNocFinalContractDate(String bankNocFinalContractDate) {
		this.bankNocFinalContractDate = bankNocFinalContractDate;
	}
	public String getEvaluationFinalContractDate() {
		return evaluationFinalContractDate;
	}
	public void setEvaluationFinalContractDate(String evaluationFinalContractDate) {
		this.evaluationFinalContractDate = evaluationFinalContractDate;
	}
	public String getProposalReciptDate() {
		return proposalReciptDate;
	}
	public void setProposalReciptDate(String proposalReciptDate) {
		this.proposalReciptDate = proposalReciptDate;
	}
	public String getServiceCompletionDate() {
		return serviceCompletionDate;
	}
	public void setServiceCompletionDate(String serviceCompletionDate) {
		this.serviceCompletionDate = serviceCompletionDate;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public void setWingId(String wingId) {
		this.wingId = wingId;
	}
	public String getWingId() {
		return wingId;
	}
	
	public String getFinancialProposalOpeningDate() {
		return financialProposalOpeningDate;
	}
	public void setFinancialProposalOpeningDate(String financialProposalOpeningDate) {
		this.financialProposalOpeningDate = financialProposalOpeningDate;
	}
	public void setProposalReciptDateFinancial(
			String proposalReciptDateFinancial) {
		this.proposalReciptDateFinancial = proposalReciptDateFinancial;
	}
	public String getProposalReciptDateFinancial() {
		return proposalReciptDateFinancial;
	}
	
	 
}
