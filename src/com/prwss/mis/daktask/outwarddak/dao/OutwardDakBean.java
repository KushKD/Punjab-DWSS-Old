package com.prwss.mis.daktask.outwarddak.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name=MISConstants.OUTWARDDAKTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class OutwardDakBean implements Serializable {
	
	
	private static final long serialVersionUID = -638346821070076950L;
	
	
	@Id
	@Column(name="document_ref_no")
	private long documentRefNo;
	
	@Column(name="location_id", nullable=false)
	private String locationId;		
	
	@Column(name="document_type", nullable=false)
	private String documentType;
	
	@Column(name="dispatch_date")
	private Date  dispatchDate;
	
	@Column(name="dispatch_through")
	private String  dispatchThrough;
	
	@Column(name="receiver_name")
	private String  receiverName;
	
	@Column(name="subject")
	private String  subject;
	
	@Column(name="receiver_address")
	private String  receiver_Address;
	
	@Column(name="enclosures")
	private String  enclosures;
	
	@Column(name="after_issue")
	private String  afterIssue;
	
	public double getPostal_charge() {
		return postal_charge;
	}

	public void setPostal_charge(double postal_charge) {
		this.postal_charge = postal_charge;
	}

	@Column(name="postal_charge")
	private double  postal_charge;
	
	@Embedded
	private MISAuditBean misAuditBean;

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

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public long getDocumentRefNo() {
		return documentRefNo;
	}

	public void setDocumentRefNo(long documentRefNo) {
		this.documentRefNo = documentRefNo;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDispatchThrough() {
		return dispatchThrough;
	}

	public void setDispatchThrough(String dispatchThrough) {
		this.dispatchThrough = dispatchThrough;
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

	public String getReceiver_Address() {
		return receiver_Address;
	}

	public void setReceiver_Address(String receiver_Address) {
		this.receiver_Address = receiver_Address;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "OutwardDakBean [locationId=" + locationId + ", documentType="
				+ documentType + ", documentRefNo=" + documentRefNo
				+ ", receiptDate=" + dispatchDate + ", dispatchThrough="
				+ dispatchThrough + ", receiverName=" + receiverName
				+ ", subject=" + subject + ", receiver_Address="
				+ receiver_Address + ", postal_charge=" + postal_charge
				+ ", misAuditBean=" + misAuditBean + "]";
	}

	

	


}
