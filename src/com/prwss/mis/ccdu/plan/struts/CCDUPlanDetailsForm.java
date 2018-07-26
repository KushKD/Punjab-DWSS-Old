package com.prwss.mis.ccdu.plan.struts;

import java.sql.Date;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class CCDUPlanDetailsForm extends ValidatorForm {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 4525353452177086443L;
	private long planId;
	private String locationId;
	private long prePlanningCount;
	private long planningCount;
	private long implementationCount;
	private long operationMaintenanceCount;
	private Datagrid ccduPlanDetailsDatagrid;
	private String trainingId;

	
	private long prePlanningCountCS;
	private long planningCountCS;
	private long implementationCountCS;
	private long operationMaintenanceCountCS;

	private long prePlanningCountNGO;
	private long planningCountNGO;
	private long implementationCountNGO;
	private long operationMaintenanceCountNGO;
	private long cbSewerageCount;
	private long sustainabilityWssCount;
	
	public long getPrePlanningCountCS() {
		return prePlanningCountCS;
	}
	public void setPrePlanningCountCS(long prePlanningCountCS) {
		this.prePlanningCountCS = prePlanningCountCS;
	}
	public long getPlanningCountCS() {
		return planningCountCS;
	}
	public void setPlanningCountCS(long planningCountCS) {
		this.planningCountCS = planningCountCS;
	}
	public long getImplementationCountCS() {
		return implementationCountCS;
	}
	public void setImplementationCountCS(long implementationCountCS) {
		this.implementationCountCS = implementationCountCS;
	}
	public long getOperationMaintenanceCountCS() {
		return operationMaintenanceCountCS;
	}
	public void setOperationMaintenanceCountCS(long operationMaintenanceCountCS) {
		this.operationMaintenanceCountCS = operationMaintenanceCountCS;
	}
	public long getPrePlanningCountNGO() {
		return prePlanningCountNGO;
	}
	public void setPrePlanningCountNGO(long prePlanningCountNGO) {
		this.prePlanningCountNGO = prePlanningCountNGO;
	}
	public long getPlanningCountNGO() {
		return planningCountNGO;
	}
	public void setPlanningCountNGO(long planningCountNGO) {
		this.planningCountNGO = planningCountNGO;
	}
	public long getImplementationCountNGO() {
		return implementationCountNGO;
	}
	public void setImplementationCountNGO(long implementationCountNGO) {
		this.implementationCountNGO = implementationCountNGO;
	}
	public long getOperationMaintenanceCountNGO() {
		return operationMaintenanceCountNGO;
	}
	public void setOperationMaintenanceCountNGO(long operationMaintenanceCountNGO) {
		this.operationMaintenanceCountNGO = operationMaintenanceCountNGO;
	}
	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	private Date enteredDate;
	
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public long getPrePlanningCount() {
		return prePlanningCount;
	}
	public void setPrePlanningCount(long prePlanningCount) {
		this.prePlanningCount = prePlanningCount;
	}
	public long getPlanningCount() {
		return planningCount;
	}
	public void setPlanningCount(long planningCount) {
		this.planningCount = planningCount;
	}
	public long getImplementationCount() {
		return implementationCount;
	}
	public void setImplementationCount(long implementationCount) {
		this.implementationCount = implementationCount;
	}
	public long getOperationMaintenanceCount() {
		return operationMaintenanceCount;
	}
	public void setOperationMaintenanceCount(long operationMaintenanceCount) {
		this.operationMaintenanceCount = operationMaintenanceCount;
	}
	public Datagrid getCcduPlanDetailsDatagrid() {
		return ccduPlanDetailsDatagrid;
	}
	public void setCcduPlanDetailsDatagrid(Datagrid ccduPlanDetailsDatagrid) {
		this.ccduPlanDetailsDatagrid = ccduPlanDetailsDatagrid;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public long getCbSewerageCount() {
		return cbSewerageCount;
	}
	public void setCbSewerageCount(long cbSewerageCount) {
		this.cbSewerageCount = cbSewerageCount;
	}
	public long getSustainabilityWssCount() {
		return sustainabilityWssCount;
	}
	public void setSustainabilityWssCount(long sustainabilityWssCount) {
		this.sustainabilityWssCount = sustainabilityWssCount;
	}	
}
