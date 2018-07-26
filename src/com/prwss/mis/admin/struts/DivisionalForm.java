package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

public class DivisionalForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	//Primitive Data Types
	private String divisionalName;
	private String districtId;
	private String[] divisionalIds;
	private String divisionalId;

	
	
	public String getDivisionalId() {
		return divisionalId;
	}
	public void setDivisionalId(String divisionalId) {
		this.divisionalId = divisionalId;
	}
	public String getDivisionalName() {
		return divisionalName;
	}
	public void setDivisionalName(String divisionalName) {
		this.divisionalName = divisionalName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String[] getDivisionalIds() {
		return divisionalIds;
	}
	public void setDivisionalIds(String[] divisionalIds) {
		this.divisionalIds = divisionalIds;
	}
	
	

	
	
}
