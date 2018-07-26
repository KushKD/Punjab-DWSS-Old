package com.prwss.mis.masters.bank.struts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.bank.BankMasterBO;
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class BankMasterAction extends DispatchAction {
	
	private BankMasterBO bankMasterBO;
	private MISSessionBean misSessionBean;
	private LocationDao locationDao;
	
	
	

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setBankMasterBO(BankMasterBO bankMasterBO) {
		this.bankMasterBO = bankMasterBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
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
		BankMasterForm bankMasterForm = (BankMasterForm)form;
		List<BankMasterBean> bankMasterBeans = null;
		if (request.getAttribute("locationIds") == null) {
			System.out.println("---------Hello"+request.getAttribute("locationIds"));
			request.setAttribute("locationIds",getLocations());
		}
		try {
			bankMasterBeans = bankMasterBO.findBank(bankMasterForm, statusList);
			if(!MisUtility.ifEmpty(bankMasterBeans)){
	//			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
	//				request.setAttribute("level2", "true");
	//			}
	//			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
	//				request.setAttribute("level2", "true");
	//			}
			request.setAttribute("bankMasterBeansList", bankMasterBeans);
			}else{
				refreshBankForm(bankMasterForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		BankMasterForm bankMasterForm = (BankMasterForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
//			if(!MisUtility.ifEmpty(bankMasterForm.getBankIds())){
//				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
//			}
			status = bankMasterBO.deleteBank(bankMasterForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Bank Information","Selected Banks");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Bank Information","Selected Banks");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("master.ids.not.selected","Deleting failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving Of Bank Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			}
		}
		refreshBankForm(bankMasterForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BankMasterForm bankMasterForm = (BankMasterForm)form;
		this.setAttrib(request);
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(bankMasterForm.getBankName())||!MisUtility.ifEmpty(bankMasterForm.getBankBranch())){
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
			status = bankMasterBO.updateBank(bankMasterForm, misSessionBean,statusList);
			if (status){
				ActionMessages errors= new ActionMessages();
				//ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.update", "Bank Information","Bank Code - "+bankMasterForm.getBankId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Bank Information","Bank Code - "+bankMasterForm.getBankId());
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
		}else{
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
		}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Vendor Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
		}
		refreshBankForm(bankMasterForm);
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		BankMasterForm bankMasterForm = (BankMasterForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
//			if(!MisUtility.ifEmpty(bankMasterForm.getBankIds())){
//				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
//			}
			status = bankMasterBO.postBank(bankMasterForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Bank Information","Selected Banks");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post", "Bank Information","Selected Banks");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("master.ids.not.selected","Posting failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving Of Bank Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving Of Bank Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
			refreshBankForm(bankMasterForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		BankMasterForm bankMasterForm = (BankMasterForm)form;
		long bankId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(bankMasterForm.getBankName()) && !MisUtility.ifEmpty(bankMasterForm.getBankBranch())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			bankId = bankMasterBO.saveBank(bankMasterForm, misSessionBean);
			if (bankId !=0){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Bank Information","Bank Code Is - "+bankId);
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Saving Of Bank Information");
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
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving Of Bank Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e.getLocalizedMessage(),e);
			//e.printStackTrace();
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving Of Bank Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
			refreshBankForm(bankMasterForm);
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		long bankId = new Long(request.getParameter("bankId"));
		BankMasterForm bankMasterForm = (BankMasterForm)form;
	
		bankMasterForm.setBankId(bankId);
		List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			List<BankMasterBean> bankMasterBeansList = bankMasterBO.findBank(bankMasterForm, statusList);

			if(!MisUtility.ifEmpty(bankMasterBeansList)){
				for (BankMasterBean bankMasterBean2 : bankMasterBeansList) {
					bankMasterForm.setBankId(bankMasterBean2.getBankId());
					bankMasterForm.setBankBranch(bankMasterBean2.getBankBranch());
					bankMasterForm.setBankName(bankMasterBean2.getBankName());
					
					bankMasterForm.setBankAddress(bankMasterBean2.getBankAddress());
					bankMasterForm.setLocationId(bankMasterBean2.getDistrcit().getLocationId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}



	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Unspecified........Vendor");
		this.setAttrib(request);
		try {
			BankMasterForm bankMasterForm = (BankMasterForm)form;
			refreshBankForm(bankMasterForm);
			Set<LabelValueBean> locationIds = null;
			locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);
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
		request.setAttribute("d__ky", "bankId@locationId");
		request.setAttribute("d__auto", "bankId");
	}

	private void refreshBankForm(BankMasterForm bankMasterForm)
	{
		bankMasterForm.setBankAddress(null);
		bankMasterForm.setBankBranch(null);
		bankMasterForm.setBankId(0);
		bankMasterForm.setBankName(null);
		bankMasterForm.setBankIds(null);
		bankMasterForm.setLocationId(null);
	}
	
	private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		List<String> locationTypeList = new ArrayList<String>();
		try {
			locationTypeList.add("DISTRICT");
//			locationTypeList.add("DO");
			locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
			locationIds = new TreeSet<LabelValueBean>();
			for (LocationBean locationBean : locationBeans) {
				locationIds.add(new LabelValueBean(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")",locationBean.getLocationId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return locationIds;
	}

}
