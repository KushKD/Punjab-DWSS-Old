package com.prwss.mis.hr.leave;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
@Entity
@Table(name="t_hr_leave_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LeaveManagementBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3906844455421785410L;
	
	@Id
	@GeneratedValue(generator = "seq_t_hr_leave", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_hr_leave", sequenceName = "prwss_main.seq_t_hr_leave")
	@Column(name = "leave_id", nullable = false)
	private long leaveId;

	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	@Column(name = "leave_from_date", nullable = false)
	private Date leaveFromDate;
	
	@Column(name = "leave_to_date", nullable = false)
	private Date leaveToDate;
	
	@Column(name = "leave_status")
	private String leaveStatus;
	
	@ManyToOne(targetEntity=EmployeeBean.class)
	@JoinColumn(name="ent_by")
	private EmployeeBean entByEmployeeBean;
	
	@Column(name = "ent_date", nullable = false)
	private Timestamp entDate;
	
	@ManyToOne(targetEntity=EmployeeBean.class)
	@JoinColumn(name="reporting_officer_id")
	private EmployeeBean reportingOfficerEmployeeBean; 
	  
	@Column(name = "leave_reason")
	private String leaveReason;
	
	@Column(name = "officer_comments")
	private String officerComments;
	
	@Column(name = "status")
	private String status;

	public long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(long leaveId) {
		this.leaveId = leaveId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(Date leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public Date getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(Date leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public EmployeeBean getEntByEmployeeBean() {
		return entByEmployeeBean;
	}

	public void setEntByEmployeeBean(EmployeeBean entByEmployeeBean) {
		this.entByEmployeeBean = entByEmployeeBean;
	}

	public Timestamp getEntDate() {
		return entDate;
	}

	public void setEntDate(Timestamp entDate) {
		this.entDate = entDate;
	}

	public EmployeeBean getReportingOfficerEmployeeBean() {
		return reportingOfficerEmployeeBean;
	}

	public void setReportingOfficerEmployeeBean(
			EmployeeBean reportingOfficerEmployeeBean) {
		this.reportingOfficerEmployeeBean = reportingOfficerEmployeeBean;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getOfficerComments() {
		return officerComments;
	}

	public void setOfficerComments(String officerComments) {
		this.officerComments = officerComments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
