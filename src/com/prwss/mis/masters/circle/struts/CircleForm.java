package com.prwss.mis.masters.circle.struts;

import org.apache.struts.validator.ValidatorForm;

public class CircleForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6103941486368726944L;
	
	private String circleId;
	private String circleName;
	private String zoneId;
	private String[] circleIds;
	
	public String getCircleId() {
		return circleId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String[] getCircleIds() {
		return circleIds;
	}
	public void setCircleIds(String[] circleIds) {
		this.circleIds = circleIds;
	}
	
	

}
