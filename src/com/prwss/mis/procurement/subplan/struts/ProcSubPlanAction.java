/*
 * <p>
 * Copyright (c) State Program Management Cell, 
 * 					Department of Water Supply & Sanitation, 
 * 					Government of Punjab
 * </p>
 */
package com.prwss.mis.procurement.subplan.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.village.dao.VillageSchemeViewBean;
import com.prwss.mis.masters.village.dao.VillageSchemeViewDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanBO;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanDao;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * The Class ProcSubPlanAction.
 * 
 * @author :
 * @version : 2.0
 * @since :
 * @project : PRWSS-MIS
 * @package : com.prwss.mis.procurement.subplan.struts
 * @file : ProcSubPlanAction.java
 * @purpose : This is the UI controller class for proc sub plan.
 */
public class ProcSubPlanAction extends DispatchAction {
	
	/** the log. */
	Logger log = Logger.getLogger(ProcSubPlanAction.class);
	
	/** the mis session bean. */
	private MISSessionBean misSessionBean;
	
	/** the proc sub plan business object. */
	private ProcSubPlanBO procSubPlanBO;
    
    /** the create proc plan dao. */
    private CreateProcPlanDao createProcPlanDao;
    
    /** the proc sub plan dao. */
    private ProcSubPlanDao procSubPlanDao;
    
    /** the scheme header dao. */
    private SchemeHeaderDao schemeHeaderDao;
    
    /** the village scheme view dao. */
    private VillageSchemeViewDao villageSchemeViewDao;
    
    
    
    

	/**
	 * Sets the village scheme view dao.
	 * 
	 * @param villageSchemeViewDao
	 *            the new village scheme view dao
	 */
	public void setVillageSchemeViewDao(
			VillageSchemeViewDao villageSchemeViewDao) {
		this.villageSchemeViewDao = villageSchemeViewDao;
	}
	
	/**
	 * Gets the scheme header dao.
	 * 
	 * @return the scheme header dao
	 */
	public SchemeHeaderDao getSchemeHeaderDao() {
		return schemeHeaderDao;
	}

	/**
	 * Sets the scheme header dao.
	 * 
	 * @param schemeHeaderDao
	 *            the new scheme header dao
	 */
	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

	/**
	 * Sets the proc sub plan dao.
	 * 
	 * @param procSubPlanDao
	 *            the new proc sub plan dao
	 */
	public void setProcSubPlanDao(ProcSubPlanDao procSubPlanDao) {
		this.procSubPlanDao = procSubPlanDao;
	}

