/**
 * 
 */
package com.prwss.mis.pmm.DSRBuilding.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface DSRBuildingDao {
public List<DSRBuildingBean> findDSRBuilding(DSRBuildingBean dsrBuildingBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageDSRBuilding(DSRBuildingBean dsrBuildingBean) throws DataAccessException;

	public boolean saveOrUpdateDSRBuilding(DSRBuildingBean dsrBuildingBean)	throws DataAccessException;
	
}
