package com.prwss.mis.service.ticket.struts;



import org.apache.struts.validator.ValidatorForm;

public class MyTicketForm extends ValidatorForm {

	
	
	
	
	private static final long serialVersionUID = -4899643112732312179L;
	private long ticketId;
	private String status;
	private String fromDate = null;
	private String toDate = null;
	private String complaintBookType;
	
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public void setComplaintBookType(String complaintBookType) {
		this.complaintBookType = complaintBookType;
	}
	public String getComplaintBookType() {
		return complaintBookType;
	}
	
	
}
