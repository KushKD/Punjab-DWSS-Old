package com.prwss.mis.ccdu.plan.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface CCDUPlanMasterDao {
	
	public List<CCDUPlanMasterBean> findCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean, List<String> statusList) throws DataAccessException;
	
	public long saveCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean) throws DataAccessException;

	public boolean updateCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean) throws DataAccessException;
	
	public Set<CCDUPlanMasterBean> getDistinctCCDUPlan() throws DataAccessException;
	
}

