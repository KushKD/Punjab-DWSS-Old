package com.prwss.mis.procurement.physicalprogresspackage.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PhysicalProgressPackageForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7494705598305638425L;
	private String packageId;
	private String locationId;
	private String packageType;
	private String componentName;
	private String percentCompletion;
	private String asOnDate;
	private String planId;
	private long subPlanId;
	private String schemeId;
	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public long getSubPlanId() {
		return subPlanId;
	}
	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	private Datagrid componentPhysicalProgressDatagrid;
	
	
	public Datagrid getComponentPhysicalProgressDatagrid() {
		return componentPhysicalProgressDatagrid;
	}
	public void setComponentPhysicalProgressDatagrid(
			Datagrid componentPhysicalProgressDatagrid) {
		this.componentPhysicalProgressDatagrid = componentPhysicalProgressDatagrid;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
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
