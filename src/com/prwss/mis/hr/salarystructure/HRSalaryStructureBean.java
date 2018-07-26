package com.prwss.mis.hr.salarystructure;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
@Entity
@Table(name="t_hr_salary_structure",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class HRSalaryStructureBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2542193318475889643L;
	
	@Column(name = "location_id", nullable = false)
	private String locationId;

	@Id
	@ManyToOne(targetEntity=EmployeeBean.class)
	@JoinColumn(name="employee_id")
	private EmployeeBean employeeBean;
	
	@Id
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "hra")
	private double hra;
	
	@Column(name = "conveyance_allowance")
	private double conveyanceAllowance;
	
	@Column(name = "personal_allowance")
	private double personalAllowance;
	
	@Column(name = "education_allowance")
	private double educationAllowance;
	
	@Column(name = "tds")
	private double tds;
	
	@Column(name = "income_tax")
	private double incomeTax;
	
	@Column(name = "provident_fund")
	private double providentFund;
	
	@Column(name = "leave_without_pay")
	private double leaveWithoutPay;
	
	@Column(name = "reimbursement")
	private double reimbursement;
	
	@Column(name = "basic_pay")
	private double basicPay;
	
	@Column(name = "dearness_allowance")
	private double dearnessAllowance;
	
	@Column(name = "total_amount")
	private double totalAmount;
	
	@Column(name = "others")
	private double others;

	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}

	public void setEmployeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
