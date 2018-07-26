package com.prwss.mis.masters.vendor.struts;

import java.math.BigDecimal;
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
import com.prwss.mis.masters.vendor.VendorBO;
import com.prwss.mis.masters.vendor.dao.VendorBean;

public class VendorAction extends DispatchAction {

	private VendorBO vendorBO;
	private MISSessionBean misSessionBean;

	public void setVendorBO(VendorBO vendorBO) {
		this.vendorBO = vendorBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		System.out.println("In Find");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
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
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		VendorForm vendorForm = (VendorForm)form;
		List<VendorBean> vendorBeans = null;
		vendorBeans = vendorBO.findVendor(vendorForm, statusList);
		if(!MisUtility.ifEmpty(vendorBeans)){
//			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
//			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
		request.setAttribute("vendorBeanList", vendorBeans);
		ActionMessages errors = new ActionMessages();
		ActionMessage message = new ActionMessage("find.list");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, errors);
		}else{
			refreshVendorForm(vendorForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		VendorForm vendorForm = (VendorForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = vendorBO.deleteVendor(vendorForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVendorForm(vendorForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		VendorForm vendorForm = (VendorForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(vendorForm.getVendorName())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			String mode = (String) request.getParameter("d__mode");
			List<String> statusList = new ArrayList<String>();
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			status = vendorBO.updateVendor(vendorForm, misSessionBean,statusList);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS004.equals(e.getCode())){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVendorForm(vendorForm);
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.setAttrib(request);
		VendorForm vendorForm = (VendorForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = vendorBO.postVendor(vendorForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post", "Vendor Details","Vendor Code -->"+vendorForm.getVendorId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			refreshVendorForm(vendorForm);
		
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		VendorForm vendorForm = (VendorForm) form;
		String vendorId = request.getParameter("vendorId");
		vendorForm.setVendorId(vendorId);
		List<VendorBean> vendorBeanList = vendorBO.findVendor(vendorForm, null);
		
		if (vendorBeanList == null) {
			log.error("vendorBeanList is\t" + vendorBeanList);
		}

		for (VendorBean vendorBean : vendorBeanList) {
				vendorForm.setAddress1(vendorBean.getAddress1());
				vendorForm.setAddress2(vendorBean.getAddress2());
				vendorForm.setCity(vendorBean.getCity());
				vendorForm.setEmail(vendorBean.getEmail());
				vendorForm.setMobPhoneNo(vendorBean.getMobilePhone());
				vendorForm.setPanNo(vendorBean.getPanNo());
				vendorForm.setPinCode(vendorBean.getPin());
				vendorForm.setVendorId(vendorBean.getVendorId());
				vendorForm.setVendorName(vendorBean.getVendorName());
				vendorForm.setState(vendorBean.getState());
				vendorForm.setStreet(vendorBean.getStreet());
				vendorForm.setWorkPhoneNo(vendorBean.getWorkPhone());
				vendorForm.setVendorClass(vendorBean.getVendorClass());
				vendorForm.setVendorClassification(vendorBean.getVendorCategory());
				vendorForm.setRecovery(vendorBean.getRecovery());
				vendorForm.setDebarringAuthority(vendorBean.getDebarringAuthority());
				vendorForm.setEnlistmentAuthority(vendorBean.getEnlistmentAuthority());
				vendorForm.setTenderingLimit(vendorBean.getTenderingLimit());
				vendorForm.setVendorCategory(vendorBean.getVendorCategory());
			}
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		request.setAttribute("level2","true");
		VendorForm vendorForm = (VendorForm)form;
		String vendorId = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}

			System.out.println("IN VENDOR ACTION"+vendorForm.toString());
			
			if(!MisUtility.ifEmpty(vendorForm.getVendorName())|| !MisUtility.ifEmpty(vendorForm.getPanNo())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			vendorId = vendorBO.saveVendor(vendorForm, misSessionBean);
			if (vendorId != null){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Vendor Details","Vendor Code -->"+vendorId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Vendor Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Saving failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVendorForm(vendorForm);
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Unspecified........Vendor");
		this.setAttrib(request);
		try {
			VendorForm vendorForm = (VendorForm)form;
			refreshVendorForm(vendorForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "vendorId@panNo");
		request.setAttribute("d__auto", "vendorId");
	}

	private void refreshVendorForm(VendorForm vendorForm)
	{
		vendorForm.setAddress1(null);
		vendorForm.setAddress2(null);
		vendorForm.setCity(null);
		vendorForm.setEmail(null);
		vendorForm.setMobPhoneNo(null);
		vendorForm.setPanNo(null);
		vendorForm.setPinCode(0);
		vendorForm.setVendorId(null);
		vendorForm.setVendorName(null);
		vendorForm.setState(null);
		vendorForm.setStreet(null);
		vendorForm.setWorkPhoneNo(null);
		vendorForm.setVendorClass(null);
		vendorForm.setVendorClassification(null);
		vendorForm.setVendorCategory(null);
		vendorForm.setRecovery(new BigDecimal(0.00));
		vendorForm.setDebarringAuthority(null);
		vendorForm.setEnlistmentAuthority(null);
		vendorForm.setTenderingLimit(new BigDecimal(0.00));
	}

}
