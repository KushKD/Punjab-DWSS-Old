package com.prwss.mis.miscreports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class WorksProgrammeReportsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8446654655918577372L;
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
	private String selectReport="WRKRPT001_1";
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
		if(selectReport.equals("WRKRPT001_1"))
			return "/MISCReports/reports/WRKRPT001_1.jasper";
		if(selectReport.equals("WRKRPT001_2"))
			return "/MISCReports/reports/WRKRPT001_2.jasper";
		if(selectReport.equals("WRKRPT001_3"))
			return "/MISCReports/reports/WRKRPT001_3.jasper";
		if(selectReport.equals("WRKRPT001_4"))
			return "/MISCReports/reports/WRKRPT001_4.jasper";
		if(selectReport.equals("WRKRPT001_5"))
			return "/MISCReports/reports/WRKRPT001_5.jasper";
		if(selectReport.equals("WRKRPT001_6"))
			return "/MISCReports/reports/WRKRPT001_6.jasper";
		if(selectReport.equals("WRKRPT001_7"))
			return "/MISCReports/reports/WRKRPT001_7.jasper";
		if(selectReport.equals("WRKRPT001_8"))
			return "/MISCReports/reports/WRKRPT001_8.jasper";
		if(selectReport.equals("WRKRPT001_9"))
			return "/MISCReports/reports/WRKRPT001_9.jasper";
		if(selectReport.equals("WRKRPT001_10"))
			return "/MISCReports/reports/WRKRPT001_10.jasper";
		if(selectReport.equals("WRKRPT001_11"))
			return "/MISCReports/reports/WRKRPT001_11.jasper";
		if(selectReport.equals("WRKRPT001_12"))
			return "/MISCReports/reports/WRKRPT001_12.jasper";
		if(selectReport.equals("WRKRPT001_13"))
			return "/MISCReports/reports/WRKRPT001_13.jasper";
		if(selectReport.equals("WRKRPT001_14"))
			return "/MISCReports/reports/WRKRPT001_14.jasper";
		if(selectReport.equals("WRKRPT001_15"))
			return "/MISCReports/reports/WRKRPT001_15.jasper";
		if(selectReport.equals("WRKRPT001_16"))
			return "/MISCReports/reports/WRKRPT001_16.jasper";
		if(selectReport.equals("WRKRPT001_17"))
			return "/MISCReports/reports/WRKRPT001_17.jasper";
		if(selectReport.equals("WRKRPT001_18"))
			return "/MISCReports/reports/WRKRPT001_18.jasper";
		if(selectReport.equals("WRKRPT001_19"))
			return "/MISCReports/reports/WRKRPT001_19.jasper";
		if(selectReport.equals("WRKRPT001_20"))
			return "/MISCReports/reports/WRKRPT001_20.jasper";

	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("WRKRPT001_1"))
			return "Abstract_village_commissioned_";
		if(selectReport.equals("WRKRPT001_2"))
			return "Scheme_wise_detail_";
		if(selectReport.equals("WRKRPT001_3"))
			return "Abstract_of_Villages_in_Progress";
		if(selectReport.equals("WRKRPT001_4"))
			return "Schemewise_detail_of_Schemes";
		if(selectReport.equals("WRKRPT001_5"))
			return "Abstract_of_Schemes_approved";
		if(selectReport.equals("WRKRPT001_6"))
			return "Details_of_Schemes_approved";
		if(selectReport.equals("WRKRPT001_7"))
			return "Abstract_of_Schemes_whose_estimates";
		if(selectReport.equals("WRKRPT001_8"))
			return "Details_of_Schemes_whose_estimates";
		if(selectReport.equals("WRKRPT001_9"))
			return "Abstract_of_Schemes_whose_estimates";
		if(selectReport.equals("WRKRPT001_10"))
			return "Details_of_SchemeS_whose_estimates";
		if(selectReport.equals("WRKRPT001_11"))
			return "District_Wise_Abstract_of_Work_Programme";
		
		if(selectReport.equals("WRKRPT001_12"))
			return "Circle_Wise_Abstract_of_Work_Programme ";
		
		if(selectReport.equals("WRKRPT001_13"))
			return "Abstract_Wing_wise_of_Work";
		
		
		if(selectReport.equals("WRKRPT001_14"))
			return "Abstract_Work_Programme_SWAp_NonSWAp_Performa_Wise";
		
		
		
		if(selectReport.equals("WRKRPT001_15"))
			return "Abstract_Work_Programme_SWAp_NonSWAp_Circle_Wise";
		
		if(selectReport.equals("WRKRPT001_16"))
			return "Abstract_of_Schemes_where_works";
		if(selectReport.equals("WRKRPT001_17"))
			return "Detail_of_Yet_to_be_approved";
		
		if(selectReport.equals("WRKRPT001_18"))
			return "Total_schemes_Commissioned";
		
		
		if(selectReport.equals("WRKRPT001_19"))
			return "Detail_of_total_Schemes";
		
		if(selectReport.equals("WRKRPT001_20"))
			return "Abstract_of_Constituency_wise";
		
	return null;
	}
	
}
