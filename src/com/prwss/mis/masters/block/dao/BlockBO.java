/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.masters.block.dao;

import java.util.List;

import com.prwss.mis.admin.struts.BlockForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * The Interface BlockBO.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.masters.block.dao
 * @file : BlockBO.java
 * @purpose : This BO interface contains the business rules for block.
 */
public interface BlockBO {
	
	/**
	 * This method is used to findBlock List.
	 * 
	 * @param blockForm
	 *            the block action form
	 * @param statusList
	 *            the status list
	 * @return the list< block bean>
	 * @throws MISException
	 *             the MIS exception
	 */
	public List<BlockBean> findBlock(BlockForm blockForm, List<String> statusList) throws MISException;
	
	/**
	 * Save block.
	 * 
	 * @param blockForm
	 *            the block action form
	 * @param misAuditBean
	 *            the mis audit bean
	 * @return true, if successful
	 * @throws MISException
	 *             the MIS exception
	 */
	public boolean saveBlock(BlockForm blockForm, MISSessionBean misAuditBean) throws MISException;
	
	
	/**
	 * Delete block.
	 * 
	 * @param blockForm
	 *            the block action form
	 * @param misAuditBean
	 *            the mis audit bean
	 * @return true, if successful
	 * @throws MISException
	 *             the MIS exception
	 */
	public boolean deleteBlock(BlockForm blockForm, MISSessionBean misAuditBean) throws MISException;
	
	/**
	 * Post block.
	 * 
	 * @param blockForm
	 *            the block action form
	 * @param misAuditBean
	 *            the mis audit bean
	 * @return true, if successful
	 * @throws MISException
	 *             the MIS exception
	 */
	public boolean postBlock(BlockForm blockForm, MISSessionBean misAuditBean) throws MISException;

	/**
	 * Update block.
	 * 
	 * @param blockForm
	 *            the block action form
	 * @param misSessionBean
	 *            the mis session bean
	 * @param statusList
	 *            the status list
	 * @return true, if successful
	 * @throws MISException
	 *             the MIS exception
	 */
	boolean updateBlock(BlockForm blockForm, MISSessionBean misSessionBean,
			List<String> statusList) throws MISException;

}
