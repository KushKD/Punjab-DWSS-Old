package com.prwss.mis.masters.vendor.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;

@Entity
@Table(name="mst_vendor", schema="prwss_main")
public class VendorBean implements Serializable,Comparable<VendorBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3064138600990791500L;

	@Id
	@Column(name="vendor_id", nullable=false)
	private String vendorId;
	
	@Column(name="vendor_name", nullable=false)
	private String vendorName;
	
	@Column(name="vendor_type")
	private String vendorType;
	
	@Column(name="work_phone")
	private long workPhone;
	
	@Column(name="mobile_phone")
	private long mobilePhone;
	
	@Column(name="address_1")
	private String address1;
	
	@Column(name="address_2")
	private String address2;
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="pin")
	private int pin;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="vd_email")
	private String email;
	
	@Column(name="pan_no")
	private String panNo;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@Column(name="vendor_class")
	private String vendorClass;
	
	@Column(name="vendor_category")
	private String vendorCategory;
	
	@Column(name="tendering_limit",columnDefinition = "numeric default 0")
	private BigDecimal tenderingLimit;
	
	@Column(name="enlistment_authority")
	private String enlistmentAuthority;
	
	@Column(name="debarring_authority")
	private String debarringAuthority;
	
	@Column(name="recovery",columnDefinition = "numeric default 0")
	private BigDecimal recovery; 
	 
	  
	  
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





	public String getVendorType() {
		return vendorType;
	}





	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}





	public long getWorkPhone() {
		return workPhone;
	}





	public void setWorkPhone(long workPhone) {
		this.workPhone = workPhone;
	}





	public long getMobilePhone() {
		return mobilePhone;
	}





	public void setMobilePhone(long mobilePhone) {
		this.mobilePhone = mobilePhone;
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





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public int getPin() {
		return pin;
	}





	public void setPin(int pin) {
		this.pin = pin;
	}





	public String getLandmark() {
		return landmark;
	}





	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getPanNo() {
		return panNo;
	}





	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}





	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}





	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}





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
		this.recovery = recovery;
	}





	@Override
	public String toString() {
		return "VendorBean [vendorId=" + vendorId + ", vendorName="
				+ vendorName + ", vendorType=" + vendorType + ", workPhone="
				+ workPhone + ", mobilePhone=" + mobilePhone + ", address1="
				+ address1 + ", address2=" + address2 + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", pin=" + pin
				+ ", landmark=" + landmark + ", email=" + email + ", panNo="
				+ panNo + ", misAuditBean=" + misAuditBean + ", vendorClass="
				+ vendorClass + ", vendorCategory=" + vendorCategory
				+ ", tenderingLimit=" + tenderingLimit
				+ ", enlistmentAuthority=" + enlistmentAuthority
				+ ", debarringAuthority=" + debarringAuthority + ", recovery="
				+ recovery + "]";
	}





	@Override
	public int compareTo(VendorBean o) {
		return o.getVendorName().toString().compareTo(this.vendorName.toString());
		 
	}
	
	
}
