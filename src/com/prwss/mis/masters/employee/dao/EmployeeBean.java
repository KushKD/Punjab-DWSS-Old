package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_employee", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeBean implements Serializable, Comparable<EmployeeBean> {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 7858983740921671926L;

	@Id
	@GeneratedValue(generator = "seq_mst_employee", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_mst_employee", sequenceName = "prwss_main.seq_mst_employee")
	@Column(name = "employee_id", nullable = false)
	private long employeeId;

	@Column(name = "employee_name", nullable = false)
	private String employeeName;

	@Column(name = "employee_type")
	private String employeeType;

	@Column(name = "father_name", nullable = false)
	private String fatherName;

	@Column(name = "designation_id")
	private String designationId;

	@Column(name = "permanent_employee_id")
	private long permanentEmployeeId;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "appointment_no")
	private String appointmentNo;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "contract_start_date")
	private Date contractStartDate;

	@Column(name = "contract_end_date")
	private Date contractEndDate;

	@Column(name = "appointed_wing")
	private String appointedWing;
	
	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "sanction_no")
	private String sanctionNo;

	@Column(name = "work_phone")
	private String workPhone;

	@Column(name = "mobile_phone")
	private String mobilePhone;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "pin")
	private String pin;

	@Column(name = "state")
	private String state;

	@Column(name = "personal_email")
	private String personalEmailId;

	@Column(name = "office_email")
	private String officialEmailId;

	@Column(name = "pan_no")
	private String panNo;

	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "reporting_officer_id")
	private Long reportingOfficerId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "contract_extended_upto")
	private Date contractExtendedUpto;
	
	@Column(name = "payscale")
	private String payscale;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "reporting_officer_loaction_id")
	private String reportingOfficerLoactionId;

	@Column(name = "contract_extention_no")
	private String contractExtentionNo;
	
	@Column(name = "contract_extention_date")
	private Date contractExtentionDate ;
	
	@Column(name = "retirement_date")
	private Date retirementDate ;
	
	@Column(name="desig_id")
	private int desigId;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=EmployeeQualificationBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="employee_id", updatable = false , insertable = false)
	private Set<EmployeeQualificationBean> employeeQualificationBeans;
	
	@OneToMany(targetEntity=EmploymentHistoryBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="employee_id", updatable = false , insertable = false)
	private Set<EmploymentHistoryBean> employmentHistoryBeans;
	
	@OneToMany(targetEntity=EmployeePromotionHistoryBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="employee_id", updatable = false , insertable = false)
	private Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans;
	
	@OneToMany(targetEntity=EmployeeContactExtendedBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="employee_id", updatable = false , insertable = false)
	private Set<EmployeeContactExtendedBean>  employeeContactExtendedBeans;

	public Set<EmployeePromotionHistoryBean> getEmployeePromotionHistoryBeans() {
		return employeePromotionHistoryBeans;
	}

	public void setEmployeePromotionHistoryBeans(
			Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans) {
		this.employeePromotionHistoryBeans = employeePromotionHistoryBeans;
	}

	public String getReportingOfficerLoactionId() {
		return reportingOfficerLoactionId;
	}

	public void setReportingOfficerLoactionId(String reportingOfficerLoactionId) {
		this.reportingOfficerLoactionId = reportingOfficerLoactionId;
	}

	public Long getReportingOfficerId() {
		return reportingOfficerId;
	}

	public void setReportingOfficerId(Long reportingOfficerId) {
		this.reportingOfficerId = reportingOfficerId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public long getPermanentEmployeeId() {
		return permanentEmployeeId;
	}

	public void setPermanentEmployeeId(long permanentEmployeeId) {
		this.permanentEmployeeId = permanentEmployeeId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSanctionNo() {
		return sanctionNo;
	}

	public void setSanctionNo(String sanctionNo) {
		this.sanctionNo = sanctionNo;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public Set<EmployeeQualificationBean> getEmployeeQualificationBeans() {
		return employeeQualificationBeans;
	}

	public void setEmployeeQualificationBeans(Set<EmployeeQualificationBean> employeeQualificationBeans) {
		this.employeeQualificationBeans = employeeQualificationBeans;
	}

	public Set<EmploymentHistoryBean> getEmploymentHistoryBeans() {
		return employmentHistoryBeans;
	}

	public void setEmploymentHistoryBeans(Set<EmploymentHistoryBean> employmentHistoryBeans) {
		this.employmentHistoryBeans = employmentHistoryBeans;
	}
	
	
	public String getAppointedWing() {
		return appointedWing;
	}

	public void setAppointedWing(String appointedWing) {
		this.appointedWing = appointedWing;
	}
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getContractExtendedUpto() {
		return contractExtendedUpto;
	}

	public void setContractExtendedUpto(Date contractExtendedUpto) {
		this.contractExtendedUpto = contractExtendedUpto;
	}

	public String getPayscale() {
		return payscale;
	}

	public void setPayscale(String payscale) {
		this.payscale = payscale;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getContractExtentionNo() {
		return contractExtentionNo;
	}

	public void setContractExtentionNo(String contractExtentionNo) {
		this.contractExtentionNo = contractExtentionNo;
	}

	public Date getContractExtentionDate() {
		return contractExtentionDate;
	}

	public void setContractExtentionDate(Date contractExtentionDate) {
		this.contractExtentionDate = contractExtentionDate;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}

	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeType="
				+ employeeType + ", fatherName=" + fatherName + ", designationId=" + designationId
				+ ", permanentEmployeeId=" + permanentEmployeeId + ", dateOfBirth=" + dateOfBirth + ", maritalStatus="
				+ maritalStatus + ", locationId=" + locationId + ", appointmentNo=" + appointmentNo + ", joiningDate="
				+ joiningDate + ", contractStartDate=" + contractStartDate + ", contractEndDate=" + contractEndDate
				+ ", gender=" + gender + ", sanctionNo=" + sanctionNo + ", workPhone=" + workPhone + ", mobilePhone="
				+ mobilePhone + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", street="
				+ street + ", city=" + city + ", pin=" + pin + ", state=" + state + ", personalEmailId="
				+ personalEmailId + ", officialEmailId=" + officialEmailId + ", panNo=" + panNo + ", nationality="
				+ nationality + ", misAuditBean=" + misAuditBean + ", employeeQualificationBeans="
				+ employeeQualificationBeans + ", employmentHistoryBeans=" + employmentHistoryBeans + ",employeeContactExtendedBeans="+ employeeContactExtendedBeans +"]";
	}

	@Override
	public int compareTo(EmployeeBean o) {
		
		return new Long(this.employeeId).compareTo(new Long(o.employeeId));
	}

	public void setEmployeeContactExtendedBeans(
			Set<EmployeeContactExtendedBean> employeeContactExtendedBeans) {
		this.employeeContactExtendedBeans = employeeContactExtendedBeans;
	}

	public Set<EmployeeContactExtendedBean> getEmployeeContactExtendedBeans() {
		return employeeContactExtendedBeans;
	}

	public void setDesigId(int desigId) {
		this.desigId = desigId;
	}

	public int getDesigId() {
		return desigId;
	}
}
