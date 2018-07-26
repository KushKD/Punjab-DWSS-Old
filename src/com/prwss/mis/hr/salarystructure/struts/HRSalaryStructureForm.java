package com.prwss.mis.hr.salarystructure.struts;

import org.apache.struts.validator.ValidatorForm;

public class HRSalaryStructureForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5457485988595881194L;
	private String locationId;
	private long employeeId;
	private String fromDate;
	private String toDate;
	private String wing;
	private String designation;
	private String employeeType;
	private String dateOfJoining;
	private String panNo;
	private String appointmentNo;
	private double hra;
	private double basicPay;
	private double conveyanceAllowance;
	private double personalAllowance;
	private double educationAllowance;
	private double tds;
	private double incomeTax;
	private double providentFund;
	private double leaveWithoutPay;
	private double reimbursement;
	private double dearnessAllowance;
	private double totalAmount;
	private double others;

	
	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
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

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getConveyanceAllowance() {
		return conveyanceAllowance;
	}

	public void setConveyanceAllowance(double conveyanceAllowance) {
		this.conveyanceAllowance = conveyanceAllowance;
	}

	public double getPersonalAllowance() {
		return personalAllowance;
	}

	public void setPersonalAllowance(double personalAllowance) {
		this.personalAllowance = personalAllowance;
	}

	public double getEducationAllowance() {
		return educationAllowance;
	}

	public void setEducationAllowance(double educationAllowance) {
		this.educationAllowance = educationAllowance;
	}

	public double getTds() {
		return tds;
	}

	public void setTds(double tds) {
		this.tds = tds;
	}

	public double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public double getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}

	public double getLeaveWithoutPay() {
		return leaveWithoutPay;
	}

	public void setLeaveWithoutPay(double leaveWithoutPay) {
		this.leaveWithoutPay = leaveWithoutPay;
	}

	public double getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(double reimbursement) {
		this.reimbursement = reimbursement;
	}

	public double getDearnessAllowance() {
		return dearnessAllowance;
	}

	public void setDearnessAllowance(double dearnessAllowance) {
		this.dearnessAllowance = dearnessAllowance;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public double getOthers() {
		return others;
	}
	
	public void setOthers(double others) {
		this.others = others;
	}


}
