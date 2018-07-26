package com.prwss.mis.daktask.taskallocation.struts;

import java.io.Serializable;

public class TaskAllocationGridBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 808908133341740880L;
	
	private String documentType;
	
	private String documentNumber;
	
	private String  senderName;
	
	private String assignTo;
	
	private String targetDate;
	
	private String remarks;
	
	private String completedDate;
	

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
