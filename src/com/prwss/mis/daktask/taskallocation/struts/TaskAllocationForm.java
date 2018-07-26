package com.prwss.mis.daktask.taskallocation.struts;

import org.apache.struts.validator.ValidatorForm;

public class TaskAllocationForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -835561122218834213L;
			
	private String locationId;
	private String dakForwardStatus;
	private String periodFromDate;
	private String periodToDate;
	private String documentNo;
//	private Datagrid taskAllocationDataGrid;
	
	
//	@Override
//	public String toString() {
//		return "TaskAllocationForm [locationId=" + locationId + ", date="
//				+ allocationDate + ", taskAllocationDataGrid=" + taskAllocationDataGrid.toString()
//				+ "]";
//	}
//	public Datagrid getTaskAllocationDataGrid() {
//		return taskAllocationDataGrid;
//	}
//	public void setTaskAllocationDataGrid(Datagrid taskAllocationDataGrid) {
//		this.taskAllocationDataGrid = taskAllocationDataGrid;
//	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getDakForwardStatus() {
		return dakForwardStatus;
	}
	public void setDakForwardStatus(String dakForwardStatus) {
		this.dakForwardStatus = dakForwardStatus;
	}
	public String getPeriodFromDate() {
		return periodFromDate;
	}
	public void setPeriodFromDate(String periodFromDate) {
		this.periodFromDate = periodFromDate;
	}
	public String getPeriodToDate() {
		return periodToDate;
	}
	public void setPeriodToDate(String periodToDate) {
		this.periodToDate = periodToDate;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
//	public String getAllocationDate() {
//		return allocationDate;
//	}
//	public void setAllocationDate(String allocationDate) {
//		this.allocationDate = allocationDate;
//	}
	

	


}
