package com.prwss.mis.WaterConnection.struts;

import org.apache.struts.action.ActionForm;

public class WaterConnectionDepartmentForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6581418048357780635L;
	
	private String appId;
	private String changeStatus;
	private String statuscurrent;
	
	
	private String fileName;
	private String fileTitle;
	
	private String comments;
	
	private String snum;
	
	
	
	
	
	
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFileName() {
		return "/WaterConnection/Reports/singlepage/CONN_001.jasper";
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileTitle() {
		return "CONN_001";
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	

	public String getStatuscurrent() {
		return statuscurrent;
	}

	public void setStatuscurrent(String statuscurrent) {
		this.statuscurrent = statuscurrent;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return "WaterConnectionDepartmentForm [appId=" + appId + ", changeStatus=" + changeStatus + ", statuscurrent="
				+ statuscurrent + ", fileName=" + fileName + ", fileTitle=" + fileTitle + ", comments=" + comments
				+ ", snum=" + snum + "]";
	}
	
	
	
	
	

	

	
	
	
	

}
