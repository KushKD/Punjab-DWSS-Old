package com.prwss.mis.service.ticket.struts;

import org.apache.struts.validator.ValidatorForm;

public class NewTicketForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278499601754201507L;
	
	private long complaintId;
	private String priorityLevel;
	private String subject;
	private String description;
	private String locationId;
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(long complaintId) {
		this.complaintId = complaintId;
	}
	
	public String getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
