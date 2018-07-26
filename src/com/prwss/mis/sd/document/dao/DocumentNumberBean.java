package com.prwss.mis.sd.document.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="sd_document_no", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DocumentNumberBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4095316244141436755L;
	@Id
	@Column(name="location_id")
	private String locationId;
	@Id
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="start_no")
	private Long startNumber;
	
	@Column(name="end_no")
	private Long endNumber;
	
	@Column(name="last_no")
	private Long lastNumber;
	
	@Column(name="auth_by")
	private String authBy;
	
	@Column(name="auth_date")
	private Date authDate;

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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Long startNumber) {
		this.startNumber = startNumber;
	}

	public Long getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(Long endNumber) {
		this.endNumber = endNumber;
	}

	public Long getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(Long lastNumber) {
		this.lastNumber = lastNumber;
	}

	public String getAuthBy() {
		return authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	@Override
	public String toString() {
		return "DocumentNumberBean [locationId=" + locationId
				+ ", documentType=" + documentType + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", startNumber=" + startNumber
				+ ", endNumber=" + endNumber + ", lastNumber=" + lastNumber
				+ ", authBy=" + authBy + ", authDate=" + authDate + "]";
	}		
	
	

}
