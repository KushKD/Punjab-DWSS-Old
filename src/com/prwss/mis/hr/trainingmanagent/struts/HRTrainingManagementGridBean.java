package com.prwss.mis.hr.trainingmanagent.struts;

import java.io.Serializable;

public class HRTrainingManagementGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072188614565454680L;
	private long id;
	private long employeeId;
	private String fromDate;
	private String toDate;
	private String trainingVenue;
	private String remarks;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getTrainingVenue() {
		return trainingVenue;
	}
	public void setTrainingVenue(String trainingVenue) {
		this.trainingVenue = trainingVenue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
