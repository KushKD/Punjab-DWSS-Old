package com.prwss.mis.tender.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TenderAwardForm extends ValidatorForm {

	
	/**
	 * Revision 2
	 */
	private static final long serialVersionUID = -6657106832689400420L;
	private String tenderNo;
	private String vendorId;
	private String locationId;
	private double tenderAwardAmount;
	private String contractNo;
	private String contractStartDate;
	private String contractEndDate;
	private String signingOfContract;
	private String noticeToProceed;
	
	private String bidEvaluationDate;
	public String getBidEvaluationDate() {
		return bidEvaluationDate;
	}

	public void setBidEvaluationDate(String bidEvaluationDate) {
		this.bidEvaluationDate = bidEvaluationDate;
	}
	
	private boolean objectionIfAny;
	private Datagrid securityDepositDatagrid;
	private Datagrid objectionDatagrid;
	private String tenderType;
	private String methodOfProcurement;
	
	public String getMethodOfProcurement() {
		return methodOfProcurement;
	}

	public void setMethodOfProcurement(String methodOfProcurement) {
		this.methodOfProcurement = methodOfProcurement;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public boolean isObjectionIfAny() {
		return objectionIfAny;
	}

	public void setObjectionIfAny(boolean objectionIfAny) {
		this.objectionIfAny = objectionIfAny;
	}

	public Datagrid getObjectionDatagrid() {
		return objectionDatagrid;
	}

	public void setObjectionDatagrid(Datagrid objectionDatagrid) {
		this.objectionDatagrid = objectionDatagrid;
	}
	public Datagrid getSecurityDepositDatagrid() {
		return securityDepositDatagrid;
	}

	public void setSecurityDepositDatagrid(Datagrid securityDepositDatagrid) {
		this.securityDepositDatagrid = securityDepositDatagrid;
	}

	public String getTenderNo() {
		return tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public double getTenderAwardAmount() {
		return tenderAwardAmount;
	}

	public void setTenderAwardAmount(double tenderAwardAmount) {
		this.tenderAwardAmount = tenderAwardAmount;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		
		this.contractStartDate = contractStartDate;
		
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
		
	}

	public String getSigningOfContract() {
		return signingOfContract;
	}

	public void setSigningOfContract(String signingOfContract) {
		this.signingOfContract = signingOfContract;
	}

	public String getNoticeToProceed() {
		return noticeToProceed;
	}

	public void setNoticeToProceed(String noticeToProceed) {
		this.noticeToProceed = noticeToProceed;
	}
	
	//OLD CODE
	@Override
	public String toString() {
		return "TenderAwardForm [tenderNo=" + tenderNo + ", vendorId=" + vendorId + ", tenderAwardAmount="
				+ tenderAwardAmount + ", contractNo=" + contractNo + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ",tenderType = "+tenderType+",methodOfProc="+methodOfProcurement+"]";
	}
	
	
}
