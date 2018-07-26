package com.prwss.mis.WaterConnection.struts;

import org.apache.struts.action.ActionForm;

public class WaterConnectionCitizenTrackingForm  extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700961234501919572L;
	
	private String appId;
	private String comments;
	
	
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "WaterConnectionCitizenTrackingForm [appId=" + appId + ", comments=" + comments + "]";
	}

	

	
	
}
