package com.prwss.mis.WaterConnection.struts;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="new_connection_application", schema=MISConstants.MIS_DB_SCHEMA_NAME)
@org.hibernate.annotations.Entity(dynamicUpdate=true, selectBeforeUpdate=true)
public class WaterConnectionBean implements Serializable {


	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator = "new_connection_application_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "new_connection_application_seq", sequenceName = "prwss_main.new_connection_application_seq")
	
	
	@Column(name = "application_id", nullable = false)
	private Integer application_id;
	
	
	@Column(name = "application_number", nullable = false)
	private String application_number;
	
	@Column(name = "connection_type", nullable = false)
	private Integer connection_type;
	
	@Column(name = "district", nullable= false)
	private String district;
	
	@Column(name = "block")
	private String block;
	
	@Column(name = "village")
	private String village;
	
	@Column(name = "connection_ward", nullable = true)
	private String connection_ward;
	
	@Column(name = "consumer_name", nullable = false)
	private String consumer_name;

	@Column(name = "father_husband_name", nullable = false)
	private String father_husband_name;
	
	
	
	
	 
	@Column(name = "mobile_number", nullable = false)
	private Long mobile_number;
	
	
	@Column(name = "connection_address")
	private String connection_address;
	
	@Column(name = "connection_pincode", nullable = true)
	private Integer connection_pincode;
	
	@Column(name = "consumer_category", nullable = false)
	private String consumer_category;
	
	@Column(name = "consumer_adhaar", nullable = true)
	private Long consumer_adhaar;
	
	@Column(name = "proof_of_address", nullable = true)
	private String proof_of_address;
	
	@Column(name = "proof_number", nullable = true)
	private String proof_number;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "remarks", nullable = true)
	private String remarks;
	
	
	@Column(name = "islyingwith")
	private String islyingwith;
	
	@Column(name = "receiveddate")
	private Date receiveddate;
	
	@Column(name = "remarks_Dept")
	private String remarks_Dept;
	
	@Column(name = "sanctioned_Number")
	private String sanctioned_Number;
	
	@Column(name = "proof_of_identity")
	private String proof_of_identity;
	
	@Column(name = "proof_of_identity_number")
	private String proof_of_identity_number;
	
	
	
	
	

	public String getProof_of_identity() {
		return proof_of_identity;
	}

	public void setProof_of_identity(String proof_of_identity) {
		this.proof_of_identity = proof_of_identity;
	}

	public String getProof_of_identity_number() {
		return proof_of_identity_number;
	}

	public void setProof_of_identity_number(String proof_of_identity_number) {
		this.proof_of_identity_number = proof_of_identity_number;
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

	public Date getReceiveddate() {
		return receiveddate;
	}

	public void setReceiveddate(Date receiveddate) {
		this.receiveddate = receiveddate;
	}

	public String getIslyingwith() {
		return islyingwith;
	}

	public void setIslyingwith(String islyingwith) {
		this.islyingwith = islyingwith;
	}

	public Integer getApplication_id() {
		return application_id;
	}

	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}

	public String getApplication_number() {
		return application_number;
	}

	public void setApplication_number(String application_number) {
		this.application_number = application_number;
	}

	public Integer getConnection_type() {
		return connection_type;
	}

	public void setConnection_type(Integer connection_type) {
		this.connection_type = connection_type;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getConnection_ward() {
		return connection_ward;
	}

	public void setConnection_ward(String connection_ward) {
		this.connection_ward = connection_ward;
	}

	public String getConsumer_name() {
		return consumer_name;
	}

	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}

	public String getFather_husband_name() {
		return father_husband_name;
	}

	public void setFather_husband_name(String father_husband_name) {
		this.father_husband_name = father_husband_name;
	}

	

	

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getConnection_address() {
		return connection_address;
	}

	public void setConnection_address(String connection_address) {
		this.connection_address = connection_address;
	}

	public Integer getConnection_pincode() {
		return connection_pincode;
	}

	public void setConnection_pincode(Integer connection_pincode) {
		this.connection_pincode = connection_pincode;
	}

	public String getConsumer_category() {
		return consumer_category;
	}

	public void setConsumer_category(String consumer_category) {
		this.consumer_category = consumer_category;
	}

	public Long getConsumer_adhaar() {
		return consumer_adhaar;
	}

	public void setConsumer_adhaar(Long consumer_adhaar) {
		this.consumer_adhaar = consumer_adhaar;
	}

	public String getProof_of_address() {
		return proof_of_address;
	}

	public void setProof_of_address(String proof_of_address) {
		this.proof_of_address = proof_of_address;
	}

	public String getProof_number() {
		return proof_number;
	}

	public void setProof_number(String proof_number) {
		this.proof_number = proof_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) { 
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "WaterConnectionBean [application_id=" + application_id
				+ ", application_number=" + application_number
				+ ", connection_type=" + connection_type + ", district="
				+ district + ", block=" + block + ", village=" + village
				+ ", connection_ward=" + connection_ward + ", consumer_name="
				+ consumer_name + ", father_husband_name="
				+ father_husband_name + ", mobile_number=" + mobile_number
				+ ", connection_address=" + connection_address
				+ ", connection_pincode=" + connection_pincode
				+ ", consumer_category=" + consumer_category
				+ ", consumer_adhaar=" + consumer_adhaar
				+ ", proof_of_address=" + proof_of_address + ", proof_number="
				+ proof_number + ", status=" + status + ", remarks=" + remarks
				+ ", islyingwith=" + islyingwith + ", receiveddate="
				+ receiveddate + ", remarks_Dept=" + remarks_Dept
				+ ", sanctioned_Number=" + sanctioned_Number
				+ ", proof_of_identity=" + proof_of_identity
				+ ", proof_of_identity_number=" + proof_of_identity_number
				+ "]";
	}

	

	
	
	
	


	
	
	

	

	
	
	
	

}
