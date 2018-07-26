package com.prwss.mis.daktask.taskupdate.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TaskUpdateForm extends ValidatorForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5406011969639082401L;
	private String locationId;
	private String updateDate;
	private Datagrid taskAllocationDataGrid;
	
	
	
	
	@Override
	public String toString() {
		return "TaskUpdateForm [locationId=" + locationId + ", date=" + updateDate
				+ ", taskAllocationDataGrid=" + taskAllocationDataGrid + "]";
	}
	public Datagrid getTaskAllocationDataGrid() {
		return taskAllocationDataGrid;
	}
	public void setTaskAllocationDataGrid(Datagrid taskAllocationDataGrid) {
		this.taskAllocationDataGrid = taskAllocationDataGrid;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


}
