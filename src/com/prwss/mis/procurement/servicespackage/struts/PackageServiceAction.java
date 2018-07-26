package com.prwss.mis.procurement.servicespackage.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.servicespackage.PackageServiceBO;
import com.prwss.mis.procurement.servicespackage.PackageServicesBean;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.ProcSubPlanPrw2HeaderBean;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanDao;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanSchemeDao;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageServiceAction extends DispatchAction {
	Logger log = Logger.getLogger(PackageServiceAction.class);
	private MISSessionBean misSessionBean;
    private CreateProcPlanDao createProcPlanDao;
    private ProcSubPlanDao procSubPlanDao;
    private ProcSubPlanSchemeDao procSubPlanSchemeDao;
    private PackageServiceBO packageServiceBO;
    private PackageHeaderDao packageHeaderDao;    
	public void setPackageServiceBO(PackageServiceBO packageServiceBO) {
		this.packageServiceBO = packageServiceBO;
	}
	public void setProcSubPlanSchemeDao(ProcSubPlanSchemeDao procSubPlanSchemeDao) {
		this.procSubPlanSchemeDao = procSubPlanSchemeDao;
	}
	public void setProcSubPlanDao(ProcSubPlanDao procSubPlanDao) {
		this.procSubPlanDao = procSubPlanDao;
	}
	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
	this.createProcPlanDao = createProcPlanDao;
    }
	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}
	
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
			PackageServiceForm packageServiceForm = (PackageServiceForm)form;
			PackageDetailBean packageDetailBean = null;
			List<PackageServicesBean> packageServicesBeans = null;
			packageDetailBean = packageServiceBO.findPackageServicesFrom(packageServiceForm, statusList);
			List<PackageHeaderBean> packageHeaderBeans = packageDetailBean.getPackageHeaderBeans();
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				request.setAttribute("level2", "true");
					for (PackageHeaderBean packageHeaderBean : packageHeaderBeans) {
						packageServiceForm.setLocationId(packageHeaderBean.getLocationId());
						request.setAttribute("planId",packageHeaderBean.getPlanId());
						request.setAttribute("subPlanId", packageHeaderBean.getSubPlanId());
						request.setAttribute("schemeId", packageHeaderBean.getSchemeId());
						request.setAttribute("packageId", packageHeaderBean.getPackageId());
						packageServiceForm.setPackageId(packageHeaderBean.getPackageId());
						packageServiceForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
						packageServiceForm.setEstimatePackageCost(packageHeaderBean.getEstimatePackageCost());
						packageServiceForm.setPackageDescription(packageHeaderBean.getPackageDescription());
						packageServiceForm.setPostPriorStatus(packageHeaderBean.getPostPriorStatus());
						packageServiceForm.setWingId(packageHeaderBean.getWingId());
					}
				

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageComponentsBeans())){
					packageServiceForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(packageDetailBean.getPackageComponentsBeans()));
				}

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageServicesBeans())){
					packageServicesBeans = packageDetailBean.getPackageServicesBeans();
					for (PackageServicesBean packageServicesBean : packageServicesBeans) {
						System.out.println("method of selection in consultancy==="+packageServicesBean.getMethodOfSelection());
						System.out.println("method of selection in consultancy==="+packageServicesBean.getBankNocCombinedDate());
						packageServiceForm.setMethodOfSelection(packageServicesBean.getMethodOfSelection());
						packageServiceForm.setBankNocCombinedDate(MisUtility.convertDateToString(packageServicesBean.getBankNocCombinedDate()));
						packageServiceForm.setBankNocFinalContractDate(MisUtility.convertDateToString(packageServicesBean.getBankNocFinalContractDate()));
						packageServiceForm.setBankNocForRfpDate(MisUtility.convertDateToString(packageServicesBean.getBankNocForRfpDate()));
						packageServiceForm.setBankNocForShortlistDate(MisUtility.convertDateToString(packageServicesBean.getBankNocForShortlistDate()));
						packageServiceForm.setBankNocForTorDate(MisUtility.convertDateToString(packageServicesBean.getBankNocForTorDate()));
						packageServiceForm.setBankNocTechnicalDate(MisUtility.convertDateToString(packageServicesBean.getBankNocTechnicalDate()));
						packageServiceForm.setDateAdvertisingShortlisting(MisUtility.convertDateToString(packageServicesBean.getDateAdvertisingShortlisting()));
						packageServiceForm.setEvaluationFinalCombinedDate(MisUtility.convertDateToString(packageServicesBean.getEvaluationFinalCombinedDate()));
						packageServiceForm.setEvaluationFinalContractDate(MisUtility.convertDateToString(packageServicesBean.getEvaluationFinalContractDate()));
						packageServiceForm.setEvaluationFinalDraftDate(MisUtility.convertDateToString(packageServicesBean.getEvaluationFinalDraftDate()));
						packageServiceForm.setEvaluationFinalTechnicalDate(MisUtility.convertDateToString(packageServicesBean.getEvaluationFinalTechnicalDate()));
						packageServiceForm.setRfpDraftBankDate(MisUtility.convertDateToString(packageServicesBean.getRfpDraftBankDate()));
						packageServiceForm.setRfpIssuedDate(MisUtility.convertDateToString(packageServicesBean.getRfpIssuedDate()));
						packageServiceForm.setProposalReciptDate(MisUtility.convertDateToString(packageServicesBean.getProposalReciptDate()));
						packageServiceForm.setServiceCompletionDate(MisUtility.convertDateToString(packageServicesBean.getServiceCompletionDate()));
						packageServiceForm.setFinancialProposalOpeningDate(MisUtility.convertDateToString(packageServicesBean.getFinancialProposalOpeningDate()));
						packageServiceForm.setProposalReciptDateFinancial(MisUtility.convertDateToString(packageServicesBean.getProposalReciptDateFinancial()));
						System.out.println("packageServiceForm===="+packageServiceForm.getMethodOfSelection());
				}
			}
			}else{
				refreshProcPackageServicesForm(packageServiceForm);
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
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageServiceForm packageServiceForm = (PackageServiceForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageServiceBO.deletePackageServicesFrom(packageServiceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Package");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Package");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshProcPackageServicesForm(packageServiceForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PackageServiceForm packageServiceForm = (PackageServiceForm)form; 
		boolean status = false;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			/*if(packageServiceForm.getPackageId().trim().equals("")||packageServiceForm.getPackageId().trim().equals("''")||packageServiceForm.getPlanId().trim().equals("''")||packageServiceForm.getSubPlanId()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}*/
			if(!MisUtility.ifEmpty(packageServiceForm.getLocationId())|| !MisUtility.ifEmpty(packageServiceForm.getPlanId())||!MisUtility.ifEmpty(packageServiceForm.getSubPlanId())|| !MisUtility.ifEmpty(packageServiceForm.getEstimatePackageCost()) || !MisUtility.ifEmpty(packageServiceForm.getWingId())|| !MisUtility.ifEmpty(packageServiceForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageServiceForm.getMethodOfSelection()) ){
				if(!MisUtility.ifEmpty(packageServiceForm.getLocationId())){
					stringMessage.append("Please select Location");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getPlanId())){
					stringMessage.append("<br> Please select Plan ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getSubPlanId())){
					stringMessage.append("<br> Please select Sub Plan ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getEstimatePackageCost())){
					stringMessage.append("<br> Please enter Estimate Package Cost");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getWingId())){
					stringMessage.append("<br> Please select Wing");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getPostPriorStatus())){
					stringMessage.append("<br> Please select Post Prior Status ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getMethodOfSelection())){
					stringMessage.append("<br> Please select Method Of Selection");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			
			if(packageServiceForm.getSchemeId().equalsIgnoreCase("null")){
				packageServiceForm.setSchemeId(null);
			}
			status = packageServiceBO.savePackageServicesFrom(packageServiceForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageServicesForm(packageServiceForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Saving of Package");
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
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
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
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Package");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Package");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageServiceForm packageServiceForm = (PackageServiceForm)form;
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
			if(!MisUtility.ifEmpty(packageServiceForm.getLocationId())|| !MisUtility.ifEmpty(packageServiceForm.getPlanId())||!MisUtility.ifEmpty(packageServiceForm.getSubPlanId())|| !MisUtility.ifEmpty(packageServiceForm.getEstimatePackageCost()) || !MisUtility.ifEmpty(packageServiceForm.getWingId())|| !MisUtility.ifEmpty(packageServiceForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageServiceForm.getMethodOfSelection()) ){
				if(!MisUtility.ifEmpty(packageServiceForm.getLocationId())){
					stringMessage.append("Please select Location");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getPlanId())){
					stringMessage.append("<br> Please select Plan ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getSubPlanId())){
					stringMessage.append("<br> Please select Sub Plan ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getEstimatePackageCost())){
					stringMessage.append("<br> Please enter Estimate Package Cost");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getWingId())){
					stringMessage.append("<br> Please select Wing");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getPostPriorStatus())){
					stringMessage.append("<br> Please select Post Prior Status ");
				}
				if(!MisUtility.ifEmpty(packageServiceForm.getMethodOfSelection())){
					stringMessage.append("<br> Please select Method Of Selection");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			
			status = packageServiceBO.updatePackageServicesFrom(packageServiceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageServicesForm(packageServiceForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Package","Package Name -->"+packageServiceForm.getPackageId());
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
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Package Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Package Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		//refreshProcPackageServicesForm(packageServiceForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageServiceForm packageServiceForm = (PackageServiceForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageServiceBO.postPackageServicesFrom(packageServiceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Package","Package Name -->"+packageServiceForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Package");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Package");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshProcPackageServicesForm(packageServiceForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "schemeId@subPlanId@planId@packageId@locationId");
		request.setAttribute("d__auto", "packageId");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		//System.out.println("IN UNSPECIFIED OF SERVICES");
		PackageServiceForm packageServiceForm = (PackageServiceForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshProcPackageServicesForm(packageServiceForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	private void refreshProcPackageServicesForm(PackageServiceForm packageServiceForm){
		packageServiceForm.setLocationId(null);
		packageServiceForm.setPlanId(null);
		packageServiceForm.setSubPlanId(0);
		packageServiceForm.setSchemeId(null);
		packageServiceForm.setPackageDescription(null);
		packageServiceForm.setPackageId(null);
		packageServiceForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
		packageServiceForm.setEstimatePackageCost(new BigDecimal(0.0));
		packageServiceForm.setMethodOfSelection(null);
		packageServiceForm.setBankNocCombinedDate(null);
		packageServiceForm.setBankNocFinalContractDate(null);
		packageServiceForm.setBankNocForRfpDate(null);
		packageServiceForm.setBankNocForShortlistDate(null);
		packageServiceForm.setBankNocForTorDate(null);
		packageServiceForm.setBankNocTechnicalDate(null);
		packageServiceForm.setDateAdvertisingShortlisting(null);
		packageServiceForm.setEvaluationFinalCombinedDate(null);
		packageServiceForm.setEvaluationFinalContractDate(null);
		packageServiceForm.setEvaluationFinalDraftDate(null);
		packageServiceForm.setEvaluationFinalTechnicalDate(null);
		packageServiceForm.setRfpDraftBankDate(null);
		packageServiceForm.setRfpIssuedDate(null);
		packageServiceForm.setProposalReciptDate(null);
		packageServiceForm.setServiceCompletionDate(null);
		packageServiceForm.setPostPriorStatus(null);
		
	}
	
	
	
	public ActionForward fetchPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchPlanIds GOODS");
		try {
			boolean releaseStatus = false;
			System.out.println("Location"+request.getParameter("locationId"));
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanIds(request.getParameter("locationId"),releaseStatus,MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY);
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
	/*public ActionForward fetchSubPlanIdsPrw2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ProcSubPlanPrw2HeaderBean> procSubPlanHeaderBeans = new TreeSet<ProcSubPlanPrw2HeaderBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(MisUtility.ifEmpty(request.getParameter("planId"))){
				procSubPlanHeaderBeans = procSubPlanDao.getSubPlanIdsPrw2(request.getParameter("planId"),statusList);
				buffer.append("<option value='0' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean : procSubPlanHeaderBeans) {
				String subPlanDesc="";
				if(MisUtility.ifEmpty(procSubPlanHeaderBean.getSubPlanDescription())){
					//System.out.println("desc is not blank");
					subPlanDesc=procSubPlanHeaderBean.getSubPlanDescription();
				}else{
					//System.out.println("desc is blank");
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
	}*/
	public ActionForward fetchSubPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = new TreeSet<ProcSubPlanHeaderBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(MisUtility.ifEmpty(request.getParameter("planId"))){
				procSubPlanHeaderBeans = procSubPlanDao.getSubPlanIds(request.getParameter("planId"),statusList);
				buffer.append("<option value='0' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ProcSubPlanHeaderBean procSubPlanHeaderBean : procSubPlanHeaderBeans) {
				String subPlanDesc="";
				if(MisUtility.ifEmpty(procSubPlanHeaderBean.getSubPlanDescription())){
					//System.out.println("desc is not blank");
					subPlanDesc=procSubPlanHeaderBean.getSubPlanDescription();
				}else{
					//System.out.println("desc is blank");
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
	
	public ActionForward fetchSubPlanSchemes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<SubPlanSchemeBean> subPlanSchemeBeans = new ArrayList<SubPlanSchemeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			SubPlanSchemeBean subPlanSchemeBean = new SubPlanSchemeBean();
			if(MisUtility.ifEmpty(request.getParameter("subPlanId"))){
				subPlanSchemeBean.setSubPlanId(new Long(request.getParameter("subPlanId")));
				subPlanSchemeBeans = procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
//				buffer.append("<option value='' selected>");
//				buffer.append("Select");
//				buffer.append("</option>");
			for (SubPlanSchemeBean planSchemeBean : subPlanSchemeBeans) {
			    	buffer.append("<option value=\"").append(planSchemeBean.getSchemeId()).append("\">");
			    	buffer.append(planSchemeBean.getSchemeName()+" - ("+planSchemeBean.getSchemeId()+")");
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
	public ActionForward fetchPackageIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<PackageHeaderBean> packageHeaderBeans = new TreeSet<PackageHeaderBean>();
		StringBuffer buffer = new StringBuffer();
		long subPlanId=0;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			//System.out.println("Action: "+request.getParameter("subPlanId")+":"+request.getParameter("schemeId"));
			if(MisUtility.ifEmpty(request.getParameter("subPlanId"))&&MisUtility.ifEmpty(request.getParameter("schemeId"))){
				subPlanId=new Long (request.getParameter("subPlanId"));
				packageHeaderBeans = packageHeaderDao.getPackageIds(request.getParameter("schemeId"),subPlanId,statusList);
//				buffer.append("<option value='' selected>");
//				buffer.append("Select");
//				buffer.append("</option>");
				for (PackageHeaderBean packageHeaderBean : packageHeaderBeans) {
					buffer.append("<option value=\"").append(packageHeaderBean.getPackageId()).append("\">");
					buffer.append(packageHeaderBean.getPackageId()+" ["+packageHeaderBean.getPackageDescription()+"...]");
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
	private Datagrid getPackageComponentsDatagrid(List<PackageComponentsBean> packageComponentsBeans) {
		Datagrid packageComponentsDatagrid = null;
		try {
			packageComponentsDatagrid = Datagrid.getInstance();
			packageComponentsDatagrid.setDataClass(PackageComponentsBean.class);
			if(!MisUtility.ifEmpty(packageComponentsBeans)){
			List<PackageComponentsBean> componentsBeans = new ArrayList<PackageComponentsBean>(packageComponentsBeans);
			packageComponentsDatagrid.setData(componentsBeans);		
			}else{
			List<PackageComponentsBean> componentsBeans = new ArrayList<PackageComponentsBean>();
			packageComponentsDatagrid.setData(componentsBeans);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return packageComponentsDatagrid;
	}

}
