package com.prwss.mis.hr.trainingscheduleplan;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailBean;
@Entity
@Table(name="t_hr_training_schedule_header", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TrainingScheduleHeaderBean implements Serializable, Comparable<TrainingScheduleHeaderBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3889865874182220689L;
	
	@Id
	@GeneratedValue(generator = "seq_t_hr_training_plan", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_hr_training_plan", sequenceName = "prwss_main.seq_t_hr_training_plan")
	@Column(name = "training_plan_id", nullable = false)
	private long trainingPlanId;
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	@Column(name = "plan_from_date", nullable = false)
	private Date planFromDate;
	
	@Column(name = "plan_to_date", nullable = false)
	private Date planToDate;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=TrainingScheduleDetailBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="training_plan_id", updatable = false , insertable = false)
	private Set<TrainingScheduleDetailBean> trainingScheduleDetailBeans;

	public long getTrainingPlanId() {
		return trainingPlanId;
	}

	public void setTrainingPlanId(long trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getPlanFromDate() {
		return planFromDate;
	}

	public void setPlanFromDate(Date planFromDate) {
		this.planFromDate = planFromDate;
	}

	public Date getPlanToDate() {
		return planToDate;
	}

	public void setPlanToDate(Date planToDate) {
		this.planToDate = planToDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public Set<TrainingScheduleDetailBean> getTrainingScheduleDetailBeans() {
		return trainingScheduleDetailBeans;
	}

	public void setTrainingScheduleDetailBeans(
			Set<TrainingScheduleDetailBean> trainingScheduleDetailBeans) {
		this.trainingScheduleDetailBeans = trainingScheduleDetailBeans;
	}
	

	@Override
	public int compareTo(TrainingScheduleHeaderBean o) {
		return new Long(this.trainingPlanId).compareTo(o.trainingPlanId);
	}

	@Override
	public String toString() {
		return "TrainingScheduleHeaderBean [trainingPlanId=" + trainingPlanId
				+ ", locationId=" + locationId + ", planFromDate="
				+ planFromDate + ", planToDate=" + planToDate
				+ ", misAuditBean=" + misAuditBean
				+ ", trainingScheduleDetailBeans="
				+ trainingScheduleDetailBeans + "]";
	}
	
	
}
