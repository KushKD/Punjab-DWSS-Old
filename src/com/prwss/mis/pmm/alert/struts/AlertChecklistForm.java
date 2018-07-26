package com.prwss.mis.pmm.alert.struts;

import org.apache.struts.validator.ValidatorActionForm;

public class AlertChecklistForm extends ValidatorActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2942452064756711761L;

	private String locationId;
	
	private String tMonth;
	
	private long tYear;
	
	private boolean adminApproval;
	
	private boolean schemeCommissioning;
	
	private boolean waterConnection;
	
	private boolean household;
	
	private boolean operationSustainability;
	
	private boolean iecTraining;
	private boolean beneficiayShare;
	private boolean spmcPaymentVoucher;
	private boolean dpmcPaymentVoucher;
	private boolean gpwscRegisterEntry;
	private boolean updationProcurementPlan;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String gettMonth() {
		return tMonth;
	}

	public void settMonth(String tMonth) {
		this.tMonth = tMonth;
	}

	public long gettYear() {
		return tYear;
	}

	public void settYear(long tYear) {
		this.tYear = tYear;
	}

	public boolean isAdminApproval() {
		return adminApproval;
	}

	public void setAdminApproval(boolean adminApproval) {
		this.adminApproval = adminApproval;
	}

	public boolean isSchemeCommissioning() {
		return schemeCommissioning;
	}

	public void setSchemeCommissioning(boolean schemeCommissioning) {
		this.schemeCommissioning = schemeCommissioning;
	}

	public boolean isWaterConnection() {
		return waterConnection;
	}

	public void setWaterConnection(boolean waterConnection) {
		this.waterConnection = waterConnection;
	}

	public boolean isHousehold() {
		return household;
	}

	public void setHousehold(boolean household) {
		this.household = household;
	}

	public boolean isOperationSustainability() {
		return operationSustainability;
	}

	public void setOperationSustainability(boolean operationSustainability) {
		this.operationSustainability = operationSustainability;
	}

	public boolean isIecTraining() {
		return iecTraining;
	}

	public void setIecTraining(boolean iecTraining) {
		this.iecTraining = iecTraining;
	}

	public boolean isBeneficiayShare() {
		return beneficiayShare;
	}

	public void setBeneficiayShare(boolean beneficiayShare) {
		this.beneficiayShare = beneficiayShare;
	}

	public boolean isSpmcPaymentVoucher() {
		return spmcPaymentVoucher;
	}

	public void setSpmcPaymentVoucher(boolean spmcPaymentVoucher) {
		this.spmcPaymentVoucher = spmcPaymentVoucher;
	}

	public boolean isDpmcPaymentVoucher() {
		return dpmcPaymentVoucher;
	}

	public void setDpmcPaymentVoucher(boolean dpmcPaymentVoucher) {
		this.dpmcPaymentVoucher = dpmcPaymentVoucher;
	}

	public boolean isGpwscRegisterEntry() {
		return gpwscRegisterEntry;
	}

	public void setGpwscRegisterEntry(boolean gpwscRegisterEntry) {
		this.gpwscRegisterEntry = gpwscRegisterEntry;
	}

	public boolean isUpdationProcurementPlan() {
		return updationProcurementPlan;
	}

	public void setUpdationProcurementPlan(boolean updationProcurementPlan) {
		this.updationProcurementPlan = updationProcurementPlan;
	}
	

}
