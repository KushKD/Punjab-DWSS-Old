package com.prwss.mis.masters.location.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_location_master", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LocationBean implements Serializable, Comparable<LocationBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 5596145896979436932L;

	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="location_name")
	private String locationName;
	
	@Column(name="location_type")
	private String locationType;
	
	@Column(name="parent_location")
	private String parentLocation;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(String parentLocation) {
		this.parentLocation = parentLocation;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "LocationBean [locationId=" + locationId + ", locationName=" + locationName + ", locationType="
				+ locationType + ", parentLocation=" + parentLocation + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(LocationBean o) {
		return this.getLocationName().compareTo(o.getLocationName());
	}
	

}
