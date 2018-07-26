package com.prwss.mis.masters.village.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="village_nc_pc_status_as_2008",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageStatusNCPCView2008Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3260466373573286641L;
	
	@Id
	@Column(name="village_id")
	private String villageId;
	
	@Column(name="nc_pc_status")
	private String ncPcStatus;

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getNcPcStatus() {
		return ncPcStatus;
	}

	public void setNcPcStatus(String ncPcStatus) {
		this.ncPcStatus = ncPcStatus;
	}
	
	

}
