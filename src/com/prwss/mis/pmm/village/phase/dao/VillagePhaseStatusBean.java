package com.prwss.mis.pmm.village.phase.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_scheme_phase_status", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillagePhaseStatusBean implements Serializable {

	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 1003693883674838374L;

	@Id
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	
	@Column(name="location_id")
	private String locationId;
	
	
	@Column(name="pre_planning_date", nullable=true)
	private Date prePlanningDate;
	
	
	@Column(name="planning_date", nullable=true)
	private Date planningDate;
	
	
	@Column(name="implementation_date", nullable=true)
	private Date implementationDate;
	
	
	@Column(name="om_date", nullable=true)
	private Date omDate;
	
	@Embedded
	private MISAuditBean misAuditBean;


	
	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	

	public Date getPrePlanningDate() {
		return prePlanningDate;
	}

	public void setPrePlanningDate(Date prePlanningDate) {
		this.prePlanningDate = prePlanningDate;
	}

	

	public Date getPlanningDate() {
		return planningDate;
	}

	public void setPlanningDate(Date planningDate) {
		this.planningDate = planningDate;
	}

	
	public Date getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(Date implementationDate) {
		this.implementationDate = implementationDate;
	}

	

	public Date getOmDate() {
		return omDate;
	}

	public void setOmDate(Date omDate) {
		this.omDate = omDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "VillagePhaseStatusBean [schemeId=" + schemeId + ", locationId="
				+ locationId + ", prePlanningDate=" + prePlanningDate
				+ ", planningDate=" + planningDate + ", implementationDate="
				+ implementationDate + ", omDate=" + omDate + ", misAuditBean="
				+ misAuditBean + "]";
	}
	
	
	
}
