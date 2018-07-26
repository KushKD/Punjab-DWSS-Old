package com.prwss.mis.procurement.plan;

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
@Table(name="t_proc_plan", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CreateProcPlanBean implements Serializable,Comparable<CreateProcPlanBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -832726962319610448L;
	@Id
	@Column(name="plan_id", nullable=false)
	private String planId;

	@Column(name="program_id")
	private String programId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="plan_type")
	private String planType;
	
	@Column(name="plan_from")
	private Date planFrom;
	
	@Column(name="plan_to")
	private Date planTo;
	
	@Column(name="plan_description")
	private String planDescription;
	
	@Column(name="release_status")
	private boolean releaseStatus;
	
	@Column(name="release_date")
	private Date releaseDate;
	
	@Column(name="release_by")
	private long releaseBy;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public Date getPlanFrom() {
		return planFrom;
	}

	public void setPlanFrom(Date planFrom) {
		this.planFrom = planFrom;
	}

	public Date getPlanTo() {
		return planTo;
	}

	public void setPlanTo(Date planTo) {
		this.planTo = planTo;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public boolean getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(boolean releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getReleaseBy() {
		return releaseBy;
	}

	public void setReleaseBy(long releaseBy) {
		this.releaseBy = releaseBy;
	}

	@Override
	public String toString() {
		return "CreateProcPlanBean [planId=" + planId + ", programId="
				+ programId + ", locationId=" + locationId + ", planType="
				+ planType + ", planFrom=" + planFrom + ", planTo=" + planTo
				+ ", planDescription=" + planDescription + ", releaseStatus="
				+ releaseStatus + ", releaseDate=" + releaseDate
				+ ", releaseBy=" + releaseBy + ", misAuditBean=" + misAuditBean
				+ "]";
	}

	@Override
	public int compareTo(CreateProcPlanBean o) {
		return this.planId.compareTo(o.planId);
	}
	
	
	
}
