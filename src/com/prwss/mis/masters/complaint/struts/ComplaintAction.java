package com.prwss.mis.masters.complaint.struts;

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
import com.prwss.mis.masters.complaint.ComplaintBO;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;

public class ComplaintAction extends DispatchAction {

	private ComplaintBO complaintBO;
	private MISSessionBean misSessionBean;

	public ComplaintBO getComplaintBO() {
		return complaintBO;
	}

	public void setComplaintBO(ComplaintBO complaintBO) {
		this.complaintBO = complaintBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Your session timed out");
			return mapping.findForward("login");
		}
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
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		
		try {
			ComplaintForm complaintForm = (ComplaintForm) form;
			List<ComplaintBean> complaintBeanList = null;
			complaintBeanList = complaintBO.findComplaint(complaintForm, statusList);
			if(!MisUtility.ifEmpty(complaintBeanList)){
			request.setAttribute("complaintBeanList", complaintBeanList);
			}else{
			refreshComplaintForm(complaintForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(), e);
		e.printStackTrace();
		
	}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ComplaintForm complaintForm = (ComplaintForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				log.debug("Your session timed out");
				return mapping.findForward("login");
			}
			status = complaintBO.deleteComplaint(complaintForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Complaint","Complaint Type-->"+complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Complaint","Complaint Type-->"+complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Delete of Complaint Master");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Deletes of Plan details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshComplaintForm(complaintForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Your session timed out");
			return mapping.findForward("login");
		}
		ComplaintForm complaintForm = (ComplaintForm) form;
		boolean status = false;
		try {
			String mode = (String) request.getParameter("d__mode");
			List<String> statusList = new ArrayList<String>();
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			status = complaintBO.updateComplaint(complaintForm, misSessionBean,statusList);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Complaint","Complaint Type-->"+complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update",  "Complaint","Complaint Type-->"+complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Update of Complaint Type");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Update of Complaint Type");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshComplaintForm(complaintForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Your session timed out");
			return mapping.findForward("login");
		}
		ComplaintForm complaintForm = (ComplaintForm) form;
		System.out.println(complaintForm.getLevel1RedressalDays());
		boolean status = false;
		try {
			status = complaintBO.saveComplaint(complaintForm, misSessionBean);
			System.out.println(status);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Complaint Type",complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Complaint Type",complaintForm.getComplaintType());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} 
				log.error(e);
				e.printStackTrace();
			

		}
		catch(Exception e){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshComplaintForm(complaintForm);
		return mapping.findForward("display");
	}

	@SuppressWarnings("unused")
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try {
			Long complaintId = Long.parseLong(request.getParameter("complaintId"));
			ComplaintForm complaintForm = (ComplaintForm) form;
			complaintForm.setComplaintId(complaintId);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			List<ComplaintBean> complaintBeanList = complaintBO.findComplaint(complaintForm, statusList);
			System.out.println("----------CompalintId is " +complaintBeanList.size());	
			
			if (complaintBeanList == null) {
				log.error("complaintBeanList is\t" + complaintBeanList);
			}
			
			for (ComplaintBean complaintBean : complaintBeanList) {
					request.setAttribute("complaintBean", complaintBean);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		ComplaintForm complaintForm = (ComplaintForm) form;
		boolean status = false;
		try{
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Your session timed out");
			return mapping.findForward("login");
		}
		status = complaintBO.postComplaint(complaintForm, misSessionBean);
		if (status){
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.post",  "Complaint Type",complaintForm.getComplaintType());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);

		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.post",  "Complaint Type",complaintForm.getComplaintType());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		}  catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Update of Complaint Type");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Post of Complaint Type");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
	
		refreshComplaintForm(complaintForm);
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		System.out.println("Unspecified____________Complaint");
		ComplaintForm complaintForm = (ComplaintForm) form;
		refreshComplaintForm(complaintForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "complaintType");
	}

	private void refreshComplaintForm(ComplaintForm complaintForm) {
		complaintForm.setComplaintType(null);
		complaintForm.setComplaintIds(null);
		complaintForm.setComplaintId(null);
		complaintForm.setLevel1RedressalDays(null);
		complaintForm.setLevel1EmployeeId(null);
		complaintForm.setLevel2EmployeeId(null);
		complaintForm.setLevel2RedressalDays(null);
		complaintForm.setLevel3EmployeeId(null);
		complaintForm.setLevel3RedressalDays(null);
		complaintForm.setLevel4EmployeeId(null);
		complaintForm.setLevel4RedressalDays(null);
		complaintForm.setLevel5EmployeeId(null);
		complaintForm.setLevel5RedressalDays(null);
		complaintForm.setComplaintDescription("");

	}

}
