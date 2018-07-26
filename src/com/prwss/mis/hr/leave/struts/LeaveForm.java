package com.prwss.mis.hr.leave.struts;

import org.apache.struts.validator.ValidatorForm;

public class LeaveForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1674158751377524810L;
	private long leaveId;
	private String locationId;
	private String leaveFromDate;
	private String leaveToDate;
	private String leaveReason;
	private long reportingOfficerId;
	private long leaveBalance;
	private long leaveEntitled;
	private long leaveAvailed;
	
	
	
	public long getLeaveEntitled() {
		return leaveEntitled;
	}
	public void setLeaveEntitled(long leaveEntitled) {
		this.leaveEntitled = leaveEntitled;
	}
	public long getLeaveAvailed() {
		return leaveAvailed;
	}
	public void setLeaveAvailed(long leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}
	public long getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(long leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public long getReportingOfficerId() {
		return reportingOfficerId;
	}
	public void setReportingOfficerId(long reportingOfficerId) {
		this.reportingOfficerId = reportingOfficerId;
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
