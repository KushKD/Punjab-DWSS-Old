package com.prwss.mis.pmm.operatingarrangement;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class OperatingArrangementForm extends ValidatorForm{

	private static final long serialVersionUID = 1L;
	
	private String schemeCode ;
	private String schemeName;
	private String programId;
	private String villageId;
	private String locationId;
	private String blockId;
	private String schemeSource;
	private String schemeUpgrade;
	private Datagrid villageOperatingDatagrid;
	private boolean waterWorksLocation;
	
	public boolean isWaterWorksLocation() {
		return waterWorksLocation;
	}
	public void setWaterWorksLocation(boolean waterWorksLocation) {
		this.waterWorksLocation = waterWorksLocation;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getSchemeSource() {
		return schemeSource;
	}
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}
	public String getSchemeUpgrade() {
		return schemeUpgrade;
	}
	public void setSchemeUpgrade(String schemeUpgrade) {
		this.schemeUpgrade = schemeUpgrade;
	}
	public Datagrid getVillageOperatingDatagrid() {
		return villageOperatingDatagrid;
	}
	public void setVillageOperatingDatagrid(Datagrid villageOperatingDatagrid) {
		this.villageOperatingDatagrid = villageOperatingDatagrid;
	}
	
	

}
