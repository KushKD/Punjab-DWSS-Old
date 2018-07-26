package com.prwss.mis.masters.iecactivity.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.prwss.mis.masters.iecactivity.IECActivityBO;
import com.prwss.mis.masters.iecactivity.dao.IECActivityBean;
import com.prwss.mis.masters.zone.dao.ZoneBean;
import com.prwss.mis.masters.zone.struts.ZoneForm;

public class IecActivityAction extends DispatchAction {

	private IECActivityBO iecActivityBO;
	private MISSessionBean misSessionBean = null;

	public void setIecActivityBO(IECActivityBO iecActivityBO) {
		this.iecActivityBO = iecActivityBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
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
		List<IECActivityBean> iecActivityList = null;
		IecActivityForm iecActivityForm =(IecActivityForm)form;
		iecActivityList = iecActivityBO.findIECActivity(iecActivityForm, statusList);
		if(MisUtility.ifEmpty(iecActivityList))
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "IEC", " Activity");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		log.debug("IEC Activity Bean List\t" + iecActivityList);
		request.setAttribute("iecActivityList", iecActivityList);
		refreshIecActivityForm(iecActivityForm);
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
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
		IecActivityForm iecActivityForm = (IecActivityForm)form;
		try{
		
		status = iecActivityBO.deleteIECActivity(iecActivityForm, misSessionBean);
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.delete",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.delete",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Deletion in IECActivity"+ status);
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	
		refreshIecActivityForm(iecActivityForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		boolean status = false;
		
		IecActivityForm iecActivityForm = (IecActivityForm)form;
	
		try{
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		
		status = iecActivityBO.updateIECActivity(iecActivityForm, misSessionBean);
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.update","IEC Activity",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Updation in IECActivity"+ status);
		}catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	
		refreshIecActivityForm(iecActivityForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		IecActivityForm iecActivityForm = (IecActivityForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
 
		String iecActivityId = null;
		try{
			iecActivityId = iecActivityBO.saveIECActivity(iecActivityForm, misSessionBean);

			if (MisUtility.ifEmpty(iecActivityId)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "IEC Activity", iecActivityId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "IEC Activity", iecActivityId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e);
				throw e;

			}
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	
		this.setAttrib(request);
		request.setAttribute("level1", "true");
		refreshIecActivityForm(iecActivityForm);
		return mapping.findForward("display");
	}
	
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String iecActivityId = request.getParameter("iecActivityId");
		IecActivityForm iecActivityForm = (IecActivityForm)form;
		iecActivityForm.setIecActivityCode(iecActivityId);
		List<IECActivityBean> iecActivityLists = null;
		iecActivityLists = iecActivityBO.findIECActivity(iecActivityForm, null);
		if (iecActivityLists == null) {
			log.error("iecActivityLists is\t" + iecActivityLists);
		}
		
		for (IECActivityBean iecActivityBean : iecActivityLists) {
			if (iecActivityId.equalsIgnoreCase(iecActivityBean.getIecActivityId()))
			{
				iecActivityForm.setIecActivityCode(iecActivityBean.getIecActivityId());
				iecActivityForm.setIecActivityName(iecActivityBean.getIecActivityName());
				iecActivityForm.setIecActivityDescription(iecActivityBean.getIecActivityName());
			}
			
		}
		return mapping.findForward("display");
	}

	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		IecActivityForm iecActivityForm = (IecActivityForm)form;
		try{
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println(misSessionBean);	
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		status = iecActivityBO.postIECActivity(iecActivityForm, misSessionBean);
		if(status)
		{
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.post",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.post",iecActivityForm.getIecActivityCode() );
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		log.debug("Posting in IECActivity"+ status);
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
	} catch (Exception e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","IEC Activity");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
		log.error(e);
		e.printStackTrace();
	}	
		
		return mapping.findForward("display");
		
	}
	
	
	
	private void refreshIecActivityForm(IecActivityForm iecActivityForm)
	{
		iecActivityForm.setIecActivityCode(null);
		iecActivityForm.setIecActivityCodes(null);
		iecActivityForm.setIecActivityDescription(null);
		iecActivityForm.setIecActivityName(null);

	}


	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("Unspecified____________IEC Activity");
		this.setAttrib(request);
		IecActivityForm iecActivityForm = (IecActivityForm)form;
		refreshIecActivityForm(iecActivityForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "ttttttttttt");
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "iecActivityId");

	}

}
