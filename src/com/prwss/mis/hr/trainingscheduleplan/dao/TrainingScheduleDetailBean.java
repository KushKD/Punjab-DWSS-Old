package com.prwss.mis.hr.trainingscheduleplan.dao;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="t_hr_training_schedule_details", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TrainingScheduleDetailBean implements Serializable, Comparable<TrainingScheduleDetailBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8066566708157050072L;
	@Id
	@GeneratedValue(generator = "seq_hr_trainingschedule_detail", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_hr_trainingschedule_detail", sequenceName = "prwss_main.seq_hr_trainingschedule_detail")
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "training_plan_id", nullable = false)
	private long trainingPlanId;
	
	@Column(name = "training_objective", nullable = false)
	private String trainingObjective;
	
	@Column(name = "number_of_training", nullable = false)
	private String numberOfTraining;
	
	@Column(name = "target_destination")
	private String targetDestination;
	
	@Column(name = "training_date")
	private Date trainingDate;
	
	@Column(name = "remarks", nullable = false)
	private String remarks;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTrainingPlanId() {
		return trainingPlanId;
	}

	public void setTrainingPlanId(long trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}

	public String getTrainingObjective() {
		return trainingObjective;
	}

	public void setTrainingObjective(String trainingObjective) {
		this.trainingObjective = trainingObjective;
	}

	public String getNumberOfTraining() {
		return numberOfTraining;
	}

	public void setNumberOfTraining(String numberOfTraining) {
		this.numberOfTraining = numberOfTraining;
	}

	public String getTargetDestination() {
		return targetDestination;
	}

	public void setTargetDestination(String targetDestination) {
		this.targetDestination = targetDestination;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	@Override
	public String toString() {
		return "TrainingScheduleDetailBean [id=" + id + ", trainingPlanId="
				+ trainingPlanId + ", trainingObjective=" + trainingObjective
				+ ", numberOfTraining=" + numberOfTraining
				+ ", targetDestination=" + targetDestination
				+ ", trainingDate=" + trainingDate + ", remarks=" + remarks
				+ ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(TrainingScheduleDetailBean o) {
		return new Long(this.trainingPlanId).compareTo(o.trainingPlanId);
	}
	
}
