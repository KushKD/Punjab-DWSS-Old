package com.prwss.mis.masters.unitofmeasurement.struts;

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
import com.prwss.mis.masters.unitofmeasurement.UnitOfMeasurementBO;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;

public class UnitOfMeasurentAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private UnitOfMeasurementBO unitOfMeasurementBO;
	
	public void setUnitOfMeasurementBO(UnitOfMeasurementBO unitOfMeasurementBO) {
		this.unitOfMeasurementBO = unitOfMeasurementBO;
	}
	
	public ActionForward  find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		
		System.out.println("in find...uom");
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean)request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		System.out.println("in find...uom 2");
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		System.out.println("in find...uom 3");
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
		try {
			UnitOfMeasurementForm unitOfMeasurementForm = (UnitOfMeasurementForm)form;
			List<UnitOfMeasurementBean> unitOfMeasurementBeans = null;
			unitOfMeasurementBeans = unitOfMeasurementBO.findUnitOfMeasurement(unitOfMeasurementForm, statusList);
			if(!MisUtility.ifEmpty(unitOfMeasurementBeans)){
				request.setAttribute("unitOfMeasurementBeanList", unitOfMeasurementBeans);
				
			}else{
				refreshUnitOfMeasurementFrom(unitOfMeasurementForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Unit Of Measurement");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		UnitOfMeasurementForm unitOfMeasurementForm = (UnitOfMeasurementForm)form;
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
			if(!MisUtility.ifEmpty(unitOfMeasurementForm.getUnitOfMeasurementId())||!MisUtility.ifEmpty(unitOfMeasurementForm.getUnitOfMeasurementName())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			status = unitOfMeasurementBO.saveUnitOfMeasurement(unitOfMeasurementForm, misSessionBean);
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save","Unit Of Measurement with Item ID - ",unitOfMeasurementForm.getUnitOfMeasurementId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Submission Unit Of Measurement");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry","Saving failed as ",e.getMessage());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Unit Of Measurement");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of  Unit Of Measurement");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshUnitOfMeasurementFrom(unitOfMeasurementForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		UnitOfMeasurementForm unitOfMeasurementForm=(UnitOfMeasurementForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = unitOfMeasurementBO.updateUnitOfMeasurement(unitOfMeasurementForm, misSessionBean);
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.update","Unit Of Measurement Entry","of Unit Of Measurement"+unitOfMeasurementForm.getUnitOfMeasurementId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Unit Of Measurement Entry","of Unit Of Measurement"+unitOfMeasurementForm.getUnitOfMeasurementId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Unit Of Measurement");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Unit Of Measurement");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshUnitOfMeasurementFrom(unitOfMeasurementForm);
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean)request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		
		UnitOfMeasurementForm unitOfMeasurementForm=(UnitOfMeasurementForm)form;
		try{
			 unitOfMeasurementBO.deleteUnitOfMeasurement(unitOfMeasurementForm, misSessionBean);
		}catch(MISException e){
			e.setMessage("Delete of Unit Of Measurement failed please try again");
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Delete of Unit Of Measurement");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
				
		return mapping.findForward("display");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		UnitOfMeasurementForm unitOfMeasurementForm = (UnitOfMeasurementForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshUnitOfMeasurementFrom(unitOfMeasurementForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	private void refreshUnitOfMeasurementFrom(UnitOfMeasurementForm unitOfMeasurementForm)
	{
		unitOfMeasurementForm.setUnitOfMeasurementId(null);
		unitOfMeasurementForm.setUnitOfMeasurementName(null);
		unitOfMeasurementForm.setUnitOfMeasurementDesc(null);
	}
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "unitOfMeasurementId");
	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		UnitOfMeasurementForm unitOfMeasurementForm=(UnitOfMeasurementForm)form;
		
		unitOfMeasurementForm.setUnitOfMeasurementId(request.getParameter("unitOfMeasurementId"));	
		
		try {			
			
			List<String> statusList = new ArrayList<String>();			
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			
			UnitOfMeasurementBean unitOfMeasurementBean = null;
			unitOfMeasurementBean = unitOfMeasurementBO.findUnitOfMeasurement(unitOfMeasurementForm, statusList).get(0);
			if(MisUtility.ifEmpty(unitOfMeasurementBean)){				
				unitOfMeasurementForm.setUnitOfMeasurementId(unitOfMeasurementBean.getMeasurementId());				
				unitOfMeasurementForm.setUnitOfMeasurementName(unitOfMeasurementBean.getMeasurementName());
				unitOfMeasurementForm.setUnitOfMeasurementDesc(unitOfMeasurementBean.getMeasurementDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	
	

}
