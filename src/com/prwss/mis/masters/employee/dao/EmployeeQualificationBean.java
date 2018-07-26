package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;

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
@Table(name="mst_employee_qualification", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeQualificationBean implements Serializable {
	
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 6592939279251906717L;

	@Id
	@GeneratedValue(generator="seq_mst_employee_qualification", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_mst_employee_qualification", sequenceName="prwss_main.seq_mst_employee_qualification")
	@Column(name="id", nullable=false)
	private long id;
	
	
	@Column(name="employee_id", nullable = false)
	private long employeeId;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="university")
	private String university;
	
	@Column(name="passing_year")
	private String yearOfPassing;
	
	@Column(name="percentile")
	private String percentile;
	
	@Column(name="specialization")
	private String specialization;
	
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

	
	public String getDegree() {
		return degree;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public String getPercentile() {
		return percentile;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "EmployeeQualificationBean [id=" + id + ", employeeBean=" + employeeId + ", degree=" + degree
				+ ", university=" + university + ", yearOfPassing=" + yearOfPassing + ", percentile=" + percentile
				+ ", specialization=" + specialization + ", misAuditBean=" + misAuditBean + "]";
	}

}
