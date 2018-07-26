package com.prwss.mis.tender.struts;

import javax.persistence.Column;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class EOIUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829448710L;
	private String locationId;
	private Long eoiUploadId;
	private String expiryDate;
	private String eoiReferenceNo;
	private String eoiTitle;
	
	private FormFile eoiFile1;
	private FormFile eoiFile2;
	private FormFile eoiFile3;
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Long getEoiUploadId() {
		return eoiUploadId;
	}
	public void setEoiUploadId(Long eoiUploadId) {
		this.eoiUploadId = eoiUploadId;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getEoiReferenceNo() {
		return eoiReferenceNo;
	}
	public void setEoiReferenceNo(String eoiReferanceNo) {
		this.eoiReferenceNo = eoiReferanceNo;
	}
	public String getEoiTitle() {
		return eoiTitle;
	}
	public void setEoiTitle(String eoiTitle) {
		this.eoiTitle = eoiTitle;
	}
	public FormFile getEoiFile1() {
		return eoiFile1;
	}
	public void setEoiFile1(FormFile eoiFile1) {
		this.eoiFile1 = eoiFile1;
	}
	public FormFile getEoiFile2() {
		return eoiFile2;
	}
	public void setEoiFile2(FormFile eoiFile2) {
		this.eoiFile2 = eoiFile2;
	}
	public FormFile getEoiFile3() {
		return eoiFile3;
	}
	public void setEoiFile3(FormFile eoiFile3) {
		this.eoiFile3 = eoiFile3;
	}	
}
