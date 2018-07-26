package com.prwss.mis.service.ticket.struts;

import org.apache.struts.validator.ValidatorForm;

public class TicketResolutionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455722216714729250L;
	private Long ticketId;
	private String ticketDate;
	private String employeeName;
	private String locationName;
	private String complaintType;
	private String status;
	private String priorityLevel;
	private boolean forward;
	private String subject;
	private String comments;
	private String description;
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	
	public boolean isForward() {
		return forward;
	}
	public void setForward(boolean forward) {
		this.forward = forward;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
