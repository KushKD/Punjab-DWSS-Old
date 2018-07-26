package com.prwss.mis.procurement.subplan.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_proc_sub_plan_scheme", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SubPlanSchemeBean implements Serializable,Comparable<SubPlanSchemeBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -50425290234823096L;
	
	@Id
	@Column(name="sub_plan_id", nullable=false)
	private long subPlanId;
	
	@Id
	@Column(name="scheme_id")
	private String schemeId;
	
	@Column(name="sch_name")
	private String schemeName;
	
	@Column(name="scheme_estimated_cost")
	private BigDecimal schemeEstimatedCost;
	

	@Column(name="total_packages")
	private long totalPackages;
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public long getSubPlanId() {
		return subPlanId;
	}

	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(SubPlanSchemeBean o) {
		
		return new Long(this.subPlanId).compareTo(o.subPlanId);
	}

	@Override
	public String toString() {
		return "SubPlanSchemeBean [subPlanId=" + subPlanId + ", schemeId="
				+ schemeId + ", misAuditBean=" + misAuditBean + "]";
	}

	public void setSchemeEstimatedCost(BigDecimal schemeEstimatedCost) {
		this.schemeEstimatedCost = schemeEstimatedCost;
	}

	public BigDecimal getSchemeEstimatedCost() {
		return schemeEstimatedCost;
	}

	public void setTotalPackages(long totalPackages) {
		this.totalPackages = totalPackages;
	}

	public long getTotalPackages() {
		return totalPackages;
	}

	
	
}
