package com.prwss.mis.tender.struts;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class CorrigendumUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829448710L;
	private FormFile corrigendumFile;
	private String corrigendumDescription;
	private String locationId;
	private String docId;
	private String corrigendumFor;
	private Long corrigendumUploadId;
	
	public FormFile getCorrigendumFile() {
		return corrigendumFile;
	}
	public void setCorrigendumFile(FormFile corrigendumFile) {
		this.corrigendumFile = corrigendumFile;
	}
	public String getCorrigendumDescription() {
		return corrigendumDescription;
	}
	public void setCorrigendumDescription(String corrigendumDescription) {
		this.corrigendumDescription = corrigendumDescription;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getCorrigendumFor() {
		return corrigendumFor;
	}
	public void setCorrigendumFor(String corrigendumFor) {
		this.corrigendumFor = corrigendumFor;
	}
	public Long getCorrigendumUploadId() {
		return corrigendumUploadId;
	}
	public void setCorrigendumUploadId(Long corrigendumUploadId) {
		this.corrigendumUploadId = corrigendumUploadId;
	}

}
