package com.prwss.mis.procurement.plan.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public interface CreateProcPlanDao {
	public  List<CreateProcPlanBean> findCreateProcPlanFrom(CreateProcPlanBean createProcPlanBean ,List<String> statusList) throws DataAccessException;

	public boolean saveCreateProcPlanFrom(CreateProcPlanBean createProcPlanBean) throws DataAccessException;

	public boolean updateCreateProcPlanFrom(CreateProcPlanBean createProcPlanBean) throws DataAccessException;
	
	public Set<CreateProcPlanBean> getProcPlanIds(String locationId, boolean releaseStatus, String procurementType)  throws DataAccessException;
	
	public Set<CreateProcPlanBean> getProcPlanIdsOnPlanType(String locationId,String procurementType) throws DataAccessException;
	public Set<CreateProcPlanBean> getProcPlanTypeOnPlanIds(String locationId,String planId) throws DataAccessException;
}
