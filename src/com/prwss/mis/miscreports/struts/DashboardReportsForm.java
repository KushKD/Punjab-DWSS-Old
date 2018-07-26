package com.prwss.mis.miscreports.struts;

import org.apache.struts.validator.ValidatorForm;


public class DashboardReportsForm extends ValidatorForm {
	
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
		if(selectReport.equals("DBRPT001"))
			fileName="/MISCReports/reports/DBRPT001.jasper";
		if(selectReport.equals("DBRPT002"))
			fileName="/MISCReports/reports/DBRPT002.jasper";
		if(selectReport.equals("DBRPT003"))
			fileName="/MISCReports/reports/DBRPT003.jasper";
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
		if(selectReport.equals("DBRPT001"))
			reportTitle= "Habitations Survey-Freezed-April,2008";
		if(selectReport.equals("DBRPT002"))
			reportTitle= "Program to be Implemented in Villages/ Main Habitations (MH)";
		if(selectReport.equals("DBRPT003"))
			reportTitle= "Program to be Implemented in Other Habitations (OH)";
		return reportTitle;
	}
	
}
