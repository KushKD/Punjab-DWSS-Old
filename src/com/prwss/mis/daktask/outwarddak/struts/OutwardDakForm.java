package com.prwss.mis.daktask.outwarddak.struts;

import org.apache.struts.validator.ValidatorForm;

public class OutwardDakForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -835561122218834213L;
	private String dispatchThrough;
	private String documentType;
	private long documentReferenceNo;
	private String dispatchDate;
	private String receiverName;
	private String subject;
	private String receiverAddress;
	private double postalCharge;		
	private String locationId;
	private String enclosures;
	private String afterIssue;
	
	
	public String getEnclosures() {
		return enclosures;
	}
	public void setEnclosures(String enclosures) {
		this.enclosures = enclosures;
	}
	public String getAfterIssue() {
		return afterIssue;
	}
	public void setAfterIssue(String afterIssue) {
		this.afterIssue = afterIssue;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getDispatchThrough() {
		return dispatchThrough;
	}
	public void setDispatchThrough(String dispatchThrough) {
		this.dispatchThrough = dispatchThrough;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public long getDocumentReferenceNo() {
		return documentReferenceNo;
	}
	public void setDocumentReferenceNo(long documentReferenceNo) {
		this.documentReferenceNo = documentReferenceNo;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public double getPostalCharge() {
		return postalCharge;
	}
	public void setPostalCharge(double postalCharge) {
		this.postalCharge = postalCharge;
	}


}
