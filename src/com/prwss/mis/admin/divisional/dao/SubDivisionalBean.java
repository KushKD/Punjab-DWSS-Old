package com.prwss.mis.admin.divisional.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="mst_subdivisional", schema="prwss_main")
public class SubDivisionalBean implements Serializable,Comparable<SubDivisionalBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 997098013507040L;

	@Id
	@Column(name="subdivisional_id", nullable=false)
	private String subdivisionId;
	
	@Column(name="subdivisional_name", nullable=false)
	private String subdivisionName;
	
	@ManyToOne(targetEntity=DivisionalBean.class)
	@JoinColumn(name="divisional_id", nullable=false)
	private DivisionalBean division;
	
	public String getSubdivisionId() {
		return subdivisionId;
	}

	public void setSubdivisionId(String subdivisionId) {
		this.subdivisionId = subdivisionId;
	}

	public String getSubdivisionName() {
		return subdivisionName;
	}

	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}

	public DivisionalBean getDivision() {
		return division;
	}

	public void setDivision(DivisionalBean division) {
		this.division = division;
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

	@Override
	public int compareTo(SubDivisionalBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
