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
@Table(name="quoatation_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class QuoatationUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730106540151471465L;
	
	@Id
	@GeneratedValue(generator ="seq_quoatation_upload_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_quoatation_upload_id", sequenceName = "prwss_main.seq_quoatation_upload_id")
	@Column(name = "quoatation_upload_id", nullable = false)
	private long quoatationUploadId;
	
	@Column(name = "district_id", nullable = false)
	private String districtId;
	
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	
	@Column(name = "quoatation_discription")
	private String quoatationDiscription;
	
	
	@Column(name = "quoatation_file_name")
	private String quoatationFileName;
	
	@Column(name = "deploy_site_name")
	private String deploySiteName;
	
	@Column(name = "quoatation_file")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] quoatationFile;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getQuoatationUploadId() {
		return quoatationUploadId;
	}

	public void setQuoatationUploadId(long quoatationUploadId) {
		this.quoatationUploadId = quoatationUploadId;
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

	public String getQuoatationDiscription() {
		return quoatationDiscription;
	}

	public void setQuoatationDiscription(String quoatationDiscription) {
		this.quoatationDiscription = quoatationDiscription;
	}

	public String getQuoatationFileName() {
		return quoatationFileName;
	}

	public void setQuoatationFileName(String quoatationFileName) {
		this.quoatationFileName = quoatationFileName;
	}

	public String getDeploySiteName() {
		return deploySiteName;
	}

	public void setDeploySiteName(String deploySiteName) {
		this.deploySiteName = deploySiteName;
	}

	public byte[] getQuoatationFile() {
		return quoatationFile;
	}

	public void setQuoatationFile(byte[] quoatationFile) {
		this.quoatationFile = quoatationFile;
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