	/**
	 * Sets the create proc plan dao.
	 * 
	 * @param createProcPlanDao
	 *            the new create proc plan dao
	 */
	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
	this.createProcPlanDao = createProcPlanDao;
    }

	/**
	 * Sets the proc sub plan business object.
	 * 
	 * @param procSubPlanBO
	 *            the new proc sub plan business object
	 */
	public void setProcSubPlanBO(ProcSubPlanBO procSubPlanBO) {
		this.procSubPlanBO = procSubPlanBO;
	}

	/**
	 * This method is used to find ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
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
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
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
			ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form;
			List<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = null;
			procSubPlanHeaderBeans = procSubPlanBO.findSubPlan(procSubPlanForm, statusList);
			if(!MisUtility.ifEmpty(procSubPlanHeaderBeans)){
				request.setAttribute("level2", "true");
				for (ProcSubPlanHeaderBean procSubPlanHeaderBean : procSubPlanHeaderBeans) {
					procSubPlanForm.setPlanId(procSubPlanHeaderBean.getPlanId());
					request.setAttribute("planId",procSubPlanHeaderBean.getPlanId());
					request.setAttribute("subPlanId", procSubPlanHeaderBean.getSubPlanId());
					request.setAttribute("subPlanName", procSubPlanHeaderBean.getSubPlanName());
					procSubPlanForm.setSubPlanName(procSubPlanHeaderBean.getSubPlanName());
					procSubPlanForm.setLocationId(procSubPlanHeaderBean.getLocationId());
					procSubPlanForm.setSubPlanDescription(procSubPlanHeaderBean.getSubPlanDescription());
					procSubPlanForm.setAttachedSchemeDatagrid(getAttachedSchemeDatagrid(procSubPlanHeaderBean.getSubPlanSchemeBeans()));
				}
			}else{
				refreshProcSubPlanForm(procSubPlanForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Procurement Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to delete ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = procSubPlanBO.deleteSubPlan(procSubPlanForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Procurement Sub Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Procurement Sub Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshProcSubPlanForm(procSubPlanForm);
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to save ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form; 
		String subPlanName= null;
		System.out.println("block"+procSubPlanForm.getBlockId());
		System.out.println("vill"+procSubPlanForm.getVillageId());
		System.out.println("scheme"+procSubPlanForm.getSchemeId());
		 
		 System.out.println(procSubPlanForm.getTotalNoPackages());
		 
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			 if(!MisUtility.ifEmpty(procSubPlanForm.getLocationId()) || !MisUtility.ifEmpty(procSubPlanForm.getPlanId()) ||   !MisUtility.ifEmpty(procSubPlanForm.getPlanType())){
				 if(!MisUtility.ifEmpty(procSubPlanForm.getLocationId())){
					 stringMessage.append("Please select Location ");
				 }
				 if(!MisUtility.ifEmpty(procSubPlanForm.getPlanId())){
					 stringMessage.append("<br>Please select Work Plan ");
				 }

				 if(!MisUtility.ifEmpty(procSubPlanForm.getPlanType())){
					 stringMessage.append("<br>Please select Plan Type ");
				 }
				 throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			 }
			if(procSubPlanForm.getPlanType().equals("WORKS")){
			if((procSubPlanForm.getBlockId().equals("Select Block"))|| !MisUtility.ifEmpty(procSubPlanForm.getVillageId())|| !MisUtility.ifEmpty(procSubPlanForm.getSchemeId())|| !MisUtility.ifEmpty(procSubPlanForm.getSchemeEstimatedCostInRs())|| !MisUtility.ifEmpty(procSubPlanForm.getTotalNoPackages()))
				{
					if(procSubPlanForm.getBlockId().equals("Select Block")||procSubPlanForm.getBlockId().equals(""))
					{
						stringMessage.append("<br> Please Select Block");
					}
					if(!MisUtility.ifEmpty(procSubPlanForm.getVillageId())||procSubPlanForm.getVillageId().equals("Select Village")||procSubPlanForm.getVillageId().equals("")	)
					{
						stringMessage.append("<br> Please Select Village");
					}
					if(!MisUtility.ifEmpty(procSubPlanForm.getSchemeId())||procSubPlanForm.getSchemeId().equals(""))
					{
						stringMessage.append("<br> Please Select Scheme");
					}
					if(!MisUtility.ifEmpty(procSubPlanForm.getSchemeEstimatedCostInRs()))
					{
						stringMessage.append("<br> Please Enter Scheme Estimated Cost");
					}
					if(!MisUtility.ifEmpty(procSubPlanForm.getTotalNoPackages()))
					{
						stringMessage.append("<br> Please Enter  Number of Packages");
					}
					throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			stringMessage = new StringBuffer();
			if(MisUtility.ifEmpty(procSubPlanForm.getAttachedSchemeDatagrid().getAddedData())){
				stringMessage.append("Please attach Scheme.");
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			 	}
			}
			subPlanName = procSubPlanBO.saveSubPlan(procSubPlanForm, misSessionBean);
			
			if (MisUtility.ifEmpty(subPlanName)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.sub.save","Procurement Sub Plan details","-->"+subPlanName);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcSubPlanForm(procSubPlanForm);
				this.setAttrib(request);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Procurement Sub Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				this.setAttrib(request);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				this.setAttrib(request);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				this.setAttrib(request);
			}
			else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				this.setAttrib(request);
			}
			else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Procurement Sub Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			this.setAttrib(request);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Procurement Sub Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			this.setAttrib(request);
			
		}
 
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to update ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form;
		boolean status = false;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(procSubPlanForm.getLocationId()) || !MisUtility.ifEmpty(procSubPlanForm.getPlanId()) || !MisUtility.ifEmpty(procSubPlanForm.getPlanType())){
				 if(!MisUtility.ifEmpty(procSubPlanForm.getLocationId())){
					 stringMessage.append("Please select Location ");
				 }
				 if(!MisUtility.ifEmpty(procSubPlanForm.getPlanId())){
					 stringMessage.append("<br>Please select Work Plan ");
				 }

				 if(!MisUtility.ifEmpty(procSubPlanForm.getPlanType())){
					 stringMessage.append("<br>Please select Plan Type ");
				 }
				 throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			 }

			
			status = procSubPlanBO.updateSubPlan(procSubPlanForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcSubPlanForm(procSubPlanForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Procurement Sub Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Procurement Sub Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		return mapping.findForward("display");
	}
	
	/**
	 * This method is used to post ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = procSubPlanBO.postSubPlan(procSubPlanForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Procurement Sub Plan","Sub Plan ID -->"+procSubPlanForm.getSubPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Procurement Sub Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Procurement Sub Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshProcSubPlanForm(procSubPlanForm);
		return mapping.findForward("display");
	}
	
	
	
	/**
	 * Sets the attrib.
	 * 
	 * @param request
	 *            the new attrib
	 */
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@locationId@subPlanId@planIds@planType");
		request.setAttribute("d__auto", "subPlanId");
		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
		this.setAttrib(request);
		ProcSubPlanForm procSubPlanForm = (ProcSubPlanForm)form;
		System.out.println("IN UNSPCIFIED PROC PLAN");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshProcSubPlanForm(procSubPlanForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	/**
	 * Refresh proc sub plan action form.
	 * 
	 * @param procSubPlanForm
	 *            the proc sub plan action form
	 */
	private void refreshProcSubPlanForm(ProcSubPlanForm procSubPlanForm){
		procSubPlanForm.setLocationId(null);
		procSubPlanForm.setPlanId(null);
		procSubPlanForm.setSubPlanDescription(null);
		procSubPlanForm.setSubPlanId(0);
		procSubPlanForm.setAttachedSchemeDatagrid(getAttachedSchemeDatagrid(null));
		
		
	}
	
	
	/**
	 * Gets the attached scheme datagrid.
	 * 
	 * @param subPlanSchemeBeans
	 *            the sub plan scheme beans
	 * @return the attached scheme datagrid
	 */
	private Datagrid getAttachedSchemeDatagrid(Set<SubPlanSchemeBean> subPlanSchemeBeans) {
		Datagrid attachedSchemeDatagrid = null;
		try {
			attachedSchemeDatagrid = Datagrid.getInstance();
			attachedSchemeDatagrid.setDataClass(SubPlanSchemeBean.class);
			if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
			List<SubPlanSchemeBean> subPlanSchemeBeans2 = new ArrayList<SubPlanSchemeBean>(subPlanSchemeBeans);
			attachedSchemeDatagrid.setData(subPlanSchemeBeans2);		
			}else{
			List<SubPlanSchemeBean> subPlanSchemeBeans2 = new ArrayList<SubPlanSchemeBean>();
			attachedSchemeDatagrid.setData(subPlanSchemeBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return attachedSchemeDatagrid;
	}
	
	/**
	 * This method is used to fetchPlanIds ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchPlanIds");
		try {
			boolean releaseStatus = false;
			System.out.println("Location"+request.getParameter("locationId"));
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanIds(request.getParameter("locationId"),releaseStatus,null);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
					buffer.append("<option value=\"").append(createProcPlanBean.getPlanId()).append("\">");
					buffer.append(createProcPlanBean.getPlanId());
					buffer.append("</option>");
				}		
			}

			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * This method is used to fetchPlanType ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchPlanType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchPlanType");
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("planId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanTypeOnPlanIds(request.getParameter("locationId"),request.getParameter("planId"));
				for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
					buffer.append("<option value=\"").append(createProcPlanBean.getPlanType()).append("\">");
					buffer.append(createProcPlanBean.getPlanType());
					buffer.append("</option>");
				}		
			}

			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * This method is used to fetchSubPlanIds ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchSubPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = new TreeSet<ProcSubPlanHeaderBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			String mode = (String) request.getParameter("d__mode");
			List<String> statusList = new ArrayList<String>();
			if (mode != null
					&& MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if(MisUtility.ifEmpty(request.getParameter("planId"))){
				procSubPlanHeaderBeans = procSubPlanDao.getSubPlanIds(request.getParameter("planId"),statusList);
				buffer.append("<option value='0' selected>");
				buffer.append("Select");
				buffer.append("</option>");
			for (ProcSubPlanHeaderBean procSubPlanHeaderBean : procSubPlanHeaderBeans) {
					String subPlanDesc="";
					if(MisUtility.ifEmpty(procSubPlanHeaderBean.getSubPlanDescription())){
						
						subPlanDesc=procSubPlanHeaderBean.getSubPlanDescription();
					}else{
	
						subPlanDesc="";
					}
											
					buffer.append("<option value=\"").append(procSubPlanHeaderBean.getSubPlanId()).append("\">");
					buffer.append(procSubPlanHeaderBean.getSubPlanName()+" ["+subPlanDesc+"...]");
					buffer.append("</option>");
				}		
			}

			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * This method is used to fetchSchemeName ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchSchemeName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
//		List<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//		VillageBean villageBean = new VillageBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("schemeId"))){
				String schemeId=request.getParameter("schemeId");
				if(schemeId.equals("GOODS")){
					buffer.append("GOODS");
				}else if(schemeId.equals("SERVICES")){
					buffer.append("SERVICES");
				}else if(schemeId.equals("CONSULTANCY")){
					buffer.append("CONSULTANCY");
				}else{				
					SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
					schemeHeaderBean.setSchemeId(request.getParameter("schemeId"));
					SchemeHeaderBean schemeHeaderBean2 = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList).get(0);
					buffer.append(schemeHeaderBean2.getSchemeName());
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
	
	 
	/**
	 * This method is used to fetchProgramId ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchProgramId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchProgramId");
		try {
			
			if(MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("planId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanTypeOnPlanIds(request.getParameter("locationId"),request.getParameter("planId"));
				for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
					 buffer.append(createProcPlanBean.getProgramId());
					 System.out.println("ProgramId---"+createProcPlanBean.getProgramId());
				}		
			}

			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * This method is used to fetchScheme ActionForward.
	 * 
	 * @param mapping
	 *            the action Mapping
	 * @param form
	 *            the action form
	 * @param request
	 *            the HTTP Request
	 * @param response
	 *            the HTTP Response
	 * @return the action forward
	 * @throws MISException
	 *             the MIS exception
	 */
	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		 
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchScheme");
		try {

			if(MisUtility.ifEmpty(request.getParameter("villageId"))&&MisUtility.ifEmpty(request.getParameter("programId"))){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				VillageSchemeViewBean villageSchemeViewBean = new VillageSchemeViewBean();
				villageSchemeViewBean.setVillageId(request.getParameter("villageId"));
				villageSchemeViewBean.setProgramId(request.getParameter("programId"));
				villageSchemeViewBean.setLocationId(request.getParameter("locationId"));
				List<VillageSchemeViewBean> schemeVillageBeans2 = villageSchemeViewDao.findVillageSchemeFromView(villageSchemeViewBean, statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(schemeVillageBeans2)){
					for (VillageSchemeViewBean schemeVillageBean2 : schemeVillageBeans2) {
						buffer.append("<option value=\"").append(schemeVillageBean2.getSchemeId()).append("\">");
						buffer.append(schemeVillageBean2.getSchemeName()+" -("+schemeVillageBean2.getSchemeId()+")-"+schemeVillageBean2.getProgramId());
						buffer.append("</option>");
					}
				}
			}

			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
}
