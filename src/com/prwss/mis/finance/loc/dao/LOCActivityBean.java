package com.prwss.mis.finance.loc.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="t_finance_loc_activity_wise_detail",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LOCActivityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7614017294160401192L;

	@Id
	@SequenceGenerator(name="seq_t_locactivity_id", sequenceName="prwss_main.seq_t_locactivity_id")
	@GeneratedValue(generator="seq_t_locactivity_id", strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="loc_id", nullable =false)
	private long locId;
	
	@Column(name="program_id")
	private String programId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="requested_amount")
	private BigDecimal requestedAmount;
	 
	@Column(name="release_amount")
	private BigDecimal releaseAmount;
	
	@Column(name="component_id")
	private String componentId;
	
	@Column(name="sub_component_id")
	private String subComponentId;
	
	@Column(name="activity_id")
	private String activityId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLocId() {
		return locId;
	}

	public void setLocId(long locId) {
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

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public BigDecimal getReleaseAmount() {
		return releaseAmount;
	}

	public void setReleaseAmount(BigDecimal releaseAmount) {
		this.releaseAmount = releaseAmount;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getSubComponentId() {
		return subComponentId;
	}

	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "LOCActivityBean [id=" + id + ", locId=" + locId
				+ ", programId=" + programId + ", locationId=" + locationId
				+ ", requestedAmount=" + requestedAmount + ", releaseAmount="
				+ releaseAmount + ", componentId=" + componentId
				+ ", subComponentId=" + subComponentId + ", activityId="
				+ activityId + ", misAuditBean=" + misAuditBean + "]";
	}
	
	
}
