package com.prwss.mis.finance.report.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class FinanceReportsForm extends ValidatorForm {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8095551153236274729L;
		private String currentDate= MisUtility.now("dd-MM-yyyy");
		private String fileTitle;
		private String selectZone="A";
		private String selectCircle="A";
		private String selectDistrict="A";
		private String selectProgram="A";
		private String selectPeriod="A";
		private String selectActivity="A";
		private String zoneId;
		private String circleId;
		private String districtId;
		private String locationId;
		private String blockId;
		private String villageId;
		private String gpwscId;
		private String divisionId;
		private String divisionId3;
		private String schemeId;		
	
		private String programId;
		private String asOnDate=this.currentDate;
		private String fromDate=this.currentDate;
		private String toDate=this.currentDate;
		private String approvalStatus="UA";
		private String selectReport="FINRPT001_1";
		private String jasperFile;
		private String monthId;
		private String finYearId;
		private String qtr;
		private String componentId;
		private String subComponentId;
		private String activityId;
		
		public String getLocationId() {
			return locationId;
		}
		public void setLocationId(String locationId) {
			this.locationId = locationId;
		}
		public String getBlockId() {
			return blockId;
		}
		public void setBlockId(String blockId) {
			this.blockId = blockId;
		}
		public String getVillageId() {
			return villageId;
		}
		public void setVillageId(String villageId) {
			this.villageId = villageId;
		}
		public String getGpwscId() {
			return gpwscId;
		}
		public void setGpwscId(String gpwscId) {
			this.gpwscId = gpwscId;
		}
	
		
		
		public String getComponentId() {
			return componentId;
		}
		public void setComponentId(String componentId) {
			this.componentId = componentId;
		}
		public String getSubComponentId() {
			return subComponentId;
		}
		public void setSubComponentId(String subComponentId) {
			this.subComponentId = subComponentId;
		}
		public String getActivityId() {
			return activityId;
		}
		public void setActivityId(String activityId) {
			this.activityId = activityId;
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
		public void setQtr(String qtr) {
			this.qtr = qtr;
		}
		public String getQtr() {
			return qtr;
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
			if(selectReport.equals("FINRPT001_1"))
				return "/finance/reports/FINRPT001_1.jasper";
			if(selectReport.equals("FINRPT001_2"))
				return "/finance/reports/FINRPT001_2.jasper";
			if(selectReport.equals("FINRPT001_3"))
				return "/finance/reports/FINRPT001_3.jasper";
			if(selectReport.equals("FINRPT001_4"))
				return "/finance/reports/FINRPT001_4.jasper";
			if(selectReport.equals("FINRPT001_5"))
				return "/finance/reports/FINRPT001_5.jasper";
			if(selectReport.equals("FINRPT001_6"))
				return "/finance/reports/FINRPT001_6.jasper";
			if(selectReport.equals("FINRPT001_7"))
				return "/finance/reports/FINRPT001_7.jasper";
			if(selectReport.equals("FINRPT001_8"))
				return "/finance/reports/FINRPT001_8.jasper";
			if(selectReport.equals("FINRPT001_9"))
				return "/finance/reports/FINRPT001_9.jasper";
			if(selectReport.equals("FINRPT001_10"))
				return "/finance/reports/FINRPT001_10.jasper";
			if(selectReport.equals("FINRPT001_11"))
				return "/finance/reports/FINRPT001_11.jasper";
			if(selectReport.equals("FINRPT001_12"))
				return "/finance/reports/FINRPT001_12.jasper";
			if(selectReport.equals("FINRPT001_12_1"))
				return "/finance/reports/FINRPT001_12_1.jasper";
			if(selectReport.equals("FINRPT001_13"))
				return "/finance/reports/FINRPT001_13.jasper";
			if(selectReport.equals("FINRPT001_14"))
				return "/finance/reports/FINRPT001_14.jasper";
			if(selectReport.equals("FINRPT001_15"))
				return "/finance/reports/FINRPT001_15.jasper";
			if(selectReport.equals("FINRPT001_16"))
				return "/finance/reports/FINRPT001_16.jasper";	
			if(selectReport.equals("FINRPT001_17"))
				return "/finance/reports/FINRPT001_17.jasper";	
			if(selectReport.equals("FINRPT001_18"))
				return "/finance/reports/FINRPT001_18.jasper";	
			if(selectReport.equals("FINRPT001_19"))
				return "/finance/reports/FINRPT001_19.jasper";	
			if(selectReport.equals("FINRPT001_20"))
				return "/finance/reports/FINRPT001_20.jasper";
			if(selectReport.equals("FINRPT001_21"))
				return "/finance/reports/FINRPT001_21.jasper";
			if(selectReport.equals("FINRPT001_24"))
				return "/finance/reports/FINRPT001_24.jasper";
			if(selectReport.equals("FINRPT001_25"))
				return "/finance/reports/FINRPT001_25.jasper";
			if(selectReport.equals("FINRPT001_26"))
				return "/finance/reports/FINRPT001_26.jasper";
		return null;
		}
		public void setFileTitle(String fileTitle) {
			this.fileTitle = fileTitle;
		}
		public String getFileTitle() {
			if(selectReport.equals("FINRPT001_1"))
				return "FINRPT001_1_";
			if(selectReport.equals("FINRPT001_2"))
				return "FINRPT001_2_";
			if(selectReport.equals("FINRPT001_3"))
				return "FINRPT001_3_";
			if(selectReport.equals("FINRPT001_4"))
				return "FINRPT001_4_";
			if(selectReport.equals("FINRPT001_5"))
				return "FINRPT001_5_";
			if(selectReport.equals("FINRPT001_6"))
				return "FINRPT001_6_";
			if(selectReport.equals("FINRPT001_7"))
				return "FINRPT001_7_";
			if(selectReport.equals("FINRPT001_8"))
				return "FINRPT001_8_";
			if(selectReport.equals("FINRPT001_9"))
				return "FINRPT001_9_";
			if(selectReport.equals("FINRPT001_10"))
				return "FINRPT001_10_";
			if(selectReport.equals("FINRPT001_11"))
				return "FINRPT001_11_";
			if(selectReport.equals("FINRPT001_12"))
				return "FINRPT001_12_";
			if(selectReport.equals("FINRPT001_12_1"))
				return "FINRPT001_12_1_";
			if(selectReport.equals("FINRPT001_13"))
				return "FINRPT001_13_";
			if(selectReport.equals("FINRPT001_14"))
				return "FINRPT001_14_";
			if(selectReport.equals("FINRPT001_15"))
				return "FINRPT001_15_";
			if(selectReport.equals("FINRPT001_16"))
				return "FINRPT001_16_";
			if(selectReport.equals("FINRPT001_17"))
				return "FINRPT001_17_";
			if(selectReport.equals("FINRPT001_18"))
				return "FINRPT001_18_";
			if(selectReport.equals("FINRPT001_19"))
				return "FINRPT001_19_";
			if(selectReport.equals("FINRPT001_20"))
				return "FINRPT001_20_";
			if(selectReport.equals("FINRPT001_21"))
				return "FINRPT001_21_";
			if(selectReport.equals("FINRPT001_24"))
				return "FINRPT001_24_";
			if(selectReport.equals("FINRPT001_25"))
				return "FINRPT001_25_";
			if(selectReport.equals("FINRPT001_26"))
				return "FINRPT001_26_";
			return null;
		}
		@Override
		public String toString() {
			return "FinanceReportsForm [selectZone="+ selectZone + ", selectCircle=" + selectCircle
					+ ", zoneId=" + zoneId + ", circleId="+circleId+", districtId="+districtId+", programId="+programId
					+ ", jasperFile="+jasperFile
					+", selectReport=" + selectReport + ", selectDistrict=" + selectDistrict + ", selectProgram="
					+ selectProgram + ", asOnDate=" + asOnDate + ", approvalStatus=" + approvalStatus + "]";
		}
		public void setSelectActivity(String selectActivity) {
			this.selectActivity = selectActivity;
		}
		public String getSelectActivity() {
			return selectActivity;
		}
		public void setDivisionId(String divisionId) {
			this.divisionId = divisionId;
		}
		public String getDivisionId() {
			return divisionId;
		}
		public void setDivisionId3(String divisionId3) {
			this.divisionId3 = divisionId3;
		}
		public String getDivisionId3() {
			return divisionId3;
		}
		public void setSchemeId(String schemeId) {
			this.schemeId = schemeId;
		}
		public String getSchemeId() {
			return schemeId;
		}
}

