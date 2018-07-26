package com.prwss.mis.admin.divisional.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.masters.district.dao.DistrictBean;

@Entity
@Table(name="mst_divisional", schema="prwss_main")
public class DivisionalBean implements Serializable,Comparable<DivisionalBean> {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="divisional_id", nullable=false)
	private String divisionalId;
	
	@Column(name="divisional_name", nullable=false)
	private String divisionalName;
	
	@ManyToOne(targetEntity=DistrictBean.class)
	@JoinColumn(name="district_id", nullable=false)
	private DistrictBean district;
	
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

	public DistrictBean getDistrict() {
		return district;
	}
	public void setDistrict(DistrictBean district) {
		this.district = district;
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
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pin")
	private Long pin ;
	
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
	@Override
	public int compareTo(DivisionalBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
