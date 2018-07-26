package com.prwss.mis.procurement.goodspackage.struts;

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
import com.prwss.mis.procurement.goodspackage.PackageGoodsBO;
import com.prwss.mis.procurement.goodspackage.PackageGoodsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanDao;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanSchemeDao;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;
import com.prwss.mis.procurement.workspackage.struts.PackageWorksAction;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageGoodsAction extends DispatchAction {
	Logger log = Logger.getLogger(PackageWorksAction.class);
	private MISSessionBean misSessionBean;
	private PackageGoodsBO packageGoodsBO;
    private CreateProcPlanDao createProcPlanDao;
    private ProcSubPlanDao procSubPlanDao;
    private ProcSubPlanSchemeDao procSubPlanSchemeDao;
    private PackageHeaderDao packageHeaderDao;   
    
	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
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

	public void setPackageGoodsBO(PackageGoodsBO packageGoodsBO) {
		this.packageGoodsBO = packageGoodsBO;
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
			PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form;
			PackageDetailBean packageDetailBean = null;
			List<PackageGoodsBean> packageGoodsBeans = null;
			packageDetailBean = packageGoodsBO.findPackageGoodsFrom(packageGoodsForm, statusList);
			List<PackageHeaderBean> packageHeaderBeans = packageDetailBean.getPackageHeaderBeans();
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				request.setAttribute("level2", "true");
					for (PackageHeaderBean packageHeaderBean : packageHeaderBeans) {
						packageGoodsForm.setLocationId(packageHeaderBean.getLocationId());
						request.setAttribute("planId",packageHeaderBean.getPlanId());
						request.setAttribute("subPlanId", packageHeaderBean.getSubPlanId());
						request.setAttribute("schemeId", packageHeaderBean.getSchemeId());
						request.setAttribute("packageId",packageHeaderBean.getPackageId());
						packageGoodsForm.setPackageId(packageHeaderBean.getPackageId());
						packageGoodsForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
						packageGoodsForm.setEstimatePackageCost(packageHeaderBean.getEstimatePackageCost());
						packageGoodsForm.setPackageDescription(packageHeaderBean.getPackageDescription());
						packageGoodsForm.setPostPriorStatus(packageHeaderBean.getPostPriorStatus());
						packageGoodsForm.setWingId(packageHeaderBean.getWingId());

					}
				

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageComponentsBeans())){
					packageGoodsForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(packageDetailBean.getPackageComponentsBeans()));
				}

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageGoodsBeans())){
					packageGoodsBeans = packageDetailBean.getPackageGoodsBeans();
					for (PackageGoodsBean packageGoodsBean : packageGoodsBeans) {
						packageGoodsForm.setNumberOfUnit(packageGoodsBean.getNumberOfUnit());
						packageGoodsForm.setMethodOfProcurement(packageGoodsBean.getMethodOfProcurement());
						packageGoodsForm.setDesignInvestigationDate(MisUtility.convertDateToString(packageGoodsBean.getDesignInvestigationDate()));
						packageGoodsForm.setEstPreparedSanctionDate(MisUtility.convertDateToString(packageGoodsBean.getEstPreparedSanctionDate()));
						packageGoodsForm.setBankNocBidDate(MisUtility.convertDateToString(packageGoodsBean.getBankNocBidDate()));
						packageGoodsForm.setBidOpeningDate(MisUtility.convertDateToString(packageGoodsBean.getBidOpeningDate()));
						packageGoodsForm.setBidInvitationDate(MisUtility.convertDateToString(packageGoodsBean.getBidInvitationDate()));
						packageGoodsForm.setConAwardDecideDate(MisUtility.convertDateToString(packageGoodsBean.getConAwardDecideDate()));
						packageGoodsForm.setBankNocConAwardDate(MisUtility.convertDateToString(packageGoodsBean.getConAwardDecideDate()));
						packageGoodsForm.setConSignDate(MisUtility.convertDateToString(packageGoodsBean.getConSignDate()));
						packageGoodsForm.setConCompletionDate(MisUtility.convertDateToString(packageGoodsBean.getConCompletionDate()));
						packageGoodsForm.setPrepBidDocDate(MisUtility.convertDateToString(packageGoodsBean.getPrepBidDocDate()));
				}
			}
			}else{
				refreshProcPackageGoodsForm(packageGoodsForm);
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
		PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageGoodsBO.deletePackageGoodsFrom(packageGoodsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Package","Package Name -->"+packageGoodsForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Package","Package Name -->"+packageGoodsForm.getPackageId());
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
		refreshProcPackageGoodsForm(packageGoodsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form; 
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
			/*if(packageGoodsForm.getPackageId().trim().equals("")||packageGoodsForm.getPackageId().trim().equals("''")||packageGoodsForm.getPlanId().trim().equals("''")||packageGoodsForm.getSubPlanId()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}*/
			if(!MisUtility.ifEmpty(packageGoodsForm.getLocationId()) || !MisUtility.ifEmpty(packageGoodsForm.getPlanId()) || !MisUtility.ifEmpty(packageGoodsForm.getSubPlanId()) || !MisUtility.ifEmpty(packageGoodsForm.getNumberOfUnit())|| !MisUtility.ifEmpty(packageGoodsForm.getWingId())|| !MisUtility.ifEmpty(packageGoodsForm.getEstimatePackageCost())|| !MisUtility.ifEmpty(packageGoodsForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageGoodsForm.getMethodOfProcurement())){
					if(!MisUtility.ifEmpty(packageGoodsForm.getLocationId())){
						stringMessage.append("Please select Location");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getPlanId())){
						stringMessage.append("<br>Please select Plan Id ");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getSubPlanId())){
						stringMessage.append("<br>Please select Sub Plan Id ");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getNumberOfUnit())){
						stringMessage.append("<br>Please enter Number of Unit ");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getWingId())){
						stringMessage.append("<br>Please select Wing Type");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getEstimatePackageCost())){
						stringMessage.append("<br>Please enter Estimate Package Cost ");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getPostPriorStatus())){
						stringMessage.append("<br>Please select Post Prior Status");
					}
					if(!MisUtility.ifEmpty(packageGoodsForm.getMethodOfProcurement())){
						stringMessage.append("<br>Please select Method Of Procurement");
					}
					throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(packageGoodsForm.getSchemeId().equalsIgnoreCase("null")){
				packageGoodsForm.setSchemeId(null);
			}
			status = packageGoodsBO.savePackageGoodsFrom(packageGoodsForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Package","Package Name -->"+packageGoodsForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageGoodsForm(packageGoodsForm);
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
		PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form;
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
			
			if(!MisUtility.ifEmpty(packageGoodsForm.getLocationId()) || !MisUtility.ifEmpty(packageGoodsForm.getPlanId()) || !MisUtility.ifEmpty(packageGoodsForm.getSubPlanId()) || !MisUtility.ifEmpty(packageGoodsForm.getNumberOfUnit())|| !MisUtility.ifEmpty(packageGoodsForm.getWingId())|| !MisUtility.ifEmpty(packageGoodsForm.getEstimatePackageCost())|| !MisUtility.ifEmpty(packageGoodsForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageGoodsForm.getMethodOfProcurement())){
				if(!MisUtility.ifEmpty(packageGoodsForm.getLocationId())){
					stringMessage.append("Please select Location");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getPlanId())){
					stringMessage.append("<br>Please select Plan Id ");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getSubPlanId())){
					stringMessage.append("<br>Please select Sub Plan Id ");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getNumberOfUnit())){
					stringMessage.append("<br>Please enter Number of Unit ");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getWingId())){
					stringMessage.append("<br>Please select Wing Type");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getEstimatePackageCost())){
					stringMessage.append("<br>Please enter Estimate Package Cost ");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getPostPriorStatus())){
					stringMessage.append("<br>Please select Post Prior Status");
				}
				if(!MisUtility.ifEmpty(packageGoodsForm.getMethodOfProcurement())){
					stringMessage.append("<br>Please select Method Of Procurement");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
		}

			status = packageGoodsBO.updatePackageGoodsFrom(packageGoodsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Package","Package Name -->"+packageGoodsForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageGoodsForm(packageGoodsForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Package","Package Name -->"+packageGoodsForm.getPackageId());
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
		//refreshProcPackageGoodsForm(packageGoodsForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageGoodsBO.postPackageGoodsFrom(packageGoodsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Package","Package Name -->"+packageGoodsForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Package","Package Name -->"+packageGoodsForm.getPackageId());
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
		
		refreshProcPackageGoodsForm(packageGoodsForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@subPlanId@schemeId@packageId@locationId");
		request.setAttribute("d__auto", "packageId");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageGoodsForm packageGoodsForm = (PackageGoodsForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshProcPackageGoodsForm(packageGoodsForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	private void refreshProcPackageGoodsForm(PackageGoodsForm packageGoodsForm){
		packageGoodsForm.setLocationId(null);
		packageGoodsForm.setPlanId(null);
		packageGoodsForm.setSubPlanId(0);
		packageGoodsForm.setSchemeId(null);
		packageGoodsForm.setPackageDescription(null);
		packageGoodsForm.setPrepBidDocDate(null);
		packageGoodsForm.setMethodOfProcurement(null);
		packageGoodsForm.setDesignInvestigationDate(null);
		packageGoodsForm.setNumberOfUnit(0);
		packageGoodsForm.setUnitCost(0);
		packageGoodsForm.setEstimatePackageCost(new BigDecimal(0.0));
		packageGoodsForm.setEstPreparedSanctionDate(null);
		packageGoodsForm.setBankNocBidDate(null);
		packageGoodsForm.setBidOpeningDate(null);
		packageGoodsForm.setBidInvitationDate(null);
		packageGoodsForm.setConAwardDecideDate(null);
		packageGoodsForm.setBankNocConAwardDate(null);
		packageGoodsForm.setConSignDate(null);
		packageGoodsForm.setConCompletionDate(null);
		packageGoodsForm.setPackageId(null);
		packageGoodsForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
		packageGoodsForm.setPostPriorStatus(null);
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
				createProcPlanBeans = createProcPlanDao.getProcPlanIds(request.getParameter("locationId"),releaseStatus,MISConstants.MIS_TYPE_OF_TENDER_GOODS);
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
			System.out.println("Action: "+request.getParameter("subPlanId")+":"+request.getParameter("schemeId"));
			if(MisUtility.ifEmpty(request.getParameter("subPlanId"))&&MisUtility.ifEmpty(request.getParameter("schemeId"))){
				subPlanId=new Long (request.getParameter("subPlanId"));
				packageHeaderBeans = packageHeaderDao.getPackageIds(request.getParameter("schemeId"),subPlanId,statusList);
				  buffer.append("<option value='' selected>");
				  buffer.append("Select");
				  buffer.append("</option>");
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
