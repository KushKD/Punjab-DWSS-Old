package com.prwss.mis.masters.district.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.masters.circle.dao.CircleBean;

@Entity
@Table(name="mst_district", schema="prwss_main")
public class DistrictBean implements Serializable,Comparable<DistrictBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4472378018819238415L;

	@Id
	@Column(name="district_id", nullable=false)
	private String districtId;
	
	@Column(name="district_name", nullable=false)
	private String districtName;
	
	@ManyToOne(targetEntity=CircleBean.class)
	@JoinColumn(name="circle_id", nullable=false)
	private CircleBean circle;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pin")
	private long pin;
	
	@Column(name="is_spmc_dpmc")
	private String isSPMC_DPMC;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private String status;
	
	@Column(name="ent_by")
	private long enteredBy;
	
	@Column(name="ent_date")
	private Timestamp enteredDate;
	
	@Column(name="auth_by")
	private long authorizedBy;
	
	@Column(name="auth_date")
	private Timestamp authorizedDate;
	
	@Column(name="freeze_by")
	private long freezedBy;
	
	@Column(name="freeze_date")
	private Timestamp freezedDate;

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public CircleBean getCircle() {
		return circle;
	}

	public void setCircle(CircleBean circle) {
		this.circle = circle;
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

	public long getPin() {
		return pin;
	}

	public void setPin(long pin) {
		this.pin = pin;
	}

	public String getIsSPMC_DPMC() {
		return isSPMC_DPMC;
	}

	public void setIsSPMC_DPMC(String isSPMC_DPMC) {
		this.isSPMC_DPMC = isSPMC_DPMC;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Timestamp getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Timestamp enteredDate) {
		this.enteredDate = enteredDate;
	}

	public long getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(long authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Timestamp getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Timestamp authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	
	public long getFreezedBy() {
		return freezedBy;
	}

	public void setFreezedBy(long freezedBy) {
		this.freezedBy = freezedBy;
	}

	public Timestamp getFreezedDate() {
		return freezedDate;
	}

	public void setFreezedDate(Timestamp freezedDate) {
		this.freezedDate = freezedDate;
	}

	@Override
	public String toString() {
		return "DistrictBean [districtId=" + districtId + ", districtName=" + districtName + ", circle=" + circle
				+ ", address1=" + address1 + ", address2=" + address2 + ", street=" + street + ", city=" + city
				+ ", pin=" + pin + ", isSPMC_DPMC=" + isSPMC_DPMC + ", email=" + email + ", status=" + status
				+ ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", authorizedBy=" + authorizedBy
				+ ", authorizedDate=" + authorizedDate + ", freezedBy=" + freezedBy + ", freezedDate=" + freezedDate
				+ "]";
	}

	@Override
	public int compareTo(DistrictBean o) {
		return o.districtId.trim().compareTo(this.districtId.trim());
	}
	

}
