package com.prwss.mis.pmm.schemeupload.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

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
@Table(name="scheme_upload", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 873010654015100090L;
	
	@Id
	@GeneratedValue(generator = "scheme_upload_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "scheme_upload_id_seq", sequenceName = "prwss_main.scheme_upload_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "scheme_id", nullable = false)
	private String scheme_id;
	
	@Column(name = "location_id", nullable = false)
	private String location_id;
	
	@Column(name = "scheme_type")
	private String scheme_type;
	
	@Column(name = "digitalSurvey_name_pdf")
	private String digitalSurvey_name_pdf;
	
	@Column(name = "digitalSurvey_name_pdf_File")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] digitalSurvey_name_pdf_File;
	
	
	
	@Column(name = "digitalSurvey_name_cdr")
	private String digitalSurvey_name_cdr;
	
	@Column(name = "digitalSurvey_name_cdr_File")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] digitalSurvey_name_cdr_File;
	
	
	@Column(name = "schemeEstimate_name")
	private String schemeEstimate_name;
	
	@Column(name = "schemeEstimateFile")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] schemeEstimateFile;
	
	
	@Column(name = "adminAproval_name")
	private String adminAproval_name;
	
	@Column(name = "adminAprovalFile")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] adminAprovalFile;
	
	@Column(name = "strataChart_name")
	private String strataChart_name;
	
	@Column(name = "strataChartFile")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] strataChartFile;
		
	//crt_by_usr
	@Column(name = "crt_by_usr")
	private String crt_by_usr;
	
	@Column(name = "user_id")
	private String user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getScheme_type() {
		return scheme_type;
	}

	public void setScheme_type(String scheme_type) {
		this.scheme_type = scheme_type;
	}

	public String getDigitalSurvey_name_pdf() {
		return digitalSurvey_name_pdf;
	}

	public void setDigitalSurvey_name_pdf(String digitalSurvey_name_pdf) {
		this.digitalSurvey_name_pdf = digitalSurvey_name_pdf;
	}

	public byte[] getDigitalSurvey_name_pdf_File() {
		return digitalSurvey_name_pdf_File;
	}

	public void setDigitalSurvey_name_pdf_File(byte[] digitalSurvey_name_pdf_File) {
		this.digitalSurvey_name_pdf_File = digitalSurvey_name_pdf_File;
	}

	public String getDigitalSurvey_name_cdr() {
		return digitalSurvey_name_cdr;
	}

	public void setDigitalSurvey_name_cdr(String digitalSurvey_name_cdr) {
		this.digitalSurvey_name_cdr = digitalSurvey_name_cdr;
	}

	public byte[] getDigitalSurvey_name_cdr_File() {
		return digitalSurvey_name_cdr_File;
	}

	public void setDigitalSurvey_name_cdr_File(byte[] digitalSurvey_name_cdr_File) {
		this.digitalSurvey_name_cdr_File = digitalSurvey_name_cdr_File;
	}

	public String getSchemeEstimate_name() {
		return schemeEstimate_name;
	}

	public void setSchemeEstimate_name(String schemeEstimate_name) {
		this.schemeEstimate_name = schemeEstimate_name;
	}

	public byte[] getSchemeEstimateFile() {
		return schemeEstimateFile;
	}

	public void setSchemeEstimateFile(byte[] schemeEstimateFile) {
		this.schemeEstimateFile = schemeEstimateFile;
	}

	public String getAdminAproval_name() {
		return adminAproval_name;
	}

	public void setAdminAproval_name(String adminAproval_name) {
		this.adminAproval_name = adminAproval_name;
	}

	public byte[] getAdminAprovalFile() {
		return adminAprovalFile;
	}

	public void setAdminAprovalFile(byte[] adminAprovalFile) {
		this.adminAprovalFile = adminAprovalFile;
	}

	public String getStrataChart_name() {
		return strataChart_name;
	}

	public void setStrataChart_name(String strataChart_name) {
		this.strataChart_name = strataChart_name;
	}

	public byte[] getStrataChartFile() {
		return strataChartFile;
	}

	public void setStrataChartFile(byte[] strataChartFile) {
		this.strataChartFile = strataChartFile;
	}

	public String getCrt_by_usr() {
		return crt_by_usr;
	}

	public void setCrt_by_usr(String crt_by_usr) {
		this.crt_by_usr = crt_by_usr;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "SchemeUploadBean [id=" + id + ", scheme_id=" + scheme_id
				+ ", location_id=" + location_id + ", scheme_type="
				+ scheme_type + ", digitalSurvey_name_pdf="
				+ digitalSurvey_name_pdf + ", digitalSurvey_name_pdf_File="
				+ Arrays.toString(digitalSurvey_name_pdf_File)
				+ ", digitalSurvey_name_cdr=" + digitalSurvey_name_cdr
				+ ", digitalSurvey_name_cdr_File="
				+ Arrays.toString(digitalSurvey_name_cdr_File)
				+ ", schemeEstimate_name=" + schemeEstimate_name
				+ ", schemeEstimateFile=" + Arrays.toString(schemeEstimateFile)
				+ ", adminAproval_name=" + adminAproval_name
				+ ", adminAprovalFile=" + Arrays.toString(adminAprovalFile)
				+ ", strataChart_name=" + strataChart_name
				+ ", strataChartFile=" + Arrays.toString(strataChartFile)
				+ ", crt_by_usr=" + crt_by_usr + ", user_id=" + user_id + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

	

	
}
