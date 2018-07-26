/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt.struts;

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
import com.prwss.mis.inventory.goodsreceipt.GoodsReceiptBO;
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptDetailsBean;
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptHeaderBean;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.dao.ItemGroupDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;


public class GoodsReceiptAction extends DispatchAction {
	
	private ItemGroupDao itemGroupDao;
	private VendorDao vendorDao;
	private ProgramDao programDao;
	private MISSessionBean misSessionBean;
	private GoodsReceiptBO goodsReceiptBO;
	

	public void setItemGroupDao(ItemGroupDao itemGroupDao) {
		this.itemGroupDao = itemGroupDao;
	}
	
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	
	public void setGoodsReceiptBO(GoodsReceiptBO goodsReceiptBO) {
		this.goodsReceiptBO = goodsReceiptBO;
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
		
		GoodsReceiptForm goodsReceiptForm =(GoodsReceiptForm)form;
		try{
		List<GoodsReceiptHeaderBean> goodsReceiptHeaderBeans=goodsReceiptBO.finGoodsReceiptHeaderBeans(goodsReceiptForm, statusList);
		
		if(!MisUtility.ifEmpty(goodsReceiptHeaderBeans))
		{
		request.setAttribute("goodsBeans", goodsReceiptHeaderBeans);	
		}else{
			refreshGoodsReceiptForm(goodsReceiptForm);
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
		GoodsReceiptForm goodsReceiptForm=(GoodsReceiptForm)form;
		
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}	
			StringBuffer strngMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(goodsReceiptForm.getProjectId())|| !MisUtility.ifEmpty(goodsReceiptForm.getStoreId()) ||!MisUtility.ifEmpty(goodsReceiptForm.getSupplierId())|| !MisUtility.ifEmpty(goodsReceiptForm.getInvoiceNumber())||!MisUtility.ifEmpty(goodsReceiptForm.getInvoiceAmount()) ||!MisUtility.ifEmpty(goodsReceiptForm.getInvoiceDate())||!MisUtility.ifEmpty(goodsReceiptForm.getReceivedDate())||!MisUtility.ifEmpty(goodsReceiptForm.getItemGroupId())||!MisUtility.ifEmpty(goodsReceiptForm.getItemId())||!MisUtility.ifEmpty(goodsReceiptForm.getQuantity())||!MisUtility.ifEmpty(goodsReceiptForm.getItemRate())||!MisUtility.ifEmpty(goodsReceiptForm.getItemAmount())){
				
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getProjectId()))){
					strngMessage.append("Please Select Project");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getStoreId())){
					strngMessage.append("<br>Please Select Store");
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getSupplierId()))){
					strngMessage.append("<br>Please Select Supplier");
				}			
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getInvoiceNumber()))){
					strngMessage.append("<br> Please enter Invoice Number");	
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getInvoiceAmount())){
					strngMessage.append("<br> Please enter Invoice Ammount");
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getInvoiceDate()))){
					strngMessage.append("<br> Please enter Invoice Date");	
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getItemGroupId())){
					strngMessage.append("<br> Please select Item Group");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getReceivedDate())){
					strngMessage.append("<br> Please enter  Revised Date");
				}
				if(!MisUtility.ifEmpty(goodsReceiptForm.getItemId())){
					strngMessage.append("<br> Please select Item ");
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getQuantity()))){
					strngMessage.append("<br> Please enter  Quantity");	
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getItemAmount()))){
					strngMessage.append("<br> Please enter Item Amount");	
				}
				if(!(MisUtility.ifEmpty(goodsReceiptForm.getItemRate()))){
					strngMessage.append("<br> Please enter Item Rate");	
				}
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			if(MisUtility.ifEmpty(goodsReceiptForm.getGoodsReceiptDatagrid().getAddedData())){
				strngMessage.append("<br> Please  attach Add Items");
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			long headerId=new Double((Math.random())*1000000).longValue();
			goodsReceiptForm.setGoodsReceiptHeaderId(headerId);
		
		status=goodsReceiptBO.saveGoodsReceiptHeaderBeans(goodsReceiptForm, misSessionBean);
		if (status){
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("success.save","Goods Receipt","Receipt Id "+goodsReceiptForm.getGoodsReceiptHeaderId()+", save it for future references");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
			refreshGoodsReceiptForm(goodsReceiptForm);

		} else {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update","Target Details","Goods Receipt");
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
			ActionMessage message = new ActionMessage("fatal.error.save","Goods Receipt");
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
				GoodsReceiptForm goodsReceiptForm=(GoodsReceiptForm)form;
		
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
					
					System.out.println("headerId-update:::  "+goodsReceiptForm.getGoodsReceiptHeaderId());
					status=goodsReceiptBO.updateGoodsReceiptHeaderBeans(goodsReceiptForm, misSessionBean);
					if (status){
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("success.save","Goods Receipt","Receipt Id "+goodsReceiptForm.getGoodsReceiptHeaderId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);

					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.update","Target Details","Goods Receipt");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
					}catch (MISException e) {
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
							ActionErrors errors = new ActionErrors();
							ActionMessage message = new ActionMessage("fatal.error.save","Goods Receipt");
							errors.add(ActionMessages.GLOBAL_MESSAGE, message);
							saveErrors(request, errors);
						
					
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
						e.printStackTrace();
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("fatal.error.save","Goods Receipt");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
						
					}
					refreshGoodsReceiptForm(goodsReceiptForm);
					return mapping.findForward("display");
				
			}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException
			{
				this.setAttrib(request);
				request.setAttribute("level2","true");
				GoodsReceiptForm goodsReceiptForm=(GoodsReceiptForm)form;
		
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
					System.out.println("headerId-delete:::  "+goodsReceiptForm.getGoodsReceiptHeaderId());
					status=goodsReceiptBO.deleteGoodsReceiptHeaderBeans(goodsReceiptForm, misSessionBean);
					if (status){
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("success.delete","Target Details","Goods Receipt");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);

					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.delete","Target Details","Goods Receipt");
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
						ActionMessage message = new ActionMessage("error.delete","Goods Receipt");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
						
					}
					refreshGoodsReceiptForm(goodsReceiptForm);
					return mapping.findForward("display");
				
			}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "goodsReceiptHeaderId");
		request.setAttribute("d__auto", "goodsReceiptHeaderId");
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
		
		GoodsReceiptForm goodsReceiptForm=(GoodsReceiptForm)form;
		
		refreshGoodsReceiptForm(goodsReceiptForm);
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

	
	private void refreshGoodsReceiptForm(GoodsReceiptForm goodsReceiptForm)
	{
		goodsReceiptForm.setContractId(null);
		goodsReceiptForm.setGoodsReceiptDatagrid(getGoodsReceiptDatagrid(null));
		goodsReceiptForm.setInvoiceAmount(0);
		goodsReceiptForm.setInvoiceDate(null);
		goodsReceiptForm.setInvoiceNumber(null);
		goodsReceiptForm.setLocationId(null);
		goodsReceiptForm.setProjectId(null);
		goodsReceiptForm.setReceivedDate(null);
		goodsReceiptForm.setRemarks(null);
		goodsReceiptForm.setStoreId(0);
		goodsReceiptForm.setSupplierId(null);
		goodsReceiptForm.setGoodsReceiptHeaderId(0);
		goodsReceiptForm.setGoodsReceiptDatagrid(getGoodsReceiptDatagrid(null));
	}
	
	private Datagrid getGoodsReceiptDatagrid(Collection<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans)
	{
		Datagrid goodsReceiptGrid=Datagrid.getInstance();
		goodsReceiptGrid.setDataClass(GoodsReceiptGridBean.class);
		List<GoodsReceiptGridBean> goodsReceiptGridBeans=new ArrayList<GoodsReceiptGridBean>();
		try {
			if (!MisUtility.ifEmpty(goodsReceiptDetailsBeans)) {
				for(GoodsReceiptDetailsBean goodsReceiptDetailsBean:goodsReceiptDetailsBeans)
				{
					if(!(goodsReceiptDetailsBean.getMisAuditBean().getStatus()).equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED)){
					GoodsReceiptGridBean goodsReceiptGridBean=new GoodsReceiptGridBean();
					goodsReceiptGridBean.setId(goodsReceiptDetailsBean.getGoodsReceiptDetailsId());
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
		
		long goodsReceiptHeaderId = Long.parseLong(request.getParameter("goodsReceiptHeaderId"));		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		GoodsReceiptForm goodsReceiptForm =(GoodsReceiptForm)form;
		goodsReceiptForm.setGoodsReceiptHeaderId(goodsReceiptHeaderId);
		try{
		List<GoodsReceiptHeaderBean>goodsReceiptHeaderBeans=goodsReceiptBO.finGoodsReceiptHeaderBeans(goodsReceiptForm, null);
		
		if(!MisUtility.ifEmpty(goodsReceiptHeaderBeans))
		{
			for(GoodsReceiptHeaderBean goodsReceiptHeaderBean:goodsReceiptHeaderBeans)
			{
				//goodsReceiptForm.setContractId(goodsReceiptHeaderBean.getContractNumber());
				goodsReceiptForm.setGoodsReceiptHeaderId(goodsReceiptHeaderBean.getGoodsReceiptHeaderId());
				request.setAttribute("contractId", goodsReceiptHeaderBean.getContractNumber());
				goodsReceiptForm.setInvoiceAmount(goodsReceiptHeaderBean.getReceiptAmount());
				goodsReceiptForm.setInvoiceDate(MisUtility.convertDateToString(goodsReceiptHeaderBean.getInvoiceDate()));
				goodsReceiptForm.setInvoiceNumber(goodsReceiptHeaderBean.getInvoiceNumber());
				goodsReceiptForm.setLocationId(goodsReceiptHeaderBean.getLocationId());
				goodsReceiptForm.setReceivedDate(MisUtility.convertDateToString(goodsReceiptHeaderBean.getReceivedDate()));
				goodsReceiptForm.setProjectId(goodsReceiptHeaderBean.getProjectId());
				goodsReceiptForm.setSupplierId(goodsReceiptHeaderBean.getSupplierId());
				//goodsReceiptForm.setStoreId(goodsReceiptHeaderBean.getStoreId());
				request.setAttribute("storeId", goodsReceiptHeaderBean.getStoreId());
				goodsReceiptForm.setRemarks(goodsReceiptHeaderBean.getRemarks());
				goodsReceiptForm.setGoodsReceiptDatagrid(getGoodsReceiptDatagrid(goodsReceiptHeaderBean.getGoodsReceiptDetailsBeans()));
				
			}
		}else{
			refreshGoodsReceiptForm(goodsReceiptForm);
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
	
		

		
	
}
