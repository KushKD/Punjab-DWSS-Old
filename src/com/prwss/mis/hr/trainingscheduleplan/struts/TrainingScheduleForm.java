package com.prwss.mis.hr.trainingscheduleplan.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TrainingScheduleForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5393586398743257828L;
	private String locationId;
	private String planFromDate;
	private String planToDate;
	private long trainingPlanId;
	private String trainingId;
	private Datagrid trainingDetailsDatagrid;
	
	
	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPlanFromDate() {
		return planFromDate;
	}
	public void setPlanFromDate(String planFromDate) {
		this.planFromDate = planFromDate;
	}
	public String getPlanToDate() {
		return planToDate;
	}
	public void setPlanToDate(String planToDate) {
		this.planToDate = planToDate;
	}
	public long getTrainingPlanId() {
		return trainingPlanId;
	}
	public void setTrainingPlanId(long trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}
	public Datagrid getTrainingDetailsDatagrid() {
		return trainingDetailsDatagrid;
	}
	public void setTrainingDetailsDatagrid(Datagrid trainingDetailsDatagrid) {
		this.trainingDetailsDatagrid = trainingDetailsDatagrid;
	}
	
	

}
