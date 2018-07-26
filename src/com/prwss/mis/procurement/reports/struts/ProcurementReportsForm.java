package com.prwss.mis.procurement.reports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class ProcurementReportsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643339094141227016L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectZone="A";
	private String selectCircle="A";
	private String selectDistrict="A";
	private String selectDivisionalOfficeId="A";
	private String selectProgram="A";
	private String selectPeriod="A";
	private String zoneId;
	private String circleId;
	private String districtId;
	private String divisionalOfficeId;
	private String programId;
	private String asOnDate=this.currentDate;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="UA";
	private String selectReport="PMMRPT001_1";
	private String jasperFile;
	private String tenderType;
	private String postPriorStatus="A";
	private String contract;
	private String schemeType;
	private String phase="A";
	
	/**
	 * KD WORK
	 * @return
	 */
	private String iDRB = "A";
	
	
	public String getiDRB() {
		return iDRB;
	}
	public void setiDRB(String iDRB) {
		this.iDRB = iDRB;
	}
	/**
	 * KD WORK
	 * @return
	 */
	
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
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
	public String getSelectPeriod() {
		return selectPeriod;
	}
	public void setSelectPeriod(String selectPeriod) {
		this.selectPeriod = selectPeriod;
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
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
		if(selectReport.equals("procrpt001_1"))
			return "/procurement/reports/procrpt001_1.jasper";
		if(selectReport.equals("procrpt001_2"))
			return "/procurement/reports/procrpt001_2.jasper";
		if(selectReport.equals("procrpt001_2_abs"))
			return "/procurement/reports/procrpt001_2_abs.jasper";
		if(selectReport.equals("procrpt001_4"))
			return "/procurement/reports/procrpt001_4.jasper";
		if(selectReport.equals("procrpt001_3"))
			return "/procurement/reports/procrpt001_3.jasper";
		if(selectReport.equals("procrpt001_5"))
			return "/procurement/reports/procrpt001_5.jasper";
		if(selectReport.equals("procrpt001_6"))
			return "/procurement/reports/procrpt001_6.jasper";
	
		if(selectReport.equals("procrpt001_6_dtl"))
			return "/procurement/reports/procrpt001_6_dtl.jasper";
		
		if(selectReport.equals("procrpt001_7"))
			return "/procurement/reports/procrpt001_7.jasper";
		
		if(selectReport.equals("procrpt001_8"))
			return "/procurement/reports/procrpt001_8.jasper";
		
		if(selectReport.equals("procrpt001_9"))
			return "/procurement/reports/procrpt001_9.jasper";
		if(selectReport.equals("procrpt001_10"))
			return "/procurement/reports/procrpt001_10.jasper";
		
		if(selectReport.equals("procrpt001_11"))
			return "/procurement/reports/procrpt001_11.jasper";
		if(selectReport.equals("procrpt001_12"))
			return "/procurement/reports/procrpt001_12.jasper";
		
		if(selectReport.equals("procrpt001_13"))
			return "/procurement/reports/procrpt001_13.jasper";
		
		if(selectReport.equals("procrpt001_13_old"))
			return "/procurement/reports/procrpt001_13_old.jasper";		
		
		if(selectReport.equals("procrpt001_14"))
			return "/procurement/reports/procrpt001_14.jasper";
		
		if(selectReport.equals("procrpt001_15"))
			return "/procurement/reports/procrpt001_15.jasper";
		
		if(selectReport.equals("procrpt001_16"))
			return "/procurement/reports/completion_of_contract.jasper";
		
		if(selectReport.equals("procrpt001_17"))
			return "/procurement/reports/non_responsive.jasper";
		
		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		/*if(selectReport.equals("procrpt001_1"))
			return "Procurement_Plan_Goods";
		if(selectReport.equals("procrpt001_2"))
			return "Procurement_Plan_Works";
		if(selectReport.equals("procrpt001_3"))
			return "Procurement_Plan_Consultancy";
		if(selectReport.equals("procrpt001_4"))
			return "Procurement_Plan_Non_Consultancy";
		
		if(selectReport.equals("procrpt001_5"))
			return "Procurment_RPT_05";
		
		if(selectReport.equals("procrpt001_6"))
			return "rpt_procrpt001_6";
		
		if(selectReport.equals("procrpt001_7"))
			return "procrpt001_7";
		
		if(selectReport.equals("procrpt001_8"))
			return "Committed Liability Report";
		
		if(selectReport.equals("procrpt001_9"))
			return "procrpt001_9";
		
		if(selectReport.equals("procrpt001_10"))
			return "procrpt001_10";
		
		if(selectReport.equals("PROCRPT001_11"))
			return "DSR_for_Tubewell_";
		
		if(selectReport.equals("procrpt001_12"))
			return "procrpt001_12";*/
		return selectReport;
	}
	public void setDivisionalOfficeId(String divisionalOfficeId) {
		this.divisionalOfficeId = divisionalOfficeId;
	}
	public String getDivisionalOfficeId() {
		return divisionalOfficeId;
	}
	public void setSelectDivisionalOfficeId(String selectDivisionalOfficeId) {
		this.selectDivisionalOfficeId = selectDivisionalOfficeId;
	}
	public String getSelectDivisionalOfficeId() {
		return selectDivisionalOfficeId;
	}
	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}
	public String getPostPriorStatus() {
		return postPriorStatus;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getContract() {
		return contract;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getSchemeType() {
		return schemeType;
	}
	
}
