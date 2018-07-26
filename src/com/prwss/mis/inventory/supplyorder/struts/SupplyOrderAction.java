/**
 * 
 */
package com.prwss.mis.inventory.supplyorder.struts;

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
import com.prwss.mis.inventory.supplyorder.SupplyOrderBO;
import com.prwss.mis.inventory.supplyorder.SupplyOrderDetailsBean;
import com.prwss.mis.inventory.supplyorder.SupplyOrderHeaderBean;
import com.prwss.mis.inventory.supplyorder.struts.SupplyOrderForm;
import com.prwss.mis.inventory.supplyorder.struts.SupplyOrderGridBean;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.dao.ItemGroupDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;


public class SupplyOrderAction extends DispatchAction {
	
	private ItemGroupDao itemGroupDao;
	private VendorDao vendorDao;
	private ProgramDao programDao;
	private MISSessionBean misSessionBean;
	private SupplyOrderBO supplyOrderBO;
	

	public void setItemGroupDao(ItemGroupDao itemGroupDao) {
		this.itemGroupDao = itemGroupDao;
	}
	
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	
	public void setsupplyOrderBO(SupplyOrderBO supplyOrderBO) {
		this.supplyOrderBO = supplyOrderBO;
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
		
		SupplyOrderForm SupplyOrderForm =(SupplyOrderForm)form;
		try{
		List<SupplyOrderHeaderBean> supplyOrderHeaderBeans=supplyOrderBO.finSupplyOrderHeaderBeans(SupplyOrderForm, statusList);
		
		if(!MisUtility.ifEmpty(supplyOrderHeaderBeans))
		{
		request.setAttribute("orderBeans", supplyOrderHeaderBeans);	
		}else{
			refreshSupplyOrderForm(SupplyOrderForm);
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
		SupplyOrderForm supplyOrderForm=(SupplyOrderForm)form;
		System.out.println("Uom"+supplyOrderForm.getUnitOfMeasurement());
		System.out.println("quantity"+supplyOrderForm.getQuantity());
		System.out.println("rate"+supplyOrderForm.getItemRate());
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			StringBuffer strngMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(supplyOrderForm.getProjectId())||!MisUtility.ifEmpty(supplyOrderForm.getStoreId()) ||!MisUtility.ifEmpty(supplyOrderForm.getSupplierId()) ||!MisUtility.ifEmpty(supplyOrderForm.getItemGroupId()) || !MisUtility.ifEmpty(supplyOrderForm.getSupplyOrderNumber())|| !MisUtility.ifEmpty(supplyOrderForm.getSupplyOrderDate())|| !MisUtility.ifEmpty(supplyOrderForm.getUnitOfMeasurement())|| !MisUtility.ifEmpty(supplyOrderForm.getQuantity())|| !MisUtility.ifEmpty(supplyOrderForm.getItemRate())){
				
				if(!(MisUtility.ifEmpty(supplyOrderForm.getProjectId()))){
					strngMessage.append("Please select Project");
				}	
				if(!MisUtility.ifEmpty(supplyOrderForm.getStoreId())){
					strngMessage.append("<br>Please select Store");
				}
				if(!(MisUtility.ifEmpty(supplyOrderForm.getSupplierId()))){
					strngMessage.append("<br>Please Select Supplier");
				}	
				if(!(MisUtility.ifEmpty(supplyOrderForm.getSupplyOrderNumber()))){
					strngMessage.append("<br>Please enter Supply Order Number");
				}			
				if(!(MisUtility.ifEmpty(supplyOrderForm.getSupplyOrderDate()))){
					strngMessage.append("<br> Please enter Supply Order Date");	
				}				
				if(!MisUtility.ifEmpty(supplyOrderForm.getItemGroupId())){
					strngMessage.append("<br> Please select Item Group");
				}
				if(!MisUtility.ifEmpty(supplyOrderForm.getItemId())){
					strngMessage.append("<br> Please select Item ");
				}
				if(!(MisUtility.ifEmpty(supplyOrderForm.getUnitOfMeasurement()))){
					strngMessage.append("<br> Please enter Unit Of measurement");	
				}	
				if(!(MisUtility.ifEmpty(supplyOrderForm.getUnitOfMeasurement()))){
					strngMessage.append("<br> Please enter Unit Of measurement");	
				}
				if(!MisUtility.ifEmpty(supplyOrderForm.getQuantity())){
					strngMessage.append("<br> Please enter  Quantity");	
				}
				if(!MisUtility.ifEmpty(supplyOrderForm.getItemRate())){
					strngMessage.append("<br> Please enter Rate");	
				}
				
				
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			if(MisUtility.ifEmpty(supplyOrderForm.getSupplyOrderDatagrid().getAddedData())){
				strngMessage.append("<br> Please  attach Add Items");
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			long headerId=new Double((Math.random())*1000000).longValue();
			supplyOrderForm.setSupplyOrderHeaderId(headerId);
			status=supplyOrderBO.saveSupplyOrderHeaderBeans(supplyOrderForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Goods Receipt","Receipt Id "+supplyOrderForm.getSupplyOrderHeaderId()+", save it for future references");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);	
				refreshSupplyOrderForm(supplyOrderForm);
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
				SupplyOrderForm SupplyOrderForm=(SupplyOrderForm)form;
		
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
					
					System.out.println("headerId-update:::  "+SupplyOrderForm.getSupplyOrderHeaderId());
					status=supplyOrderBO.updateSupplyOrderHeaderBeans(SupplyOrderForm, misSessionBean);
					if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("success.save","Goods Receipt","Receipt Id "+SupplyOrderForm.getSupplyOrderHeaderId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);

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
					refreshSupplyOrderForm(SupplyOrderForm);
					return mapping.findForward("display");
				
			}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException
			{
				this.setAttrib(request);
				request.setAttribute("level2","true");
				SupplyOrderForm SupplyOrderForm=(SupplyOrderForm)form;
		
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
					System.out.println("headerId-delete:::  "+SupplyOrderForm.getSupplyOrderHeaderId());
					status=supplyOrderBO.deleteSupplyOrderHeaderBeans(SupplyOrderForm, misSessionBean);
					if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("success.delete","Target Details","Goods Receipt");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);

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
					refreshSupplyOrderForm(SupplyOrderForm);
					return mapping.findForward("display");
				
			}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "supplyOrderHeaderId");
		request.setAttribute("d__auto", "supplyOrderHeaderId");
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
		
		SupplyOrderForm SupplyOrderForm=(SupplyOrderForm)form;
		
		refreshSupplyOrderForm(SupplyOrderForm);
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
	

	
	private void refreshSupplyOrderForm(SupplyOrderForm SupplyOrderForm)
	{
		SupplyOrderForm.setSupplyOrderNumber(null);
		SupplyOrderForm.setSupplyOrderDatagrid(getSupplyOrderDatagrid(null));
		SupplyOrderForm.setSupplyOrderDate(null);
		SupplyOrderForm.setLocationId(null);
		SupplyOrderForm.setProjectId(null);
		SupplyOrderForm.setRemarks(null);
		SupplyOrderForm.setStoreId(0);
		SupplyOrderForm.setSupplierId(null);
		SupplyOrderForm.setSupplyOrderHeaderId(0);
		SupplyOrderForm.setItemGroupId(null);
		SupplyOrderForm.setItemId(null);
		SupplyOrderForm.setItemRate(0);
		SupplyOrderForm.setQuantity(0);
		SupplyOrderForm.setUnitOfMeasurement(null);
		
	}
	
	private Datagrid getSupplyOrderDatagrid(Collection<SupplyOrderDetailsBean> supplyOrderDetailsBeans)
	{
		Datagrid supplyOrderGrid=Datagrid.getInstance();
		supplyOrderGrid.setDataClass(SupplyOrderGridBean.class);
		List<SupplyOrderGridBean> supplyOrderGridBeans=new ArrayList<SupplyOrderGridBean>();
		try {
			if (!MisUtility.ifEmpty(supplyOrderDetailsBeans)) {
				for(SupplyOrderDetailsBean supplyOrderDetailsBean:supplyOrderDetailsBeans)
				{
					if(!(supplyOrderDetailsBean.getMisAuditBean().getStatus()).equalsIgnoreCase(MISConstants.MASTER_STATUS_DELETED)){
					SupplyOrderGridBean supplyOrderGridBean=new SupplyOrderGridBean();
					supplyOrderGridBean.setId(supplyOrderDetailsBean.getSupplyOrderDetailsId());
					supplyOrderGridBean.setItemGroupId(supplyOrderDetailsBean.getItemGroupId());
					supplyOrderGridBean.setUnitOfMeasurement(supplyOrderDetailsBean.getUnitOfMeasurementId());
					supplyOrderGridBean.setItemId(supplyOrderDetailsBean.getItemId());
					supplyOrderGridBean.setItemRate(supplyOrderDetailsBean.getRate());
					supplyOrderGridBean.setQuantity(supplyOrderDetailsBean.getQuantity());
					supplyOrderGridBean.setRemarks(supplyOrderDetailsBean.getRemarks());
					supplyOrderGridBeans.add(supplyOrderGridBean);
					System.out.println("status details bean::: "+supplyOrderDetailsBean.getMisAuditBean().getStatus());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		supplyOrderGrid.setData(supplyOrderGridBeans);
		return supplyOrderGrid;
	}
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		long supplyOrderHeaderId = Long.parseLong(request.getParameter("supplyOrderHeaderId"));		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		SupplyOrderForm SupplyOrderForm =(SupplyOrderForm)form;
		SupplyOrderForm.setSupplyOrderHeaderId(supplyOrderHeaderId);
		try{
		List<SupplyOrderHeaderBean>supplyOrderHeaderBeans=supplyOrderBO.finSupplyOrderHeaderBeans(SupplyOrderForm, null);
		
		if(!MisUtility.ifEmpty(supplyOrderHeaderBeans))
		{
			for(SupplyOrderHeaderBean supplyOrderHeaderBean:supplyOrderHeaderBeans)
			{
				//SupplyOrderForm.setContractId(supplyOrderHeaderBean.getContractNumber());
				SupplyOrderForm.setSupplyOrderHeaderId(supplyOrderHeaderBean.getSupplyOrderHeaderId());
				SupplyOrderForm.setProjectId(supplyOrderHeaderBean.getProjectId());
				SupplyOrderForm.setLocationId(supplyOrderHeaderBean.getLocationId());
				SupplyOrderForm.setSupplyOrderDate((MisUtility.convertDateToString(supplyOrderHeaderBean.getSupplyOrderDate())));
				request.setAttribute("storeId", supplyOrderHeaderBean.getStoreId());
				SupplyOrderForm.setSupplierId(supplyOrderHeaderBean.getSupplierId());
				SupplyOrderForm.setSupplyOrderNumber(supplyOrderHeaderBean.getSupplyOrderNumber());
				SupplyOrderForm.setSupplyOrderDatagrid(getSupplyOrderDatagrid(supplyOrderHeaderBean.getSupplyOrderDetailsBeans()));
				
			}
		}else{
			refreshSupplyOrderForm(SupplyOrderForm);
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
