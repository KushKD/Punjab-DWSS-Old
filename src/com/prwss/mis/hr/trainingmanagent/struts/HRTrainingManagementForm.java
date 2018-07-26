package com.prwss.mis.hr.trainingmanagent.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class HRTrainingManagementForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -180202605877614715L;
	private long trainingPlanId;
	private String locationId;
	private String trainingObjective;
	private Datagrid employeeTrainingDetailDatagrid;
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
	public String getTrainingObjective() {
		return trainingObjective;
	}
	public void setTrainingObjective(String trainingObjective) {
		this.trainingObjective = trainingObjective;
	}
	public Datagrid getEmployeeTrainingDetailDatagrid() {
		return employeeTrainingDetailDatagrid;
	}
	public void setEmployeeTrainingDetailDatagrid(
			Datagrid employeeTrainingDetailDatagrid) {
		this.employeeTrainingDetailDatagrid = employeeTrainingDetailDatagrid;
	}
	
	

}
