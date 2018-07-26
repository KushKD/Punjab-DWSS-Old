package com.prwss.mis.masters.complaint.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_complaint_type", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ComplaintBean implements Serializable, Comparable<ComplaintBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 5013312453301864714L;

	@Id
	@SequenceGenerator(name="seq_mst_complaint",sequenceName="prwss_main.seq_mst_complaint")
	@GeneratedValue(generator="seq_mst_complaint", strategy=GenerationType.AUTO)
	@Column(name="complaint_id", nullable=false)
	private long complaintId;
	
	@Column(name="complaint_type", nullable=false)
	private String complaintType;
	
	@Column(name="complaint_description")
	private String complaintDescription;
	
	@Column(name="level1_redressal_days")
	private long level1RedressalDays;
	
	@Column(name="level1_emp_id")
	private long level1EmployeeId;
	
	@Column(name="level2_redressal_days")
	private long level2RedressalDays;
	
	@Column(name="level2_emp_id")
	private long level2EmployeeId;
	
	@Column(name="level3_redressal_days")
	private long level3RedressalDays;
	
	@Column(name="level3_emp_id")
	private long level3EmployeeId;
	
	@Column(name="level4_redressal_days")
	private long level4RedressalDays;
	
	@Column(name="level4_emp_id")
	private long level4EmployeeId;
	
	@Column(name="level5_redressal_days")
	private long level5RedressalDays;
	
	@Column(name="level5_emp_id")
	private long level5EmployeeId;

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

	public long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(long complaintId) {
		this.complaintId = complaintId;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public long getLevel1RedressalDays() {
		return level1RedressalDays;
	}

	public void setLevel1RedressalDays(long level1RedressalDays) {
		this.level1RedressalDays = level1RedressalDays;
	}

	public long getLevel1EmployeeId() {
		return level1EmployeeId;
	}

	public void setLevel1EmployeeId(long level1EmployeeId) {
		this.level1EmployeeId = level1EmployeeId;
	}

	public long getLevel2RedressalDays() {
		return level2RedressalDays;
	}

	public void setLevel2RedressalDays(long level2RedressalDays) {
		this.level2RedressalDays = level2RedressalDays;
	}

	public long getLevel2EmployeeId() {
		return level2EmployeeId;
	}

	public void setLevel2EmployeeId(long level2EmployeeId) {
		this.level2EmployeeId = level2EmployeeId;
	}

	public long getLevel3RedressalDays() {
		return level3RedressalDays;
	}

	public void setLevel3RedressalDays(long level3RedressalDays) {
		this.level3RedressalDays = level3RedressalDays;
	}

	public long getLevel3EmployeeId() {
		return level3EmployeeId;
	}

	public void setLevel3EmployeeId(long level3EmployeeId) {
		this.level3EmployeeId = level3EmployeeId;
	}

	public long getLevel4RedressalDays() {
		return level4RedressalDays;
	}

	public void setLevel4RedressalDays(long level4RedressalDays) {
		this.level4RedressalDays = level4RedressalDays;
	}

	public long getLevel4EmployeeId() {
		return level4EmployeeId;
	}

	public void setLevel4EmployeeId(long level4EmployeeId) {
		this.level4EmployeeId = level4EmployeeId;
	}

	public long getLevel5RedressalDays() {
		return level5RedressalDays;
	}

	public void setLevel5RedressalDays(long level5RedressalDays) {
		this.level5RedressalDays = level5RedressalDays;
	}

	public long getLevel5EmployeeId() {
		return level5EmployeeId;
	}

	public void setLevel5EmployeeId(long level5EmployeeId) {
		this.level5EmployeeId = level5EmployeeId;
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
		return "ComplaintBean [complaintId=" + complaintId + ", complaintType=" + complaintType
				+ ", complaintDescription=" + complaintDescription + ", level1RedressalDays=" + level1RedressalDays
				+ ", level1EmployeeId=" + level1EmployeeId + ", level2RedressalDays=" + level2RedressalDays
				+ ", level2EmployeeId=" + level2EmployeeId + ", level3RedressalDays=" + level3RedressalDays
				+ ", level3EmployeeId=" + level3EmployeeId + ", level4RedressalDays=" + level4RedressalDays
				+ ", level4EmployeeId=" + level4EmployeeId + ", level5RedressalDays=" + level5RedressalDays
				+ ", level5EmployeeId=" + level5EmployeeId + ", status=" + status + ", enteredBy=" + enteredBy
				+ ", enteredDate=" + enteredDate + ", authorizedBy=" + authorizedBy + ", authorizedDate="
				+ authorizedDate + ", freezedBy=" + freezedBy + ", freezedDate=" + freezedDate + "]";
	}

	@Override
	public int compareTo(ComplaintBean o) {
		return new Long(this.getComplaintId()).compareTo(new Long(o.getComplaintId()));
	}
	
	
}
