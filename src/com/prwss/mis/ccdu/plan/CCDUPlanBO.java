package com.prwss.mis.ccdu.plan;

import java.util.List;

import com.prwss.mis.ccdu.plan.dao.CCDUPlanHeaderBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterBean;
import com.prwss.mis.ccdu.plan.struts.CCDUPlanDetailsForm;
import com.prwss.mis.ccdu.plan.struts.CCDUPlanSummaryBean;
import com.prwss.mis.ccdu.plan.struts.CreateCCDUPlanForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface CCDUPlanBO {
	
	public List<CCDUPlanMasterBean> findCCDUPlan(CreateCCDUPlanForm createCCDUPlanForm, List<String> statusList) throws MISException;
	
	public List<CCDUPlanSummaryBean> getPlanSummary(CreateCCDUPlanForm createCCDUPlanForm, List<String> statusList) throws MISException ;
	
	public long createCCDUPlan(CreateCCDUPlanForm createCCDUPlanForm, MISSessionBean misSessionBean) throws MISException;
	
	public List<CCDUPlanHeaderBean> findCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, List<String> statusList) throws MISException;
	
	public boolean saveCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean) throws MISException;

}
