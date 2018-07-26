package com.prwss.mis.pmm.DSRPonds.struts;

import java.math.BigDecimal;
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
import com.prwss.mis.pmm.DSRPonds.DSRPondsBO;
import com.prwss.mis.pmm.DSRPonds.dao.DSRPondsBean;

public class DSRPondsAction extends DispatchAction {
	Logger log = Logger.getLogger(DSRPondsAction.class);
	private MISSessionBean misSessionBean;
	private DSRPondsBO dsrPondsBO;
	
	public void setDsrPondsBO(DSRPondsBO dsrPondsBO) {
		this.dsrPondsBO = dsrPondsBO;
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
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
		List<DSRPondsBean> dsrPondsBeans = null;
		dsrPondsBeans = dsrPondsBO.findDSRPonds(dsrPondsForm, statusList);
		String villageId = dsrPondsForm.getVillageId();
		String blockId = dsrPondsForm.getBlockId();
		if(!MisUtility.ifEmpty(dsrPondsBeans)){
			request.setAttribute("level2", "true");
			for (DSRPondsBean dsrPondsBean : dsrPondsBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", dsrPondsBean.getLocationId());
				request.setAttribute("schemeId", dsrPondsBean.getSchemeId());
				dsrPondsForm.setDsrDate(MisUtility.convertDateToString(dsrPondsBean.getDsrDate()));
				dsrPondsForm.setDrainsCost(dsrPondsBean.getDrainsCost());
				dsrPondsForm.setHaudisCost(dsrPondsBean.getHaudisCost());
				dsrPondsForm.setHaudisQuantity(dsrPondsBean.getHaudisQuantity());
				dsrPondsForm.setJcbHours(dsrPondsBean.getJcbHours());
				dsrPondsForm.setJcbHoursCost(dsrPondsBean.getJcbHoursCost());
				dsrPondsForm.setLabourMandays(dsrPondsBean.getLabourMandays());
				dsrPondsForm.setLabourMandaysCost(dsrPondsBean.getLabourMandaysCost());
				dsrPondsForm.setLocationId(dsrPondsBean.getLocationId());
				dsrPondsForm.setOtheralliedCost(dsrPondsBean.getOtheralliedCost());
				dsrPondsForm.setPipelineCost(dsrPondsBean.getPipelineCost());
				dsrPondsForm.setPipelineLength(dsrPondsBean.getPipelineLength());
				dsrPondsForm.setSchemeId(dsrPondsBean.getSchemeId());
				dsrPondsForm.setTotalCost(dsrPondsBean.getTotalCost());
				dsrPondsForm.setTractorsHours(dsrPondsBean.getTractorsHours());
				dsrPondsForm.setTractorsHoursCost(dsrPondsBean.getTractorsHoursCost());
				dsrPondsForm.setUnforeseenCost(dsrPondsBean.getUnforeseenCost());
			}
		}else{
			refreshDSRPondsForm(dsrPondsForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrPondsBO.deleteDSRPonds(dsrPondsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","DSR Information ","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","DSR Information ","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshDSRPondsForm(dsrPondsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
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

			status = dsrPondsBO.saveDSRPonds(dsrPondsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "DSR Information ","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "DSR Information");
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
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshDSRPondsForm(dsrPondsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrPondsBO.updateDSRPonds(dsrPondsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",  "DSR Information ","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "DSR Information ","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshDSRPondsForm(dsrPondsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrPondsBO.postDSRPonds(dsrPondsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","DSR Information","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","DSR Information","Scheme ID -->"+dsrPondsForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshDSRPondsForm(dsrPondsForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@schemeId@locationId@blockId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRPondsForm dsrPondsForm = (DSRPondsForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshDSRPondsForm(dsrPondsForm);
		return mapping.findForward("display");
	}

	private void refreshDSRPondsForm(DSRPondsForm dsrPondsForm){
		dsrPondsForm.setDsrDate(null);
		dsrPondsForm.setDrainsCost(0);
		dsrPondsForm.setHaudisCost(0);
		dsrPondsForm.setHaudisQuantity(0);
		dsrPondsForm.setJcbHours(null);
		dsrPondsForm.setJcbHoursCost(0);
		dsrPondsForm.setLabourMandays(null);
		dsrPondsForm.setLabourMandaysCost(0);
		dsrPondsForm.setLocationId(null);
		dsrPondsForm.setOtheralliedCost(0);
		dsrPondsForm.setPipelineCost(0);
		dsrPondsForm.setPipelineLength(null);
		dsrPondsForm.setSchemeId(null);
		dsrPondsForm.setTotalCost(new BigDecimal(0.00));
		dsrPondsForm.setTractorsHours(null);
		dsrPondsForm.setTractorsHoursCost(0);
		dsrPondsForm.setUnforeseenCost(0);
		
	}
}
