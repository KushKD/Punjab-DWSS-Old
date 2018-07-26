package com.prwss.mis.masters.village.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_village_scheme_matching", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageSchemeViewBean implements Serializable,Comparable<VillageSchemeViewBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2022530637630707847L;
	@Id
	@Column(name="village_id")
	private String villageId;
	
	@Column(name="village_name")
	private String villageName;
	
	@Id
	@Column(name="scheme_id")
	private String schemeId;
	@Column(name="scheme_name")
	private String schemeName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="program_id")
	private String programId;
	

	@Column(name="scheme_source")
	private String schemeSource;
	
	@Column (name="location_id")
	private String locationId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	@Override
	public int compareTo(VillageSchemeViewBean o) {
		// TODO Auto-generated method stub
		return this.schemeName.compareTo(o.schemeName);
	}
	@Override
	public String toString() {
		return "VillageSchemeViewBean [villageId=" + villageId
				+ ", villageName=" + villageName + ", schemeId=" + schemeId
				+ ", schemeName=" + schemeName + ", status=" + status + ",locationId=" +locationId+ "]";
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}
	public String getSchemeSource() {
		return schemeSource;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationId() {
		return locationId;
	}

	

}
