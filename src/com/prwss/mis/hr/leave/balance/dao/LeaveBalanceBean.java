package com.prwss.mis.hr.leave.balance.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_hr_leave_balance",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LeaveBalanceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6080688134264065564L;
	
	@Id
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	@Id
	@Column(name = "employee_id", nullable = false)
	private long employeeId;
	
	@Id
	@Column(name = "year_for", nullable = false)
	private long yearFor;
	
	@Column(name = "total_eligibile_leave")
	private long totalEligibileLeave;
	
	@Column(name = "total_applied")
	private long totalApplied;
	
	@Column(name = "total_availed")
	private long totalAvailed;
	
	@Column(name = "total_rejected")
	private long totalRejected;
	
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getYearFor() {
		return yearFor;
	}

	public void setYearFor(long yearFor) {
		this.yearFor = yearFor;
	}
	public long getTotalEligibileLeave() {
		return totalEligibileLeave;
	}

	public void setTotalEligibileLeave(long totalEligibileLeave) {
		this.totalEligibileLeave = totalEligibileLeave;
	}

	public long getTotalApplied() {
		return totalApplied;
	}

	public void setTotalApplied(long totalApplied) {
		this.totalApplied = totalApplied;
	}

	public long getTotalAvailed() {
		return totalAvailed;
	}

	public void setTotalAvailed(long totalAvailed) {
		this.totalAvailed = totalAvailed;
	}

	public long getTotalRejected() {
		return totalRejected;
	}

	public void setTotalRejected(long totalRejected) {
		this.totalRejected = totalRejected;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	
	
	
	   
}
