package com.prwss.mis.masters.circle.struts;

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
import com.prwss.mis.masters.circle.CircleBO;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.zone.dao.ZoneBean;
import com.prwss.mis.masters.zone.dao.ZoneDao;

public class CircleAction extends DispatchAction {
	private CircleBO circleBO;
	private ZoneDao zoneDao;

	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	private MISSessionBean misAuditBean = new MISSessionBean();

	public void setCircleBO(CircleBO circleBO) {
		this.circleBO = circleBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		System.out.println("find :" + request.getParameter("d__mode"));
		try {
			CircleForm circleForm = (CircleForm) form;
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
			if (request.getSession().getAttribute("zones") != null) {
				getZoneIds(request);
			}
			List<CircleBean> circleBeanList = null;
			System.out.println(circleForm);
			circleBeanList = circleBO.findCircle(circleForm, statusList);
			System.out.println(circleBeanList);
			if(!MisUtility.ifEmpty(circleBeanList)){
			request.setAttribute("circleBeanList", circleBeanList);
			}
			else{
				refreshCircleForm(circleForm);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		CircleForm circleForm = (CircleForm) form;
		boolean status = circleBO.deleteCircle(circleForm, misAuditBean);
		log.debug("Circle delete" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Circle Id ---->"+circleForm.getCircleId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Circle Id ---->"+circleForm.getCircleId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Circle Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Circle Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		if (request.getSession().getAttribute("zones") != null) {
			getZoneIds(request);
		}
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		CircleForm circleForm = (CircleForm) form;
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		
		status = circleBO.updateCircle(circleForm, misAuditBean,statusList);
		if (request.getSession().getAttribute("zones") != null) {

			getZoneIds(request);

		}
		if (status){
			ActionMessages msg = new ActionMessages();
			ActionMessage message = new ActionMessage("success.update", "Circle Master","Circle Id -->"+circleForm.getCircleId());
			msg.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, msg);

		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Circle Master ","Circle Id -->"+circleForm.getCircleId());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	} catch (MISException e) {
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
		ActionMessage message = new ActionMessage("fatal.error.save","Circle Master ");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
		log.error(e);
		e.printStackTrace();
	}
		log.debug("Circle update" + status);
		refreshCircleForm(circleForm);
		System.out.println("modify :" + request.getParameter("d__mode"));		
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String circleId = request.getParameter("circleId");
		CircleForm circleForm = (CircleForm) form;
		circleForm.setCircleId(circleId);
		List<CircleBean> circleBeanList = circleBO.findCircle(circleForm, null);

		if (circleBeanList == null) {
			log.error("circleBeanList is\t" + circleBeanList);
		}

		for (CircleBean circleBean : circleBeanList) {
			if (circleId.equalsIgnoreCase(circleBean.getCircleId())) {

				request.setAttribute("circleBean", circleBean);
			}
		}

		
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		boolean status = false;
		CircleForm circleForm = (CircleForm) form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
			status = circleBO.saveCircle(circleForm, misAuditBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Circle Master","Circle Id -->"+circleForm.getCircleId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Circle Master ","Circle Id -->"+circleForm.getCircleId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
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
			ActionMessage message = new ActionMessage("fatal.error.save","Circle Master ");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		if (request.getSession().getAttribute("zones") != null) {

			getZoneIds(request);

		}
		refreshCircleForm(circleForm);
		return mapping.findForward("display");
	}

	
	private List<LabelValueBean> getZoneIds(HttpServletRequest request) {
		System.out.println("in getzone");
		List<LabelValueBean> zones = null;
		List<ZoneBean> zoneCodes = null;
		ZoneBean bean = new ZoneBean();
		bean.setZoneId("");
		bean.setZoneName("");
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			zoneCodes =  zoneDao.findZone(bean, statusList);
			zones = new ArrayList<LabelValueBean>();
			for (ZoneBean zoneCode : zoneCodes) {
				zones.add(new LabelValueBean(zoneCode.getZoneId() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ zoneCode.getZoneName(), zoneCode.getZoneId()));

			}
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("before setting");
		
		return zones;
	}

	private void refreshCircleForm(CircleForm circleForm) {
		circleForm.setCircleId("");
		circleForm.setCircleName("");
		circleForm.setCircleIds(null);
	}
	
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
		CircleForm circleForm = (CircleForm) form;
		boolean status = circleBO.postCircle(circleForm, misAuditBean);
		log.debug(status);
		if (status){
			ActionMessages msg = new ActionMessages();
			ActionMessage message = new ActionMessage("success.update", "Circle Master","Circle Id -->"+circleForm.getCircleId());
			msg.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, msg);

		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Circle Master ","Circle Id -->"+circleForm.getCircleId());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Circle Master ");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
	} catch (Exception e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Circle Master ");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
		log.error(e);
		e.printStackTrace();
	}
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		List<LabelValueBean> zones = getZoneIds(request);
		request.getSession().setAttribute("zones", zones);
		CircleForm circleForm = (CircleForm)form;
		refreshCircleForm(circleForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
	
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "circleId");
		request.setAttribute("d__auto", "circleId");
	}


}
