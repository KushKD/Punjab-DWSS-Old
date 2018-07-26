package com.prwss.mis.daktask.inwarddak.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name=MISConstants.INWARDDAKTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class InwardDakBean implements Serializable,Comparable<InwardDakBean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7694203505819994602L;

	@Id
	@Column(name="location_id")
	private String locationId;
	
	@Id
	@Column(name="document_no")
	private String documentNo;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="document_ref_no")
	private String  documentRefNo;
	
	@Column(name="receipt_date")
	private Date  receiptDate;
	
	@Column(name="sender_name")
	private String  senderName;
	
	@Column(name="subject")
	private String  subject;	
		
	@Column(name="assign_to")
	private String  assignTo;	
	
	@Column(name="target_date")
	private Date  targetDate;	
	
	@Column(name="completed_date")
	private Date  completedDate;	
	
	@Column(name="sender_address")
	private String  senderAddress;	
	
	@Column(name="remarks")
	private String  remarks;
	
	@Column(name="row_cret_date")
	private Date rowCretDate;
	
	@Column(name="forwarded_to")
	private String forwardedTo;
	
	
	public String getForwardedTo() {
		return forwardedTo;
	}

	public void setForwardedTo(String forwardedTo) {
		this.forwardedTo = forwardedTo;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public Date getRowCretDate() {
		return rowCretDate;
	}

	public void setRowCretDate(Date rowCretDate) {
		this.rowCretDate = rowCretDate;
	}

	@Embedded
	private MISAuditBean misAuditBean;
	
	

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getDocumentRefNo() {
		return documentRefNo;
	}

	public void setDocumentRefNo(String documentRefNo) {
		this.documentRefNo = documentRefNo;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
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

	public void setSender_Address(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "InwardDakBean [locationId=" + locationId + ", documentNo="
				+ documentNo + ", documentType=" + documentType
				+ ", documentRefNo=" + documentRefNo + ", receiptDate="
				+ receiptDate + ", senderName=" + senderName + ", subject="
				+ subject + ", assignTo=" + assignTo + ", targetDate="
				+ targetDate + ", completedDate=" + completedDate
				+ ", senderAddress=" + senderAddress + ", remarks=" + remarks
				+ ", rowCretDate=" + rowCretDate + ", misAuditBean="
				+ misAuditBean + "]";
	}

	@Override
	public int compareTo(InwardDakBean o) {
		return this.documentNo.compareTo(o.documentNo);
	}

	


}
