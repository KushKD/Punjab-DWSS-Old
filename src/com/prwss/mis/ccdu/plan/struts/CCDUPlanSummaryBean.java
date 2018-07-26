package com.prwss.mis.ccdu.plan.struts;

import java.io.Serializable;

public class CCDUPlanSummaryBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5234803145045427677L;
	
	private String locationName;
	private long trainingsCount;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public long getTrainingsCount() {
		return trainingsCount;
	}
	public void setTrainingsCount(long trainingsCount) {
		this.trainingsCount = trainingsCount;
	}
	@Override
	public String toString() {
		return "CCDUPlanSummaryBean [locationName=" + locationName + ", trainingsCount=" + trainingsCount + "]";
	}
	

}
