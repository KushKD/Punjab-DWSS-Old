package com.prwss.mis.tender.struts;


public class TenderSecurityGridBean {
	
	private long depositId;
	private String tenderId;
	private String instrumentType;
	private String instrumentIssuer;
	private String dateOfIssue;
	private String startDate;
	private String endDate;
	private double amount;
	private String remarks;
	private String locationId;
	
	public long getDepositId() {
		return depositId;
	}
	public void setDepositId(long depositId) {
		this.depositId = depositId;
	}
	public String getTenderId() {
		return tenderId;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public String getInstrumentIssuer() {
		return instrumentIssuer;
	}
	public void setInstrumentIssuer(String instrumentIssuer) {
		this.instrumentIssuer = instrumentIssuer;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	

}
