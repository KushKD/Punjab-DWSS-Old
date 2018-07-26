package com.prwss.mis.daktask.reports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class DakTaskReportForm extends ValidatorForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1750277907127510079L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectPeriod="A";
	private String zoneId;
	private String locationId;
	private String circleId;
	private String districtId;
	private String programId;
	private String asOnDate=this.currentDate;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="UA";
	private String selectReport="DTMRPT001_1";
	private String jasperFile;
	
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getSelectPeriod() {
		return selectPeriod;
	}
	public void setSelectPeriod(String selectPeriod) {
		this.selectPeriod = selectPeriod;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}
	public String getSelectReport() {
		return selectReport;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getCircleId() {
		return circleId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	public String getJasperFile() {
		if(selectReport.equals("DTMRPT001_1"))
			return "/Dak_Task/reports/DTMRPT001_1.jasper";
		if(selectReport.equals("DTMRPT001_2"))
			return "/Dak_Task/reports/DTMRPT001_2.jasper";
		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("DTMRPT001_1"))
			return "Inward_Dak_";
		if(selectReport.equals("DTMRPT001_2"))
			return "Outward_Dak_";
		
	return null;
	}
	@Override
	public String toString() {
		return "DakTaskReportForm [currentDate=" + currentDate + ", fileTitle="
				+ fileTitle + ", selectPeriod=" + selectPeriod + ", zoneId="
				+ zoneId + ", locationId=" + locationId + ", circleId="
				+ circleId + ", districtId=" + districtId + ", programId="
				+ programId + ", asOnDate=" + asOnDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", approvalStatus="
				+ approvalStatus + ", selectReport=" + selectReport
				+ ", jasperFile=" + jasperFile + "]";
	}
		
}


