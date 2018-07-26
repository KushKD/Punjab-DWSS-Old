package com.prwss.mis.hr.payroll.struts;

import org.apache.struts.validator.ValidatorForm;

public class HRPayrollForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4427651151659055998L;
	private String locationId;
	private String payrollMonth;
	private String payrollYear;
	private long employeeId;
	private double totalAmount;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPayrollMonth() {
		return payrollMonth;
	}
	public void setPayrollMonth(String payrollMonth) {
		this.payrollMonth = payrollMonth;
	}
	public String getPayrollYear() {
		return payrollYear;
	}
	public void setPayrollYear(String payrollYear) {
		this.payrollYear = payrollYear;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
