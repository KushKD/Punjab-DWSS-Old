package com.prwss.mis.masters.scheme.struts;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class SchemeHeaderVillageForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6886214479503081327L;
	private String schemeCode ;
	private String schemeName;
	private String programId;
	private String villageId;
	private String locationId;
	
	private String blockId;
	private String schemeSource;
	private Datagrid villageDatagrid;
	private String schemeUpgraded;
	private String refSchemeCode;
	private String waterWorksExistingNew;
	private String supplyServiceLevel;
	
	/**
	 * KD WORK
	 */
	private String subCategoryProgramme;
	
	private String subDivisionId;
	
	
	/**
	 * @return the subDivisionId
	 */
	public String getSubDivisionId() {
		return subDivisionId;
	}
	/**
	 * @param subDivisionId the subDivisionId to set
	 */
	public void setSubDivisionId(String subDivisionId) {
		this.subDivisionId = subDivisionId;
	}
	public String getSubCategoryProgramme() {
		return subCategoryProgramme;
	}
	public void setSubCategoryProgramme(String subCategoryProgramme) {
		this.subCategoryProgramme = subCategoryProgramme;
	}
	
	/**
	 * KD Work Done
	 */
	private String watersupply="ws";
	private boolean waterWorksLocation;
	private String waterWorksExisting;
	private List<SchemeVillageBean> schemeVillageBeans = null;
	
	
	
	public List<SchemeVillageBean> getSchemeVillageBeans() {
		return schemeVillageBeans;
	}
	public void setSchemeVillageBeans(List<SchemeVillageBean> schemeVillageBeans) {
		this.schemeVillageBeans = schemeVillageBeans;
	}
	
	public String getWaterWorksExisting() {
		return waterWorksExisting;
	}

	public void setWaterWorksExisting(String waterWorksExisting) {
		this.waterWorksExisting = waterWorksExisting;
	}

	public boolean isWaterWorksLocation() {
		return waterWorksLocation;
	}



	public void setWaterWorksLocation(boolean waterWorksLocation) {
		this.waterWorksLocation = waterWorksLocation;
	}

	public String getRefSchemeCode() {
		return refSchemeCode;
	}
	public void setRefSchemeCode(String refSchemeCode) {
		this.refSchemeCode = refSchemeCode;
	}
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
	public String getWaterWorksExistingNew() {
		return waterWorksExistingNew;
	}
	public void setWaterWorksExistingNew(String waterWorksExistingNew) {
		this.waterWorksExistingNew = waterWorksExistingNew;
	}
	public String getSchemeSource() {
		return schemeSource;
	}
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
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
	public Datagrid getVillageDatagrid() {
		return villageDatagrid;
	}
	public void setVillageDatagrid(Datagrid villageDatagrid) {
		this.villageDatagrid = villageDatagrid;
	}
	
	public void setSupplyServiceLevel(String supplyServiceLevel) {
		this.supplyServiceLevel = supplyServiceLevel;
	}
	public String getSupplyServiceLevel() {
		return supplyServiceLevel;
	}
	public void setWatersupply(String watersupply) {
		this.watersupply = watersupply;
	}
	public String getWatersupply() {
		return watersupply;
	}/*
	public void waterWorksLocation(){
		int count=0;
		List<SchemeVillageBean> schemeVillage=(List<SchemeVillageBean>)getSchemeVillageBeans();
		for(SchemeVillageBean schemeVillageBean:schemeVillage ){
			System.out.println(schemeVillageBean);
		}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchemeHeaderVillageForm [schemeCode=" + schemeCode + ", schemeName=" + schemeName + ", programId="
				+ programId + ", villageId=" + villageId + ", locationId=" + locationId + ", blockId=" + blockId
				+ ", schemeSource=" + schemeSource + ", villageDatagrid=" + villageDatagrid + ", schemeUpgraded="
				+ schemeUpgraded + ", refSchemeCode=" + refSchemeCode + ", waterWorksExistingNew="
				+ waterWorksExistingNew + ", supplyServiceLevel=" + supplyServiceLevel + ", subCategoryProgramme="
				+ subCategoryProgramme + ", subDivisionId=" + subDivisionId + ", watersupply=" + watersupply
				+ ", waterWorksLocation=" + waterWorksLocation + ", waterWorksExisting=" + waterWorksExisting
				+ ", schemeVillageBeans=" + schemeVillageBeans + "]";
	}
	
	
	
	}
	




	 
	

