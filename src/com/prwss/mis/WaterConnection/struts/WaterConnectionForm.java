package com.prwss.mis.WaterConnection.struts;

import javax.persistence.Column;

import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.servlet.ValidateActionForm;

public class WaterConnectionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1684359113054094146L;
	private String districtId;

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getUrban() {
		return urban;
	}

	public void setUrban(String urban) {
		this.urban = urban;
	}

	public String getRural() {
		return rural;
	}

	public void setRural(String rural) {
		this.rural = rural;
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

	public String getDistrictIdurban() {
		return districtIdurban;
	}

	public void setDistrictIdurban(String districtIdurban) {
		this.districtIdurban = districtIdurban;
	}

	public String getWardno() {
		return wardno;
	}

	public void setWardno(String wardno) {
		this.wardno = wardno;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFh_name() {
		return fh_name;
	}

	public void setFh_name(String fh_name) {
		this.fh_name = fh_name;
	}

	public String getPoiType() {
		return poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public String getPoi_number() {
		return poi_number;
	}

	public void setPoi_number(String poi_number) {
		this.poi_number = poi_number;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	private String urban;
	private String rural;
	private String blockId;
	private String villageId;

	private String districtIdurban;
	private String wardno;

	private String firstname;
	private String lastname;
	private String fh_name;
	private String poiType;
	private String poi_number;

	private String mobile;
	private String aadhaarNumber;
	private String pincode;

	private String category;

	private String decTwo;
	private String decOne;

	private String remarks_Dept;
	private String sanctioned_Number;

	private String otp;
	
	private String proofOfIdentity;
	private String poiNumber;
	
	
	

	public String getProofOfIdentity() {
		return proofOfIdentity;
	}

	public void setProofOfIdentity(String proofOfIdentity) {
		this.proofOfIdentity = proofOfIdentity;
	}

	public String getPoiNumber() {
		return poiNumber;
	}

	public void setPoiNumber(String poiNumber) {
		this.poiNumber = poiNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getRemarks_Dept() {
		return remarks_Dept;
	}

	public void setRemarks_Dept(String remarks_Dept) {
		this.remarks_Dept = remarks_Dept;
	}

	public String getSanctioned_Number() {
		return sanctioned_Number;
	}

	public void setSanctioned_Number(String sanctioned_Number) {
		this.sanctioned_Number = sanctioned_Number;
	}

	public String getDecTwo() {
		return decTwo;
	}

	public void setDecTwo(String decTwo) {
		this.decTwo = decTwo;
	}

	public String getDecOne() {
		return decOne;
	}

	public void setDecOne(String decOne) {
		this.decOne = decOne;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String address;

	@Override
	public String toString() {
		return "WaterConnectionForm [districtId=" + districtId + ", urban="
				+ urban + ", rural=" + rural + ", blockId=" + blockId
				+ ", villageId=" + villageId + ", districtIdurban="
				+ districtIdurban + ", wardno=" + wardno + ", firstname="
				+ firstname + ", lastname=" + lastname + ", fh_name=" + fh_name
				+ ", poiType=" + poiType + ", poi_number=" + poi_number
				+ ", mobile=" + mobile + ", aadhaarNumber=" + aadhaarNumber
				+ ", pincode=" + pincode + ", category=" + category
				+ ", decTwo=" + decTwo + ", decOne=" + decOne
				+ ", remarks_Dept=" + remarks_Dept + ", sanctioned_Number="
				+ sanctioned_Number + ", otp=" + otp + ", proofOfIdentity="
				+ proofOfIdentity + ", poiNumber=" + poiNumber + ", address="
				+ address + "]";
	}

	

}
