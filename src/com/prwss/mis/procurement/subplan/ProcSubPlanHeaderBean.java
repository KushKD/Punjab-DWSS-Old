package com.prwss.mis.procurement.subplan;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;

@Entity
@Table(name="t_proc_sub_plan", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProcSubPlanHeaderBean implements Serializable,Comparable<ProcSubPlanHeaderBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2100855839680153670L;
	
	@Id
//	@GeneratedValue(generator = "seq_t_proc_subplan", strategy = GenerationType.AUTO)
//  @SequenceGenerator(name = "seq_t_proc_subplan", sequenceName = "prwss_main.seq_t_proc_subplan")
	@Column(name="sub_plan_id", nullable=false)
	private long subPlanId;

	@Column(name="plan_id")
	private String planId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="sub_plan_name")
	private String subPlanName;
	
	@Column(name="sub_plan_description")
	private String subPlanDescription;
	
	@OneToMany(targetEntity=SubPlanSchemeBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="sub_plan_id", updatable = false , insertable = false)
	private Set<SubPlanSchemeBean> subPlanSchemeBeans;
	
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	

	public long getSubPlanId() {
		return subPlanId;
	}

	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSubPlanDescription() {
		return subPlanDescription;
	}

	public void setSubPlanDescription(String subPlanDescription) {
		this.subPlanDescription = subPlanDescription;
	}

	public Set<SubPlanSchemeBean> getSubPlanSchemeBeans() {
		return subPlanSchemeBeans;
	}

	public void setSubPlanSchemeBeans(Set<SubPlanSchemeBean> subPlanSchemeBeans) {
		this.subPlanSchemeBeans = subPlanSchemeBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(ProcSubPlanHeaderBean o) {
		return new Long(this.subPlanId).compareTo(o.subPlanId);
	}


	public void setSubPlanName(String subPlanName) {
		this.subPlanName = subPlanName;
	}

	public String getSubPlanName() {
		return subPlanName;
	}


	@Override
	public String toString() {
		return "ProcSubPlanHeaderBean [subPlanId=" + subPlanId + ", subPlanName=" + subPlanName+" , planId="
				+ planId + ", locationId=" + locationId
				+ ", subPlanDescription=" + subPlanDescription
				+ ", subPlanSchemeBeans=" + subPlanSchemeBeans
				+ ", misAuditBean=" + misAuditBean + "]";
	}	
	
	
	
	
	
	
}
