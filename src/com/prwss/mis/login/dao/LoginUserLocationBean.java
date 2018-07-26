package com.prwss.mis.login.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="sd_user_location", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LoginUserLocationBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4558686924325947878L;

	@Id
	@Column(name="user_id", nullable=false)
	private String userId;
	
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="location_type")
	private String  locationType;
	
	
	
	
	 
	@Embedded
	private MISAuditBean misAuditBean;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	
	
	

}
