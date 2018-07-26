package com.prwss.mis.ccdu.plan.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_wing_training_plan_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CCDUPlanHeaderBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -8083879468765762261L;

	@Id
	@Column(name="plan_id", nullable=false)
	private long planId;
	
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="pre_planning_count")
	private long prePlanningCount;
	
	@Column(name="planning_count")
	private long planningCount;
	
	@Column(name="implementation_count")
	private long implementationCount;
	
	@Column(name="operation_maintenance_count")
	private long operationMaintenanceCount;

	@Column(name="pre_planning_count_cs")
	private long prePlanningCountCS;
	
	@Column(name="planning_count_cs")
	private long planningCountCS;
	
	@Column(name="implementation_count_cs")
	private long implementationCountCS;
	
	@Column(name="operation_maintenance_count_cs")
	private long operationMaintenanceCountCS;
	
	@Column(name="pre_planning_count_ngo")
	private long prePlanningCountNGO;
	
	@Column(name="planning_count_ngo")
	private long planningCountNGO;
	
	@Column(name="implementation_count_ngo")
	private long implementationCountNGO;
	
	@Column(name="operation_maintenance_count_ngo")
	private long operationMaintenanceCountNGO;
	
	@Column(name = "cb_sew_count")
	private long cbSewerageCount;
	
	@Column(name = "cl_sus_wss_count")
	private long sustainabilityWssCount;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public long getPrePlanningCountCS() {
		return prePlanningCountCS;
	}

	public void setPrePlanningCountCS(long prePlanningCountCS) {
		this.prePlanningCountCS = prePlanningCountCS;
	}

	public long getPlanningCountCS() {
		return planningCountCS;
	}

	public void setPlanningCountCS(long planningCountCS) {
		this.planningCountCS = planningCountCS;
	}

	public long getImplementationCountCS() {
		return implementationCountCS;
	}

	public void setImplementationCountCS(long implementationCountCS) {
		this.implementationCountCS = implementationCountCS;
	}

	public long getOperationMaintenanceCountCS() {
		return operationMaintenanceCountCS;
	}

	public void setOperationMaintenanceCountCS(long operationMaintenanceCountCS) {
		this.operationMaintenanceCountCS = operationMaintenanceCountCS;
	}

	public long getPrePlanningCountNGO() {
		return prePlanningCountNGO;
	}

	public void setPrePlanningCountNGO(long prePlanningCountNGO) {
		this.prePlanningCountNGO = prePlanningCountNGO;
	}

	public long getPlanningCountNGO() {
		return planningCountNGO;
	}

	public void setPlanningCountNGO(long planningCountNGO) {
		this.planningCountNGO = planningCountNGO;
	}

	public long getImplementationCountNGO() {
		return implementationCountNGO;
	}

	public void setImplementationCountNGO(long implementationCountNGO) {
		this.implementationCountNGO = implementationCountNGO;
	}

	public long getOperationMaintenanceCountNGO() {
		return operationMaintenanceCountNGO;
	}

	public void setOperationMaintenanceCountNGO(long operationMaintenanceCountNGO) {
		this.operationMaintenanceCountNGO = operationMaintenanceCountNGO;
	}

	@OneToMany(targetEntity=CCDUPlanDetailBean.class, fetch=FetchType.EAGER)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumns(value = { @JoinColumn (name="plan_id", insertable=false, updatable=false), 
	@JoinColumn (name="location_id", insertable=false, updatable=false)}) 
	private Set<CCDUPlanDetailBean> ccduPlanDetailBeans;

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getPrePlanningCount() {
		return prePlanningCount;
	}

	public void setPrePlanningCount(long prePlanningCount) {
		this.prePlanningCount = prePlanningCount;
	}

	public long getPlanningCount() {
		return planningCount;
	}

	public void setPlanningCount(long planningCount) {
		this.planningCount = planningCount;
	}

	public long getImplementationCount() {
		return implementationCount;
	}

	public void setImplementationCount(long implementationCount) {
		this.implementationCount = implementationCount;
	}

	public long getOperationMaintenanceCount() {
		return operationMaintenanceCount;
	}

	public void setOperationMaintenanceCount(long operationMaintenanceCount) {
		this.operationMaintenanceCount = operationMaintenanceCount;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\n --CCDUPlanHeaderBean [planId=" + planId + ", locationId=" + locationId + ", prePlanningCount="
				+ prePlanningCount + ", planningCount=" + planningCount + ", implementationCount="
				+ implementationCount + ", operationMaintenanceCount=" + operationMaintenanceCount + ", misAuditBean="
				+ misAuditBean + "]\n";
	}

	public void setCcduPlanDetailBeans(Set<CCDUPlanDetailBean> ccduPlanDetailBeans) {
		this.ccduPlanDetailBeans = ccduPlanDetailBeans;
	}

	public Set<CCDUPlanDetailBean> getCcduPlanDetailBeans() {
		return ccduPlanDetailBeans;
	}

	public long getCbSewerageCount() {
		return cbSewerageCount;
	}

	public void setCbSewerageCount(long cbSewerageCount) {
		this.cbSewerageCount = cbSewerageCount;
	}

	public long getSustainabilityWssCount() {
		return sustainabilityWssCount;
	}

	public void setSustainabilityWssCount(long sustainabilityWssCount) {
		this.sustainabilityWssCount = sustainabilityWssCount;
	}
		
}
