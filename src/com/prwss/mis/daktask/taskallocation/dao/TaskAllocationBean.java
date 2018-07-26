package com.prwss.mis.daktask.taskallocation.dao;

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
@Table(name=MISConstants.TASKALLOCATIONTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TaskAllocationBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9197834956924113908L;

	@Id
	@Column(name="document_no", nullable=false)
	private String documentNo;
	
	private String searchDate;
	
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="wing")
	private String wing;
	
	@Column(name="assign_to")
	private String assignTo;
	
	@Column(name="target_date")
	private Date targetDate;
	
	@Column(name="completed_date")
	private Date completedDate;
	
	@Column(name="remarks")
	private String remarks;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
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

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

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

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
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
	

}
