package com.prwss.mis.daktask.inwarddak.struts;

import org.apache.struts.validator.ValidatorForm;

public class InwardDakForm extends ValidatorForm {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 4106139764488102430L;
	@Override
	public String toString() {
		return "InwardDakForm [locationId=" + locationId + ", documentNo="
				+ documentNo + ", documentType=" + documentType
				+ ", documentReferenceNo=" + documentReferenceNo
				+ ", recieptDate=" + recieptDate + ", senderName=" + senderName
				+ ", subject=" + subject + ", senderAddress=" + senderAddress
				+ ", committeeId=" + committeeId + "]";
	}
	private String locationId;
	private String documentNo;
	private String documentType;
	private String documentReferenceNo;
	private String recieptDate;
	private String senderName;
	private String subject;
	private String senderAddress;
	private String committeeId;
	private String forwardedTo;
	
	
	
	public String getForwardedTo() {
		return forwardedTo;
	}
	public void setForwardedTo(String forwardedTo) {
		this.forwardedTo = forwardedTo;
	}
	public String getRecieptDate() {
		return recieptDate;
	}
	public void setRecieptDate(String recieptDate) {
		this.recieptDate = recieptDate;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(String committeeId) {
		this.committeeId = committeeId;
	}
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentReferenceNo() {
		return documentReferenceNo;
	}
	public void setDocumentReferenceNo(String documentReferenceNo) {
		this.documentReferenceNo = documentReferenceNo;
	}
	
	
    
}
