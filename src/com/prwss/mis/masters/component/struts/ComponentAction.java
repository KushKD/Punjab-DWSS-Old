/**
 * 
 */
package com.prwss.mis.masters.component.struts;

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
import com.prwss.mis.common.exception.MISSessionTimeOutException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.component.ComponentBO;
import com.prwss.mis.masters.component.dao.ComponentBean;

/**
 * @author vgadiraju
 * 
 */
public class ComponentAction extends DispatchAction {

	Logger log = Logger.getLogger(this.clazz);
	private ComponentBO componentBo;
	private MISSessionBean misAuditBean = null;

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, MISSessionTimeOutException {
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
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		this.setAttrib(request);
		
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
		{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
		else
		{
			return mapping.findForward("login");
		}
			
		try {
			List<ComponentBean> componentBeanList = null;
			ComponentForm componentForm = (ComponentForm) form;
			componentBeanList = componentBo.findComponent(componentForm, statusList);
			log.debug("ComponentBean List\t" + componentBeanList);
			if(!MisUtility.ifEmpty(componentBeanList)){
//			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
//			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
				request.setAttribute("componentBeanList", componentBeanList);
			}else{
				refreshComponentForm(componentForm);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("delete:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		
		
		ComponentForm componentForm = (ComponentForm) form;
		try{
		boolean status = componentBo.deleteComponent(componentForm, misAuditBean);
		log.debug("Deleted\t" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Component Id ---->"+componentForm.getComponentId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Component Id ---->"+componentForm.getComponentId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		 
		refreshComponentForm(componentForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		request.setAttribute("level2", "true");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		
		ComponentForm componentForm = (ComponentForm) form;

		boolean status = false;
		try {
			status = componentBo.saveComponent(componentForm, misAuditBean);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Component", componentForm.getComponentName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
				System.out.println("MISExceptionCodes.MIS001:"+MISExceptionCodes.MIS001);
				System.out.println("e.getCode():"+e.getCode());
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				throw e;
			}

		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		log.debug("Component Save" + status);
		refreshComponentForm(componentForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("update:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
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
		ComponentForm componentForm = (ComponentForm) form;
		try{
				boolean status = componentBo.updateComponent(componentForm, misAuditBean,statusList);
				log.debug(status);
				if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.update","Component Id ---->"+componentForm.getComponentId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.update","Component Id ---->"+componentForm.getComponentId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		refreshComponentForm(componentForm);
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		ComponentForm componentForm = (ComponentForm) form;
		try{
		boolean status = componentBo.postComponent(componentForm, misAuditBean);
		log.debug(status);
				if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.post","Component Id ---->"+componentForm.getComponentId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.post","Component Id ---->"+componentForm.getComponentId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Component Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		refreshComponentForm(componentForm);
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String componentId = request.getParameter("componentId");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ComponentForm componentForm = (ComponentForm) form;
		componentForm.setComponentId(componentId);
		List<ComponentBean> componentBeanList = componentBo.findComponent(componentForm, null);
		if (componentBeanList == null) {
			log.error("componentBeanList is\t" + componentBeanList);
		}

		for (ComponentBean componentBean : componentBeanList) {
			if (componentId.equalsIgnoreCase(componentBean.getComponentId())) {
				System.out.println("componentBean" + componentBean);
				request.setAttribute("componentBean", componentBean);
			}
		}

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "componentId");

	}


	/**
	 * @param componentBo
	 *            the componentBo to set
	 */
	public void setComponentBO(ComponentBO componentBo) {
		this.componentBo = componentBo;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		// System.out.println("unspecified:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		ComponentForm componentForm=(ComponentForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				log.error("Login session timed out");
				return mapping.findForward("login");
			}
		refreshComponentForm(componentForm);
		return mapping.findForward("display");
	}

	private void refreshComponentForm(ComponentForm componentForm) {
		//componentForm=null;
		componentForm.setComponentDescription(null);
		componentForm.setComponentName(null);
		componentForm.setComponentIds(null);
		componentForm.setComponentId(null);
	}

}
