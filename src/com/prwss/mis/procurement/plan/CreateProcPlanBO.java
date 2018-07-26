package com.prwss.mis.procurement.plan;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.plan.struts.CreateProcPlanForm;

public interface CreateProcPlanBO {
	
public List<CreateProcPlanBean> findCreateProcPlanFrom(CreateProcPlanForm createProcPlanForm, List<String> statusList) throws MISException;
	
	public boolean saveCreateProcPlanFrom(CreateProcPlanForm createProcPlanForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateCreateProcPlanFrom(CreateProcPlanForm createProcPlanForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteCreateProcPlanFrom(CreateProcPlanForm createProcPlanForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postCreateProcPlanFrom(CreateProcPlanForm createProcPlanForm,  MISSessionBean misSessionBean) throws MISException;

}
