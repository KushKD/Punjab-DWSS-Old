package com.prwss.mis.hr.changeOfficer.struts;

import org.apache.struts.validator.ValidatorForm;

public class ChangeOfficerLocationForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 501440863733245664L;
	
	private String locationId;
	private String newLocationId;
	private long newReportingOfficerId;
	private long oldReportingOfficerId;
	private long employeeId;
	private String employeeName;
	private long reportingOfficerId;
	private String dateOfBirth;
	private long[] selectedIds;
	private String changeLocationOfficer="changeLocation";
	
	private String location;
	private long employeeIds;
	private String changeLocationId;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public long getNewReportingOfficerId() {
		return newReportingOfficerId;
	}
	public void setNewReportingOfficerId(long newReportingOfficerId) {
		this.newReportingOfficerId = newReportingOfficerId;
	}
	public long getOldReportingOfficerId() {
		return oldReportingOfficerId;
	}
	public void setOldReportingOfficerId(long oldReportingOfficerId) {
		this.oldReportingOfficerId = oldReportingOfficerId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setReportingOfficerId(long reportingOfficerId) {
		this.reportingOfficerId = reportingOfficerId;
	}
	public long getReportingOfficerId() {
		return reportingOfficerId;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setSelectedIds(long[] selectedIds) {
		this.selectedIds = selectedIds;
	}
	public long[] getSelectedIds() {
		return selectedIds;
	}
	public void setNewLocationId(String newLocationId) {
		this.newLocationId = newLocationId;
	}
	public String getNewLocationId() {
		return newLocationId;
	}
	public void setChangeLocationOfficer(String changeLocationOfficer) {
		this.changeLocationOfficer = changeLocationOfficer;
	}
	public String getChangeLocationOfficer() {
		return changeLocationOfficer;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return location;
	}
	public void setEmployeeIds(long employeeIds) {
		this.employeeIds = employeeIds;
	}
	public long getEmployeeIds() {
		return employeeIds;
	}
	public void setChangeLocationId(String changeLocationId) {
		this.changeLocationId = changeLocationId;
	}
	public String getChangeLocationId() {
		return changeLocationId;
	}
	 
	

}
