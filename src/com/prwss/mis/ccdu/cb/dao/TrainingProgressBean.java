package com.prwss.mis.ccdu.cb.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

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
@Entity
@Table(name="t_ccdu_cb_training_progress", schema=MISConstants.MIS_DB_SCHEMA_NAME )
public class TrainingProgressBean implements Serializable,Comparable<TrainingProgressBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 8521249871786962021L;

	@Id
	@Column(name="cb_progress_id")
	@SequenceGenerator(name="seq_cb_progress_id", sequenceName="prwss_main.seq_cb_progress_id")
	@GeneratedValue(generator="seq_cb_progress_id", strategy=GenerationType.AUTO)
	private long cbProgressId;
	
	@Column(name="plan_id")
	private long planId;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="training_id")
	private String trainingId;
	
	@Column(name="village_id")
	private String villageId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="phase_of_village")
	private String phaseOfVillage;
	
	@Column(name="date_of_training")
	private Date dateOfTraining;
	
	@Column(name="total_participants")
	private int totalParticipants;
	
	@Column(name="issue_id")
	private String issueId;
	
	@Column(name="other_issue")
	private String otherIssue;
	
	@Column(name="recommendation_id")
	private String recommendation_id;
	
	@Column(name="other_recommendation")
	private String otherRecommendation;
	
	@Column(name="action_by_whom")
	private String actionByWhom;
	
	@Column(name="action_by_when")
	private Date actionByWhen;
	
	@Column(name="outcome_id")
	private String outcomeId;
	
	@Column(name="other_outcome")
	private String otherOutcome;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="expenditure")
	private double expenditure;
	
	@Column(name="level_of_traning")
	private String levelOfTraining;
	
	

	@Column(name="is_training_complete")
	private boolean trainingComplete;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=TrainingMaterialUtilizationBean.class,fetch=FetchType.EAGER)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="cb_progress_id", updatable=false, insertable=false)
	private Set<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans;
	
	@OneToMany(targetEntity=TrainingOfficerBean.class,fetch=FetchType.EAGER)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="cb_progress_id", updatable=false, insertable=false)
	private Set<TrainingOfficerBean> trainingOfficerBeans;

	public long getCbProgressId() {
		return cbProgressId;
	}

	public void setCbProgressId(long cbProgressId) {
		this.cbProgressId = cbProgressId;
	}

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getPhaseOfVillage() {
		return phaseOfVillage;
	}

	public void setPhaseOfVillage(String phaseOfVillage) {
		this.phaseOfVillage = phaseOfVillage;
	}

	public Date getDateOfTraining() {
		return dateOfTraining;
	}

	public void setDateOfTraining(Date dateOfTraining) {
		this.dateOfTraining = dateOfTraining;
	}

	public int getTotalParticipants() {
		return totalParticipants;
	}

	public void setTotalParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getOtherIssue() {
		return otherIssue;
	}

	public void setOtherIssue(String otherIssue) {
		this.otherIssue = otherIssue;
	}

	public String getRecommendation_id() {
		return recommendation_id;
	}

	public void setRecommendation_id(String recommendation_id) {
		this.recommendation_id = recommendation_id;
	}

	public String getOtherRecommendation() {
		return otherRecommendation;
	}

	public void setOtherRecommendation(String otherRecommendation) {
		this.otherRecommendation = otherRecommendation;
	}

	public String getActionByWhom() {
		return actionByWhom;
	}

	public void setActionByWhom(String actionByWhom) {
		this.actionByWhom = actionByWhom;
	}

	public Date getActionByWhen() {
		return actionByWhen;
	}

	public void setActionByWhen(Date actionByWhen) {
		this.actionByWhen = actionByWhen;
	}

	public String getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(String outcomeId) {
		this.outcomeId = outcomeId;
	}

	public String getOtherOutcome() {
		return otherOutcome;
	}

	public void setOtherOutcome(String otherOutcome) {
		this.otherOutcome = otherOutcome;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isTrainingComplete() {
		return trainingComplete;
	}

	public void setTrainingComplete(boolean trainingComplete) {
		this.trainingComplete = trainingComplete;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nTrainingProgressBean [cbProgressId=" + cbProgressId + ", planId=" + planId + ", blockId=" + blockId
				+ ", trainingId=" + trainingId + ", villageId=" + villageId + ", locationId=" + locationId
				+ ", phaseOfVillage=" + phaseOfVillage + ", dateOfTraining=" + dateOfTraining + ", totalParticipants="
				+ totalParticipants + ", issueId=" + issueId + ", otherIssue=" + otherIssue + ", recommendation_id="
				+ recommendation_id + ", otherRecommendation=" + otherRecommendation + ", actionByWhom=" + actionByWhom
				+ ", actionByWhen=" + actionByWhen + ", outcomeId=" + outcomeId + ", otherOutcome=" + otherOutcome
				+ ", notes=" + notes + ", trainingComplete=" + trainingComplete + ", misAuditBean=" + misAuditBean
				+ ",expenditure=" +expenditure +"]\n";
	}

	public void setTrainingMaterialUtilizationBeans(Set<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans) {
		this.trainingMaterialUtilizationBeans = trainingMaterialUtilizationBeans;
	}

	public Set<TrainingMaterialUtilizationBean> getTrainingMaterialUtilizationBeans() {
		return trainingMaterialUtilizationBeans;
	}

	public void setTrainingOfficerBeans(Set<TrainingOfficerBean> trainingOfficerBeans) {
		this.trainingOfficerBeans = trainingOfficerBeans;
	}

	public Set<TrainingOfficerBean> getTrainingOfficerBeans() {
		return trainingOfficerBeans;
	}

	@Override
	public int compareTo(TrainingProgressBean o) {
		return new Long(this.cbProgressId).compareTo(o.cbProgressId);
	}
	public double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}

	public void setLevelOfTraining(String levelOfTraining) {
		this.levelOfTraining = levelOfTraining;
	}

	public String getLevelOfTraining() {
		return levelOfTraining;
	}

}
