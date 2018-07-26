/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.masters.district.struts;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.circle.dao.CircleDao;
import com.prwss.mis.masters.district.DistrictBO;
import com.prwss.mis.masters.district.dao.DistrictBean;

/**
 * The Class DistrictAction.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.masters.district.struts
 * @file : DistrictAction.java
 * @purpose : This is the UI controller class for district.
 */
public class DistrictAction extends DispatchAction {
	
	/** the district business object. */
	private DistrictBO districtBO;
	
	/** the mis audit bean. */
	private MISSessionBean misAuditBean = new MISSessionBean();
	
	/** the circle dao. */
	private CircleDao circleDao;

	/**
	 * Sets the circle dao.
	 * 
	 * @param circleDao
	 *            the new circle dao
	 */
	public void setCircleDao(CircleDao circleDao) {
		this.circleDao = circleDao;
	}

	/**
	 * Sets the district business object.
	 * 
	 * @param districtBO
	 *            the new district business object
	 */
	public void setDistrictBO(DistrictBO districtBO) {
		this.districtBO = districtBO;
	}

	/**
	 * This method is used to find ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
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
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		request.setAttribute("level2", "true");
		System.out.println("find :" + request.getParameter("d__mode"));
		if (request.getSession().getAttribute("circles") != null) {
			getCircleIds(request);
		}
		DistrictForm districtform = (DistrictForm)form;
		List<DistrictBean> districtList = null;
		System.out.println(districtform);
		districtList = districtBO.findDistrict(districtform, statusList);
		System.out.println(districtList);
		if(!MisUtility.ifEmpty(districtList)){
			request.setAttribute("districtList", districtList);
				}
			else{
				refreshDistrictForm(districtform);
				ActionErrors messages= new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode","");
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, messages);		
			}
	} catch (Exception e) {
		log.error(e);
		e.printStackTrace();
	}
	return mapping.findForward("display");
	}
	
	/**
	 * Gets the circle ids.
	 * 
	 * @param request
	 *            the HTTP Request
	 * @return the circle ids
	 */
	private Set<LabelValueBean> getCircleIds(HttpServletRequest request) {
		System.out.println("in getcircles");
		Set<LabelValueBean> circles = null;
		Set<CircleBean> circleCodes = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			circleCodes = circleDao.getDistinctCircleCodes(statusList);
			circles = new HashSet<LabelValueBean>();
			for (CircleBean circleCode : circleCodes) {
				circles.add(new LabelValueBean(circleCode.getCircleId() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ circleCode.getCircleName(), circleCode.getCircleId()));

			}
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("before setting");
		return circles;
	}

	/**
	 * This method is used to delete ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		DistrictForm districtform = null;
		boolean status = false;
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
			
		
		districtform = (DistrictForm)form;
		status = districtBO.deleteDistrict(districtform, misAuditBean);
		log.debug("District delete" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","District Id ---->"+districtform.getDistrictCode());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","District Id ---->"+districtform.getDistrictCode());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		 
		if (request.getSession().getAttribute("circles") != null) {
			getCircleIds(request);
		}
		
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to update ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}

		DistrictForm districtform = (DistrictForm)form;
		try{
		boolean status = districtBO.updateDistrict(districtform, misAuditBean,statusList);
		log.debug("District update" + status);
				if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.update","District Id ---->"+districtform.getDistrictCode());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.update","District Id ---->"+districtform.getDistrictCode());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);				
				}
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		refreshDistrictForm(districtform);
		System.out.println("modify :" + request.getParameter("d__mode"));
		if (request.getSession().getAttribute("circles") != null) {
			getCircleIds(request);
		}
		
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to post ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
		DistrictForm districtform = (DistrictForm)form;
		boolean status = districtBO.postDistrict(districtform, misAuditBean);
		log.debug(status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","District Id ---->"+districtform.getDistrictCode());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","District Id ---->"+districtform.getDistrictCode());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		 
	
		return mapping.findForward("display");
	}

	/**
	 * This method is used to save ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		DistrictForm districtform = (DistrictForm)form;
		System.out.println(districtform.getIsSpmc());
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = districtBO.saveDistrict(districtform,misAuditBean);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","District Name ---->"+districtform.getDistrictName());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save","District Name ---->"+districtform.getDistrictName());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);				
				}
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","District Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		if (request.getSession().getAttribute("circles") != null) {
			getCircleIds(request);
		}
		
		log.debug(status);
		
		return mapping.findForward("display");
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		Set<LabelValueBean> circles = getCircleIds(request);
		request.getSession().setAttribute("circles", circles);
		
		DistrictForm districtform = (DistrictForm)form;
		refreshDistrictForm(districtform);
		return mapping.findForward("display");
	}

	/**
	 * Sets the attrib.
	 * 
	 * @param request
	 *            the new attrib
	 */
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "circleCode@districtCode");
		request.setAttribute("d__auto", "districtCode");
	}
	
	/**
	 * Refresh district action form.
	 * 
	 * @param districtForm
	 *            the district action form
	 */
	private void refreshDistrictForm(DistrictForm districtForm) {
		districtForm.setDistrictCode("");
		districtForm.setDistrictName("");
		districtForm.setDistrictCodes(null);
		districtForm.setCircleCode("");
	}

	/**
	 * This method is used to populate ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String distristCode = request.getParameter("districtCode");
		DistrictForm districtform = (DistrictForm)form;		
		districtform.setDistrictCode(distristCode);
		List<DistrictBean> districtList = districtBO.findDistrict(districtform, null);
		if (districtList == null) {
			log.error("districtList is\t" + districtList);
		}

		for (DistrictBean districtBean : districtList) {
			if (distristCode.equalsIgnoreCase(districtBean.getDistrictId())) {

				request.setAttribute("districtBean", districtBean);
			}
			
		}

		if (request.getSession().getAttribute("circles") != null) {

			getCircleIds(request);

		}
		
		return mapping.findForward("display");
	}
	
	


}
