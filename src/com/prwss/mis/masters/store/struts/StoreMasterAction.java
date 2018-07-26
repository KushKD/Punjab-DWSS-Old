package com.prwss.mis.masters.store.struts;

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
import com.prwss.mis.masters.store.StoreBO;
import com.prwss.mis.masters.store.dao.StoreBean;


public class StoreMasterAction extends DispatchAction{
	private MISSessionBean misSessionBean;
	private StoreBO storeBO;	
	
	public void setStoreBO(StoreBO storeBO) {
		this.storeBO = storeBO;
	}
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean)request.getSession().getAttribute("misSessionBean");
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
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
	
		this.setAttrib(request);
		try{
			
			StoreForm storeForm=(StoreForm)form;
			List<StoreBean> storeBeans=null;
			storeBeans=storeBO.findStore(storeForm, statusList);
			if(!MisUtility.ifEmpty(storeBeans))
			{
				
				request.setAttribute("storeList", storeBeans);
				
				
			}else
			{
				refreshStoreForm(storeForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","Store");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","find of store");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
		
	}
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		StoreForm storeForm=(StoreForm)form;
		long storeId = 0;
			try {
				storeId = storeBO.saveStore(storeForm, misSessionBean);
				if (MisUtility.ifEmpty(storeId))

				{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("bank.success.save", "Store Information","Store Id is :"+storeId );
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);

				}
				else
				{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Saving of Store Information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();	
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Saving of Store Master");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					}
				} catch (Exception e) {
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Saving of Store Master");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					
				}
			refreshStoreForm(storeForm);
			return mapping.findForward("display");
		
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		StoreForm storeForm=(StoreForm)form;
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
				status = storeBO.updateStore(storeForm, misSessionBean,statusList);
				if (status)

				{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.update", "Store Information","Store Id :"+storeForm.getStoreId() );
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);

				}
				else
				{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Updating of Store Information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();	
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Updating of Store Master");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					}
				} catch (Exception e) {
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Updating of Store Master");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					
				}
			refreshStoreForm(storeForm);
			return mapping.findForward("display");
		
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "storeId@locationId");
		
	}
	
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		System.out.println("in SCEHEME");
		try {
			StoreForm storeForm=(StoreForm)form;
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			
			refreshStoreForm(storeForm);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	private void refreshStoreForm(StoreForm storeForm)
	{
		storeForm.setLocationId(null);
		storeForm.setStoreName(null);
		storeForm.setStoreAddress(null);
		
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String storeId = request.getParameter("storeId");
		StoreForm storeForm = (StoreForm)form;
		storeForm.setStoreId(new Long(storeId));
		List<StoreBean> storeBeans = storeBO.findStore(storeForm, null);
		if(!MisUtility.ifEmpty(storeBeans)){
			for (StoreBean bean : storeBeans) {
				storeForm.setLocationId(bean.getLocationId());
				storeForm.setStoreId(bean.getStoreId());
				storeForm.setStoreAddress(bean.getStoreAddress());
				storeForm.setStoreName(bean.getStoreName());
			}
			
		}
		
		return mapping.findForward("display");
	}
}
