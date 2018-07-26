package com.prwss.mis.procurement.subplan.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;

public interface ProcSubPlanDao {
	public List<ProcSubPlanHeaderBean> findSubplan(ProcSubPlanHeaderBean procSubPlanHeaderBean, List<String> statusList) throws DataAccessException;
	//public List<ProcSubPlanPrw2HeaderBean> findSubplanPrw2(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean, List<String> statusList) throws DataAccessException;
	
	public Set<ProcSubPlanHeaderBean> getSubPlanIds(String planId,List<String> statusList) throws DataAccessException;
    public long saveSubPlan(ProcSubPlanHeaderBean procSubPlanHeaderBean) throws DataAccessException;
	public boolean updateSubPlan(ProcSubPlanHeaderBean procSubPlanHeaderBean) throws DataAccessException;
	//public long saveSubPlanPrwss2(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean2) throws DataAccessException;
	//public Set<ProcSubPlanPrw2HeaderBean> getSubPlanIdsPrw2(String planId,List<String> statusList) throws DataAccessException;
	//public boolean updateSubPlanPrw2(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean2);
//	public boolean updateSubPlan(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean);
}
