package com.prwss.mis.masters.scheme.dao;

import java.io.Serializable;

import javax.persistence.Column;											
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_current_nc_pc_status", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CurrentFcVillageStatusBean implements Serializable{
	private static final long serialVersionUID = 23L;
	
	@Id
	@Column(name="village_id")
	private String villageId;

	@Column(name="location_id")
	private String locationId;

	@Column(name="village_name")
	private String villageName;
	
	@Column(name="nc_pc_status")
	private String ncPcstatus;
	
	
	

	public String getLocationId() {
		return locationId;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	public String getVillageName() {
		return villageName;
	}


	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}


	public String getNcPcstatus() {
		return ncPcstatus;
	}


	public void setNcPcstatus(String ncPcstatus) {
		this.ncPcstatus = ncPcstatus;
	}


	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}


	public String getVillageId() {
		return villageId;
	}
	
	@Override
	public String toString() {
		return "CurrentFcVillageStatusBean [villageId=" + villageId + "}";
	}
}
