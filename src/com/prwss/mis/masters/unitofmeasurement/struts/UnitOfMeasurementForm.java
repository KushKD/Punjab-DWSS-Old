package com.prwss.mis.masters.unitofmeasurement.struts;

import org.apache.struts.validator.ValidatorForm;

public class UnitOfMeasurementForm extends ValidatorForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8089752939610338080L;
	private String unitOfMeasurementId;
	private String unitOfMeasurementName;
	private String unitOfMeasurementDesc;
	private String unitOfMeasurementIds;
	
	public String getUnitOfMeasurementId() {
		return unitOfMeasurementId;
	}
	public void setUnitOfMeasurementId(String unitOfMeasurementId) {
		this.unitOfMeasurementId = unitOfMeasurementId;
	}
	public String getUnitOfMeasurementName() {
		return unitOfMeasurementName;
	}
	public void setUnitOfMeasurementName(String unitOfMeasurementName) {
		this.unitOfMeasurementName = unitOfMeasurementName;
	}
	public String getUnitOfMeasurementIds() {
		return unitOfMeasurementIds;
	}
	public void setUnitOfMeasurementIds(String unitOfMeasurementIds) {
		this.unitOfMeasurementIds = unitOfMeasurementIds;
	}
	public String getUnitOfMeasurementDesc() {
		return unitOfMeasurementDesc;
	}
	public void setUnitOfMeasurementDesc(String unitOfMeasurementDesc) {
		this.unitOfMeasurementDesc = unitOfMeasurementDesc;
	}
	

}
