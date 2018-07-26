package com.prwss.mis.ccdu.plan.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_wing_training_plan_master", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CCDUPlanMasterBean implements Serializable, Comparable<CCDUPlanMasterBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -7791415316763768689L;

	@Id
	@SequenceGenerator(name="seq_t_plan_id", sequenceName="prwss_main.seq_t_plan_id")
	@GeneratedValue(generator="seq_t_plan_id", strategy=GenerationType.AUTO)
	@Column(name="plan_id", nullable=false)
	private long planId;
	
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	
	@OneToMany(targetEntity=CCDUPlanHeaderBean.class)
	@JoinColumn(name="plan_id", updatable=false, insertable=false)
	private Set<CCDUPlanHeaderBean> ccduPlanHeaderBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nCCDUPlanMasterBean [planId=" + planId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", misAuditBean=" + misAuditBean + "]\n";
	}

	public void setCcduPlanHeaderBeans(Set<CCDUPlanHeaderBean> ccduPlanHeaderBeans) {
		this.ccduPlanHeaderBeans = ccduPlanHeaderBeans;
	}

	public Set<CCDUPlanHeaderBean> getCcduPlanHeaderBeans() {
		return ccduPlanHeaderBeans;
	}

	@Override
	public int compareTo(CCDUPlanMasterBean o) {
		
		return new Long(this.planId).compareTo(o.planId);
	}
	
	

}
