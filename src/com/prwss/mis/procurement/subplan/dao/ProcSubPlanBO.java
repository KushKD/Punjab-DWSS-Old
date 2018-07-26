package com.prwss.mis.procurement.subplan.dao;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.struts.ProcSubPlanForm;

public interface ProcSubPlanBO {
	public List<ProcSubPlanHeaderBean> findSubPlan(ProcSubPlanForm procSubPlanForm, List<String> statusList) throws MISException;
	//public List<ProcSubPlanPrw2HeaderBean> findSubPlanPrw2(ProcSubPlanForm procSubPlanForm, List<String> statusList) throws MISException;
	public String saveSubPlan(ProcSubPlanForm procSubPlanForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateSubPlan(ProcSubPlanForm procSubPlanForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteSubPlan(ProcSubPlanForm procSubPlanForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postSubPlan(ProcSubPlanForm procSubPlanForm, MISSessionBean misSessionBean) throws MISException;
	//String saveSubPlanPrw2(ProcSubPlanForm procSubPlanForm,	MISSessionBean misSessionBean) throws MISException;
	//public String saveSubPlanPrw2(ProcSubPlanForm procSubPlanForm,MISSessionBean misSessionBean) throws MISException;
}
