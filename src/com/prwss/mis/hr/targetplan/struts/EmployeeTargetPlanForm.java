package com.prwss.mis.hr.targetplan.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeTargetPlanForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 965991062330187612L;
	private String locationId;
	private long targetPlanId;
	private long employeeId;
	private String planFromDate;
	private String planToDate;
	private String reportingOfficerName;
	private Datagrid employeeTargetDatagrid;
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getReportingOfficerName() {
		return reportingOfficerName;
	}
	public void setReportingOfficerName(String reportingOfficerName) {
		this.reportingOfficerName = reportingOfficerName;
	}
	public long getTargetPlanId() {
		return targetPlanId;
	}
	public void setTargetPlanId(long targetPlanId) {
		this.targetPlanId = targetPlanId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getPlanFromDate() {
		return planFromDate;
	}
	public void setPlanFromDate(String planFromDate) {
		this.planFromDate = planFromDate;
	}
	public String getPlanToDate() {
		return planToDate;
	}
	public void setPlanToDate(String planToDate) {
		this.planToDate = planToDate;
	}
	public Datagrid getEmployeeTargetDatagrid() {
		return employeeTargetDatagrid;
	}
	public void setEmployeeTargetDatagrid(Datagrid employeeTargetDatagrid) {
		this.employeeTargetDatagrid = employeeTargetDatagrid;
	}
	
}
