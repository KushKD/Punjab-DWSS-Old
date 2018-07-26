package com.prwss.mis.inventory.reports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class InventoryReportsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6890943694301947727L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectZone="A";
	private String selectCircle="A";
	private String selectDistrict="A";
	private String selectProgram="A";
	private String selectPeriod="A";
	private String selectDivisionalOfficeId="A";
	private String zoneId;
	private String circleId;
	private String districtId;
	private String programId;
	private String divisionalOfficeId;
	private String asOnDate=this.currentDate;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="UA";
	private String selectReport="PMMRPT001_1";
	private String jasperFile;
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
		if(selectReport.equals("INVRPT001_1"))
			return "/inventory/reports/INVRPT001_1.jasper";
		if(selectReport.equals("INVRPT001_2"))
			return "/inventory/reports/INVRPT001_2.jasper";
		if(selectReport.equals("INVRPT001_3"))
			return "/inventory/reports/INVRPT001_3.jasper";
		if(selectReport.equals("INVRPT001_4"))
			return "/inventory/reports/INVRPT001_4.jasper";
		if(selectReport.equals("INVRPT001_5"))
			return "/inventory/reports/INVRPT001_5.jasper";
		if(selectReport.equals("INVRPT001_6"))
			return "/inventory/reports/INVRPT001_6.jasper";
		
		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		if(selectReport.equals("INVRPT001_1"))
			return "Goods_Received_Tender";
		if(selectReport.equals("INVRPT001_2"))
			return "Goods_Received_Transfer";
		if(selectReport.equals("INVRPT001_3"))
			return "Material_Issue_Note";
		if(selectReport.equals("INVRPT001_4"))
			return "Material_Transfer_Note";
		if(selectReport.equals("INVRPT001_5"))
			return "Supply_order_";
		if(selectReport.equals("INVRPT001_6"))
			return "Status_of_Available_Items_in_Store";
	return null;
	}
	public void setSelectDivisionalOfficeId(String selectDivisionalOfficeId) {
		this.selectDivisionalOfficeId = selectDivisionalOfficeId;
	}
	public String getSelectDivisionalOfficeId() {
		return selectDivisionalOfficeId;
	}
	public void setDivisionalOfficeId(String divisionalOfficeId) {
		this.divisionalOfficeId = divisionalOfficeId;
	}
	public String getDivisionalOfficeId() {
		return divisionalOfficeId;
	}
	
	

}
