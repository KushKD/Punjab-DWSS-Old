package com.prwss.mis.miscreports.struts;

import org.apache.struts.validator.ValidatorForm;


public class AdhocReportsForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5652961277689965332L;
	private String selectReport="AGDRPT001_1";
	private String select_fields[];
	private String select_fields_label[];
	private String criteria;
	private String fileName;
	private String reportTitle;
	private String cField1;
	private String cField2;
	private String cField3;
	private String cField4;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String cValue1;
	private String cValue2;
	private String cValue3;
	private String cValue4;
	private String rad1="AND";
	private String rad2="AND";
	private String rad3="AND";
	private String rad4="AND";
	
	
	
	
	
	public String getRad1() {
		return rad1;
	}
	public void setRad1(String rad1) {
		this.rad1 = rad1;
	}
	public String getRad2() {
		return rad2;
	}
	public void setRad2(String rad2) {
		this.rad2 = rad2;
	}
	public String getRad3() {
		return rad3;
	}
	public void setRad3(String rad3) {
		this.rad3 = rad3;
	}
	public String getRad4() {
		return rad4;
	}
	public void setRad4(String rad4) {
		this.rad4 = rad4;
	}
	public String getcField1() {
		return cField1;
	}
	public void setcField1(String cField1) {
		this.cField1 = cField1;
	}
	public String getcField2() {
		return cField2;
	}
	public void setcField2(String cField2) {
		this.cField2 = cField2;
	}
	public String getcField3() {
		return cField3;
	}
	public void setcField3(String cField3) {
		this.cField3 = cField3;
	}
	public String getcField4() {
		return cField4;
	}
	public void setcField4(String cField4) {
		this.cField4 = cField4;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public String getcValue1() {
		return cValue1;
	}
	public void setcValue1(String cValue1) {
		this.cValue1 = cValue1;
	}
	public String getcValue2() {
		return cValue2;
	}
	public void setcValue2(String cValue2) {
		this.cValue2 = cValue2;
	}
	public String getcValue3() {
		return cValue3;
	}
	public void setcValue3(String cValue3) {
		this.cValue3 = cValue3;
	}
	public String getcValue4() {
		return cValue4;
	}
	public void setcValue4(String cValue4) {
		this.cValue4 = cValue4;
	}
	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}
	public String getSelectReport() {
		return selectReport;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		if(selectReport.equals("vw_adhoc_scheme_village_details_14_sep_2011"))
			fileName="HC";		
		if(selectReport.equals("vw_master_info"))
			fileName="MI";	
		if(selectReport.equals("vw_mst_village_info_new"))
			fileName="VMI";
		
	return fileName;
	}
	public void setSelect_fields(String select_fields[]) {
		this.select_fields = select_fields;
	}
	public String[] getSelect_fields() {
		return select_fields;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setSelect_fields_label(String select_fields_label[]) {
		this.select_fields_label = select_fields_label;
	}
	public String[] getSelect_fields_label() {
		return select_fields_label;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getReportTitle() {
		if(selectReport.equals("vw_adhoc_scheme_village_details_14_sep_2011"))
			reportTitle= "Habitations Commissioning";
		
		if(selectReport.equals("vw_master_info"))
			reportTitle= "Master Info";
		
		
		if(selectReport.equals("vw_mst_village_info_new"))
			reportTitle= "Village Master Info";
		return reportTitle;
	}
	
}
