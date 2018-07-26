package com.prwss.mis.tender.dao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_tender_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
public class TenderDetailBean implements Serializable, Comparable<TenderDetailBean> {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3564081431119849287L;
	
	@Id
	@Column(name="tender_id", nullable=false, updatable=false)
	private String tenderId;
	
	@Column(name="estimate_date")
	private Date estimateDate;
	
	@Column(name="actual_date")
	private Date actualDate;
	
	@Id
	@Column(name="tender_activity", nullable=false, updatable=false)
	private String tenderActivity;
	
	@Column(name="tender_activity_status")
	private String tenderActivityStatus;
	
	@Column(name="status")	
	private String status;
	
	@Column(name="ent_by")
	private long enteredBy;
	
	@Column(name="ent_date")
	private Timestamp enteredDate;
	
	@Column(name="auth_by")
	private long authorizedBy;
	
	@Column(name="auth_date")
	private Timestamp authorizedDate;
	
	@Column(name="freeze_by")
	private long freezedBy;
	
	@Column(name="freeze_date")
	private Timestamp freezedDate;


	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

	public String getTenderActivity() {
		return tenderActivity;
	}

	public void setTenderActivity(String tenderActivity) {
		this.tenderActivity = tenderActivity;
	}
	
	public String getTenderActivityStatus() {
		return tenderActivityStatus;
	}

	public void setTenderActivityStatus(String tenderActivityStatus) {
		this.tenderActivityStatus = tenderActivityStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Timestamp getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Timestamp enteredDate) {
		this.enteredDate = enteredDate;
	}

	public long getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(long authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Timestamp getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Timestamp authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public long getFreezedBy() {
		return freezedBy;
	}

	public void setFreezedBy(long freezedBy) {
		this.freezedBy = freezedBy;
	}

	public Timestamp getFreezedDate() {
		return freezedDate;
	}

	public void setFreezedDate(Timestamp freezedDate) {
		this.freezedDate = freezedDate;
	}

	@Override
	public String toString() {
		return "\nTenderDetailBean [tenderId=" + tenderId + ", estimateDate=" + estimateDate
				+ ", actualDate=" + actualDate + ", tenderActivity=" + tenderActivity + ", status=" + status
				+ ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", authorizedBy=" + authorizedBy
				+ ", authorizedDate=" + authorizedDate + ", freezedBy=" + freezedBy + ", freezedDate=" + freezedDate
				+ "]\n";
	}

	@Override
	public int compareTo(TenderDetailBean o) {
		return this.getTenderActivity().compareTo(o.getTenderActivity());
	}	
	
	
}
