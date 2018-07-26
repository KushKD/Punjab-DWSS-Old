/**
 * 
 */
package com.prwss.mis.inventory.goodsissue.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
import com.prwss.mis.inventory.goodsissue.GoodsIssueBO;
import com.prwss.mis.inventory.goodsissue.GoodsIssueDetailsBean;
import com.prwss.mis.inventory.goodsissue.GoodsIssueHeaderBean;
import com.prwss.mis.inventory.goodsissue.StoreItemAvailabilityIssueBean;
import com.prwss.mis.inventory.goodsissue.dao.GoodsIssueDao;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueForm;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueGridBean;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.dao.ItemGroupDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.scheme.dao.CurrentFcVillageStatusBean;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class GoodsIssueAction extends DispatchAction {
	
	private ItemGroupDao itemGroupDao;
	private VendorDao vendorDao;
	private ProgramDao programDao;
	private MISSessionBean misSessionBean;
	private GoodsIssueBO goodsIssueBO;
	private GoodsIssueDao GoodsIssueDao;
	
	public void setGoodsIssueDao(GoodsIssueDao goodsIssueDao) {
		GoodsIssueDao = goodsIssueDao;
	}
	 
	public void setItemGroupDao(ItemGroupDao itemGroupDao) {
		this.itemGroupDao = itemGroupDao;
	}
	
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	
	public void setGoodsIssueBO(GoodsIssueBO goodsIssueBO) {
		this.goodsIssueBO = goodsIssueBO;
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
			request.setAttribute("level2", "true");
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			request.setAttribute("level2", "true");
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		
		GoodsIssueForm goodsReceiptForm =(GoodsIssueForm)form;
		try{
		List<GoodsIssueHeaderBean> goodsIssueHeaderBeans=goodsIssueBO.finGoodsIssueHeaderBeans(goodsReceiptForm, statusList);
		
		if(!MisUtility.ifEmpty(goodsIssueHeaderBeans))
		{
		request.setAttribute("issueBeans", goodsIssueHeaderBeans);	
		}else{
			refreshGoodsIssueForm(goodsReceiptForm);
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

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		GoodsIssueForm goodsReceiptForm=(GoodsIssueForm)form;
//		System.out.println("Isue type"+goodsReceiptForm.getIssuedType());
//		System.out.println("store"+goodsReceiptForm.getStoreId());
//		System.out.println("quantiyy"+goodsReceiptForm.getQuantity());
	 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}	
			StringBuffer strngMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(goodsReceiptForm.getProjectId())||!MisUtility.ifEmpty(goodsReceiptForm.getStoreId())||!MisUtility.ifEmpty(goodsReceiptForm.getIndentNumber())|| !MisUtility.ifEmpty(goodsReceiptForm.getIndentDate())|| !MisUtility.ifEmpty(goodsReceiptForm.getIssuedType())||!MisUtility.ifEmpty(goodsReceiptForm.getIssuedTo())||!MisUtility.ifEmpty(goodsReceiptForm.getItemGroupId())||!MisUtility.ifEmpty(goodsReceiptForm.getItemId())|| !MisUtility.ifEmpty(goodsReceiptForm.getQuantity())||!MisUtility.ifEmpty(goodsReceiptForm.getItemAmount())|| !MisUtility.ifEmpty(goodsReceiptForm.getItemRate())){
				if(!MisUtility.ifEmpty(goodsReceiptForm.getProjectId())){
					strngMessage.append("Please select Project");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getStoreId())){
					strngMessage.append("<br>Please select Store");
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getIndentNumber()))){
					strngMessage.append("<br>Please enter Indent Number");
				}			
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getIndentDate()))){
					strngMessage.append("<br> Please enter Indent Order Date");	
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getIssuedType()))){
					strngMessage.append("<br> Please select Issue/Transfer Type");	
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getIssuedTo())){
					strngMessage.append("<br> Please select Issue/Transfer To");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getItemGroupId())){
					strngMessage.append("<br> Please select Item Group");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getItemId())){
					strngMessage.append("<br> Please select Item");
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getQuantity()))){
					strngMessage.append("<br> Please enter Quantity");	
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getItemRate()))){
					strngMessage.append("<br> Please enter Item Rate");	
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getItemAmount())){
					strngMessage.append("<br> Please enter Item Amount");
				}
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			if(MisUtility.ifEmpty(goodsReceiptForm.getGoodsIssueDatagrid().getAddedData())){
				strngMessage.append("<br> Please  attach Add Items");
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			
			// checking the goods available or not in the store
		
				@SuppressWarnings("unchecked")
				Collection<GoodsIssueGridBean> GoodsIssueGridBeans=goodsReceiptForm.getGoodsIssueDatagrid().getAddedData();
				for(GoodsIssueGridBean goodsIssueGridBean : GoodsIssueGridBeans){
					
					StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean = new StoreItemAvailabilityIssueBean();
					StoreItemAvailabilityIssueBean stAvailabilityIssueBean = new StoreItemAvailabilityIssueBean();
					stAvailabilityIssueBean.setLocationId(goodsReceiptForm.getLocationId());
					stAvailabilityIssueBean.setStoreId(goodsReceiptForm.getStoreId());
					stAvailabilityIssueBean.setItemId(goodsIssueGridBean.getItemId());
					storeItemAvailabilityIssueBean = GoodsIssueDao.findTotalAvailabeItemInStore(stAvailabilityIssueBean).get(0);
					if(MisUtility.ifEmpty(storeItemAvailabilityIssueBean)){
							long totalAvailable = storeItemAvailabilityIssueBean.getTotalAvailableItem();
						 
							if(totalAvailable<goodsIssueGridBean.getQuantity()){
								strngMessage.append("<br> Quantity should be less then or equal to the total availabe quantity i.e "+totalAvailable +"for item "+goodsIssueGridBean.getItemId());
								throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
							} 
								
							}
					}
				
					

			
			long headerId=new Double((Math.random())*1000000).longValue();
			goodsReceiptForm.setGoodsIssueHeaderId(headerId);
		
		status=goodsIssueBO.saveGoodsIssueHeaderBeans(goodsReceiptForm, misSessionBean);
		if (status){
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.save","Goods Issue","Id "+goodsReceiptForm.getGoodsIssueHeaderId()+", save it for future references");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
			refreshGoodsIssueForm(goodsReceiptForm);
		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Target Details","Goods Issue");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		}catch (MISException ex) {
			if(MISExceptionCodes.MIS012.equals(ex.getCode())){
				log.error(ex.getLocalizedMessage(),ex);
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",ex.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{	
				log.error(ex.getLocalizedMessage(),ex);
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Goods Receipt");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Goods Issue");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException
			{
				this.setAttrib(request);
				request.setAttribute("level2","true");
				GoodsIssueForm goodsReceiptForm=(GoodsIssueForm)form;
				StringBuffer strngMessage = new StringBuffer();
				boolean status = false;
				try {
					if (request.getSession().getAttribute("misSessionBean") != null) {
						{
							misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
						}
					} else {
						System.out.println("NO AUDIT");
						return mapping.findForward("login");
					}	
					
					System.out.println("headerId-update:::  "+goodsReceiptForm.getGoodsIssueHeaderId());
					
					
					// checking the goods available or not in the store
					
					@SuppressWarnings("unchecked")
					Collection<GoodsIssueGridBean> GoodsIssueGridBeans=goodsReceiptForm.getGoodsIssueDatagrid().getModifiedData();
					for(GoodsIssueGridBean goodsIssueGridBean : GoodsIssueGridBeans){
						
						StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean = new StoreItemAvailabilityIssueBean();
						StoreItemAvailabilityIssueBean stAvailabilityIssueBean = new StoreItemAvailabilityIssueBean();
						stAvailabilityIssueBean.setLocationId(goodsReceiptForm.getLocationId());
						stAvailabilityIssueBean.setStoreId(goodsReceiptForm.getStoreId());
						stAvailabilityIssueBean.setItemId(goodsIssueGridBean.getItemId());
						storeItemAvailabilityIssueBean = GoodsIssueDao.findTotalAvailabeItemInStore(stAvailabilityIssueBean).get(0);
						if(MisUtility.ifEmpty(storeItemAvailabilityIssueBean)){
								long totalAvailable = storeItemAvailabilityIssueBean.getTotalAvailableItem();
							 
								if(totalAvailable<goodsIssueGridBean.getQuantity()){
									strngMessage.append(" Quantity should be less then or equal to the total availabe quantity i.e "+totalAvailable +"for item "+goodsIssueGridBean.getItemId());
									throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
								} 
									
								}
						}
					
					
					status=goodsIssueBO.updateGoodsIssueHeaderBeans(goodsReceiptForm, misSessionBean);
					if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("success.save","Goods Issue","Receipt Id "+goodsReceiptForm.getGoodsIssueHeaderId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshGoodsIssueForm(goodsReceiptForm);

					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.update","Target Details","Goods Issue");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
					}catch (MISException e) {
						if(MISExceptionCodes.MIS012.equals(e.getCode())){
							log.error(e.getLocalizedMessage(),e);
							e.printStackTrace();
							ActionErrors errors = new ActionErrors();
							ActionMessage message = new ActionMessage("required.fields",e.getMessage());
							errors.add(ActionMessages.GLOBAL_MESSAGE, message);
							saveErrors(request, errors);
						}else{
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
							ActionErrors errors = new ActionErrors();
							ActionMessage message = new ActionMessage("fatal.error.save","Goods Issue");
							errors.add(ActionMessages.GLOBAL_MESSAGE, message);
							saveErrors(request, errors);
						}
					
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("fatal.error.save","Goods Issue");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
						
					}
					
					return mapping.findForward("display");
				
			}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException
			{
				this.setAttrib(request);
				request.setAttribute("level2","true");
				GoodsIssueForm goodsReceiptForm=(GoodsIssueForm)form;
		
				boolean status = false;
				try {
					if (request.getSession().getAttribute("misSessionBean") != null) {
						{
							misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
						}
					} else {
						System.out.println("NO AUDIT");
						return mapping.findForward("login");
					}	
					System.out.println("headerId-delete:::  "+goodsReceiptForm.getGoodsIssueHeaderId());
					status=goodsIssueBO.deleteGoodsIssueHeaderBeans(goodsReceiptForm, misSessionBean);
					if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("success.delete","Target Details","Goods Issue");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);

					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.delete","Target Details","Goods Issue");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
					}catch (MISException e) {
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
							ActionErrors errors = new ActionErrors();
							ActionMessage message = new ActionMessage("error.delete",e.getMessage());
							errors.add(ActionMessages.GLOBAL_MESSAGE, message);
							saveErrors(request, errors);
						
					
					}
					catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.delete","Goods Issue");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
						
					}
					refreshGoodsIssueForm(goodsReceiptForm);
					return mapping.findForward("display");
				
			}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "goodsIssueHeaderId");
		request.setAttribute("d__auto", "goodsIssueHeaderId");
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		request.getSession().setAttribute("projects",getPrograms());
		request.getSession().setAttribute("suppliers",getVendors());		
		request.getSession().setAttribute("itemgroups",getItemGroup());	
		
		GoodsIssueForm goodsReceiptForm=(GoodsIssueForm)form;
		
		refreshGoodsIssueForm(goodsReceiptForm);
		return mapping.findForward("display");
	}
	
	private Set<LabelValueBean> getItemGroup(){
		Set<LabelValueBean> itemGroups = null;
		List<ItemGroupBean> itemGroupBeans = null;
		try {
			itemGroupBeans = itemGroupDao.findItemGroup(null);
			itemGroups = new HashSet<LabelValueBean>();
			for (ItemGroupBean itemGroupBean : itemGroupBeans) {
				itemGroups.add(new LabelValueBean(itemGroupBean.getItemGroupId()+"-("+itemGroupBean.getItemGroupName()+")",itemGroupBean.getItemGroupId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return itemGroups;
	}

	
	private Set<LabelValueBean> getVendors()
	{
		Set<LabelValueBean> vendors=new HashSet<LabelValueBean>() ;
		Set<VendorBean> vendorBeans=null;
		try {
			vendorBeans=vendorDao.getDistinctVendorCodes();
			for(VendorBean vendorBean:vendorBeans)
			{
				vendors.add(new LabelValueBean("select",null));
				vendors.add(new LabelValueBean(vendorBean.getVendorId()+"-("+vendorBean.getVendorName()+")",vendorBean.getVendorId()));
			}
		} catch (DataAccessException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendors;
		
	}
	private Set<LabelValueBean> getPrograms()
	{
		Set<LabelValueBean> programs=new HashSet<LabelValueBean>() ;
		Set<ProgramBean> programBeans=null;
	try{
		programBeans=programDao.getDistinctPrograms();
		for(ProgramBean programBean:programBeans)
		{
			programs.add(new LabelValueBean(programBean.getProgramId()+"-("+programBean.getProgramName()+")",programBean.getProgramId()));
		}
		
	} catch (DataAccessException e){
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return programs;
	}

	
	private void refreshGoodsIssueForm(GoodsIssueForm goodsReceiptForm)
	{
		
		goodsReceiptForm.setGoodsIssueDatagrid(getGoodsIssueDatagrid(null));
		goodsReceiptForm.setIndentDate(null);
		goodsReceiptForm.setIndentNumber(null);
		goodsReceiptForm.setLocationId(null);
		goodsReceiptForm.setProjectId(null);
		goodsReceiptForm.setStoreId(0);
		goodsReceiptForm.setGoodsIssueHeaderId(0);
		goodsReceiptForm.setIssuedTo(null);
		goodsReceiptForm.setIssuedType(null);
		
	}
	
	private Datagrid getGoodsIssueDatagrid(Collection<GoodsIssueDetailsBean> goodsReceiptDetailsBeans)
	{
		Datagrid goodsReceiptGrid=Datagrid.getInstance();
		goodsReceiptGrid.setDataClass(GoodsIssueGridBean.class);
		List<GoodsIssueGridBean> goodsReceiptGridBeans=new ArrayList<GoodsIssueGridBean>();
		try {
			if (!MisUtility.ifEmpty(goodsReceiptDetailsBeans)) {
				for(GoodsIssueDetailsBean goodsReceiptDetailsBean:goodsReceiptDetailsBeans)
				{
					if(!(goodsReceiptDetailsBean.getMisAuditBean().getStatus()).equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED)){
					GoodsIssueGridBean goodsReceiptGridBean=new GoodsIssueGridBean();
					goodsReceiptGridBean.setId(goodsReceiptDetailsBean.getGoodsIssueDetailsId());
					goodsReceiptGridBean.setItemGroupId(goodsReceiptDetailsBean.getItemGroupId());
					goodsReceiptGridBean.setItemAmount(goodsReceiptDetailsBean.getAmount());
					goodsReceiptGridBean.setItemId(goodsReceiptDetailsBean.getItemId());
					goodsReceiptGridBean.setItemRate(goodsReceiptDetailsBean.getRate());
					goodsReceiptGridBean.setQuantity(goodsReceiptDetailsBean.getQuantity());
					goodsReceiptGridBeans.add(goodsReceiptGridBean);
					System.out.println("status details bean::: "+goodsReceiptDetailsBean.getMisAuditBean().getStatus());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		goodsReceiptGrid.setData(goodsReceiptGridBeans);
		return goodsReceiptGrid;
	}
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		long goodsIssueHeaderId = Long.parseLong(request.getParameter("goodsIssueHeaderId"));		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		GoodsIssueForm goodsReceiptForm =(GoodsIssueForm)form;
		goodsReceiptForm.setGoodsIssueHeaderId(goodsIssueHeaderId);
		try{
		List<GoodsIssueHeaderBean>goodsIssueHeaderBeans=goodsIssueBO.finGoodsIssueHeaderBeans(goodsReceiptForm, null);
		
		if(!MisUtility.ifEmpty(goodsIssueHeaderBeans))
		{
			for(GoodsIssueHeaderBean goodsIssueHeaderBean:goodsIssueHeaderBeans)
			{
				goodsReceiptForm.setGoodsIssueHeaderId(goodsIssueHeaderBean.getGoodsIssueHeaderId());
				goodsReceiptForm.setIndentDate(MisUtility.convertDateToString(goodsIssueHeaderBean.getIndentDate()));
				goodsReceiptForm.setIndentNumber(goodsIssueHeaderBean.getIndentNumber());
				goodsReceiptForm.setLocationId(goodsIssueHeaderBean.getLocationId());				
				goodsReceiptForm.setProjectId(goodsIssueHeaderBean.getProjectId());
				request.setAttribute("issuedTo", goodsIssueHeaderBean.getIssuedTo());	
				request.setAttribute("issuedType", goodsIssueHeaderBean.getIssuedType());	
				request.setAttribute("storeId", goodsIssueHeaderBean.getStoreId());				
				goodsReceiptForm.setGoodsIssueDatagrid(getGoodsIssueDatagrid(goodsIssueHeaderBean.getGoodsIssueDetailsBeans()));
				
			}
		}else{
			refreshGoodsIssueForm(goodsReceiptForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("Error Occured", "While Populating","the Transaction");
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
	
	public ActionForward fetchAvailableItemInStore(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
	//	Set<TenderHeaderBean> tenders = null;
		List<StoreItemAvailabilityIssueBean> storeItemAvailabilityIssueBeans = null;
		boolean status = false; 
		 System.out.println("inside the method for checking");
		try {
		//	StringBuffer buffer = new StringBuffer();
			long totalavailabeItem = 0;
			PrintWriter out = res.getWriter();
			String messString = null;
			if(MisUtility.ifEmpty(req.getParameter("locationId")))
			{ 
				//System.out.println(req.getParameter("locationId"));
				log.debug("villageId"+req.getParameter("locationId"));
				System.out.println("req.getParameterjjj"+req.getParameter("locationId"));
				StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean = new StoreItemAvailabilityIssueBean();
				storeItemAvailabilityIssueBean.setLocationId(req.getParameter("locationId"));
				storeItemAvailabilityIssueBean.setStoreId(Long.parseLong(req.getParameter("storeId")));
				storeItemAvailabilityIssueBean.setItemId(req.getParameter("itemId"));
				long quantity = Long.parseLong(req.getParameter("quantityId"));
				//System.out.println("quantityiiiii"+quantity);
				storeItemAvailabilityIssueBeans = GoodsIssueDao.findTotalAvailabeItemInStore(storeItemAvailabilityIssueBean);
				if(!MisUtility.ifEmpty(storeItemAvailabilityIssueBeans)){
					//System.out.println("storeItemAvailabilityIssueBeans"+storeItemAvailabilityIssueBeans);
					StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean1=storeItemAvailabilityIssueBeans.get(0);
					
					totalavailabeItem = storeItemAvailabilityIssueBean1.getTotalAvailableItem();
				//	System.out.println("totalavailabeItem"+totalavailabeItem);
					if(totalavailabeItem<quantity){
						status = false;
						messString = "Error: Quantity should be less then or eqaul to the total available item i.e "+totalavailabeItem;
					}else{
						status = true;
					}
				}else{
					messString = "No Item Found in Store ";
				}
			}
			if(status){
				out.print("OK");
			}else{
				out.print(messString);
			}
			
	    
      }
				
		 catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	
}
