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
@Table(name="adv_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class AdvUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730106540151471465L;
	
	@Id
	@GeneratedValue(generator ="seq_adv_upload_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_adv_upload_id", sequenceName = "prwss_main.seq_adv_upload_id")
	@Column(name = "adv_upload_id", nullable = false)
	private long advUploadId;
	
	@Column(name = "district_id", nullable = false)
	private String districtId;
	
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	@Column(name = "post_name")
	private String postName;
	
	@Column(name = "discription")
	private String advDiscription;
	
	
	@Column(name = "adv_file_name")
	private String advFileName;
	
	@Column(name = "deploy_site_name")
	private String deploySiteName;
	
	@Column(name = "adv_file")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] advFile;
	
	@Column(name = "last_date")
	private Date lastDate;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getAdvUploadId() {
		return advUploadId;
	}

	public void setAdvUploadId(long advUploadId) {
		this.advUploadId = advUploadId;
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

	public String getAdvDiscription() {
		return advDiscription;
	}

	public void setAdvDiscription(String advDiscription) {
		this.advDiscription = advDiscription;
	}

	public String getAdvFileName() {
		return advFileName;
	}

	public void setAdvFileName(String advFileName) {
		this.advFileName = advFileName;
	}

	public String getDeploySiteName() {
		return deploySiteName;
	}

	public void setDeploySiteName(String deploySiteName) {
		this.deploySiteName = deploySiteName;
	}

	public byte[] getAdvFile() {
		return advFile;
	}

	public void setAdvFile(byte[] advFile) {
		this.advFile = advFile;
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

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostName() {
		return postName;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

			
}
