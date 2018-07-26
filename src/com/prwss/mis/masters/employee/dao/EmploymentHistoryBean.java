package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;

@Entity
@Table(name = "mst_employment_history", schema = "prwss_main")
public class EmploymentHistoryBean implements Serializable {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5595557418328610288L;

	@Id
	@GeneratedValue(generator = "seq_mst_employee_history", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_mst_employee_history", sequenceName = "prwss_main.seq_mst_employee_history")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "employee_id", nullable = false)
	private long employeeId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "nature_of_business")
	private String natureOfBusiness;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "duration_in_months")
	private int durationInMonths;

	@Column(name = "reason_for_leaving")
	private String reasonForLeaving;

	@Embedded
	private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public EmployeeBean getEmployeeBean() {
//		return employeeBean;
//	}
//
//	public void setEmployeeBean(EmployeeBean employeeBean) {
//		this.employeeBean = employeeBean;
//	}
	

	public String getCompanyName() {
		return companyName;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
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

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public String getReasonForLeaving() {
		return reasonForLeaving;
	}

	public void setReasonForLeaving(String reasonForLeaving) {
		this.reasonForLeaving = reasonForLeaving;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "EmploymentHistoryBean [id=" + id + ", employeeBean=" + employeeId + ", companyName=" + companyName
				+ ", natureOfBusiness=" + natureOfBusiness + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", durationInMonths=" + durationInMonths + ", reasonForLeaving=" + reasonForLeaving
				+ ", misAuditBean=" + misAuditBean + "]";
	}

}
