package com.prwss.mis.ccdu.reports.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class CcduReportsForm extends ValidatorForm {
	private static final long serialVersionUID = 8095551153236274729L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectZone="A";
	private String selectCircle="A";
	private String selectDistrict="A";
	private String selectDivisionalOfficeId="A";
	private String zoneId;
	private String circleId;
	private String districtId;
	private String toDate=this.currentDate;
	private String selectReport="CCDURPT001_1";
	private String jasperFile;
	
	private String divisionalOfficeId;
	private long planId;
	private String finYearId;
	private String finYear;
	private String monthId;
	private String selectPeriod = "A";
	private String fromDate=this.currentDate;
	private String month;
	private String year;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
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
		System.out.println("Hello");
		this.fromDate = fromDate;
	}
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
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
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	public String getJasperFile() {
		if(selectReport.equals("CCDURPT001_1"))
			return "/ccdu/reports/CCDURPT001_1.jasper";
		if(selectReport.equals("CCDURPT001_2"))
			return "/ccdu/reports/CCDURPT001_2.jasper";
		if(selectReport.equals("CCDURPT001_3"))
			return "/ccdu/reports/CCDURPT001_3.jasper";
		if(selectReport.equals("CCDURPT001_4"))
			return "/ccdu/reports/CCDURPT001_4.jasper";
		if(selectReport.equals("CCDURPT001_5"))
			return "/ccdu/reports/CCDURPT001_5.jasper";
		if(selectReport.equals("CCDURPT001_6"))
			return "/ccdu/reports/CCDURPT001_6.jasper";
		if(selectReport.equals("CCDURPT001_7"))
			return "/ccdu/reports/CCDURPT001_7.jasper";	
		if(selectReport.equals("CCDURPT001_8"))
			return "/ccdu/reports/CCDURPT001_8.jasper";	
		if(selectReport.equals("CCDURPT001_9"))
			return "/ccdu/reports/CCDURPT001_9.jasper";	
		if(selectReport.equals("CCDURPT001_1_new"))
			return "/ccdu/reports/CCDURPT001_1_new.jasper";		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		return selectReport;
	}
	@Override
	public String toString() {
		return "EstimatesAwardContractsReportForm [selectZone="+ selectZone + ", selectCircle=" + selectCircle
				+ ", zoneId=" + zoneId + ", circleId="+circleId+", districtId="+districtId+", jasperFile="+jasperFile
				+", selectReport=" + selectReport + ", selectDistrict=" + selectDistrict + "]";
	}
	public void setFinYearId(String finYearId) {
		System.out.println("-------------Check------"+finYearId);
		this.finYearId = finYearId;
	}
	public String getFinYearId() {
		return finYearId;
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
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getMonthId() {
		return monthId;
	}			

}
