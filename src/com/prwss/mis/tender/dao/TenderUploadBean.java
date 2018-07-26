package com.prwss.mis.tender.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="tender_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TenderUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730106540151471465L;
	
	@Id
	@GeneratedValue(generator ="seq_tender_upload_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_tender_upload_id", sequenceName = "prwss_main.seq_tender_upload_id")
	@Column(name = "tender_upload_id", nullable = false)
	private long tenderUploadId;
	
	@Column(name = "district_id", nullable = false)
	private String districtId;
	
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	
	@Column(name = "work_description")
	private String workDescription;
	
	@Column(name = "last_date")
	private Date lastDateofReceipt;
	
	@Column(name = "bid_opening_date")
	private Date bidsOpeningDate;
	
	@Column(name = "tender_id")
	private String tenderId;
	
	@Column(name = "bid_doc_file_name")
	private String bidDocFileName;
	
	@Column(name = "tender_notice_file_name")
	private String tenderNoticeFileName;
	
	@Column(name = "deploy_site_name")
	private String deploySiteName;
	
	@Column(name = "bid_doc_file")
//	@Type(type="org.hibernate.type.BinaryType")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] bidDocFile;
	
	
	@Column(name = "tender_notice_file")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] tenderNoticeFile;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getDeploySiteName() {
		return deploySiteName;
	}

	public void setDeploySiteName(String deploySiteName) {
		this.deploySiteName = deploySiteName;
	}

	public String getBidDocFileName() {
		return bidDocFileName;
	}

	public void setBidDocFileName(String bidDocFileName) {
		this.bidDocFileName = bidDocFileName;
	}

	public String getTenderNoticeFileName() {
		return tenderNoticeFileName;
	}

	public void setTenderNoticeFileName(String tenderNoticeFileName) {
		this.tenderNoticeFileName = tenderNoticeFileName;
	}

	public long getTenderUploadId() {
		return tenderUploadId;
	}

	public void setTenderUploadId(long tenderUploadId) {
		this.tenderUploadId = tenderUploadId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Date getLastDateofReceipt() {
		return lastDateofReceipt;
	}

	public void setLastDateofReceipt(Date lastDateofReceipt) {
		this.lastDateofReceipt = lastDateofReceipt;
	}

	public Date getBidsOpeningDate() {
		return bidsOpeningDate;
	}

	public void setBidsOpeningDate(Date bidsOpeningDate) {
		this.bidsOpeningDate = bidsOpeningDate;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public byte[] getBidDocFile() {
		return bidDocFile;
	}

	public void setBidDocFile(byte[] bidDocFile) {
		this.bidDocFile = bidDocFile;
	}

	public byte[] getTenderNoticeFile() {
		return tenderNoticeFile;
	}

	public void setTenderNoticeFile(byte[] tenderNoticeFile) {
		this.tenderNoticeFile = tenderNoticeFile;
	}
	
}
