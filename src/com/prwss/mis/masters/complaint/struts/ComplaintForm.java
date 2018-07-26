package com.prwss.mis.masters.complaint.struts;


import org.apache.struts.validator.ValidatorForm;

public class ComplaintForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948280019099981299L;
	
	private Long complaintId;
	private String complaintType;
	private String locationId;
	private String complaintDescription;
	private Long level1EmployeeId;
	private Long level2EmployeeId;
	private Long level3EmployeeId;
	private Long level4EmployeeId;
	private Long level5EmployeeId;
	private Long level1RedressalDays;
	private Long level2RedressalDays;
	private Long level3RedressalDays;
	private Long level4RedressalDays;
	private Long level5RedressalDays;
	private Long[] complaintIds;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getComplaintDescription() {
		return complaintDescription;
	}
	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}
	public Long getLevel1EmployeeId() {
		return level1EmployeeId;
	}
	public void setLevel1EmployeeId(Long level1EmployeeId) {
		this.level1EmployeeId = level1EmployeeId;
	}
	public Long getLevel2EmployeeId() {
		return level2EmployeeId;
	}
	public void setLevel2EmployeeId(Long level2EmployeeId) {
		this.level2EmployeeId = level2EmployeeId;
	}
	public Long getLevel3EmployeeId() {
		return level3EmployeeId;
	}
	public void setLevel3EmployeeId(Long level3EmployeeId) {
		this.level3EmployeeId = level3EmployeeId;
	}
	public Long getLevel4EmployeeId() {
		return level4EmployeeId;
	}
	public void setLevel4EmployeeId(Long level4EmployeeId) {
		this.level4EmployeeId = level4EmployeeId;
	}
	public Long getLevel5EmployeeId() {
		return level5EmployeeId;
	}
	public void setLevel5EmployeeId(Long level5EmployeeId) {
		this.level5EmployeeId = level5EmployeeId;
	}
	public Long getLevel1RedressalDays() {
		return level1RedressalDays;
	}
	public void setLevel1RedressalDays(Long level1RedressalDays) {
		this.level1RedressalDays = level1RedressalDays;
	}
	public Long getLevel2RedressalDays() {
		return level2RedressalDays;
	}
	public void setLevel2RedressalDays(Long level2RedressalDays) {
		this.level2RedressalDays = level2RedressalDays;
	}
	public Long getLevel3RedressalDays() {
		return level3RedressalDays;
	}
	public void setLevel3RedressalDays(Long level3RedressalDays) {
		this.level3RedressalDays = level3RedressalDays;
	}
	public Long getLevel4RedressalDays() {
		return level4RedressalDays;
	}
	public void setLevel4RedressalDays(Long level4RedressalDays) {
		this.level4RedressalDays = level4RedressalDays;
	}
	public Long getLevel5RedressalDays() {
		return level5RedressalDays;
	}
	public void setLevel5RedressalDays(Long level5RedressalDays) {
		this.level5RedressalDays = level5RedressalDays;
	}
	public Long[] getComplaintIds() {
		return complaintIds;
	}
	public void setComplaintIds(Long[] complaintIds) {
		this.complaintIds = complaintIds;
	}
	
	
	
}
