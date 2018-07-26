package com.prwss.mis.masters.employee.struts;

import java.util.Arrays;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5745872026632157571L;

	private long employeeId;
	private String employeeName;
	private String qualification;
	private String personalEmail;
	private String officeEmail;
	private String nationality;
	private String gender;
	private String dateOfBirth;
	private String fatherName;
	private String maritalStatus;
	private String locationId;
	private String designation;
	private String employeeType;
	private String joiningDate;
	private long permanentEmployeeId;
	private String appointmentNo;
	private String sanctionNo;
	private String state;
	private String appointedWing;
	private String panNo;
	private Datagrid employeeHistoryGrid;
	private Datagrid employeeQualificationGrid;
	private Datagrid employeePromotionGrid;
	private Datagrid contractExtentionGrid;
	private String addressLine1;
	private String addressLine2;
	private String street;
	private String city;
	private String contractStartDate;
	private String contractEndDate;
	private String workPhoneNo;
	private String mobilePhoneNo;
	private String pinCode;
	private Long[] employeeIds;
	private String reportingOfficerLocation;
	private long reportingOfficerId;
	private String contractExtentionNo;
	private String contractExtentionDate ;
	private String retirementDate ;	
 
	
	
	// These Fields needs to updated in BO and TABLE// 
	private String contractExtendedUpto;
	private String currentDesignation;
	private String category;
	private String payScale;
	private String specialization;
	private String promotedDesignation;
	private String dateOfPromotion;
	
	
	public Datagrid getEmployeePromotionGrid() {
		return employeePromotionGrid;
	}
	public void setEmployeePromotionGrid(Datagrid employeePromotionGrid) {
		this.employeePromotionGrid = employeePromotionGrid;
	}
	public String getContractExtendedUpto() {
		return contractExtendedUpto;
	}
	public void setContractExtendedUpto(String contractExtendedUpto) {
		this.contractExtendedUpto = contractExtendedUpto;
	}
	public String getCurrentDesignation() {
		return currentDesignation;
	}
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPayScale() {
		return payScale;
	}
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getPromotedDesignation() {
		return promotedDesignation;
	}
	public void setPromotedDesignation(String promotedDesignation) {
		this.promotedDesignation = promotedDesignation;
	}
	public String getDateOfPromotion() {
		return dateOfPromotion;
	}
	public void setDateOfPromotion(String dateOfPromotion) {
		this.dateOfPromotion = dateOfPromotion;
	}
	public String getReportingOfficerLocation() {
		return reportingOfficerLocation;
	}
	public void setReportingOfficerLocation(String reportingOfficerLocation) {
		this.reportingOfficerLocation = reportingOfficerLocation;
	}
	public long getReportingOfficerId() {
		return reportingOfficerId;
	}
	public void setReportingOfficerId(long reportingOfficerId) {
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getOfficeEmail() {
		return officeEmail;
	}
	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public long getPermanentEmployeeId() {
		return permanentEmployeeId;
	}
	public void setPermanentEmployeeId(long permanentEmployeeId) {
		this.permanentEmployeeId = permanentEmployeeId;
	}
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getSanctionNo() {
		return sanctionNo;
	}
	public void setSanctionNo(String sanctionNo) {
		this.sanctionNo = sanctionNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAppointedWing() {
		return appointedWing;
	}
	public void setAppointedWing(String appointedWing) {
		this.appointedWing = appointedWing;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public Datagrid getEmployeeHistoryGrid() {
		return employeeHistoryGrid;
	}
	public void setEmployeeHistoryGrid(Datagrid employeeHistoryGrid) {
		this.employeeHistoryGrid = employeeHistoryGrid;
	}
	public Datagrid getEmployeeQualificationGrid() {
		return employeeQualificationGrid;
	}
	public void setEmployeeQualificationGrid(Datagrid employeeQualificationGrid) {
		this.employeeQualificationGrid = employeeQualificationGrid;
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
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	public String getWorkPhoneNo() {
		return workPhoneNo;
	}
	public void setWorkPhoneNo(String workPhoneNo) {
		this.workPhoneNo = workPhoneNo;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public Long[] getEmployeeIds() {
		return employeeIds;
	}
	public void setEmployeeIds(Long[] employeeIds) {
		this.employeeIds = employeeIds;
	}
	
	
	
	public String getContractExtentionNo() {
		return contractExtentionNo;
	}
	public void setContractExtentionNo(String contractExtentionNo) {
		this.contractExtentionNo = contractExtentionNo;
	}
	public String getContractExtentionDate() {
		return contractExtentionDate;
	}
	public void setContractExtentionDate(String contractExtentionDate) {
		this.contractExtentionDate = contractExtentionDate;
	}
	public String getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}
	@Override
	public String toString() {
		return "EmployeeForm [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", qualification=" + qualification
				+ ", personalEmail=" + personalEmail + ", officeEmail="
				+ officeEmail + ", nationality=" + nationality + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", fatherName="
				+ fatherName + ", maritalStatus=" + maritalStatus
				+ ", officeName=" + locationId + ", designation=" + designation
				+ ", employeeType=" + employeeType + ", joiningDate="
				+ joiningDate + ", permanentEmployeeId=" + permanentEmployeeId
				+ ", appointmentNo=" + appointmentNo + ", sanctionNo="
				+ sanctionNo + ", state=" + state + ", appointedWing="
				+ appointedWing + ", panNo=" + panNo + ", employeeHistoryGrid="
				+ employeeHistoryGrid + ", employeeQualificationGrid="
				+ employeeQualificationGrid + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", street=" + street
				+ ", city=" + city + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", workPhoneNo="
				+ workPhoneNo + ",contractExtentionGrid="+ contractExtentionGrid +", mobilePhoneNo=" + mobilePhoneNo
				+ ", pinCode=" + pinCode + ", employeeIds="
				+ Arrays.toString(employeeIds) + "]";
	}
  
	public void setContractExtentionGrid(Datagrid contractExtentionGrid) {
		this.contractExtentionGrid = contractExtentionGrid;
	}
	public Datagrid getContractExtentionGrid() {
		return contractExtentionGrid;
	}
	
	
	

}
