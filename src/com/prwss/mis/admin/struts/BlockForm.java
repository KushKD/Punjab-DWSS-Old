/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * The Class BlockForm.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.admin.struts
 * @file : BlockForm.java
 * @purpose : This form bean is used to hold block UI values.
 */
public class BlockForm extends ValidatorForm {

	/** the serial version uid. */
	private static final long serialVersionUID = 1L;
	
	/** the block id. */
	private String blockId;
	
	/** the block name. */
	private String blockName;
	
	/** the district id. */
	private String districtId;
	
	/** the block ids. */
	private String blockIds[];
	
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
	 * Gets the block name.
	 * 
	 * @return the block name
	 */
	public String getBlockName() {
		return blockName;
	}
	
	/**
	 * Sets the block name.
	 * 
	 * @param blockName
	 *            the new block name
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	
	/**
	 * Gets the district id.
	 * 
	 * @return the district id
	 */
	public String getDistrictId() {
		return districtId;
	}
	
	/**
	 * Sets the district id.
	 * 
	 * @param districtId
	 *            the new district id
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	/**
	 * Gets the block ids.
	 * 
	 * @return the block ids
	 */
	public String[] getBlockIds() {
		return blockIds;
	}
	
	/**
	 * Sets the block ids.
	 * 
	 * @param blockIds
	 *            the new block ids
	 */
	public void setBlockIds(String[] blockIds) {
		this.blockIds = blockIds;
	}
	
}
