/**
 * 
 */
package com.prwss.mis.masters.subcomponent.struts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.subcomponent.SubComponentBO;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;

/**
 * @author vgadiraju
 *
 */
public class SubComponentAction extends DispatchAction {
	
	private SubComponentBO subComponentBO;
	private ComponentDao componentDao;
	MISSessionBean misAuditBean = new MISSessionBean();
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		System.out.println(mode);
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
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			if (request.getSession().getAttribute("components") == null) 
				request.getSession().setAttribute("components",this.getComponents());		
			
			SubComponentForm subComponent = (SubComponentForm)form;
			List<SubComponentBean> subComponentList = subComponentBO.findSubComponent(subComponent, statusList);
			if(!MisUtility.ifEmpty(subComponentList)){
			request.setAttribute("subComponentList", subComponentList);
			}else{
				refreshSubComponentForm(subComponent);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		boolean status=false;
		this.setAttrib(request);
		if (request.getSession().getAttribute("components") == null) 
			request.getSession().setAttribute("components",this.getComponents());
		SubComponentForm subComponentForm = (SubComponentForm) form;
		if(log.isDebugEnabled()){
			for (String subComponentIds : subComponentForm.getSubComponentIds()) {
				log.debug("subComponentIds\t"+subComponentIds);
			}
		}
		try{
		status = subComponentBO.deleteSubComponent(subComponentForm, misAuditBean);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		 
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.getSession().setAttribute("components",this.getComponents());
		request.setAttribute("level2","true");
		SubComponentForm subComponentForm = (SubComponentForm)form;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);	
		try{
		boolean status=subComponentBO.saveSubComponent(subComponentForm, misAuditBean,statusList);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Component Id ---->"+subComponentForm.getComponentId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Component Id ---->"+subComponentForm.getComponentId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshSubComponentForm(subComponentForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response) 
	throws MISException {
		this.setAttrib(request);
		if (request.getSession().getAttribute("components") == null) 
			request.getSession().setAttribute("components",this.getComponents());
		SubComponentForm subComponentForm = (SubComponentForm)form;
		log.debug("SubComponentBean from the request"+subComponentForm);
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		try{
		boolean status=subComponentBO.saveSubComponent(subComponentForm, misAuditBean,statusList);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshSubComponentForm(subComponentForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		if (request.getSession().getAttribute("components") == null) 
			request.getSession().setAttribute("components",this.getComponents());
		SubComponentForm subComponentForm = (SubComponentForm) form;
		if(log.isDebugEnabled()){
			for (String subComponentIds : subComponentForm.getSubComponentIds()) {
				log.debug("subComponentIds\t"+subComponentIds);
			}
		}
		try{
		boolean status = subComponentBO.postSubComponent(subComponentForm, misAuditBean);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","SubComponent Id ---->"+subComponentForm.getSubComponentId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubComponent Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshSubComponentForm(subComponentForm);
		log.debug("update");
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.getSession().setAttribute("components",this.getComponents());
		SubComponentForm subComponentForm = (SubComponentForm)form;
		refreshSubComponentForm(subComponentForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "componentId@subComponentId");		
	}
//	public ActionForward populate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws MISException {
//		try {
//			String subComponentId = request.getParameter("subComponentId");
//			this.setAttrib(request);
//			request.setAttribute("level2","true");
//			SubComponentForm subComponentForm = (SubComponentForm)form;
//			subComponentForm.setSubComponentId(subComponentId);
//			List<SubComponentBean> subComponentBeanList = subComponentBO.findSubComponent(subComponentForm, null);
//			if(subComponentBeanList == null){
//				log.error("subComponentBeanList is\t"+subComponentBeanList);
//			}
//			PrintWriter out = response.getWriter();
//			for (SubComponentBean subComponentBean : subComponentBeanList) {
//				if(subComponentId.equalsIgnoreCase(subComponentBean.getSubComponentId())){
////				request.setAttribute("subComponentBean", subComponentBean);
//					//System.out.println("componentId@!"+subComponentBean.getComponentBean().getComponentId()+"subComponentId@!"+subComponentBean.getSubComponentId() + "~"+"subComponentName@!"+subComponentBean.getSubComponentName()+"~"+"subComponentDescription@!"+subComponentBean.getSubComponentDescription());
//				out.println("componentId@!"+subComponentBean.getComponentBean().getComponentId()+"~"+"subComponentId@!"+
//							subComponentBean.getSubComponentId() + "~"+"subComponentName@!"+subComponentBean.getSubComponentName()+ 
//							"~"+"subComponentDescription@!"+subComponentBean.getSubComponentDescription());
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		return null;
//	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String subComponentId = request.getParameter("subComponentId");
		SubComponentForm subComponentForm = (SubComponentForm)form;
		subComponentForm.setSubComponentId(subComponentId);
		try {
			List<SubComponentBean> subComponentBeans = subComponentBO.findSubComponent(subComponentForm, null);

			if(!MisUtility.ifEmpty(subComponentBeans)){
				for (SubComponentBean bean : subComponentBeans) {
					subComponentForm.setComponentId(bean.getComponentBean().getComponentId());
					subComponentForm.setSubComponentId(bean.getSubComponentId());
					subComponentForm.setSubComponentName(bean.getSubComponentName());
					subComponentForm.setSubComponentDescription(bean.getSubComponentDescription());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	


	public void setSubComponentBO(SubComponentBO subComponentBO) {
		this.subComponentBO = subComponentBO;
	}

	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	private Set<LabelValueBean> getComponents(){
		Set<LabelValueBean> components = null;
		try {
			Set<ComponentBean> componentCodes = componentDao.getDistinctComponentCodes();
			components = new  HashSet<LabelValueBean>();
			log.debug("componentCodes"+componentCodes);
			for(ComponentBean component: componentCodes){
				components.add( new LabelValueBean(component.getComponentId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+component.getComponentName(),component.getComponentId()));	
			}
			
		} catch (DataAccessException e) {
			log.error(e);
		}
		return components;
	}
	private void refreshSubComponentForm(SubComponentForm subComponentForm){
		subComponentForm.setComponentId(null);
		subComponentForm.setSubComponentIds(null);
		subComponentForm.setSubComponentName(null);
		subComponentForm.setSubComponentDescription(null);
	}
}
