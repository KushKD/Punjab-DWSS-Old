package com.prwss.mis.masters.constituency.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="mst_constituency", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ConstituencyBean implements Serializable, Comparable<ConstituencyBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3515068480456278965L;
	  
	@Id
	@Column(name="constituency_id")
	private String constituencyId;
	
	@Column(name="constituency_name")
	private String constituencyName;
	
	@Column(name="district_id")
	private String districtId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(ConstituencyBean o) {
	return this.constituencyId.compareTo(o.constituencyId);
	}
	
	@Override
	public String toString() {
		return "CommitteeMemberBean [constituencyId=" + constituencyId + ", constituencyName="
				+ constituencyName + ", districtId=" + districtId + " , misAuditBean="
				+ misAuditBean + "]";
	}

	

}
