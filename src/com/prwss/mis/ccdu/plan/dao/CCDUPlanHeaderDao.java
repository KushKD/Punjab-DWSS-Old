package com.prwss.mis.ccdu.plan.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CCDUPlanHeaderDao {
	
	public List<CCDUPlanHeaderBean> findCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean) throws DataAccessException;

	public boolean updateCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean) throws DataAccessException;

}
