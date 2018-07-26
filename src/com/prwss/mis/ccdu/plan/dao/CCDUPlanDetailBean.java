package com.prwss.mis.ccdu.plan.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.master.training.dao.TrainingBean;


@Entity
@Table(name="t_wing_training_plan_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CCDUPlanDetailBean implements Serializable,Comparable<CCDUPlanDetailBean> {
	
	/**
	 *  Initial Version
	 */
	private static final long serialVersionUID = -4354378715224191123L;
	
	@Id
	@Column(name="plan_id", nullable=false, updatable=false)
	private long planId;
	
	@Id
	@ManyToOne(targetEntity=TrainingBean.class)
	@JoinColumn(name="training_id", nullable=false)
	private TrainingBean trainingBean;
	
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="count")
	private long count;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public TrainingBean getTrainingBean() {
		return trainingBean;
	}

	public void setTrainingBean(TrainingBean trainingBean) {
		this.trainingBean = trainingBean;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	@Override
	public int compareTo(CCDUPlanDetailBean o) {
		return this.getTrainingBean().getTrainingId().compareTo(o.getTrainingBean().getTrainingId());
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationId() {
		return locationId;
	}
	
	@Override
	public String toString() {
		return "CCDUPlanDetailBean [planId=" + planId + ", trainingBean="
				+ trainingBean + ", locationId=" + locationId + ", count="
				+ count + ", misAuditBean=" + misAuditBean + "]";
	}

}
