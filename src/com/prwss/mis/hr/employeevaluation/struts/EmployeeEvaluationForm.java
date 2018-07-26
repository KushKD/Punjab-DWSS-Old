package com.prwss.mis.hr.employeevaluation.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeEvaluationForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1992474621052143113L;
	private String locationId;
	private long targetPlanId;
	private long employeeId;
	private String planFromDate;
	private String planToDate;
	private String reportingOfficerName;
	private Datagrid employeeTargetDatagrid;
	private String performanceRating;
	private String appraisal;
	
	
	
	public String getPerformanceRating() {
		return performanceRating;
	}
	public void setPerformanceRating(String performanceRating) {
		this.performanceRating = performanceRating;
	}
	public String getAppraisal() {
		return appraisal;
	}
	public void setAppraisal(String appraisal) {
		this.appraisal = appraisal;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	public String getReportingOfficerName() {
		return reportingOfficerName;
	}
	public void setReportingOfficerName(String reportingOfficerName) {
		this.reportingOfficerName = reportingOfficerName;
	}
	public Datagrid getEmployeeTargetDatagrid() {
		return employeeTargetDatagrid;
	}
	public void setEmployeeTargetDatagrid(Datagrid employeeTargetDatagrid) {
		this.employeeTargetDatagrid = employeeTargetDatagrid;
	}

}
