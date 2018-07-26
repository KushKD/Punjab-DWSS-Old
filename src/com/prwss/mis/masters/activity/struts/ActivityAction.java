package com.prwss.mis.masters.activity.struts;

import java.io.PrintWriter;
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
import com.prwss.mis.masters.activity.ActivityBO;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;

public class ActivityAction extends DispatchAction {
	
	private ComponentDao componentDao;
	private SubComponentDao subComponentDao;
	private ActivityBO activityBO;
	private MISSessionBean misAuditBean = new MISSessionBean();
	
	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}

	public void setActivityBO(ActivityBO activityBO) {
		this.activityBO = activityBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//step 1:set toolbar *****************************************************
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		ActivityForm activityForm = (ActivityForm)form;
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
		//step 2:get get components values*****************************************************
//		Set<LabelValueBean> components = getComponents();
		//step 3: set component values to session *****************************************************
		if (request.getSession().getAttribute("components") == null) {
			System.out.println("--------HI "+request.getSession().getAttribute("components"));
			request.getSession().setAttribute("components",getComponents());}
		if (request.getSession().getAttribute("subComponents") == null) 
			request.getSession().setAttribute("subComponents",getSubComponents(activityForm.getComponentId()));
		//step 4: create display tag list*****************************************************
		
		try {
			List<ActivityBean> activityList = activityBO.findActivity(activityForm, statusList);
			
			if(!MisUtility.ifEmpty(activityList)){
				request.setAttribute("activityList", activityList);
			}else{
				refreshActivityForm(activityForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		//step 5: set display tag list to request*****************************************************
				return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		boolean status=false;
		this.setAttrib(request);
		ActivityForm activityForm = (ActivityForm)form;
		try {
			request.getSession().setAttribute("components",getComponents());
			request.setAttribute("level2","true");
			status=activityBO.deleteActivity(activityForm, misAuditBean);
			if(status) {
				ActionMessages actionErrors = new ActionMessages();
				ActionMessage actionMessage = new ActionMessage("success.delete","Activity ", activityForm.getActivityIds());
				actionErrors.add(ActionErrors.GLOBAL_MESSAGE, actionMessage);
				saveMessages(request, actionErrors);			
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshActivityForm(activityForm);
		return mapping.findForward("display");
	}
	public ActionForward post(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws MISException {
		boolean status=false;
		this.setAttrib(request);
		ActivityForm activityForm = (ActivityForm)form;
		try {
			request.getSession().setAttribute("components",getComponents());
			request.setAttribute("level2","true");
			status=activityBO.postActivity(activityForm, misAuditBean);
			if(status) {
				ActionMessages actionErrors = new ActionMessages();
				ActionMessage actionMessage = new ActionMessage("success.post","Activity ", activityForm.getActivityIds());
				actionErrors.add(ActionErrors.GLOBAL_MESSAGE, actionMessage);
				saveMessages(request, actionErrors);			
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshActivityForm(activityForm);
		return mapping.findForward("display");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		boolean status=false;
		this.setAttrib(request);
		ActivityForm activityForm = (ActivityForm)form;
		try {
			request.getSession().setAttribute("components",getComponents());
			String mode = (String) request.getParameter("d__mode");
			List<String> statusList = new ArrayList<String>();
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			status=activityBO.updateActivity(activityForm, misAuditBean,statusList);
			if(status) {
				ActionMessages actionErrors = new ActionMessages();
				ActionMessage actionMessage = new ActionMessage("success.update","Activity ", activityForm.getActivityId());
				actionErrors.add(ActionErrors.GLOBAL_MESSAGE, actionMessage);
				saveMessages(request, actionErrors);			
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Update of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshActivityForm(activityForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean status=false;
		this.setAttrib(request);
		request.getSession().setAttribute("components",getComponents());
		request.setAttribute("subComponents",getSubComponents(null));
		request.setAttribute("level2","true");
		ActivityForm activityForm = (ActivityForm)form;
		try{
		status=activityBO.saveActivity(activityForm, misAuditBean);
		if(status) {
			ActionMessages actionErrors = new ActionMessages();
			ActionMessage actionMessage = new ActionMessage("success.save","Activity ", activityForm.getActivityId());
			actionErrors.add(ActionErrors.GLOBAL_MESSAGE, actionMessage);
			saveMessages(request, actionErrors);			
		}
		}
		catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Save of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Save of Activity Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshActivityForm(activityForm);
		return mapping.findForward("display");
	}
	
	public ActionForward getSubComponents(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String componentId = request.getParameter("componentId");
			Set<SubComponentBean> subComponentBeans = subComponentDao.getDistinctSubComponentCodes(componentId);
			StringBuffer buffer = new StringBuffer();
			log.debug("subComponentBeans\t"+subComponentBeans);
			for(SubComponentBean subComponent: subComponentBeans){
				buffer.append("<option value=\"").append(subComponent.getSubComponentId()).append("\">");
				buffer.append(subComponent.getSubComponentId()).append(MISConstants.LABEL_VALUE_BEAN_SEPARATOR).append(subComponent.getSubComponentName());
				buffer.append("</option>");
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				//out.print(buffer.substring(0, buffer.length() - 1));
				out.print(buffer.toString());
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);		
		request.setAttribute("components", getComponents());
		request.setAttribute("subComponents",getSubComponents(null));
		ActivityForm activityForm = (ActivityForm)form;
		refreshActivityForm(activityForm);
		return mapping.findForward("display");
	}
//	public ActionForward populate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws MISException {
//		try {
//			String activityId = request.getParameter("activityId");
//			this.setAttrib(request);
//			request.setAttribute("level2","true");
//			ActivityForm activityForm = (ActivityForm)form;
//			activityForm.setActivityId(activityId);
//			List<ActivityBean> activityBeanList = activityBO.findActivity(activityForm, null);
//			if(activityBeanList == null){
//				log.error("activityBeanList is\t"+activityBeanList);
//			}
//			
//			PrintWriter out = response.getWriter();
//			for (ActivityBean activityBean : activityBeanList) {
//				if(activityId.equalsIgnoreCase(activityBean.getActivityId())){
//					"~subComponentId@!"+activityBean.getSubComponent().getSubComponentId()+
//					"~activityId@!"+activityBean.getActivityId()+
//					"~activityName@!"+activityBean.getActivityName()+
//					"~activityDescription@!"+activityBean.getActivityDescription());
//				out.println("componentId@!"+activityBean.getComponent().getComponentId()+
//						"~subComponentId@!"+activityBean.getSubComponent().getSubComponentId()+
//						"~activityId@!"+activityBean.getActivityId()+
//						"~activityName@!"+activityBean.getActivityName()+
//						"~activityDescription@!"+activityBean.getActivityDescription());
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		return null;
//	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String activityId = request.getParameter("activityId");
		ActivityForm activityForm = (ActivityForm)form;
		activityForm.setActivityId(activityId);
		try {
			List<ActivityBean> activityBeans = activityBO.findActivity(activityForm, null);

			if(!MisUtility.ifEmpty(activityBeans)){
				for (ActivityBean bean : activityBeans) {
					activityForm.setActivityId(bean.getActivityId());
					activityForm.setActivityDescription(bean.getActivityDescription());
					activityForm.setActivityName(bean.getActivityName());
					activityForm.setComponentId(bean.getComponent().getComponentId());
					request.setAttribute("subComponentId", bean.getSubComponent().getSubComponentId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "componentId@subComponentId@activityId");
	}

	private Set<LabelValueBean> getSubComponents(String componentId){
		Set<LabelValueBean> subComponents = null;
		try {
			Set<SubComponentBean> subComponentCodes = subComponentDao.getDistinctSubComponentCodes(componentId);
			subComponents = new  HashSet<LabelValueBean>();
			log.debug("sub componentCodes"+subComponentCodes);
			for(SubComponentBean subComponent: subComponentCodes){
				subComponents.add( new LabelValueBean(subComponent.getSubComponentId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+subComponent.getSubComponentName(),subComponent.getSubComponentId()));	
			}
			
		} catch (DataAccessException e) {
			log.error(e);
		}
		return subComponents;
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
	private void refreshActivityForm(ActivityForm activityForm){
		activityForm.setActivityDescription(null);
		activityForm.setActivityId(null);
		activityForm.setActivityName(null);
		activityForm.setComponentId(null);
	}
}
