package com.prwss.mis.hr.targetplan.struts;

import java.io.Serializable;

public class EmployeeTargetGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4771546711162394825L;
	private long id;
	private long targetPlanId;
	private String targetName;
	private String targetCompletionDate;
	private String plannerRemarks;
	private String targetActualCompletionDate;
	private String targetStatus;
	private String employeeRemarks;
	private String evaluatorRemarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTargetPlanId() {
		return targetPlanId;
	}
	public void setTargetPlanId(long targetPlanId) {
		this.targetPlanId = targetPlanId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getTargetCompletionDate() {
		return targetCompletionDate;
	}
	public void setTargetCompletionDate(String targetCompletionDate) {
		this.targetCompletionDate = targetCompletionDate;
	}
	public String getPlannerRemarks() {
		return plannerRemarks;
	}
	public void setPlannerRemarks(String plannerRemarks) {
		this.plannerRemarks = plannerRemarks;
	}
	public String getTargetActualCompletionDate() {
		return targetActualCompletionDate;
	}
	public void setTargetActualCompletionDate(String targetActualCompletionDate) {
		this.targetActualCompletionDate = targetActualCompletionDate;
	}
	public String getTargetStatus() {
		return targetStatus;
	}
	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}
	public String getEmployeeRemarks() {
		return employeeRemarks;
	}
	public void setEmployeeRemarks(String employeeRemarks) {
		this.employeeRemarks = employeeRemarks;
	}
	public String getEvaluatorRemarks() {
		return evaluatorRemarks;
	}
	public void setEvaluatorRemarks(String evaluatorRemarks) {
		this.evaluatorRemarks = evaluatorRemarks;
	}

}
