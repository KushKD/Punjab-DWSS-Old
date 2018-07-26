package com.prwss.mis.ccdu.plan.struts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.ccdu.plan.CCDUPlanBO;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class CreateCCDUPlanAction extends DispatchAction {
	
	private CCDUPlanBO ccduPlanBO;
	private MISSessionBean misSessionBean;
	private CCDUPlanMasterDao ccduPlanMasterDao;
	private Logger log = Logger.getLogger(CreateCCDUPlanAction.class);
	
	public void setCcduPlanBO(CCDUPlanBO ccduPlanBO) {
		this.ccduPlanBO = ccduPlanBO;
	}

	public void setCcduPlanMasterDao(CCDUPlanMasterDao ccduPlanMasterDao) {
		this.ccduPlanMasterDao = ccduPlanMasterDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		System.out.println("In Find");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		CreateCCDUPlanForm createCCDUPlanForm = (CreateCCDUPlanForm)form;		
		List<CCDUPlanMasterBean>ccduPlanMasterBeans = null; 
		try {
			ccduPlanMasterBeans = ccduPlanBO.findCCDUPlan(createCCDUPlanForm, statusList);
			List<CCDUPlanSummaryBean> ccduPlanSummaryBeans = ccduPlanBO.getPlanSummary(createCCDUPlanForm, statusList);
			if(!MisUtility.ifEmpty(ccduPlanSummaryBeans)){
				request.setAttribute("planSummary", ccduPlanSummaryBeans);
			}
			if(!MisUtility.ifEmpty(ccduPlanMasterBeans)){
				request.setAttribute("level2", "true");
				for (CCDUPlanMasterBean ccduPlanMasterBean : ccduPlanMasterBeans) {
				createCCDUPlanForm.setPlanId(ccduPlanMasterBean.getPlanId());
				createCCDUPlanForm.setPlanStartDate(MisUtility.convertDateToString(ccduPlanMasterBean.getFromDate()));
				createCCDUPlanForm.setPlanEndDate(MisUtility.convertDateToString(ccduPlanMasterBean.getToDate()));
				}
			}else{
				refreshCreatePlanForm(createCCDUPlanForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.no.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("ccdu.error.find");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			e.printStackTrace();
			
		}
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Save");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		CreateCCDUPlanForm createCCDUPlanForm = (CreateCCDUPlanForm)form;
		long planID = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(createCCDUPlanForm.getPlanStartDate().trim().equals("")||createCCDUPlanForm.getPlanStartDate().trim().equals("''")||createCCDUPlanForm.getPlanEndDate().trim().equals("")||createCCDUPlanForm.getPlanEndDate().trim().equals("''")){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			planID = ccduPlanBO.createCCDUPlan(createCCDUPlanForm, misSessionBean);
			if (MisUtility.ifEmpty(planID)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("createPlan.success.save", "Your Plan","auto-generated Plan ID is -->"+planID);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("createPlan.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of CB Progress");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of CB Progress");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		try {
			refreshCreatePlanForm(createCCDUPlanForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	private void refreshCreatePlanForm(CreateCCDUPlanForm createCCDUPlanForm){
		createCCDUPlanForm.setPlanEndDate(null);
		createCCDUPlanForm.setPlanStartDate(null);
		createCCDUPlanForm.setPlanId(0);
	}
	
	
	private Set<LabelValueBean> getPlanIds(){
		Set<LabelValueBean> ccduPlanIds = null;
		Set<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		try {
			System.out.println("in get plan");
			ccduPlanMasterBeans = ccduPlanMasterDao.getDistinctCCDUPlan();
			System.out.println("in get plan after"+ccduPlanMasterBeans);
			ccduPlanIds = new HashSet<LabelValueBean>();
			for (CCDUPlanMasterBean ccduPlanMasterBean : ccduPlanMasterBeans) {
				ccduPlanIds.add(new LabelValueBean(ccduPlanMasterBean.getPlanId()+" - ("+MisUtility.convertDateToStringForDisplay(ccduPlanMasterBean.getFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToStringForDisplay(ccduPlanMasterBean.getToDate())+")",new Long(ccduPlanMasterBean.getPlanId()).toString()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return ccduPlanIds;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
//		Set<CCDUPlanSummaryBean> planSummary = new TreeSet<CCDUPlanSummaryBean>();
//		CCDUPlanSummaryBean ccduPlanSummaryBean = new CCDUPlanSummaryBean();
//		ccduPlanSummaryBean.setLocationName("Amritsar");
//		ccduPlanSummaryBean.setTrainingsCount(10);
//		planSummary.add(ccduPlanSummaryBean);
//		request.setAttribute("planSummary", planSummary);
		try {
			CreateCCDUPlanForm createCCDUPlanForm = (CreateCCDUPlanForm)form;
			Set<LabelValueBean> ccduPlanIds = null;
			ccduPlanIds = getPlanIds();
			request.getSession().setAttribute("ccduPlanIds", ccduPlanIds);
			refreshCreatePlanForm(createCCDUPlanForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "ttttttttttt");
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId");

	}

}
