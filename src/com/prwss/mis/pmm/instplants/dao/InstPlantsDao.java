/**
 * 
 */
package com.prwss.mis.pmm.instplants.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.instplants.InstPlantsBean;

/**
 * @author pjha
 *
 */
public interface InstPlantsDao {
	public  List<InstPlantsBean> findInstPlants(InstPlantsBean instPlantsBean ,List<String> statusList) throws DataAccessException;

	public boolean saveInstPlants(InstPlantsBean instPlantsBean) throws DataAccessException;

	public boolean saveOrUpdateInstPlants(InstPlantsBean instPlantsBean) throws DataAccessException;
}
