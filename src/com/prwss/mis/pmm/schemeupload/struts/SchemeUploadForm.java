package com.prwss.mis.pmm.schemeupload.struts;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class SchemeUploadForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512440170829440000L;

	private int id;

	private String digitalSurvey_name_pdf;
	private FormFile digitalSurvey_name_pdf_File;

	private String digitalSurvey_name_cdr;
	private FormFile digitalSurvey_name_cdr_File;

	private String schemeEstimate_name;
	private FormFile schemeEstimateFile;

	private String adminAproval_name;
	private FormFile adminAprovalFile;

	private String strataChart_name;
	private FormFile strataChartFile;

	private String scheme_id;
	private String location_id;
	
	private String scheme_type;
	private String user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDigitalSurvey_name_pdf() {
		return digitalSurvey_name_pdf;
	}
	public void setDigitalSurvey_name_pdf(String digitalSurvey_name_pdf) {
		this.digitalSurvey_name_pdf = digitalSurvey_name_pdf;
	}
	public FormFile getDigitalSurvey_name_pdf_File() {
		return digitalSurvey_name_pdf_File;
	}
	public void setDigitalSurvey_name_pdf_File(FormFile digitalSurvey_name_pdf_File) {
		this.digitalSurvey_name_pdf_File = digitalSurvey_name_pdf_File;
	}
	public String getDigitalSurvey_name_cdr() {
		return digitalSurvey_name_cdr;
	}
	public void setDigitalSurvey_name_cdr(String digitalSurvey_name_cdr) {
		this.digitalSurvey_name_cdr = digitalSurvey_name_cdr;
	}
	public FormFile getDigitalSurvey_name_cdr_File() {
		return digitalSurvey_name_cdr_File;
	}
	public void setDigitalSurvey_name_cdr_File(FormFile digitalSurvey_name_cdr_File) {
		this.digitalSurvey_name_cdr_File = digitalSurvey_name_cdr_File;
	}
	public String getSchemeEstimate_name() {
		return schemeEstimate_name;
	}
	public void setSchemeEstimate_name(String schemeEstimate_name) {
		this.schemeEstimate_name = schemeEstimate_name;
	}
	public FormFile getSchemeEstimateFile() {
		return schemeEstimateFile;
	}
	public void setSchemeEstimateFile(FormFile schemeEstimateFile) {
		this.schemeEstimateFile = schemeEstimateFile;
	}
	public String getAdminAproval_name() {
		return adminAproval_name;
	}
	public void setAdminAproval_name(String adminAproval_name) {
		this.adminAproval_name = adminAproval_name;
	}
	public FormFile getAdminAprovalFile() {
		return adminAprovalFile;
	}
	public void setAdminAprovalFile(FormFile adminAprovalFile) {
		this.adminAprovalFile = adminAprovalFile;
	}
	public String getStrataChart_name() {
		return strataChart_name;
	}
	public void setStrataChart_name(String strataChart_name) {
		this.strataChart_name = strataChart_name;
	}
	public FormFile getStrataChartFile() {
		return strataChartFile;
	}
	public void setStrataChartFile(FormFile strataChartFile) {
		this.strataChartFile = strataChartFile;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "SchemeUploadForm [id=" + id + ", digitalSurvey_name_pdf="
				+ digitalSurvey_name_pdf + ", digitalSurvey_name_pdf_File="
				+ digitalSurvey_name_pdf_File + ", digitalSurvey_name_cdr="
				+ digitalSurvey_name_cdr + ", digitalSurvey_name_cdr_File="
				+ digitalSurvey_name_cdr_File + ", schemeEstimate_name="
				+ schemeEstimate_name + ", schemeEstimateFile="
				+ schemeEstimateFile + ", adminAproval_name="
				+ adminAproval_name + ", adminAprovalFile=" + adminAprovalFile
				+ ", strataChart_name=" + strataChart_name
				+ ", strataChartFile=" + strataChartFile + ", scheme_id="
				+ scheme_id + ", location_id=" + location_id + ", scheme_type="
				+ scheme_type + ", user_id=" + user_id + "]";
	}
	
	

}
