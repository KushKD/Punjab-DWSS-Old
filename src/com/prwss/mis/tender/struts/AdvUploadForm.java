package com.prwss.mis.tender.struts;

import javax.persistence.Column;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class AdvUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829448710L;
	private String locationId;
	private Long advUploadId;
	private String expiryDate;
	private String postName;
	private String advDiscription;
	private String lastDate;
	
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	private FormFile advFile;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Long getAdvUploadId() {
		return advUploadId;
	}

	public void setAdvUploadId(Long advUploadId) {
		this.advUploadId = advUploadId;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getAdvDiscription() {
		return advDiscription;
	}

	public void setAdvDiscription(String advDiscription) {
		this.advDiscription = advDiscription;
	}

	public FormFile getAdvFile() {
		return advFile;
	}

	public void setAdvFile(FormFile advFile) {
		this.advFile = advFile;
	}

}
