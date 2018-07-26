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
@Table(name="mst_employee_contact_extention", schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class EmployeeContactExtendedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1710477224934774546L;
	
	@Id
	@GeneratedValue(generator="seq_mst_employee_contact_extention", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_mst_employee_contact_extention", sequenceName="prwss_main.seq_mst_employee_contact_extention")
	@Column(name="id", nullable=false)
	private long id;
	
	
	@Column(name="employee_id", nullable = false)
	private long employeeId;
	
	@Column(name="contract_extention_no")
	private String extentionContarctNo;
	
	@Column(name="contract_extention_date")
	private Date extentionContractDate;
	
	@Column(name="contract_extended_upto")
	private Date extentionContractUpto;
	
	@Embedded
	private MISAuditBean misAuditBean;

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

	public String getExtentionContarctNo() {
		return extentionContarctNo;
	}

	public void setExtentionContarctNo(String extentionContarctNo) {
		this.extentionContarctNo = extentionContarctNo;
	}

	public Date getExtentionContractDate() {
		return extentionContractDate;
	}

	public void setExtentionContractDate(Date extentionContractDate) {
		this.extentionContractDate = extentionContractDate;
	}

	public Date getExtentionContractUpto() {
		return extentionContractUpto;
	}

	public void setExtentionContractUpto(Date extentionContractUpto) {
		this.extentionContractUpto = extentionContractUpto;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	@Override
	public String toString() {
		return "EmployeeContactExtendedBean [id=" + id + ", employeeBean=" + employeeId + ", extentionContarctNo=" + extentionContarctNo
				+ ", extentionContractDate=" + extentionContractDate + ", extentionContractUpto=" + extentionContractUpto + ", misAuditBean=" + misAuditBean + "]";
	}


}
