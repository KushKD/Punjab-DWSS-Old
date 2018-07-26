package com.prwss.mis.WaterConnection.struts;

import org.apache.struts.action.ActionForm;

public class WaterConnectionsuccessForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8799898607420591697L;
	
	
	private String appId;
	private String appName;
	
	private String fileName;
	private String fileTitle;
	
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Override
	public String toString() {
		return "WaterConnectionsuccessForm [appId=" + appId + ", appName=" + appName + ", fileName=" + fileName
				+ ", fileTitle=" + fileTitle + ", fileNameUrban=" + fileNameUrban + ", fileTitleUrban=" + fileTitleUrban
				+ "]";
	}
	
	
	
	
	

	

}
