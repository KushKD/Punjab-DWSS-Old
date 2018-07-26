package com.prwss.mis.procurement.subplan.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ProcSubPlanSchemeDao {

	public List<SubPlanSchemeBean> findSubPlanScheme(SubPlanSchemeBean subPlanSchemeBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateSubPlanScheme(Collection<SubPlanSchemeBean> subPlanSchemeBeans) throws DataAccessException;
	
	public List<SubPlanSchemeBean> findSchemeId(String SchemeId,List<String> statusList);

	
}
