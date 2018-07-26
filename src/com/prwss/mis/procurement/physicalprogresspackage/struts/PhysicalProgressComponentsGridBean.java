package com.prwss.mis.procurement.physicalprogresspackage.struts;

import java.io.Serializable;

public class PhysicalProgressComponentsGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -656108845206824187L;
	private long id;
	private String packageId;
	private String componentName;
	private String locationId;
	private String percentCompletion;
	private String asOnDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPercentCompletion() {
		return percentCompletion;
	}
	public void setPercentCompletion(String percentCompletion) {
		this.percentCompletion = percentCompletion;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	

}
