package com.prwss.mis.masters.employee.struts;

import java.io.Serializable;

public class EmployeePromotionHistoryGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6963003689893840753L;
	private long id;
	private long employeeId;
	private String dateOfPromotion;
	private String promotedDesignation;
	private String payrollExtended;
	
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
	public String getDateOfPromotion() {
		return dateOfPromotion;
	}
	public void setDateOfPromotion(String dateOfPromotion) {
		this.dateOfPromotion = dateOfPromotion;
	}
	public String getPromotedDesignation() {
		return promotedDesignation;
	}
	public void setPromotedDesignation(String promotedDesignation) {
		this.promotedDesignation = promotedDesignation;
	}
	public void setPayrollExtended(String payrollExtended) {
		this.payrollExtended = payrollExtended;
	}
	public String getPayrollExtended() {
		return payrollExtended;
	}
	
}
