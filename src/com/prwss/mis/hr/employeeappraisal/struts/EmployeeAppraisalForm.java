package com.prwss.mis.hr.employeeappraisal.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeAppraisalForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3563502922229928052L;
	private long targetPlanId;
	private String reportingOfficerName;
	private String employeeName;
	private String locationId;
	private String planFromDate;
	private String planToDate;
	private Datagrid employeeTargetDatagrid;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long getTargetPlanId() {
		return targetPlanId;
	}
	public void setTargetPlanId(long targetPlanId) {
		this.targetPlanId = targetPlanId;
	}
	public String getReportingOfficerName() {
		return reportingOfficerName;
	}
	public void setReportingOfficerName(String reportingOfficerName) {
		this.reportingOfficerName = reportingOfficerName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
