package com.prwss.mis.miscreports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class AgendaReportsForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5652961277689965332L;
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
	private String selectReport="AGDRPT001_1";
	private String jasperFile;
	private String monthId;
	private String finYearId;
	private String swap;
	private String toDate1;
	
	
	
	
	
	public String getToDate1() {
		return toDate1;
	}
	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}
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
		if(selectReport.equals("AGDRPT001_1"))
			return "/MISCReports/reports/AG0003.jasper";
		if(selectReport.equals("AGDRPT001_2"))
			return "/MISCReports/reports/AGDRPT001_2.jasper";
		if(selectReport.equals("AGDRPT001_3"))
			return "/MISCReports/reports/AGDRPT001_3.jasper";
		if(selectReport.equals("AGDRPT001_4"))
			return "/MISCReports/reports/AGDRPT001_4.jasper";
		if(selectReport.equals("AGDRPT001_5"))
			return "/MISCReports/reports/AGDRPT001_5.jasper";
		if(selectReport.equals("AGDRPT001_6"))
			return "/MISCReports/reports/AGDRPT001_6.jasper";
		if(selectReport.equals("AGDRPT001_7"))
			return "/MISCReports/reports/AGDRPT001_7.jasper";
		if(selectReport.equals("AGDRPT001_8"))
			return "/MISCReports/reports/AG0002.jasper";
		if(selectReport.equals("AGDRPT001_9"))
			return "/MISCReports/reports/AG0004.jasper";
		if(selectReport.equals("AGDRPT001_10"))
			return "/MISCReports/reports/AGDRPT001_8.jasper";
		if(selectReport.equals("AGDRPT001_11"))
			return "/MISCReports/reports/AG0001.jasper";
		if(selectReport.equals("AGDRPT001_12"))
			return "/MISCReports/reports/AGDRPT001_12.jasper";
		if(selectReport.equals("AGDRPT001_13"))
			return "/MISCReports/reports/AGDRPT001_13.jasper";
		if(selectReport.equals("AGDRPT001_13A"))
			return "/MISCReports/reports/AGDRPT001_13A.jasper";
		
		if(selectReport.equals("AGDRPT001_14"))
			return "/MISCReports/reports/AGDRPT001_14.jasper";
		if(selectReport.equals("AGDRPT001_15"))
			return "/MISCReports/reports/AGDRPT001_15.jasper";
		/*if(selectReport.equals("AGDRPT001_16"))
			return "/MISCReports/reports/AGDRPT001_16.jasper";*/
//		if(selectReport.equals("PMMRPT001_17"))
//			return "/MISCReports/reports/PMMRPT001_17.jasper";
//		if(selectReport.equals("PMMRPT001_18"))
//			return "/MISCReports/reports/PMMRPT001_18.jasper";
//		if(selectReport.equals("PMMRPT001_19"))
//			return "/MISCReports/reports/PMMRPT001_19.jasper";
//		if(selectReport.equals("PMMRPT001_20"))
//			return "/MISCReports/reports/PMMRPT001_20.jasper";
//		if(selectReport.equals("PMMRPT001_21"))
//			return "/MISCReports/reports/PMMRPT001_21.jasper";
//		if(selectReport.equals("PMMRPT001_22"))
//			return "/MISCReports/reports/PMMRPT001_22.jasper";
//		if(selectReport.equals("PMMRPT001_23"))
//			return "/MISCReports/reports/PMMRPT001_23.jasper";
//		
//		if(selectReport.equals("MH_3161"))
//			return "/MISCReports/reports/MH_3161.jasper";
//		
//		if(selectReport.equals("status_MH"))
//			return "/MISCReports/reports/status_MH.jasper";
//		
//		if(selectReport.equals("status_OH"))
//			return "/MISCReports/reports/status_OH.jasper";
//		
//		if(selectReport.equals("commissioning_IDA_RPT"))
//			return "/MISCReports/reports/commissioning_IDA_RPT.jasper";
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("AGDRPT001_1"))
			return "AgendaReport_01";
		if(selectReport.equals("AGDRPT001_2"))
			return "AgendaReport_02";
		if(selectReport.equals("AGDRPT001_3"))
			return "AgendaReport_03";
		if(selectReport.equals("AGDRPT001_4"))
			return "AgendaReport_04";
		if(selectReport.equals("AGDRPT001_5"))
			return "AgendaReport_05";
		if(selectReport.equals("AGDRPT001_6"))
			return "AgendaReport_06";
		if(selectReport.equals("AGDRPT001_7"))
			return "AgendaReport_07";
		if(selectReport.equals("AGDRPT001_8"))
			return "AgendaReport_08";
		if(selectReport.equals("AGDRPT001_9"))
			return "AgendaReport_09";
		if(selectReport.equals("AGDRPT001_10"))
			return "AgendaReport_10";
		if(selectReport.equals("AGDRPT001_11"))
			return "AgendaReport_11";
		if(selectReport.equals("AGDRPT001_12"))
			return "AgendaReport_12";
		if(selectReport.equals("AGDRPT001_13"))
			return "AgendaReport_13";
		if(selectReport.equals("AGDRPT001_14"))
			return "AgendaReport_14";
		if(selectReport.equals("AGDRPT001_15"))
			return "AgendaReport_15";
		if(selectReport.equals("AGDRPT001_13A"))
			return "AgendaReport_13A";
//		if(selectReport.equals("PMMRPT001_17"))
//			return "Operational_Sustainability";
//		if(selectReport.equals("PMMRPT001_18"))
//			return "State Habitation Abstract";
//		if(selectReport.equals("PMMRPT001_19"))
//			return "State Habitation Abstract WB";
//		if(selectReport.equals("PMMRPT001_20"))
//			return "Scheme-wise details for commissioned villages";
//		if(selectReport.equals("PMMRPT001_21"))
//			return "Abstract of commissioned villages";
//		if(selectReport.equals("PMMRPT001_22"))
//			return "Scheme-wise details for commissioned villages (1-B)";
//		if(selectReport.equals("PMMRPT001_23"))
//			return "Abstract of commissioned villages (1-A)";
//		
//		if(selectReport.equals("MH_3161"))
//			return "MH-3161 Commissioning Report";
//		
//		if(selectReport.equals("status_MH"))
//			return "MH- Commissioning Report";
//		
//		if(selectReport.equals("status_OH"))
//			return "OH- Commissioning Report";
//		
//		if(selectReport.equals("commissioning_IDA_RPT"))
//			return "Village Commissioned under IDA";
		
	return null;
	}

}
