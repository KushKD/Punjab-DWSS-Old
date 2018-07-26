package com.prwss.mis.masters.activity.struts;

import org.apache.struts.action.ActionForm;

public class ActivityForm extends ActionForm {
	
	/**
	 * Initial version 
	 */
	private static final long serialVersionUID = 7912150564668641278L;
	
	private String activityId;
	private String componentId;
	private String subComponentId;
	private String activityName;
	private String activityDescription;
	private String[] activityIds;
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSubComponentId() {
		return subComponentId;
	}
	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public String[] getActivityIds() {
		return activityIds;
	}
	public void setActivityIds(String[] activityIds) {
		this.activityIds = activityIds;
	}
	
}
