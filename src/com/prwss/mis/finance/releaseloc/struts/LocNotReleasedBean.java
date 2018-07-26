package com.prwss.mis.finance.releaseloc.struts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_not_release_loc", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LocNotReleasedBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "sch_name")
	private String schemeName;
	
	@Column(name = "loc_id")
	private String locId;
	
	@Column(name = "program_id")
	private String programId;
	
	@Column(name = "location_id")
	private String locationId;
	
	@Column(name = "loc_request_date")
	private String locRequestDate;
	
	@Column(name = "loc_release_date")
	private String locReleaseDate;

	@Column(name = "request_to_location_id")
	private String requestToLocationId;
		
	public String getRequestToLocationId() {
		return requestToLocationId;
	}

	public void setRequestToLocationId(String requestToLocationId) {
		this.requestToLocationId = requestToLocationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocRequestDate() {
		return locRequestDate;
	}

	public void setLocRequestDate(String locRequestDate) {
		this.locRequestDate = locRequestDate;
	}

	public String getLocReleaseDate() {
		return locReleaseDate;
	}

	public void setLocReleaseDate(String locReleaseDate) {
		this.locReleaseDate = locReleaseDate;
	}
	
	
	
}
