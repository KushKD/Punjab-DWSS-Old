package com.prwss.mis.tender.struts;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class TenderUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829448710L;
	private FormFile bidDocumentFile;
	private FormFile tenderNoticeFile;
	private String workDescription;
	private String lastDateofReceipt;
	private String bidsOpeningDate;
	private String locationId;
	private String tenderNo;
	private String expiryDate;
	private Long tenderUploadId;
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	public FormFile getBidDocumentFile() {
		return bidDocumentFile;
	}
	public void setBidDocumentFile(FormFile bidDocumentFile) {
		this.bidDocumentFile = bidDocumentFile;
	}
	public FormFile getTenderNoticeFile() {
		return tenderNoticeFile;
	}
	public void setTenderNoticeFile(FormFile tenderNoticeFile) {
		this.tenderNoticeFile = tenderNoticeFile;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getLastDateofReceipt() {
		return lastDateofReceipt;
	}
	public void setLastDateofReceipt(String lastDateofReceipt) {
		this.lastDateofReceipt = lastDateofReceipt;
	}
	public String getBidsOpeningDate() {
		return bidsOpeningDate;
	}
	public void setBidsOpeningDate(String bidsOpeningDate) {
		this.bidsOpeningDate = bidsOpeningDate;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setTenderUploadId(Long tenderUploadId) {
		this.tenderUploadId = tenderUploadId;
	}
	public Long getTenderUploadId() {
		return tenderUploadId;
	}
	
	
	

}
