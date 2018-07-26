package com.prwss.mis.WaterConnection.struts;

import org.apache.struts.action.ActionForm;

public class WaterQualityStatusForm extends ActionForm{

	private static final long serialVersionUID = -87998987420591697L;

	private String districtId;
	private String blockId;
	private String villageId;
	private String jasperFile="/WaterConnection/Reports/water_status_report.jasper";
	private String fileTitle="water_status_report";
	private String villageIds;
	
	
	public String getVillageIds() {
		return villageIds;
	}
	public void setVillageIds(String villageIds) {
		this.villageIds = villageIds;
	}
	public String getJasperFile() {
		return jasperFile;
	}
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	@Override
	public String toString() {
		return "WaterQualityStatusForm [districtId=" + districtId + ", blockId=" + blockId + ", villageId=" + villageId
				+ "]";
	}
	
}
