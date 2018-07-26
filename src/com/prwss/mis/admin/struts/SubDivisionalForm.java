package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

public class SubDivisionalForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String subdivisionId;
	private String subdivisionName;
	private String divisionId;
	private String districtId;
	private String[] subdivisionalIds;
	
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getSubdivisionId() {
		return subdivisionId;
	}
	public void setSubdivisionId(String subdivisionId) {
		this.subdivisionId = subdivisionId;
	}
	public String getSubdivisionName() {
		return subdivisionName;
	}
	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String[] getSubdivisionalIds() {
		return subdivisionalIds;
	}
	public void setSubdivisionalIds(String[] subdivisionalIds) {
		this.subdivisionalIds = subdivisionalIds;
	}
	
	@Override
	public String toString() {
		System.out.println("subdivisionId "+subdivisionId+" subdivisionName "+subdivisionName+" divisionId "+divisionId+" districtId"+districtId);
		return super.toString();
	}
}
