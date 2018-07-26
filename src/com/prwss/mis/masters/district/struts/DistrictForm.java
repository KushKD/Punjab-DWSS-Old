package com.prwss.mis.masters.district.struts;

import org.apache.struts.validator.ValidatorForm;

public class DistrictForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4074929071627857609L;

	private String districtCode;
	private String districtName;
	private String officeName;
	private String circleCode;
	private String zoneId;
	
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	private boolean isSpmc;
	private String address1;
	private String address2;
	private String street;
	private String city;
	private long pinCode;
	private String email;
	private String[] districtCodes;
	
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getCircleCode() {
		return circleCode;
	}
	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}
	public boolean getIsSpmc() {
		return isSpmc;
	}
	public void setIsSpmc(boolean isSpmc) {
		this.isSpmc = isSpmc;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPinCode() {
		return pinCode;
	}
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getDistrictCodes() {
		return districtCodes;
	}
	public void setDistrictCodes(String[] districtCodes) {
		this.districtCodes = districtCodes;
	}
	
	
}
