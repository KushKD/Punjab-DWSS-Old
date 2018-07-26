package com.prwss.mis.pdo.struts;

import org.apache.struts.validator.ValidatorForm;

public class PerformanceReviewReportsForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8446654655918500090L;

	private String selectZone = "A";
	private String selectCircle = "A";
	private String selectDistrict = "A";
	private String selectDivision = "A";
	private String selectsubDivision = "A";
	
	
	
	/**
	 * KD Select Division
	 */

	public String getSelectsubDivision() {
		return selectsubDivision;
	}

	public void setSelectsubDivision(String selectsubDivision) {
		this.selectsubDivision = selectsubDivision;
	}

	public String getSelectDivision() {
		return selectDivision;
	}

	public void setSelectDivision(String selectDivision) {
		this.selectDivision = selectDivision;
	}

	/**
	 * 
	 */

	private String zoneId;
	private String circleId;
	private String divisionId;
	private String district_Id;
	private String subdivisonId;

	/**
	 * KD WORK
	 */

	public String getDistrict_Id() {
		return district_Id;
	}

	public void setDistrict_Id(String district_Id) {
		this.district_Id = district_Id;
	}

	public String getSubdivisonId() {
		return subdivisonId;
	}

	public void setSubdivisonId(String subdivisonId) {
		this.subdivisonId = subdivisonId;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	/**
	 * KD WORK
	 */

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

	
	
	

}
