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
import com.prwss.mis.common.util.MISConstants;


@Entity
@Table(name="mst_employment_promotion_history",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeePromotionHistoryBean implements Serializable , Comparable<EmployeePromotionHistoryBean>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6844753199779517561L;


	@Id
	@GeneratedValue(generator="seq_mst_employee_promotion", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_mst_employee_promotion", sequenceName="prwss_main.seq_mst_employee_promotion")
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="employee_id", nullable = false)
	private long employeeId;
	
	@Column(name="date_of_promotion", nullable = false)
	private Date dateOfPromotion;
	
	@Column(name="promoted_designation", nullable = false)
	private String promotedDesignation;
	
	@Column(name="payscale")
	private String payrollExtended;

	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

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

	public Date getDateOfPromotion() {
		return dateOfPromotion;
	}

	public void setDateOfPromotion(Date dateOfPromotion) {
		this.dateOfPromotion = dateOfPromotion;
	}

	public String getPromotedDesignation() {
		return promotedDesignation;
	}

	public void setPromotedDesignation(String promotedDesignation) {
		this.promotedDesignation = promotedDesignation;
	}
	
	@Override
	public int compareTo(EmployeePromotionHistoryBean o) {
		return this.dateOfPromotion.compareTo(o.dateOfPromotion);
	}

	public String getPayrollExtended() {
		return payrollExtended;
	}

	public void setPayrollExtended(String payrollExtended) {
		this.payrollExtended = payrollExtended;
	}
	

}
