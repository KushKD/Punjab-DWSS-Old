package com.prwss.mis.tender.struts;

import java.io.Serializable;

public class TenderObjectionGridBean implements Serializable {
	
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3285666053732199398L;
	
	
	private String tenderId;
	
	private String objectedBy;

	private String dateOfObjection;
	
	private String reasons;
	
	private String statusOfObjection;
	
	private String locationId;
	
	private long objectionId;
	

	
	public long getObjectionId() {
		return objectionId;
	}

	public void setObjectionId(long objectionId) {
		this.objectionId = objectionId;
	}

	public String getObjectedBy() {
		return objectedBy;
	}

	public void setObjectedBy(String objectedBy) {
		this.objectedBy = objectedBy;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}


	public String getDateOfObjection() {
		return dateOfObjection;
	}

	public void setDateOfObjection(String dateOfObjection) {
		this.dateOfObjection = dateOfObjection;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getStatusOfObjection() {
		return statusOfObjection;
	}

	public void setStatusOfObjection(String statusOfObjection) {
		this.statusOfObjection = statusOfObjection;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


}
