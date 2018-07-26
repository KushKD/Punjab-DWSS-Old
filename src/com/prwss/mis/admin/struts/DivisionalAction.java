package com.prwss.mis.admin.struts;

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

import com.prwss.mis.admin.divisional.dao.DivisionalBO;
import com.prwss.mis.admin.divisional.dao.DivisionalBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.district.dao.DistrictDao;

public class DivisionalAction extends DispatchAction {
	
	private DivisionalBO divisionalBO;
	private MISSessionBean misAuditBean = new MISSessionBean();
	private DistrictDao districtDao;

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	public void setDivisionalBO(DivisionalBO divisionalBO) {
		this.divisionalBO = divisionalBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	/*	this.setAttrib(request);
		request.setAttribute("level2", "true");
		DivisionalForm divisionalform = (DivisionalForm)form;
		System.out.println(divisionalform.getAddress1());
		
		return mapping.findForward("display");*/
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
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		DivisionalForm divisionalform = (DivisionalForm)form;
		List<DivisionalBean> divisionalList = null;
		System.out.println(divisionalform);
		divisionalList = divisionalBO.findDivisional(divisionalform, statusList);
		System.out.println(divisionalList);
		
		if(!MisUtility.ifEmpty(divisionalList)){
			request.setAttribute("divisionalList", divisionalList);
			}else{
				//refershZoneForm(zoneform);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		
	} catch (Exception e) {
		log.error(e);
		e.printStackTrace();
	}
	return mapping.findForward("display");
	}
	
	private Set<LabelValueBean> getDistrictIds(HttpServletRequest request) {
		System.out.println("in getdistricts");
		Set<LabelValueBean> districts = null;
		Set<DistrictBean> districtIds = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);		
		try {
			districtIds = districtDao.getDistinctDistrictCodes(statusList);
			districts = new HashSet<LabelValueBean>();
			for (DistrictBean districtId : districtIds) {
				districts.add(new LabelValueBean(districtId.getDistrictId() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ districtId.getDistrictName(), districtId.getDistrictId()));

			}
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("before setting");
		return districts;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	/*	DivisionalForm divisionalform = (DivisionalForm)form;
		System.out.println(divisionalform.getPinId());
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");*/
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		DivisionalForm divisionalform = (DivisionalForm)form;
		boolean status = divisionalBO.deleteDivisional(divisionalform, misAuditBean);
		log.debug("Divisional delete" + status);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.delete","Divisional Id ---->"+divisionalform.getDivisionalId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.delete","Divisional Id ---->"+divisionalform.getDivisionalId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");*/
		
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

		DivisionalForm divisionalform = (DivisionalForm)form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		boolean status = divisionalBO.updateDivisional(divisionalform, misAuditBean,statusList);
		log.debug("Divisional update" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Divisional Id ---->"+divisionalform.getDivisionalId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Divisional Id ---->"+divisionalform.getDivisionalId());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshDivisionalForm(divisionalform);
		System.out.println("modify :" + request.getParameter("d__mode"));
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		
		return mapping.findForward("display");
	}
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		DivisionalForm divisionalform = (DivisionalForm)form;
		try{
		boolean status = divisionalBO.postDivisional(divisionalform, misAuditBean);		
		log.debug(status);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.post","Divisional Id ---->"+divisionalform.getDivisionalId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.post","Divisional Id ---->"+divisionalform.getDivisionalId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
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
		DivisionalForm divisionalform = (DivisionalForm)form;
		boolean status = false;
		try {
			status = divisionalBO.saveDivisional(divisionalform,misAuditBean);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","Divisional Id ---->"+divisionalform.getDivisionalId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save","Divisional Id ---->"+divisionalform.getDivisionalId());
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
				ActionMessage message = new ActionMessage("fatal.error.save","Divisional Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
			if (request.getSession().getAttribute("districts") != null) {
				getDistrictIds(request);
			}
			
		
		log.debug(status);
		
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*this.setAttrib(request);
		
		System.out.println("Unspecified........Divisional");
		return mapping.findForward("display");*/
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		Set<LabelValueBean> districts = getDistrictIds(request);
		request.getSession().setAttribute("districts", districts);		
		DivisionalForm divisionalform = (DivisionalForm)form;
		refreshDivisionalForm(divisionalform);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "districtId@divisionalId");
		request.setAttribute("d__auto", "divisionalId");
	}
	
	private void refreshDivisionalForm(DivisionalForm divisionalForm) {
		divisionalForm.setDivisionalId("");
		divisionalForm.setDivisionalName("");
		divisionalForm.setDivisionalIds(null);
		divisionalForm.setDistrictId("");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String divisionalId = request.getParameter("divisionalId");
		DivisionalForm divisionalform = (DivisionalForm)form;		
		divisionalform.setDivisionalId(divisionalId);
		List<DivisionalBean> divisionalList = divisionalBO.findDivisional(divisionalform, null);

		if (divisionalList == null) {
			log.error("districtBeanList is\t" + divisionalList);
		}

		for (DivisionalBean divisionalBean : divisionalList) {
			if (divisionalId.equalsIgnoreCase(divisionalBean.getDivisionalId())) {

				request.setAttribute("divisionalBean", divisionalBean);
			}
			
		}

		if (request.getSession().getAttribute("districts") != null) {

			getDistrictIds(request);

		}
		
		return mapping.findForward("display");
	}
}