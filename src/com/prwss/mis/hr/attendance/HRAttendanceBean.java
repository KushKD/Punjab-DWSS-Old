package com.prwss.mis.hr.attendance;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
@Entity
@Table(name="t_hr_attendance_register",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class HRAttendanceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133591427660076201L;
	
	@Id
	@GeneratedValue(generator = "seq_t_hr_attendance", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_hr_attendance", sequenceName = "prwss_main.seq_t_hr_attendance")
	@Column(name = "attendence_id", nullable = false)
	private long attendenceId;
	
	@Column(name = "location_id", nullable = false)
	private String locationId;

	
	@ManyToOne(targetEntity=EmployeeBean.class)
	@JoinColumn(name="employee_id")
	private EmployeeBean employeeBean;
	
	
	@Column(name = "attendance_date")
	private Date attendanceDate;
	
	@Column(name = "attendance_status")
	private String attendanceStatus;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}

	public void setEmployeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public long getAttendenceId() {
		return attendenceId;
	}

	public void setAttendenceId(long attendenceId) {
		this.attendenceId = attendenceId;
	}
	
}
