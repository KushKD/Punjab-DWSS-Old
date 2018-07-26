package com.prwss.mis.master.training.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_training", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TrainingBean implements Serializable,Comparable<TrainingBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -30297421647022534L;

	@Id
	@Column(name="training_id", nullable=false)
	private String trainingId;
	
	@Column(name="training_name", nullable=false)
	private String trainingName;
	
	@Column(name="training_type")
	private String trainingType;
	
	@Column(name="category")
	private String category;
	
	@Column(name="target_group")
	private String targetGroup;
	
	@Column(name="responsibility")
	private String responsibility;
	
	@Column(name="duration_in_days")
	private long durationInDays;
	
	@Column(name="no_of_programmes")
	private long noOfProgrammes;
	
	@Column(name="focus_area")
	private String focusArea;
	
	@Column(name="resource_institution")
	private String resourceInstitution;
	
	@Column(name="estimated_cost", precision=2)
	private double estimatedCost;
	
	@Column(name="expected_outcome")
	private String expectedOutcome;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public long getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(long durationInDays) {
		this.durationInDays = durationInDays;
	}

	public long getNoOfProgrammes() {
		return noOfProgrammes;
	}

	public void setNoOfProgrammes(long noOfProgrammes) {
		this.noOfProgrammes = noOfProgrammes;
	}

	public String getFocusArea() {
		return focusArea;
	}

	public void setFocusArea(String focusArea) {
		this.focusArea = focusArea;
	}

	public String getResourceInstitution() {
		return resourceInstitution;
	}

	public void setResourceInstitution(String resourceInstitution) {
		this.resourceInstitution = resourceInstitution;
	}

	public double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getExpectedOutcome() {
		return expectedOutcome;
	}

	public void setExpectedOutcome(String expectedOutcome) {
		this.expectedOutcome = expectedOutcome;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nTrainingBean [trainingId=" + trainingId + ", trainingName=" + trainingName + ", trainingType="
				+ trainingType + ", category=" + category + ", targetGroup=" + targetGroup + ", responsibility="
				+ responsibility + ", durationInDays=" + durationInDays + ", noOfProgrammes=" + noOfProgrammes
				+ ", focusArea=" + focusArea + ", resourceInstitution=" + resourceInstitution + ", estimatedCost=" + estimatedCost + ", expectedOutcome=" + expectedOutcome
				+ ", misAuditBean=" + misAuditBean + "]\n";
	}

	@Override
	public int compareTo(TrainingBean o) {
		
		return this.getTrainingId().compareTo(o.getTrainingId());
	}

}
