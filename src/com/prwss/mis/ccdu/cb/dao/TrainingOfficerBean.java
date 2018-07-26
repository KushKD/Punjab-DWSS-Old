package com.prwss.mis.ccdu.cb.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_ccdu_cb_training_officers", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TrainingOfficerBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 5534529068131530739L;

	@Id
	@Column(name="cb_progress_id", nullable=false)
	private long cbProgressId;
	
	@Id
	@Column(name="employee_id", nullable=false)
	private long employeeId;
	
	@Column(name="employee_name")
	private String employeeName;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public long getCbProgressId() {
		return cbProgressId;
	}

	public void setCbProgressId(long cbProgressId) {
		this.cbProgressId = cbProgressId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeName() {
		return employeeName;
	}



}
