package com.prwss.mis.hr.leave.struts;

import org.apache.struts.validator.ValidatorForm;

public class LeaveApprovalForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4242045339109943537L;
	
	private String leaveStatus;
	private String leaveRequestFromDate;
	private String leaveRequestToDate;
	private long leaveId;
	private String locationId;
	private String leaveFromDate;
	private String leaveToDate;
	private String leaveReason;
	private String officerComments;
	
	
	public String getOfficerComments() {
		return officerComments;
	}
	public void setOfficerComments(String officerComments) {
		this.officerComments = officerComments;
	}
	public String getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	public String getLeaveRequestFromDate() {
		return leaveRequestFromDate;
	}
	public void setLeaveRequestFromDate(String leaveRequestFromDate) {
		this.leaveRequestFromDate = leaveRequestFromDate;
	}
	public String getLeaveRequestToDate() {
		return leaveRequestToDate;
	}
	public void setLeaveRequestToDate(String leaveRequestToDate) {
		this.leaveRequestToDate = leaveRequestToDate;
	}
	public long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(long leaveId) {
		this.leaveId = leaveId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLeaveFromDate() {
		return leaveFromDate;
	}
	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}
	public String getLeaveToDate() {
		return leaveToDate;
	}
	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	
}
