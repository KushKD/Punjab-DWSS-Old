package com.prwss.mis.WaterConnection.struts;

import org.apache.struts.action.ActionForm;

public class WaterConnectionResultForm extends ActionForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6750939856519437838L;
	
	private String appId;
	private String changeStatus;
	private String statuscurrent;
	
	
	private String fileName;
	private String fileTitle;
	

	private String comments;
	
	private String snum;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	
	private String fileNameUrban;
	private String fileTitleUrban;
	
	
	
	
	
	
	
	public String getFileNameUrban() {
		return "/WaterConnection/Reports/CONN_002.jasper";
	}
	public void setFileNameUrban(String fileNameRural) {
		this.fileNameUrban = fileNameRural;
	}
	public String getFileTitleUrban() {
		return "CONN_002";
	}
	public void setFileTitleUrban(String fileTitleRural) {
		this.fileTitleUrban = fileTitleRural;
	}
	public String getFileName() {
		return "/WaterConnection/Reports/CONN_001.jasper";
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
		return "WaterConnectionResultForm [appId=" + appId + ", changeStatus=" + changeStatus + ", statuscurrent="
				+ statuscurrent + ", fileName=" + fileName + ", fileTitle=" + fileTitle + ", fileNameUrban="
				+ fileNameUrban + ", fileTitleUrban=" + fileTitleUrban + ", comments=" + comments + ", snum=" + snum
				+ "]";
	}
	
	
	
	
	
	
}
