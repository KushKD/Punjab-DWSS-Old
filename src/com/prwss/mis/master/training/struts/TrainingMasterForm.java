package com.prwss.mis.master.training.struts;

import java.util.Arrays;

import org.apache.struts.validator.ValidatorForm;

public class TrainingMasterForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6753653493412737373L;
	private String trainingId;
	private String trainingName;
	private String typeOfTraining;
	private String category;
	private String targetGroup;
	private String responsibility;
	private long durations;
	private long numberOfPrograms;
	private String focusArea;
	private String resourceInstitution;
	private double estimateCost;
	private String expectedOutcomes;
	private String[] trainingIds;
	
	public String[] getTrainingIds() {
		return trainingIds;
	}
	public void setTrainingIds(String[] trainingIds) {
		this.trainingIds = trainingIds;
	}
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
	public String getTypeOfTraining() {
		return typeOfTraining;
	}
	public void setTypeOfTraining(String typeOfTraining) {
		this.typeOfTraining = typeOfTraining;
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
	public long getDurations() {
		return durations;
	}
	public void setDurations(long durations) {
		this.durations = durations;
	}
	public long getNumberOfPrograms() {
		return numberOfPrograms;
	}
	public void setNumberOfPrograms(long numberOfPrograms) {
		this.numberOfPrograms = numberOfPrograms;
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
	public double getEstimateCost() {
		return estimateCost;
	}
	public void setEstimateCost(double estimateCost) {
		this.estimateCost = estimateCost;
	}
	public String getExpectedOutcomes() {
		return expectedOutcomes;
	}
	public void setExpectedOutcomes(String expectedOutcomes) {
		this.expectedOutcomes = expectedOutcomes;
	}
	@Override
	public String toString() {
		return "TrainingMasterForm [trainingId=" + trainingId + ", trainingName=" + trainingName + ", typeOfTraining="
				+ typeOfTraining + ", category=" + category + ", targetGroup=" + targetGroup + ", responsibility="
				+ responsibility + ", durations=" + durations + ", numberOfPrograms=" + numberOfPrograms
				+ ", focusArea=" + focusArea + ", resourceInstitution=" + resourceInstitution + ", estimateCost="
				+ estimateCost + ", expectedOutcomes=" + expectedOutcomes + ", trainingIds="
				+ Arrays.toString(trainingIds) + "]";
	}
	
	
	
	
	

}
