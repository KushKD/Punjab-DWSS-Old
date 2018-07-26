package com.prwss.mis.masters.scheme.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class SchemeVillageCommisionForm extends ValidatorForm {

	private static final long serialVersionUID = -5950758256864325880L;
	private String schemeCode ;
	private String schemeName;
	private String programId;
	private String villageId;
	private String locationId;
	private String blockId;
	private String schemeSource;
	private String schemeUpgrade;
	private Datagrid villageDatagrid;
	private String schemeOperatedBy;
	private boolean waterWorksLocation;
	
	
	
	public boolean isWaterWorksLocation() {
		return waterWorksLocation;
	}
	public void setWaterWorksLocation(boolean waterWorksLocation) {
		this.waterWorksLocation = waterWorksLocation;
	}
	public String getSchemeUpgrade() {
		return schemeUpgrade;
	}
	public void setSchemeUpgrade(String schemeUpgrade) {
		this.schemeUpgrade = schemeUpgrade;
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
	public String getSchemeOperatedBy() {
		return schemeOperatedBy;
	}
	public void setSchemeOperatedBy(String schemeOperatedBy) {
		this.schemeOperatedBy = schemeOperatedBy;
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
	public Datagrid getVillageDatagrid() {
		return villageDatagrid;
	}
	public void setVillageDatagrid(Datagrid villageDatagrid) {
		this.villageDatagrid = villageDatagrid;
	}
	
}
