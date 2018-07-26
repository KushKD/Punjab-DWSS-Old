package com.prwss.mis.hr.trainingscheduleplan.struts;

import java.io.Serializable;

public class TrainingScheduleDetailGridBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5908394568652000552L;

	private long id;
	
	private long trainingPlanId;
	
	private String trainingObjective;
	
	private String numberOfTraining;
	
	private String targetDestination;
	
	private String trainingDate;
	
	private String remarks;
	
	

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

	public String getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}
	
	


}
