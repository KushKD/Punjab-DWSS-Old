package com.prwss.mis.tender.struts;

import javax.persistence.Column;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class QuoatationUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829448710L;
	private String locationId;
	private Long quoatationUploadId;
	private String expiryDate;
	private String quoatationDiscription;
	
	private FormFile quoatationFile;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Long getQuoatationUploadId() {
		return quoatationUploadId;
	}

	public void setQuoatationUploadId(Long quoatationUploadId) {
		this.quoatationUploadId = quoatationUploadId;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getQuoatationDiscription() {
		return quoatationDiscription;
	}

	public void setQuoatationDiscription(String quoatationDiscription) {
		this.quoatationDiscription = quoatationDiscription;
	}

	public FormFile getQuoatationFile() {
		return quoatationFile;
	}

	public void setQuoatationFile(FormFile quoatationFile) {
		this.quoatationFile = quoatationFile;
	}

}
