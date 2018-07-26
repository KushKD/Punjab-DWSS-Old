/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.masters.block.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

/**
 * The Interface BlockDao.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.masters.block.dao
 * @file : BlockDao.java
 * @purpose : This DAO interface is used for getting and updating the block
 *          values from and into database.
 */
public interface BlockDao {
	
	/**
	 * Gets the distinct block codes.
	 * 
	 * @param locationId
	 *            the location id
	 * @return the distinct block codes
	 * @throws DataAccessException
	 *             the data access exception
	 */
	public Set<BlockBean> getDistinctBlockCodes(String locationId) throws DataAccessException;

	/**
	 * This method is used to findBlock List.
	 * 
	 * @param blockBean
	 *            the block bean
	 * @param statusList
	 *            the status list
	 * @return the list< block bean>
	 * @throws DataAccessException
	 *             the data access exception
	 */
	List<BlockBean> findBlock(BlockBean blockBean, List<String> statusList)
			throws DataAccessException;

	/**
	 * This method is used to findBlock List.
	 * 
	 * @param blockIds
	 *            the block ids
	 * @return the list< block bean>
	 * @throws DataAccessException
	 *             the data access exception
	 */
	List<BlockBean> findBlock(List<String> blockIds) throws DataAccessException;

	/**
	 * Save block.
	 * 
	 * @param blockBean
	 *            the block bean
	 * @return true, if successful
	 * @throws DataAccessException
	 *             the data access exception
	 */
	boolean saveBlock(BlockBean blockBean) throws DataAccessException;

	/**
	 * Update block.
	 * 
	 * @param blockBean
	 *            the block bean
	 * @return true, if successful
	 * @throws DataAccessException
	 *             the data access exception
	 */
	boolean updateBlock(BlockBean blockBean) throws DataAccessException;

	/**
	 * Update block.
	 * 
	 * @param blockBeans
	 *            the block beans
	 * @return true, if successful
	 * @throws DataAccessException
	 *             the data access exception
	 */
	boolean updateBlock(List<BlockBean> blockBeans) throws DataAccessException;

}
