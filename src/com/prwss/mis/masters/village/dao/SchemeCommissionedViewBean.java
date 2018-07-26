package com.prwss.mis.masters.village.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="vw_latest_commissioned_scheme", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeCommissionedViewBean implements Serializable,Comparable<SchemeCommissionedViewBean>{

	private static final long serialVersionUID = 2022530637630707847L;
	@Id
	@Column(name="village_id")
	private String villageId;

	@Id
	@Column(name="scheme_id")
	private String schemeId;
	
	@Column (name="location_id")
	private String locationId;

	
	
	@Column(name="scheme_name")
	private String schemeName;
	
	
	@Column(name="ws_sw")
	private String schemesource1;


	public String getSchemesource1() {
		return schemesource1;
	}
	public void setSchemesource1(String schemesource1) {
		this.schemesource1 = schemesource1;
	}



	@Column(name="scheme_commissioned_date")
	private String schemecommissioneddate;
	
	@Column(name="scheme_upgraded")
	private String schemeupgraded;

	@Column(name="prog_id")
	private String programId;
	


	@Column(name="scheme_source")
	private String schemeSource;
	


	@Override
	public int compareTo(SchemeCommissionedViewBean o) {
		// TODO Auto-generated method stub
		return this.schemeName.compareTo(o.schemeName);
	}
	@Override
	public String toString() {
		return "SchemeCommissionedViewBean [villageId=" + villageId
				+ ", schemeId=" + schemeId + ", locationId=" + locationId
				+ ", schemeName=" + schemeName + ", schemesource="
				+ schemesource1 + ", schemecommissioneddate="
				+ schemecommissioneddate + ", schemeupgraded=" + schemeupgraded
				+ ", programId=" + programId + ", schemeSource=" + schemeSource
				+ "]";
	}
	
	public String getVillageId() {
		return villageId;
	}



	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}



	public String getSchemeId() {
		return schemeId;
	}



	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}



	public String getLocationId() {
		return locationId;
	}



	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}



	public String getSchemeName() {
		return schemeName;
	}



	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}






	public String getSchemecommissioneddate() {
		return schemecommissioneddate;
	}



	public void setSchemecommissioneddate(String schemecommissioneddate) {
		this.schemecommissioneddate = schemecommissioneddate;
	}



	public String getSchemeupgraded() {
		return schemeupgraded;
	}



	public void setSchemeupgraded(String schemeupgraded) {
		this.schemeupgraded = schemeupgraded;
	}



	public String getProgramId() {
		return programId;
	}



	public void setProgramId(String programId) {
		this.programId = programId;
	}



	public String getSchemeSource() {
		return schemeSource;
	}



	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}


	
}
