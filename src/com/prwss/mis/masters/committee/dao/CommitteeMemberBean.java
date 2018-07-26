package com.prwss.mis.masters.committee.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_committee_member", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CommitteeMemberBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4024565828251930156L;
	 @Id
	 @GeneratedValue(generator = "seq_mst_committeemember", strategy = GenerationType.AUTO)
	 @SequenceGenerator(name = "seq_mst_committeemember", sequenceName = "prwss_main.seq_mst_committeemember")
	@Column(name="member_id", nullable = false)
	private long memberId;
	
	@Column(name="member_name", nullable = false)
	private String memberName;
	@Column(name="address")
	private String address;
	@Column(name="designation")
	private String designation;
	@Column(name="gender")
	private String gender;
	@Column(name="cast_category")
	private String castCategory;
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="committee_id")
	private long committeeId;
	@Column(name="member_status", nullable = false)
	private String memberStatus;
	
	
	@Embedded
	private MISAuditBean misAuditBean;


	
	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCastCategory() {
		return castCategory;
	}


	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}


	public long getMemberId() {
		return memberId;
	}


	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public long getCommitteeId() {
		return committeeId;
	}


	public void setCommitteeId(long committeeId) {
		this.committeeId = committeeId;
	}


	public String getMemberStatus() {
		return memberStatus;
	}


	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}


	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}


	@Override
	public String toString() {
		return "CommitteeMemberBean [memberId=" + memberId + ", memberName="
				+ memberName + ", address=" + address + ", contactNumber="
				+ contactNumber + ", committeeId=" + committeeId
				+ ", memberStatus=" + memberStatus + ", misAuditBean="
				+ misAuditBean + "]";
	}
	
	
	  
}
