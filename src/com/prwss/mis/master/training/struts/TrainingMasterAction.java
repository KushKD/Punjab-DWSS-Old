package com.prwss.mis.master.training.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.prwss.mis.master.training.TrainingBO;
import com.prwss.mis.master.training.dao.TrainingBean;

public class TrainingMasterAction extends DispatchAction {

	private TrainingBO trainingBO ;
	private MISSessionBean misSessionBean = null;

	public void setTrainingBO(TrainingBO trainingBO) {
		this.trainingBO = trainingBO;
	}


	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}

		this.setAttrib(request);
		System.out.println("find:" + request.getParameter("d__mode"));
		String mode = (String) request.getParameter("d__mode");
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		try {
			List<String> statusList = new ArrayList<String>();
			if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
				request.setAttribute("level2", "true");
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
				request.setAttribute("level2", "true");
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			List<TrainingBean> trainingLists = null;
			
			trainingLists = trainingBO.findTrainingMaster(trainingMasterForm, statusList);
			if(!MisUtility.ifEmpty(trainingLists))
			{
				request.setAttribute("trainingLists", trainingLists);
				
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}

			log.debug("Training Master Bean List\t" + trainingLists);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		refreshTrainingMasterForm(trainingMasterForm);
		return mapping.findForward("display");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		System.out.println("IN Save");
		request.setAttribute("level2", "true");
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}

		String trainingId = null;
		try{
			trainingId = trainingBO.saveTrainingMaster(trainingMasterForm, misSessionBean);

			if (trainingId!=null){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Training", trainingId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Training",  trainingId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Training Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","training Master");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		this.setAttrib(request);
		refreshTrainingMasterForm(trainingMasterForm);
		return mapping.findForward("display");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		boolean status = false;
		
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		try{
		status = trainingBO.updateTrainingMaster(trainingMasterForm, misSessionBean,statusList);
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.update","Training",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Training",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Updation in Training Master"+ status);
		}catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Training Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","training Master");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		refreshTrainingMasterForm(trainingMasterForm);
		return mapping.findForward("display");

	}


	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		boolean status = false;
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		try{
		System.out.println("IN Delete"+trainingMasterForm.toString());
		status = trainingBO.deleteTrainingMaster(trainingMasterForm, misSessionBean);
		
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.delete",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.delete",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Deletion in Training Master"+ status);
		}catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Training Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","training Master");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		refreshTrainingMasterForm(trainingMasterForm);
		return mapping.findForward("display");

	}

	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		try{
		System.out.println("IN Post"+trainingMasterForm.toString());
		status = trainingBO.postTrainingMaster(trainingMasterForm, misSessionBean);
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.post",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.post",trainingMasterForm.getTrainingId() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Posting Training Master"+ status);
		}catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Training Master");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
					log.error(e);
					e.printStackTrace();
				} catch (Exception e) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","training Master");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					log.error(e);
					e.printStackTrace();
				}	 
		return mapping.findForward("display");
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String trainingId = request.getParameter("trainingId");
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		trainingMasterForm.setTrainingId(trainingId);
		List<TrainingBean> trainingBeans = null;
		trainingBeans = trainingBO.findTrainingMaster(trainingMasterForm, null);
		if (trainingBeans == null) {
			log.error("Trainings in populate is\t" + trainingBeans);
		}

		for (TrainingBean trainingBean : trainingBeans) {
			if(trainingId.equals(trainingBean.getTrainingId()))
			{
				trainingMasterForm.setCategory(trainingBean.getCategory());
				trainingMasterForm.setDurations(trainingBean.getDurationInDays());
				trainingMasterForm.setEstimateCost(trainingBean.getEstimatedCost());
				trainingMasterForm.setExpectedOutcomes(trainingBean.getExpectedOutcome());
				trainingMasterForm.setFocusArea(trainingBean.getFocusArea());
				trainingMasterForm.setTargetGroup(trainingBean.getTargetGroup());
				trainingMasterForm.setTrainingId(trainingBean.getTrainingId());
				trainingMasterForm.setTypeOfTraining(trainingBean.getTrainingType());
				trainingMasterForm.setTrainingName(trainingBean.getTrainingName());
				trainingMasterForm.setResourceInstitution(trainingBean.getResourceInstitution());
				trainingMasterForm.setResponsibility(trainingBean.getResponsibility());
				trainingMasterForm.setNumberOfPrograms(trainingBean.getNoOfProgrammes());
			}
		}
		return mapping.findForward("display");
	}


	private void refreshTrainingMasterForm(TrainingMasterForm trainingMasterForm)
	{
		trainingMasterForm.setCategory(null);
		trainingMasterForm.setDurations(0);
		trainingMasterForm.setEstimateCost(0);
		trainingMasterForm.setExpectedOutcomes(null);
		trainingMasterForm.setFocusArea(null);
		trainingMasterForm.setTargetGroup(null);
		trainingMasterForm.setTrainingIds(null);
		trainingMasterForm.setTrainingId(null);
		trainingMasterForm.setTypeOfTraining(null);
		trainingMasterForm.setTrainingName(null);
		trainingMasterForm.setResourceInstitution(null);
		trainingMasterForm.setResponsibility(null);
		trainingMasterForm.setNumberOfPrograms(0);


	}


	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		TrainingMasterForm trainingMasterForm = (TrainingMasterForm)form;
		refreshTrainingMasterForm(trainingMasterForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "trainingId@typeOfTrainingId");
	}
}
