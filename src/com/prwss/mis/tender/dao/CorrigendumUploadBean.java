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
@Table(name="corrigendum_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CorrigendumUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730106540151471465L;
	
	@Id
	@GeneratedValue(generator ="seq_corrigendum_upload_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_corrigendum_upload_id", sequenceName = "prwss_main.seq_corrigendum_upload_id")
	@Column(name = "corrigendum_upload_id", nullable = false)
	private long corrigendumUploadId;
	
	@Column(name = "district_id", nullable = false)
	private String districtId;
	
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	
	@Column(name = "corrigendum_description")
	private String corrigendumDescription;
	
	
	@Column(name = "doc_id")
	private String docId;
	
	@Column(name = "corrigendum_file_name")
	private String corrigendumFileName;
	
	@Column(name = "corrigendum_for")
	private String corrigendumFor;
	
	@Column(name = "deploy_site_name")
	private String deploySiteName;
	
	@Column(name = "corrigendum_file")
//	@Type(type="org.hibernate.type.BinaryType")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] corrigendumFile;
	
	
	@Embedded
	private MISAuditBean misAuditBean;


	public long getCorrigendumUploadId() {
		return corrigendumUploadId;
	}


	public void setCorrigendumUploadId(long corrigendumUploadId) {
		this.corrigendumUploadId = corrigendumUploadId;
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


	public String getCorrigendumDescription() {
		return corrigendumDescription;
	}


	public void setCorrigendumDescription(String corrigendumDescription) {
		this.corrigendumDescription = corrigendumDescription;
	}


	public String getDocId() {
		return docId;
	}


	public void setDocId(String docId) {
		this.docId = docId;
	}


	public String getCorrigendumFileName() {
		return corrigendumFileName;
	}


	public void setCorrigendumFileName(String corrigendumFileName) {
		this.corrigendumFileName = corrigendumFileName;
	}


	public String getDeploySiteName() {
		return deploySiteName;
	}


	public void setDeploySiteName(String deploySiteName) {
		this.deploySiteName = deploySiteName;
	}


	public byte[] getCorrigendumFile() {
		return corrigendumFile;
	}


	public void setCorrigendumFile(byte[] corrigendumFile) {
		this.corrigendumFile = corrigendumFile;
	}


	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}


	public void setCorrigendumFor(String corrigendumFor) {
		this.corrigendumFor = corrigendumFor;
	}


	public String getCorrigendumFor() {
		return corrigendumFor;
	}
	
	
		
}
