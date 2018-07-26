package com.prwss.mis.masters.item.struts;

import java.io.IOException;
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
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.item.ItemBO;
import com.prwss.mis.masters.item.dao.ItemBean;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.dao.ItemGroupDao;
import com.prwss.mis.masters.store.dao.StoreBean;
import com.prwss.mis.masters.store.dao.StoreDao;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementDao;


public class ItemAction extends DispatchAction {
	
	private ItemBO itemBO;
	private StoreDao storeDao;
	private ItemGroupDao itemGroupDao;
	private UnitOfMeasurementDao unitOfMeasurementDao;
	private MISSessionBean misSessionBean;
	
	public void setUnitOfMeasurementDao(UnitOfMeasurementDao unitOfMeasurementDao) {
		this.unitOfMeasurementDao = unitOfMeasurementDao;
	}
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setItemGroupDao(ItemGroupDao itemGroupDao) {
		this.itemGroupDao = itemGroupDao;
	}
	private MISSessionBean misAuditBean = new MISSessionBean();

	public void setItemBO(ItemBO itemBO) {
		this.itemBO = itemBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		try{
		ItemForm itemForm = (ItemForm)form;
		List<ItemBean>itemBeanList = itemBO.findItem(itemForm, statusList);	
		if(!MisUtility.ifEmpty(itemBeanList))
		{
		request.setAttribute("itemBeanList", itemBeanList);	
		}else{
			refreshItemForm(itemForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Item");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		ItemForm itemForm = (ItemForm)form;
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
			status = itemBO.deleteItem(itemForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Item","Item ID:"+itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Item","Item ID:"+itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Item");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			}
		refreshItemForm(itemForm);
	
		return mapping.findForward("display");
	}

	
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		ItemForm itemForm = (ItemForm)form;
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
			status = itemBO.postItem(itemForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Item","Item ID:"+itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Item","Item ID:"+itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Posting of Item Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			}
			refreshItemForm(itemForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ItemForm itemForm = (ItemForm)form;
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
//			if(!MisUtility.ifEmpty(bankMasterForm.getBankName())||!MisUtility.ifEmpty(bankMasterForm.getBankBranch())){
//				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
//			}
			
			status = itemBO.updateItem(itemForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Item","Item ID:"+itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Item","Item ID:"+itemForm.getItemId());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Item Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
		}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Item Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
		}
		refreshItemForm(itemForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean)request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ItemForm itemForm = (ItemForm)form;
		//System.out.println(itemForm.toString());
        boolean status = false;
		try {
			
			if(!MisUtility.ifEmpty(itemForm.getItemId()) && !MisUtility.ifEmpty(itemForm.getItemName()) && !MisUtility.ifEmpty(itemForm.getStoreId())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			status = itemBO.saveItem(itemForm, misAuditBean);
			//System.out.println(status);
			if (status)

			{
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Item", itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			}
			else
			{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Item", itemForm.getItemId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Item Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			}
			}
		refreshItemForm(itemForm);
		return mapping.findForward("display");
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String itemId = request.getParameter("itemId");
		//System.out.println("Populate:" + itemId);
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ItemForm itemForm = (ItemForm) form;
		itemForm.setItemId(itemId);
		
		List<ItemBean> itemBeanList = itemBO.findItem(itemForm, null);
		if (itemBeanList == null) {
			log.error("zoneBeanList is\t" + itemBeanList);
		}

		for (ItemBean itemBean : itemBeanList) {
			if (itemId.equalsIgnoreCase(itemBean.getItemId())) {

				itemForm.setItemName(itemBean.getItemName());
				itemForm.setOpeningBalance(itemBean.getOpeningBalance());
				itemForm.setServiceable(itemBean.getServiceable());
				itemForm.setLocationId(itemBean.getLocationId());
				request.setAttribute("storeId", itemBean.getStore().getStoreId());
				itemForm.setStoreId(itemBean.getStore().getStoreId());
				itemForm.setUnitOfMeasurement(itemBean.getUnitOfMeasurement().getMeasurementId());
				itemForm.setItemGroupId(itemBean.getItemGroup().getItemGroupId());
				itemForm.setItemId(itemBean.getItemId());
				itemForm.setItemName(itemBean.getItemName());
				itemForm.setStoreIssueRate(itemBean.getStoreIssueRate());
			}
		}

		return mapping.findForward("display");
	}
	
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.println("Unspecified____________ITEM");
		this.setAttrib(request);
		//System.out.println("Unspecified____________ITEM 1");
		Set<LabelValueBean> itemGroups = getItemGroup();
		//System.out.println("Unspecified____________ITEM 2");
		Set<LabelValueBean> storeIds = getAllStores();
		//System.out.println("Unspecified____________ITEM 3");
		Set<LabelValueBean>unitOfMeasurements =getUnitOfMeasurementIds();
		//System.out.println("Unspecified____________ITEM 4");
		//System.out.println("unit of mesurements size::"+unitOfMeasurements.size()+"unit of m:::::::" +unitOfMeasurements.toString());
		request.getSession().setAttribute("itemGroups", itemGroups);
		request.getSession().setAttribute("storeIds", storeIds);
		request.getSession().setAttribute("unitOfMeasurements", unitOfMeasurements);
		ItemForm itemForm = (ItemForm)form;
		
		refreshItemForm(itemForm);
		return mapping.findForward("display");
	}
	
	
	

	

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "itemId@storeId@locationId");
		request.setAttribute("d__auto", "itemId");	
	}
	
	private void refreshItemForm(ItemForm itemForm)
	{
		
		itemForm.setItemGroupId(null);
		itemForm.setItemId(null);
		itemForm.setItemName(null);
		itemForm.setStoreId(0);
		itemForm.setUnitOfMeasurement(null);
		itemForm.setOpeningBalance(0);
		itemForm.setServiceable(null);
		itemForm.setStoreIssueRate(0);
		
	}
	private Set<LabelValueBean> getAllStores(){
		Set<LabelValueBean> allStores = null;
		Set<StoreBean> storeBeans = null;
		try {
			storeBeans = storeDao.getDistinctStoreCodes(null);
			allStores = new HashSet<LabelValueBean>();
			for (StoreBean storeBean : storeBeans) {
				allStores.add(new LabelValueBean(storeBean.getStoreName()+" - ("+storeBean.getStoreId()+")", new Long(storeBean.getStoreId()).toString()));
			}
		} catch (DataAccessException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allStores;
	}
	
	public ActionForward fetchStores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<StoreBean> storeBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				StoreBean storeBean = new StoreBean();
				storeBean.setLocationId(request.getParameter("locationId"));
				storeBeans = storeDao.findStore(storeBean, statusList);
				for (StoreBean bean : storeBeans) {
					buffer.append("<option value=\"").append(bean.getStoreId()).append("\">");
					buffer.append(bean.getStoreName()+" -("+bean.getStoreId()+")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	private Set<LabelValueBean> getItemGroup(){
		Set<LabelValueBean> itemGroups = null;
		List<ItemGroupBean> itemGroupBeans = null;
		try {
			itemGroupBeans = itemGroupDao.findItemGroup(null);
			itemGroups = new HashSet<LabelValueBean>();
			for (ItemGroupBean itemGroupBean : itemGroupBeans) {
				itemGroups.add(new LabelValueBean(itemGroupBean.getItemGroupName()+" - ("+itemGroupBean.getItemGroupId()+")",itemGroupBean.getItemGroupId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return itemGroups;
	}
	
	private Set<LabelValueBean> getUnitOfMeasurementIds(){
		Set<LabelValueBean> unitofMeasurments = null;
		Set<UnitOfMeasurementBean> unitOfMeasurementBeans = null;
		try {
			//System.out.println("hey 1");
			unitOfMeasurementBeans = unitOfMeasurementDao.getUnitOfMeasurementIds(null);
			//System.out.println("hey 2");
			unitofMeasurments = new HashSet<LabelValueBean>();
			for (UnitOfMeasurementBean unitOfMeasurementBean : unitOfMeasurementBeans) {
				unitofMeasurments.add(new LabelValueBean(unitOfMeasurementBean.getMeasurementName()+" - ("+unitOfMeasurementBean.getMeasurementId()+")", unitOfMeasurementBean.getMeasurementId()));
			}
		} catch (DataAccessException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitofMeasurments;
	}
	

}
