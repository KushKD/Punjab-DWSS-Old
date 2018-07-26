package com.prwss.mis.pdo.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class PdoForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8446654655918500000L;
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
	private String selectReport="PMMRPT001_82";
	private String jasperFile;
	private String finYearId;
	private String monthId;
	private String swap="A";
	
	public String getSwap() {
		return swap;
	}
	public void setSwap(String swap) {
		this.swap = swap;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getFinYearId() {
		return finYearId;
	}
	public void setFinYearId(String finYearId) {
		this.finYearId = finYearId;
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
	public void setSelectPeriod(String selectPeriod) {
		this.selectPeriod = selectPeriod;
	}
	public String getSelectPeriod() {
		return selectPeriod;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromDate() {
		return fromDate;
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
		if(selectReport.equals("PMMRPT001_82"))
			return "/MISCReports/reports/PMMRPT001_82.jasper";
		if(selectReport.equals("PMMRPT001_81"))
			return "/MISCReports/reports/PMMRPT001_81.jasper";
		if(selectReport.equals("PMMRPT001_84"))
			return "/MISCReports/reports/PMMRPT001_84.jasper";
		if(selectReport.equals("PMMRPT001_86"))
			return "/MISCReports/reports/PMMRPT001_86.jasper";
		if(selectReport.equals("PMMRPT001_88"))
			return "/MISCReports/reports/PMMRPT001_88.jasper";
		if(selectReport.equals("PMMRPT001_90"))
			return "/MISCReports/reports/PMMRPT001_90.jasper";
		if(selectReport.equals("PMMRPT001_97"))
			return "/MISCReports/reports/PMMRPT001_97.jasper";
		if(selectReport.equals("PMMRPT001_92"))
			return "/MISCReports/reports/PMMRPT001_92.jasper";
		if(selectReport.equals("PMMRPT001_75"))
			return "/MISCReports/reports/PMMRPT001_75.jasper";
		if(selectReport.equals("PMMRPT001_76_1A"))
			return "/MISCReports/reports/PMMRPT001_76_1A.jasper";
		if(selectReport.equals("PMMRPT001_77_2A"))
			return "/MISCReports/reports/PMMRPT001_77_2A.jasper";
		if(selectReport.equals("PMMRPT001_78_2B"))
			return "/MISCReports/reports/PMMRPT001_78_2B.jasper";
		
		
		/**
		 * Deails Report to be added here
		 */
		if(selectReport.equals("PMMRPT001_79"))
			return "/MISCReports/reports/PMMRPT001_79.jasper";
		if(selectReport.equals("PMMRPT001_80"))
			return "/MISCReports/reports/PMMRPT001_80.jasper";
		if(selectReport.equals("PMMRPT001_83"))
			return "/MISCReports/reports/PMMRPT001_83.jasper";
		if(selectReport.equals("PMMRPT001_85"))
			return "/MISCReports/reports/PMMRPT001_85.jasper";
		if(selectReport.equals("PMMRPT001_87"))
			return "/MISCReports/reports/PMMRPT001_87.jasper";
		if(selectReport.equals("PMMRPT001_89"))
			return "/MISCReports/reports/PMMRPT001_89.jasper";
		if(selectReport.equals("PMMRPT001_96"))
			return "/MISCReports/reports/PMMRPT001_96.jasper";
		if(selectReport.equals("PMMRPT001_91"))
			return "/MISCReports/reports/PMMRPT001_91.jasper";
		
		
		
		

	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("PMMRPT001_82"))
			return "PMMRPT001_82";
		if(selectReport.equals("PMMRPT001_81"))
			return "PMMRPT001_81";
		if(selectReport.equals("PMMRPT001_84"))
			return "PMMRPT001_84";
		if(selectReport.equals("PMMRPT001_86"))
			return "PMMRPT001_86";
		if(selectReport.equals("PMMRPT001_88"))
			return "PMMRPT001_88";
		if(selectReport.equals("PMMRPT001_90"))
			return "PMMRPT001_90";
		if(selectReport.equals("PMMRPT001_97"))
			return "PMMRPT001_97";
		if(selectReport.equals("PMMRPT001_92"))
			return "PMMRPT001_92";
		if(selectReport.equals("PMMRPT001_75"))
			return "PMMRPT001_75";
		if(selectReport.equals("PMMRPT001_76_1A"))
			return "PMMRPT001_76_1A";
		if(selectReport.equals("PMMRPT001_77_2A"))
			return "PMMRPT001_77_2A";
		if(selectReport.equals("PMMRPT001_78_2B"))
			return "PMMRPT001_78_2B";
		
		
		if(selectReport.equals("PMMRPT001_79"))
			return "PMMRPT001_79";
		if(selectReport.equals("PMMRPT001_80"))
			return "PMMRPT001_80";
		if(selectReport.equals("PMMRPT001_83"))
			return "PMMRPT001_83";
		if(selectReport.equals("PMMRPT001_85"))
			return "PMMRPT001_85";
		if(selectReport.equals("PMMRPT001_87"))
			return "PMMRPT001_87";
		if(selectReport.equals("PMMRPT001_89"))
			return "PMMRPT001_89";
		if(selectReport.equals("PMMRPT001_96"))
			return "PMMRPT001_96";
		if(selectReport.equals("PMMRPT001_91"))
			return "PMMRPT001_91";
		
		
	return null;
	}
	
}