package com.prwss.mis.hr.payroll;

import java.io.Serializable;

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
@Table(name="t_hr_payroll",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class HRPayrollBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3904914043023945734L;
	
	@Id
	@Column(name="voc_id")
	private long vocId;
	
	@ManyToOne(targetEntity=EmployeeBean.class)
	@JoinColumn(name="employee_id")
	private EmployeeBean employeeBean;
	
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="payroll_month", nullable=false)
	private String payrollMonth;
	
	@Column(name="payroll_year", nullable=false)
	private String payrollYear;
	
	@Column(name="total_amount", nullable=false)
	private double totalAmount;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

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

	

	public long getVocId() {
		return vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
	}

	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}

	public void setEmployeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
