package com.prwss.mis.tender.award.dao;

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
@Table(name="t_tender_security_deposit", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TenderSecurityDepositBean implements Serializable{

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4149194285361094159L;

	@Id
	@SequenceGenerator(name="seq_tender_security_deposit_id", sequenceName="prwss_main.seq_tender_security_deposit_id")
	@GeneratedValue(generator="seq_tender_security_deposit_id",strategy=GenerationType.AUTO)
	@Column(name="deposit_id", nullable=false)
	private long depositId;
	
	@Column(name="tender_id")
	private String tenderId;
	
	@Column(name="instrument_type")
	private String instrumentType;
	
	@Column(name="instrument_issuer")
	private String instrumentIssuer;
	
	@Column(name="date_of_issue")
	private Date dateOfIssue;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="location_id")
	private String locationId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getDepositId() {
		return depositId;
	}

	public void setDepositId(long depositId) {
		this.depositId = depositId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public String getInstrumentIssuer() {
		return instrumentIssuer;
	}

	public void setInstrumentIssuer(String instrumentIssuer) {
		this.instrumentIssuer = instrumentIssuer;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "TenderSecurityDepositBean [depositId=" + depositId + ", tenderId=" + tenderId + ", instrumentType="
				+ instrumentType + ", instrumentIssuer=" + instrumentIssuer + ", dateOfIssue=" + dateOfIssue
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", remarks=" + remarks
				+ ", locationId=" + locationId + ", misAuditBean=" + misAuditBean + "]";
	}
		
}
