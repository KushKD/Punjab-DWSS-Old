package com.prwss.mis.masters.scheme.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_commissioning_data", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TestSchemeViewBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6171160669628573579L;
	@Id
	@Column(name="district_id")
	private String districtId;
	@Id
	@Column(name="program_id")
	private String programId;
	@Id
	@Column(name="scheme_name")
	private String schemeName;
	@Id
	@Column(name="admin_approval_no")
	private String adminApprovalNumber;
	
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getAdminApprovalNumber() {
		return adminApprovalNumber;
	}
	public void setAdminApprovalNumber(String adminApprovalNumber) {
		this.adminApprovalNumber = adminApprovalNumber;
	}
	
	

}
