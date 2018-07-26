package com.prwss.mis.procurement.physicalprogresspackage.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PhysicalProgressComponentsDao {
	
	public List<PhysicalProgressComponentsBean> findPhysicalProgressComponents(PhysicalProgressComponentsBean physicalProgressComponentsBean, List<String> statusList) throws DataAccessException;
	
	public List<PhysicalProgressComponentsBean> findPhysicalProgressAsOnDate(String packageId,List<String> statusList) throws DataAccessException;
	
	
	public boolean saveOrUpdatePhysicalProgressComponents(Collection<PhysicalProgressComponentsBean> physicalProgressComponentsBean) throws DataAccessException;



}
