package com.prwss.mis.masters.vendor.struts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.vendor.dao.BlackListVendorBean;
import com.prwss.mis.masters.vendor.dao.BlackListVendorDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import com.prwss.mis.procurement.plan.struts.CreateProcPlanAction;
import com.prwss.mis.procurement.plan.struts.CreateProcPlanForm;

public class BlackListVendorAction extends DispatchAction {
	Logger log = Logger.getLogger(CreateProcPlanAction.class);
	private MISSessionBean misSessionBean;
	private BlackListVendorDao blackListVendorDao;
	private VendorDao vendorDao;
	
	public void setBlackListVendorDao(BlackListVendorDao blackListVendorDao) {
		this.blackListVendorDao = blackListVendorDao;
	}
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find");
		if (request.getSession().getAttribute("misSessionBean") != null) {
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
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
		this.setAttrib(request);
		try {
			BlackListVendorForm blackListVendorForm=(BlackListVendorForm)form;
			BlackListVendorBean blackListVendorBean=new BlackListVendorBean();
			blackListVendorBean.setVendorId(blackListVendorForm.getVendorId());
			
			List<BlackListVendorBean> blackListVendorBeans = null;
			blackListVendorBeans = blackListVendorDao.findBlackListVendor(blackListVendorBean, statusList);
			if(!MisUtility.ifEmpty(blackListVendorBeans)){
				for (BlackListVendorBean blackListVendorBean2 : blackListVendorBeans) {
					blackListVendorForm.setVendorId(blackListVendorBean2.getVendorId());
					blackListVendorForm.setReason(blackListVendorBean2.getReason());
					blackListVendorForm.setBlackListFrom(MisUtility.convertDateToString(blackListVendorBean2.getBlackListFrom()));
					blackListVendorForm.setBlackListTo(MisUtility.convertDateToString(blackListVendorBean2.getBlackListTo()));
				}
			}else{
				refreshBlackListForm(blackListVendorForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Blacklist Vendor");
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

		BlackListVendorForm blackListVendorForm=(BlackListVendorForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			BlackListVendorBean blackListVendorBean=new BlackListVendorBean();
			blackListVendorBean.setVendorId(blackListVendorForm.getVendorId());
			blackListVendorBean.setReason(blackListVendorForm.getReason());
			blackListVendorBean.setBlackListFrom(MisUtility.convertStringToDate(blackListVendorForm.getBlackListFrom()));
			blackListVendorBean.setBlackListTo(MisUtility.convertStringToDate(blackListVendorForm.getBlackListTo()));
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			blackListVendorBean.setMisAuditBean(misAuditBean);
			status = blackListVendorDao.saveBlackListVendor(blackListVendorBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Blacklist Vendor"," Id-->"+blackListVendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Saving failed for Vendor "," Id-->"+blackListVendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Blacklist Vendor");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshBlackListForm(blackListVendorForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws MISException {
	this.setAttrib(request);
	BlackListVendorForm blackListVendorForm=(BlackListVendorForm)form;
	boolean status = false;
	try {
		if (request.getSession().getAttribute("misSessionBean") != null) {
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		BlackListVendorBean blackListVendorBean=new BlackListVendorBean();
		blackListVendorBean.setVendorId(blackListVendorForm.getVendorId());
		blackListVendorBean.setReason(blackListVendorForm.getReason());
		blackListVendorBean.setBlackListFrom(MisUtility.convertStringToDate(blackListVendorForm.getBlackListFrom()));
		blackListVendorBean.setBlackListTo(MisUtility.convertStringToDate(blackListVendorForm.getBlackListTo()));
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
		blackListVendorBean.setMisAuditBean(misAuditBean);
		status = blackListVendorDao.updateBlackListVendor(blackListVendorBean); 
		if (status){
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.update","Black List ","Vendor Name-->"+blackListVendorForm.getVendorName());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);

		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Black List ","Vendor Name -->"+blackListVendorForm.getVendorName());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Updation of Blacklist Vendor");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
		
	}
	refreshBlackListForm(blackListVendorForm);
	return mapping.findForward("display");
}
	
		
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "vendorId@vendorName");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		BlackListVendorForm blackListVendorForm=(BlackListVendorForm)form;
		System.out.println("IN UNSPCIFIED PROC PLAN");
		if (request.getSession().getAttribute("misSessionBean") != null) {
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			Set<LabelValueBean> vendorIds = getVendorIds();
			request.getSession().setAttribute("vendorIds", vendorIds);
			refreshBlackListForm(blackListVendorForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	private Set<LabelValueBean> getVendorIds(){
		Set<LabelValueBean> vendorIds = null;
		Set<VendorBean> vendorBeans = null;
		try{
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			vendorBeans = vendorDao.getDistinctVendorCodes();
			vendorIds = new TreeSet<LabelValueBean>();
			for (VendorBean vendorBean1 : vendorBeans) {
				vendorIds.add(new LabelValueBean(vendorBean1.getVendorName()+" - ("+vendorBean1.getVendorId()+")",vendorBean1.getVendorId()));
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return vendorIds;	
	}
	private void refreshBlackListForm(BlackListVendorForm blackListVendorForm){
		blackListVendorForm.setVendorId(null); 
		blackListVendorForm.setVendorName(null);
		blackListVendorForm.setBlackListFrom(null);
		blackListVendorForm.setBlackListTo(null);
		blackListVendorForm.setReason(null);
	}
}
