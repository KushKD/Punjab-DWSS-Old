package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

public class EntitledLeaveForm extends ValidatorForm{
	
	private static final long serialVersionUID = 8813762135029002058L;
	
	private String locationId;
	private long employeeId;
	private long totalEligibileLeave;
	private  long yearFor;
	
 
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	 
	public void setYearFor(long yearFor) {
		this.yearFor = yearFor;
	}
	public long getYearFor() {
		return yearFor;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setTotalEligibileLeave(long totalEligibileLeave) {
		this.totalEligibileLeave = totalEligibileLeave;
	}
	public long getTotalEligibileLeave() {
		return totalEligibileLeave;
	}
	 
}