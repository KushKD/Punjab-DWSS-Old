package com.prwss.mis.pmm.instplants.struts;

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
import com.prwss.mis.pmm.instplants.InstPlantsBO;
import com.prwss.mis.pmm.instplants.InstPlantsBean;

public class InstPlantsAction extends DispatchAction {

	Logger log = Logger.getLogger(InstPlantsAction.class);
	private MISSessionBean misSessionBean;
	private InstPlantsBO instPlantsBO;
	
	public void setInstPlantsBO(InstPlantsBO instPlantsBO) {
		this.instPlantsBO = instPlantsBO;
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
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
		List<InstPlantsBean> instPlantsBeans= null;
		instPlantsBeans = instPlantsBO.findInstPlants(instPlantsForm, statusList);
		if(!MisUtility.ifEmpty(instPlantsBeans)){
			request.setAttribute("level2", "true");
			for (InstPlantsBean instPlantsBean : instPlantsBeans) {
				instPlantsForm.setActualDateCompletion(MisUtility.convertDateToString(instPlantsBean.getActualDateCompletion()));
				instPlantsForm.setClusterNumber(instPlantsBean.getClusterNumber());
				instPlantsForm.setClustorNumber(instPlantsBean.getClustorNumber());
				instPlantsForm.setDivision(instPlantsBean.getDivision());
				instPlantsForm.setElectricConnection(instPlantsBean.getElectricConnection());
				instPlantsForm.setLocationId(instPlantsBean.getLocationId());
				request.setAttribute("locationId", instPlantsBean.getLocationId());
				request.setAttribute("blockId", instPlantsBean.getBlockId());
				request.setAttribute("villageId", instPlantsBean.getVillageId());
				instPlantsForm.setBlockId(instPlantsBean.getBlockId());
				instPlantsForm.setVillageId(instPlantsBean.getVillageId());
				instPlantsForm.setSiteSelection(instPlantsBean.getSiteSelection());
				instPlantsForm.setStipulatedDateCompletion(MisUtility.convertDateToString(instPlantsBean.getStipulatedDateCompletion()));
				instPlantsForm.setTubewellAllotted(instPlantsBean.getTubewellAllotted());
				instPlantsForm.setTubewellCommissioningPlant(instPlantsBean.getTubewellCommissioningPlant());
				instPlantsForm.setTubewellCompleted(instPlantsBean.getTubewellCompleted());
				instPlantsForm.setTubewellDateStart(MisUtility.convertDateToString(instPlantsBean.getTubewellDateStart()));
				instPlantsForm.setTubewellInprogress(instPlantsBean.getTubewellInprogress());
				instPlantsForm.setTubewellPhysicalCompletion(instPlantsBean.getTubewellPhysicalCompletion());
				instPlantsForm.setTubewellPlantArranged(instPlantsBean.getTubewellPlantArranged());
				instPlantsForm.setTubewellPlantInstalled(instPlantsBean.getTubewellPlantInstalled());
				instPlantsForm.setTubewellReleaseElectric(instPlantsBean.getTubewellReleaseElectric());
				instPlantsForm.setTypePlant(instPlantsBean.getTypePlant());
				instPlantsForm.setVillagesCovered(instPlantsBean.getVillagesCovered());
				instPlantsForm.setTransactionDate(MisUtility.convertDateToString(instPlantsBean.getTransactionDate()));
				instPlantsForm.setMachineryArranged(instPlantsBean.getTubewellMachineryArranged());
				instPlantsForm.setMachineryInstalled(instPlantsBean.getTubewellMachineryInstalled());
				instPlantsForm.setPlatformCompleted(instPlantsBean.getTubewellPlatformCompleted());
				
				instPlantsForm.setTubewellHousingCompleted(instPlantsBean.getTubewellHousingCompleted());
			}
		}else{
			refreshInstPlantsForm(instPlantsForm);
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
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = instPlantsBO.deleteInstPlants(instPlantsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshInstPlantsForm(instPlantsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
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

			status = instPlantsBO.saveInstPlants(instPlantsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "RO Plant Status");
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
				ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshInstPlantsForm(instPlantsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = instPlantsBO.updateInstPlants(instPlantsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshInstPlantsForm(instPlantsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = instPlantsBO.postInstPlants(instPlantsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","RO Plant Status","Location ID -->"+instPlantsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","RO Plant Status");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshInstPlantsForm(instPlantsForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "transactionDateId@locationId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		InstPlantsForm instPlantsForm = (InstPlantsForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshInstPlantsForm(instPlantsForm);
		return mapping.findForward("display");
	}

	private void refreshInstPlantsForm(InstPlantsForm instPlantsForm){
		
		instPlantsForm.setActualDateCompletion(null);
		instPlantsForm.setClusterNumber(null);
		instPlantsForm.setClustorNumber(null);
		instPlantsForm.setDivision(null);
		instPlantsForm.setElectricConnection(null);
		instPlantsForm.setLocationId(null);
		instPlantsForm.setSiteSelection(null);
		instPlantsForm.setStipulatedDateCompletion(null);
		instPlantsForm.setTubewellAllotted(null);
		instPlantsForm.setTubewellCommissioningPlant(null);
		instPlantsForm.setTubewellCompleted(null);
		instPlantsForm.setTubewellDateStart(null);
		instPlantsForm.setTubewellInprogress(0);
		instPlantsForm.setTubewellPhysicalCompletion(0);
		instPlantsForm.setTubewellPlantArranged(null);
		instPlantsForm.setTubewellPlantInstalled(null);
		instPlantsForm.setTubewellReleaseElectric(null);
		instPlantsForm.setTypePlant(null);
		instPlantsForm.setVillagesCovered(0);
		instPlantsForm.setLocationId(null);
		instPlantsForm.setTransactionDate(null);
		instPlantsForm.setBlockId(null);
		instPlantsForm.setVillageId(null);
		instPlantsForm.setTubewellHousingCompleted(null);
	}
}
