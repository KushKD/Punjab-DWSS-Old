/**
 * 
 */
package com.prwss.mis.pmm.watersupply.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.watersupply.WaterSupplyBean;

/**
 * @author pjha
 *
 */
public interface WaterSupplyDao {
	public  List<WaterSupplyBean> findWaterSupply(WaterSupplyBean waterSupplyBean ,List<String> statusList) throws DataAccessException;

	public boolean saveWaterSupply(WaterSupplyBean waterSupplyBean) throws DataAccessException;

	public boolean saveOrUpdateWaterSupply(WaterSupplyBean waterSupplyBean) throws DataAccessException;
}
