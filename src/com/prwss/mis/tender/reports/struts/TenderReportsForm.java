package com.prwss.mis.tender.reports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class TenderReportsForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1217322190551491565L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectZone="A";
	private String selectCircle="A";
	private String selectDistrict="A";
	private String selectProgram="A";
	private String selectPeriod="A";
	private String zoneId;
	private String circleId;
	private String districtId;
	private String programId;
	private String asOnDate=this.currentDate;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="UA";
	private String selectReport="HRRPT001_01";
	private String jasperFile;
	public String getSelectPeriod() {
		return selectPeriod;
	}
	public void setSelectPeriod(String selectPeriod) {
		this.selectPeriod = selectPeriod;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	public String getSelectZone() {
		return selectZone;
	}
	public void setSelectZone(String selectZone) {
		this.selectZone = selectZone;
	}
	public String getSelectCircle() {
		return selectCircle;
	}
	public void setSelectCircle(String selectCircle) {
		this.selectCircle = selectCircle;
	}
	public String getSelectDistrict() {
		return selectDistrict;
	}
	public void setSelectDistrict(String selectDistrict) {
		this.selectDistrict = selectDistrict;
	}
	public String getSelectProgram() {
		return selectProgram;
	}
	public void setSelectProgram(String selectProgram) {
		this.selectProgram = selectProgram;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getCircleId() {
		return circleId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
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
	public String getSelectReport() {
		return selectReport;
	}
	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}
	
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	
	public String getJasperFile() {
		if(selectReport.equals("HRRPT001_01"))
			return "/hr/reports/HRRPT001_01.jasper";
		if(selectReport.equals("HRRPT001_02"))
			return "/hr/reports/HRRPT001_02.jasper";
		if(selectReport.equals("HRRPT001_03"))
			return "/hr/reports/HRRPT001_03.jasper";
		if(selectReport.equals("HRRPT001_04"))
			return "/hr/reports/HRRPT001_04.jasper";
		if(selectReport.equals("HRRPT001_05"))
			return "/hr/reports/HRRPT001_05.jasper";
		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("HRRPT001_01"))
			return "Salary_Slip";
		if(selectReport.equals("HRRPT001_02"))
			return "Attendance_Register_";
		if(selectReport.equals("HRRPT001_03"))
			return "Leave_Register_";
		if(selectReport.equals("HRRPT001_04"))
			return "Appraisal_";
		if(selectReport.equals("HRRPT001_05"))
			return "Training_Maintenance_";
	
	return null;
	}
}
