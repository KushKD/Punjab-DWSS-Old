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
@Table(name="eoi_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EOIUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730106540151471465L;
	
	@Id
	@GeneratedValue(generator ="seq_eoi_upload_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_eoi_upload_id", sequenceName = "prwss_main.seq_eoi_upload_id")
	@Column(name = "eoi_upload_id", nullable = false)
	private long eoiUploadId;
	
	@Column(name = "district_id", nullable = false)
	private String districtId;
	
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	
	@Column(name = "eoi_referance_no")
	private String eoiReferanceNo;
	
	@Column(name="eoi_title")
	private String eoiTitle;
	
	@Column(name = "eoi_file_name1")
	private String eoiFileName1;
	
	@Column(name = "eoi_file_name2")
	private String eoiFileName2;
	
	@Column(name = "eoi_file_name3")
	private String eoiFileName3;
	
	@Column(name = "deploy_site_name")
	private String deploySiteName;
	
	@Column(name = "eoi_file1")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] eoiFile1;
	
	@Column(name = "eoi_file2")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] eoiFile2;
	
	@Column(name = "eoi_file3")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] eoiFile3;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getEoiUploadId() {
		return eoiUploadId;
	}

	public void setEoiUploadId(long eoiUploadId) {
		this.eoiUploadId = eoiUploadId;
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

	public String getEoiReferanceNo() {
		return eoiReferanceNo;
	}

	public void setEoiReferanceNo(String eoiReferanceNo) {
		this.eoiReferanceNo = eoiReferanceNo;
	}

	public String getEoiTitle() {
		return eoiTitle;
	}

	public void setEoiTitle(String eoiTitle) {
		this.eoiTitle = eoiTitle;
	}

	public String getEoiFileName1() {
		return eoiFileName1;
	}

	public void setEoiFileName1(String eoiFileName1) {
		this.eoiFileName1 = eoiFileName1;
	}

	public String getEoiFileName2() {
		return eoiFileName2;
	}

	public void setEoiFileName2(String eoiFileName2) {
		this.eoiFileName2 = eoiFileName2;
	}

	public String getEoiFileName3() {
		return eoiFileName3;
	}

	public void setEoiFileName3(String eoiFileName3) {
		this.eoiFileName3 = eoiFileName3;
	}

	public String getDeploySiteName() {
		return deploySiteName;
	}

	public void setDeploySiteName(String deploySiteName) {
		this.deploySiteName = deploySiteName;
	}

	public byte[] getEoiFile1() {
		return eoiFile1;
	}

	public void setEoiFile1(byte[] eoiFile1) {
		this.eoiFile1 = eoiFile1;
	}

	public byte[] getEoiFile2() {
		return eoiFile2;
	}

	public void setEoiFile2(byte[] eoiFile2) {
		this.eoiFile2 = eoiFile2;
	}

	public byte[] getEoiFile3() {
		return eoiFile3;
	}

	public void setEoiFile3(byte[] eoiFile3) {
		this.eoiFile3 = eoiFile3;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
		
}
