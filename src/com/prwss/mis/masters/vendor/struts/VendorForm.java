package com.prwss.mis.masters.vendor.struts;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.struts.validator.ValidatorForm;

public class VendorForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2081184303847801007L;

	private String vendorId;
	private String vendorName;
	private String vendorClassification;
	private String state;
	private String panNo;
	private String address1;
	private String address2;
	private String street;
	private String city;
	private Long workPhoneNo;
	private Long mobPhoneNo;
	private int pinCode;
	private String email;
	private String[] vendorIds;
	private String vendorClass;
	private String vendorCategory;
	private BigDecimal tenderingLimit;
	private String enlistmentAuthority;
	private String debarringAuthority;
	private BigDecimal recovery;	 
	 
	
	public String getVendorClass() {
		return vendorClass;
	}
	public void setVendorClass(String vendorClass) {
		this.vendorClass = vendorClass;
	}
	public String getVendorCategory() {
		return vendorCategory;
	}
	public void setVendorCategory(String vendorCategory) {
		this.vendorCategory = vendorCategory;
	}
	public BigDecimal getTenderingLimit() {
		return tenderingLimit;
	}
	public void setTenderingLimit(BigDecimal tenderingLimit) {
		this.tenderingLimit = tenderingLimit;
	}
	public String getEnlistmentAuthority() {
		return enlistmentAuthority;
	}
	public void setEnlistmentAuthority(String enlistmentAuthority) {
		this.enlistmentAuthority = enlistmentAuthority;
	}
	public String getDebarringAuthority() {
		return debarringAuthority;
	}
	public void setDebarringAuthority(String debarringAuthority) {
		this.debarringAuthority = debarringAuthority;
	}
	
	public BigDecimal getRecovery() {
		return recovery;
	}
	public void setRecovery(BigDecimal recovery) {
//		if(!MisUtility.ifEmpty(recovery))
//		this.recovery = new  BigDecimal(0.00);	
		this.recovery = recovery;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorClassification() {
		return vendorClassification;
	}
	public void setVendorClassification(String vendorClassification) {
		this.vendorClassification = vendorClassification;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
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
	public Long getWorkPhoneNo() {
		return workPhoneNo;
	}
	public void setWorkPhoneNo(Long workPhoneNo) {
		this.workPhoneNo = workPhoneNo;
	}
	public Long getMobPhoneNo() {
		return mobPhoneNo;
	}
	public void setMobPhoneNo(Long mobPhoneNo) {
		this.mobPhoneNo = mobPhoneNo;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getVendorIds() {
		return vendorIds;
	}
	public void setVendorIds(String[] vendorIds) {
		this.vendorIds = vendorIds;
	}
	@Override
	public String toString() {
		return "VendorForm [vendorId=" + vendorId + ", vendorName="
				+ vendorName + ", vendorClassification=" + vendorClassification
				+ ", state=" + state + ", panNo=" + panNo + ", address1="
				+ address1 + ", address2=" + address2 + ", street=" + street
				+ ", city=" + city + ", workPhoneNo=" + workPhoneNo
				+ ", mobPhoneNo=" + mobPhoneNo + ", pinCode=" + pinCode
				+ ", email=" + email + ", vendorIds="
				+ Arrays.toString(vendorIds) + ", vendorClass=" + vendorClass
				+ ", vendorCategory=" + vendorCategory + ", tenderingLimit="
				+ tenderingLimit + ", enlistmentAuthority="
				+ enlistmentAuthority + ", debarringAuthority="
				+ debarringAuthority + ", recovery=" + recovery + "]";
	}
	
	
	
	
}
	
	
