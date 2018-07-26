package com.prwss.mis.hr.attendance.struts;

import org.apache.struts.validator.ValidatorForm;

public class AttendanceForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7611998045904791793L;
	private String locationId;
	private long employeeId;
	private long attendenceId;
	private String attendanceDate;
	private String attendanceStatus;
	private long [] attendenceIds;
	
	
	public long getAttendenceId() {
		return attendenceId;
	}
	public void setAttendenceId(long attendenceId) {
		this.attendenceId = attendenceId;
	}
	public long[] getAttendenceIds() {
		return attendenceIds;
	}
	public void setAttendenceIds(long[] attendenceIds) {
		this.attendenceIds = attendenceIds;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	@Override
	public String toString() {
		return "AttendanceForm [locationId=" + locationId + ", employeeId="
				+ employeeId + ", attendanceDate=" + attendanceDate
				+ ", attendanceStatus=" + attendanceStatus + "]";
	}
	
	

}
