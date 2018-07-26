package com.prwss.mis.ccdu.plan.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.ccdu.plan.struts.CCDUPlanSummaryBean;

public interface CCDUPlanDetailDao {
	
	public List<CCDUPlanDetailBean> findCCDUPlanDetail(CCDUPlanDetailBean ccduPlanDetailBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateCCDUPlanDetail(Collection<CCDUPlanDetailBean> ccduPlanDetailBeans) throws DataAccessException;
	
	public Set<CCDUPlanDetailBean> getTrainingIdByPlanIdLocationId(Long planId) throws DataAccessException;
	
	public List<CCDUPlanSummaryBean> getPlanSummary(long planId, List<String> statusList) throws DataAccessException ;

	

}
