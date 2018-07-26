package com.prwss.mis.finance.billapproval.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

public class BillApprovalForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -347678996379079943L;
	private String contractId;
	private String documentNo;
	private String billNo;
	private long milestonId;
	private String vendorId; // This field will be hidden in jsp
	private BigDecimal billAmount;
	private BigDecimal releasedAmount;
	private String locationId;
	private String documentReferenceNo;
	
	
	public String getDocumentReferenceNo() {
		return documentReferenceNo;
	}
	public void setDocumentReferenceNo(String documentReferenceNo) {
		this.documentReferenceNo = documentReferenceNo;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public long getMilestonId() {
		return milestonId;
	}
	public void setMilestonId(long milestonId) {
		this.milestonId = milestonId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	public BigDecimal getReleasedAmount() {
		return releasedAmount;
	}
	public void setReleasedAmount(BigDecimal releasedAmount) {
		this.releasedAmount = releasedAmount;
	}
	
}
