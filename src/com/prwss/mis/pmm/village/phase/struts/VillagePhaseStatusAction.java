package com.prwss.mis.pmm.village.phase.struts;

import java.util.ArrayList;
import java.util.List;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.village.struts.VillageAction;
import com.prwss.mis.pmm.village.phase.VillagePhaseStatusBO;
import com.prwss.mis.pmm.village.phase.dao.VillagePhaseStatusBean;

public class VillagePhaseStatusAction extends DispatchAction {

	Logger log = Logger.getLogger(VillageAction.class);
	private MISSessionBean misSessionBean;
	private VillagePhaseStatusBO villagePhaseStatusBO;
	
	
	public void setVillagePhaseStatusBO(VillagePhaseStatusBO villagePhaseStatusBO) {
		this.villagePhaseStatusBO = villagePhaseStatusBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
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
		try {
			VillagePhaseStatusForm villagePhaseStatusForm = (VillagePhaseStatusForm)form;
			String blockId = villagePhaseStatusForm.getBlockId();
			String villageId = villagePhaseStatusForm.getVillageId();
			List<VillagePhaseStatusBean> villagePhaseStatusBeans = null;
			villagePhaseStatusBeans = villagePhaseStatusBO.findVillagePhaseStatus(villagePhaseStatusForm, statusList);
			if(!MisUtility.ifEmpty(villagePhaseStatusBeans)){
			request.setAttribute("level2", "true");
			for (VillagePhaseStatusBean villagePhaseStatusBean : villagePhaseStatusBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", villagePhaseStatusBean.getLocationId());
				request.setAttribute("schemeId",villagePhaseStatusBean.getSchemeId() );
				villagePhaseStatusForm.setImplementationDate(MisUtility.convertDateToString(villagePhaseStatusBean.getImplementationDate()));
				villagePhaseStatusForm.setOmDate(MisUtility.convertDateToString(villagePhaseStatusBean.getOmDate()));
				villagePhaseStatusForm.setPlanningDate(MisUtility.convertDateToString(villagePhaseStatusBean.getPlanningDate()));
				villagePhaseStatusForm.setPrePlanningDate(MisUtility.convertDateToString(villagePhaseStatusBean.getPrePlanningDate()));
			}
			}else{
				refreshVillagePhaseForm(villagePhaseStatusForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		VillagePhaseStatusForm villagePhaseStatusForm = (VillagePhaseStatusForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = villagePhaseStatusBO.deleteVillagePhaseStatus(villagePhaseStatusForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete",  "Village Phase information","Village ID -->"+villagePhaseStatusForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete",  "Village Phase information","Village ID -->"+villagePhaseStatusForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshVillagePhaseForm(villagePhaseStatusForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		VillagePhaseStatusForm villagePhaseStatusForm = (VillagePhaseStatusForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}

			status = villagePhaseStatusBO.saveVillagePhaseStatus(villagePhaseStatusForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Village Phase information","Village ID -->"+villagePhaseStatusForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Village Phase information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVillagePhaseForm(villagePhaseStatusForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillagePhaseStatusForm villagePhaseStatusForm = (VillagePhaseStatusForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = villagePhaseStatusBO.updateVillagePhaseStatus(villagePhaseStatusForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Village Phase information","Village ID -->"+villagePhaseStatusForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Village Phase information","Village ID -->"+villagePhaseStatusForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Phase information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshVillagePhaseForm(villagePhaseStatusForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
//	public ActionForward post(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws MISException {
//		System.out.println("save:"+request.getParameter("d__mode"));
//		this.setAttrib(request);
//		VillageForm villageForm = (VillageForm)form;
//		boolean status = false;
//		try {
//			if (request.getSession().getAttribute("misSessionBean") != null) {
//				{
//					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
//				}
//			} else {
//				return mapping.findForward("login");
//			}
//
//			status = villageBO.postVillage(villageForm, misSessionBean);
//			if (status){
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("success.post", "Village Information ","Village ID -->"+villageForm.getVillageId());
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//
//			} else {
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("error.post", "Village Information ","Village ID -->"+villageForm.getVillageId());
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//			}
//		} catch (MISException e) {
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//			log.error(e);
//			e.printStackTrace();
//		} catch (Exception e) {
//			ActionErrors errors = new ActionErrors();
//			ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
//			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//			saveErrors(request, errors);
//			log.error(e);
//			e.printStackTrace();
//		}
//		
//			refreshVillageForm(villageForm);
//		
//		return mapping.findForward("display");
//	}
//	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@locationId@blockId@schemeId");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillagePhaseStatusForm villagePhaseStatusForm = (VillagePhaseStatusForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshVillagePhaseForm(villagePhaseStatusForm);
		return mapping.findForward("display");
	}

	private void refreshVillagePhaseForm(VillagePhaseStatusForm villagePhaseStatusForm){
		villagePhaseStatusForm.setImplementationDate(null);
		villagePhaseStatusForm.setOmDate(null);
		villagePhaseStatusForm.setPlanningDate(null);
		villagePhaseStatusForm.setPrePlanningDate(null);
		villagePhaseStatusForm.setPrePlanningDate(null);
		villagePhaseStatusForm.setLocationId(null);
		villagePhaseStatusForm.setVillageId(null);
		villagePhaseStatusForm.setBlockId(null);
		villagePhaseStatusForm.setSchemeId(null);
		
	}
}
