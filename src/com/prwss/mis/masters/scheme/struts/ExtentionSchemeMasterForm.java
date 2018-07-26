/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.masters.scheme.struts;
 
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * The Class ExtentionSchemeMasterForm.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.masters.scheme.struts
 * @file : ExtentionSchemeMasterForm.java
 * @purpose : This form bean is used to hold extention scheme master UI values.
 */
public class ExtentionSchemeMasterForm extends ValidatorForm {

	/** the serial version uid. */
	private static final long serialVersionUID = 6886214479503081327L;
	
	/** the scheme code. */
	private String schemeCode ;
	
	/** the scheme name. */
	private String schemeName;
	
	/** the program id. */
	private String programId;
	
	/** the village id. */
	private String villageId;
	
	/** the location id. */
	private String locationId;
	
	/** the block id. */
	private String blockId;
	
	/** the scheme source. */
	private String schemeSource;
	
	/** the village datagrid. */
	private Datagrid villageDatagrid;
	
	/** the scheme upgraded. */
	private String schemeUpgraded;
	
	/** the ref scheme code. */
	private String refSchemeCode;
 
	/** the supply service level. */
	private String supplyServiceLevel;
	
	/** the watersupply. */
	private String watersupply="ws";
	
	private String villageCategory;
	
	public String getVillageCategory() {
		return villageCategory;
	}

	public void setVillageCategory(String villageCategory) {
		this.villageCategory = villageCategory;
	}

	/** the scheme village beans. */
	private List<SchemeVillageBean> schemeVillageBeans = null;
	
	
	
	/**
	 * Gets the scheme village beans.
	 * 
	 * @return the scheme village beans
	 */
	public List<SchemeVillageBean> getSchemeVillageBeans() {
		return schemeVillageBeans;
	}
	
	/**
	 * Sets the scheme village beans.
	 * 
	 * @param schemeVillageBeans
	 *            the new scheme village beans
	 */
	public void setSchemeVillageBeans(List<SchemeVillageBean> schemeVillageBeans) {
		this.schemeVillageBeans = schemeVillageBeans;
	}
	
	/**
	 * Gets the ref scheme code.
	 * 
	 * @return the ref scheme code
	 */
	public String getRefSchemeCode() {
		return refSchemeCode;
	}
	
	/**
	 * Sets the ref scheme code.
	 * 
	 * @param refSchemeCode
	 *            the new ref scheme code
	 */
	public void setRefSchemeCode(String refSchemeCode) {
		this.refSchemeCode = refSchemeCode;
	}
	
	/**
	 * Gets the scheme upgraded.
	 * 
	 * @return the scheme upgraded
	 */
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	
	/**
	 * Sets the scheme upgraded.
	 * 
	 * @param schemeUpgraded
	 *            the new scheme upgraded
	 */
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
	 
	/**
	 * Gets the scheme source.
	 * 
	 * @return the scheme source
	 */
	public String getSchemeSource() {
		return schemeSource;
	}
	
	/**
	 * Sets the scheme source.
	 * 
	 * @param schemeSource
	 *            the new scheme source
	 */
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}
	
	/**
	 * Gets the village id.
	 * 
	 * @return the village id
	 */
	public String getVillageId() {
		return villageId;
	}
	
	/**
	 * Sets the village id.
	 * 
	 * @param villageId
	 *            the new village id
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	
	/**
	 * Gets the location id.
	 * 
	 * @return the location id
	 */
	public String getLocationId() {
		return locationId;
	}
	
	/**
	 * Sets the location id.
	 * 
	 * @param locationId
	 *            the new location id
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	/**
	 * Gets the block id.
	 * 
	 * @return the block id
	 */
	public String getBlockId() {
		return blockId;
	}
	
	/**
	 * Sets the block id.
	 * 
	 * @param blockId
	 *            the new block id
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	
	/**
	 * Gets the scheme code.
	 * 
	 * @return the scheme code
	 */
	public String getSchemeCode() {
		return schemeCode;
	}
	
	/**
	 * Sets the scheme code.
	 * 
	 * @param schemeCode
	 *            the new scheme code
	 */
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	
	/**
	 * Gets the scheme name.
	 * 
	 * @return the scheme name
	 */
	public String getSchemeName() {
		return schemeName;
	}
	
	/**
	 * Sets the scheme name.
	 * 
	 * @param schemeName
	 *            the new scheme name
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	/**
	 * Gets the program id.
	 * 
	 * @return the program id
	 */
	public String getProgramId() {
		return programId;
	}
	
	/**
	 * Sets the program id.
	 * 
	 * @param programId
	 *            the new program id
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	/**
	 * Gets the village datagrid.
	 * 
	 * @return the village datagrid
	 */
	public Datagrid getVillageDatagrid() {
		return villageDatagrid;
	}
	
	/**
	 * Sets the village datagrid.
	 * 
	 * @param villageDatagrid
	 *            the new village datagrid
	 */
	public void setVillageDatagrid(Datagrid villageDatagrid) {
		this.villageDatagrid = villageDatagrid;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchemeHeaderVillageForm [schemeCode=" + schemeCode
				+ ", schemeName=" + schemeName + ", programId=" + programId
				+ ", villageId=" + villageId + ", locationId=" + locationId
				+ ", blockId=" + blockId + ", schemeSource=" + schemeSource
				+ ", villageDatagrid=" + villageDatagrid + ",watersupply="+watersupply+"]";
	}
	
	/**
	 * Sets the supply service level.
	 * 
	 * @param supplyServiceLevel
	 *            the new supply service level
	 */
	public void setSupplyServiceLevel(String supplyServiceLevel) {
		this.supplyServiceLevel = supplyServiceLevel;
	}
	
	/**
	 * Gets the supply service level.
	 * 
	 * @return the supply service level
	 */
	public String getSupplyServiceLevel() {
		return supplyServiceLevel;
	}
	
	/**
	 * Sets the watersupply.
	 * 
	 * @param watersupply
	 *            the new watersupply
	 */
	public void setWatersupply(String watersupply) {
		this.watersupply = watersupply;
	}
	
	/**
	 * Gets the watersupply.
	 * 
	 * @return the watersupply
	 */
	public String getWatersupply() {
		return watersupply;
	}
	
	
	 
	
}
