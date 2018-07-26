package com.prwss.mis.procurement.workspackage.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.prwss.mis.masters.scheme.struts.SchemeHeaderVillageForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommissioningVillageGridBean;
import com.prwss.mis.masters.village.dao.PrwssVillageAllHabitationBean;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.pmm.village.struts.VillageConnectionGridBean;
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
import com.prwss.mis.procurement.workspackage.PackageWorksBO;
import com.prwss.mis.procurement.workspackage.PackageWorksBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageWorksAction extends DispatchAction {
	Logger log = Logger.getLogger(PackageWorksAction.class);
	private MISSessionBean misSessionBean;
	private PackageWorksBO packageWorksBO;
    private CreateProcPlanDao createProcPlanDao;
    private ProcSubPlanDao procSubPlanDao;
    private ProcSubPlanSchemeDao procSubPlanSchemeDao;
    private PackageHeaderDao packageHeaderDao;
    private SchemeVillageDao schemeVillageDao;
    private MISJdcDaoImpl misJdcDaoImpl;
    
    public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
    public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
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

	public void setPackageWorksBO(PackageWorksBO packageWorksBO) {
		this.packageWorksBO = packageWorksBO;
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
			PackageWorksForm packageWorksForm = (PackageWorksForm)form;
			PackageDetailBean packageDetailBean = null;
			List<PackageWorksBean> packageWorksBeans = null;
			packageDetailBean = packageWorksBO.findPackageWorksFrom(packageWorksForm, statusList);
			List<PackageHeaderBean> packageHeaderBeans = packageDetailBean.getPackageHeaderBeans();
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				request.setAttribute("level2", "true");
					for (PackageHeaderBean packageHeaderBean : packageHeaderBeans) {
						packageWorksForm.setLocationId(packageHeaderBean.getLocationId());
						request.setAttribute("planId",packageHeaderBean.getPlanId());
						request.setAttribute("subPlanId", packageHeaderBean.getSubPlanId());
						request.setAttribute("schemeId", packageHeaderBean.getSchemeId());
						request.setAttribute("villageId",packageHeaderBean.getVillageId());
						packageWorksForm.setPackageId(packageHeaderBean.getPackageId());
						packageWorksForm.setVillageId(packageHeaderBean.getVillageId());
						//packageWorksForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
						packageWorksForm.setEstimatePackageCost(packageHeaderBean.getEstimatePackageCost());
						packageWorksForm.setPackageDescription(packageHeaderBean.getPackageDescription());
						packageWorksForm.setPostPriorStatus(packageHeaderBean.getPostPriorStatus());
						packageWorksForm.setAnticipatedExpenditureCost(packageHeaderBean.getAnticipatedExpenditureCost());
					}
				

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageComponentsBeans())){
					packageWorksForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(packageDetailBean.getPackageComponentsBeans()));
				}

				if(!MisUtility.ifEmpty(packageDetailBean.getPackageWorksBeans())){
					packageWorksBeans = packageDetailBean.getPackageWorksBeans();
					for (PackageWorksBean packageWorksBean : packageWorksBeans) {
						packageWorksForm.setMethodOfProcurement(packageWorksBean.getMethodOfProcurement());
						packageWorksForm.setDesignInvestigationDate(MisUtility.convertDateToString(packageWorksBean.getDesignInvestigationDate()));
						packageWorksForm.setEstPreparedSanctionDate(MisUtility.convertDateToString(packageWorksBean.getEstPreparedSanctionDate()));
						packageWorksForm.setBankNocBidDate(MisUtility.convertDateToString(packageWorksBean.getBankNocBidDate()));
						packageWorksForm.setBidOpeningDate(MisUtility.convertDateToString(packageWorksBean.getBidOpeningDate()));
						packageWorksForm.setBidInvitationDate(MisUtility.convertDateToString(packageWorksBean.getBidInvitationDate()));
						packageWorksForm.setConAwardDecideDate(MisUtility.convertDateToString(packageWorksBean.getConAwardDecideDate()));
						packageWorksForm.setBankNocConAwardDate(MisUtility.convertDateToString(packageWorksBean.getConAwardDecideDate()));
						packageWorksForm.setConSignDate(MisUtility.convertDateToString(packageWorksBean.getConSignDate()));
						packageWorksForm.setConCompletionDate(MisUtility.convertDateToString(packageWorksBean.getConCompletionDate()));
						packageWorksForm.setPrepBidDocDate(MisUtility.convertDateToString(packageWorksBean.getPrepBidDocDate()));
						packageWorksForm.setPackageId(packageWorksBean.getPackageId());
						packageWorksForm.setTargetCommissioningDate(MisUtility.convertDateToString(packageWorksBean.getTargetCommissioningDate()));
				}
			}
			}else{
				refreshProcPackageWorksForm(packageWorksForm);
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
		PackageWorksForm packageWorksForm = (PackageWorksForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageWorksBO.deletePackageWorksFrom(packageWorksForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Package","Package Name -->"+packageWorksForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Package","Package Name -->"+packageWorksForm.getPackageId());
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
		refreshProcPackageWorksForm(packageWorksForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PackageWorksForm packageWorksForm = (PackageWorksForm)form;
		StringBuffer strngMessage = new StringBuffer();
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!(MisUtility.ifEmpty(packageWorksForm.getPlanId()))||packageWorksForm.getSubPlanId()==0||
					!(MisUtility.ifEmpty(packageWorksForm.getSchemeId())) || !MisUtility.ifEmpty(packageWorksForm.getEstimatePackageCost())|| !MisUtility.ifEmpty(packageWorksForm.getVillageId())|| !MisUtility.ifEmpty(packageWorksForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageWorksForm.getMethodOfProcurement())|| !MisUtility.ifEmpty(packageWorksForm.getTargetCommissioningDate())){
				
				   if(!(MisUtility.ifEmpty(packageWorksForm.getPlanId()))){
					strngMessage.append("Please enter Plan Id");
				    }			
					if(packageWorksForm.getSubPlanId()==0){
						strngMessage.append("<br> Please enter Sub-Plan Id");	
					}
					if(!(MisUtility.ifEmpty(packageWorksForm.getSchemeId()))){
							strngMessage.append("<br> Please enter Scheme Id");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getEstimatePackageCost())){
							strngMessage.append("<br> Please enter Estimated Cost");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getVillageId())){
							strngMessage.append("<br> Please select Village Id");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getPostPriorStatus())){
						strngMessage.append("<br> Please select Post/Prior review ");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getMethodOfProcurement())){
						strngMessage.append("<br> Please select Method Of Procurement");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getTargetCommissioningDate())){
						strngMessage.append("<br> Please enter Target Commissioning Date");
					}
					
					
				
				throw new MISException(MISExceptionCodes.MIS004,strngMessage.toString());
			}
			strngMessage = new StringBuffer();
			if((!MisUtility.ifEmpty(packageWorksForm.getComponentNameId()))||(MisUtility.ifEmpty(packageWorksForm.getPackageComponentsDatagrid().getAddedData())))
			{
				/*if(!MisUtility.ifEmpty(packageWorksForm.getComponentNameId())){
					strngMessage.append("<br> Please enter Component Name");
				}*/
				if(MisUtility.ifEmpty(packageWorksForm.getPackageComponentsDatagrid().getAddedData())){
					strngMessage.append("<br> Please attach Components of package");
				}
				
				throw new MISException(MISExceptionCodes.MIS004,strngMessage.toString());
			}
			System.out.println("before call method======");
			packageBasedComponentLogicCall(packageWorksForm.getPackageComponentsDatagrid(),packageWorksForm.getSchemeId());
			status = packageWorksBO.savePackageWorksFrom(packageWorksForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Package","Package Name -->"+packageWorksForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageWorksForm(packageWorksForm);
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
				ActionMessage message = new ActionMessage("error.missing.field",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			else if(MISExceptionCodes.MIS014.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.missing.field",e.getMessage());
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
		refreshProcPackageWorksForm(packageWorksForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageWorksForm packageWorksForm = (PackageWorksForm)form;
		boolean status = false;
		try {
			StringBuffer strngMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!(MisUtility.ifEmpty(packageWorksForm.getPlanId()))||packageWorksForm.getSubPlanId()==0||
					!(MisUtility.ifEmpty(packageWorksForm.getSchemeId())) || !MisUtility.ifEmpty(packageWorksForm.getEstimatePackageCost())|| !MisUtility.ifEmpty(packageWorksForm.getVillageId())|| !MisUtility.ifEmpty(packageWorksForm.getPostPriorStatus())|| !MisUtility.ifEmpty(packageWorksForm.getMethodOfProcurement())|| !MisUtility.ifEmpty(packageWorksForm.getTargetCommissioningDate())){
				
				   if(!(MisUtility.ifEmpty(packageWorksForm.getPlanId()))){
					strngMessage.append(" Please enter Plan Id");
				    }			
					if(packageWorksForm.getSubPlanId()==0){
						strngMessage.append("<br> Please enter Sub-Plan Id");	
					}
					if(!(MisUtility.ifEmpty(packageWorksForm.getSchemeId()))){
							strngMessage.append("<br> Please enter Scheme Id");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getEstimatePackageCost())){
							strngMessage.append("<br> Please enter Estimated Cost");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getVillageId())){
							strngMessage.append("<br> Please select Village Id");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getPostPriorStatus())){
						strngMessage.append("<br> Please select Post/Prior review ");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getMethodOfProcurement())){
						strngMessage.append("<br> Please select Method Of Procurement");
					}
					if(!MisUtility.ifEmpty(packageWorksForm.getTargetCommissioningDate())){
						strngMessage.append("<br> Please enter Target Commissioning Date");
					}
				
				
				throw new MISException(MISExceptionCodes.MIS004,strngMessage.toString());
			}
//			strngMessage = new StringBuffer();
//			if((!MisUtility.ifEmpty(packageWorksForm.getComponentNameId()))||(MisUtility.ifEmpty(packageWorksForm.getPackageComponentsDatagrid().getAddedData())))
//			{
//				if(!MisUtility.ifEmpty(packageWorksForm.getComponentNameId())){
//					strngMessage.append("<br> Please enter Component Name");
//				}
//				if(MisUtility.ifEmpty(packageWorksForm.getPackageComponentsDatagrid().getAddedData())){
//					strngMessage.append("<br> Please attach Components of package");
//				}
//				
//				throw new MISException(MISExceptionCodes.MIS004,strngMessage.toString());
//			}
			packageComponentLogicCall(packageWorksForm.getPackageComponentsDatagrid(),packageWorksForm.getPackageId());
			packageBasedComponentLogicCall(packageWorksForm.getPackageComponentsDatagrid(),packageWorksForm.getSchemeId());
			status = packageWorksBO.updatePackageWorksFrom(packageWorksForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Package","Package Name -->"+packageWorksForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshProcPackageWorksForm(packageWorksForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Package","Package Name -->"+packageWorksForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			 if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.missing.field",e.getMessage());
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
		//refreshProcPackageWorksForm(packageWorksForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PackageWorksForm packageWorksForm = (PackageWorksForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageWorksBO.postPackageWorksFrom(packageWorksForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Package","Package Name -->"+packageWorksForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Package","Package Name -->"+packageWorksForm.getPackageId());
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
		
		refreshProcPackageWorksForm(packageWorksForm);
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
		PackageWorksForm packageWorksForm = (PackageWorksForm)form;
		System.out.println("IN UNSPCIFIED Package Works");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshProcPackageWorksForm(packageWorksForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	private void refreshProcPackageWorksForm(PackageWorksForm packageWorksForm){
		packageWorksForm.setLocationId(null);
		packageWorksForm.setPlanId(null);
		packageWorksForm.setSubPlanId(0);
		packageWorksForm.setSchemeId(null);
		packageWorksForm.setPackageDescription(null);
		packageWorksForm.setPrepBidDocDate(null);
		packageWorksForm.setMethodOfProcurement(null);
		packageWorksForm.setDesignInvestigationDate(null);
		packageWorksForm.setEstimatePackageCost(new BigDecimal(0.0));
		packageWorksForm.setAnticipatedExpenditureCost(new BigDecimal(0.0));
		packageWorksForm.setEstPreparedSanctionDate(null);
		packageWorksForm.setBankNocBidDate(null);
		packageWorksForm.setBidOpeningDate(null);
		packageWorksForm.setBidInvitationDate(null);
		packageWorksForm.setConAwardDecideDate(null);
		packageWorksForm.setBankNocConAwardDate(null);
		packageWorksForm.setConSignDate(null);
		packageWorksForm.setConCompletionDate(null);
		packageWorksForm.setPackageId(null);
		packageWorksForm.setPackageComponentsDatagrid(getPackageComponentsDatagrid(null));
		packageWorksForm.setPostPriorStatus(null);
		packageWorksForm.setTargetCommissioningDate(null);
	}
	
	
	
	public ActionForward fetchPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			boolean releaseStatus = false;
			System.out.println("Location"+request.getParameter("locationId"));
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanIds(request.getParameter("locationId"),releaseStatus,MISConstants.MIS_TYPE_OF_TENDER_WORKS);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(createProcPlanBeans)){
				for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
					buffer.append("<option value=\"").append(createProcPlanBean.getPlanId()).append("\">");
					buffer.append(createProcPlanBean.getPlanId()+" ["+createProcPlanBean.getPlanDescription()+"...]");
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
	
	/*public ActionForward fetchSubPlanIdsPrw2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ProcSubPlanPrw2HeaderBean> procSubPlanHeaderBeans = new TreeSet<ProcSubPlanPrw2HeaderBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(MisUtility.ifEmpty(request.getParameter("planId"))){
				procSubPlanHeaderBeans = procSubPlanDao.getSubPlanIdsPrw2(request.getParameter("planId"),statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(procSubPlanHeaderBeans)){
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
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(procSubPlanHeaderBeans)){
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
	
	public ActionForward fetchSubPlanSchemes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<SubPlanSchemeBean> subPlanSchemeBeans = new ArrayList<SubPlanSchemeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		//	if(request.getParameter("projectCode").equals("prwss1")||request.getParameter("projectCode")=="prwss1"){
			SubPlanSchemeBean subPlanSchemeBean = new SubPlanSchemeBean();
			if(MisUtility.ifEmpty(request.getParameter("subPlanId"))){
				subPlanSchemeBean.setSubPlanId(new Long(request.getParameter("subPlanId")));
				subPlanSchemeBeans = procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
//				buffer.append("<option value='' selected>");
//				buffer.append("Select");
//				buffer.append("</option>");
			for (SubPlanSchemeBean planSchemeBean : subPlanSchemeBeans) {
			    	buffer.append("<option value=\"").append(planSchemeBean.getSchemeId()).append("\">");
					buffer.append(planSchemeBean.getSchemeName()+" ["+planSchemeBean.getSchemeId()+"]");
					buffer.append("</option>");
				}		
			}
			System.out.println("Buffer: "+buffer.toString());
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
	//fetchcomponentNameIdBySchemeId
	public ActionForward fetchVillageIdBySchemeId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			//System.out.println("inside villlllllllllllllllllllllll");
			//System.out.println("schemeiddddddddddd"+request.getParameter("schemeId"));
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			if(MisUtility.ifEmpty(request.getParameter("schemeId"))){
				//System.out.println("out sideeeeeeeeeeeeeeee");
				schemeVillageBean.setSchemeId(request.getParameter("schemeId"));
				schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
			for (SchemeVillageBean schemeVillageBean2 : schemeVillageBeans) {
			    	buffer.append("<option value=\"").append(schemeVillageBean2.getVillageId()).append("\">");
					buffer.append(schemeVillageBean2.getVillageId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+schemeVillageBean2.getVillageName());
					buffer.append("</option>");
				}		
				buffer.append("<option value='common'>");
				buffer.append("Common");
				buffer.append("</option>");
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
	
	public ActionForward fetchcomponentNameIdBySchemeId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		    StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("schemeId"))){
				String schemeId=request.getParameter("schemeId");
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				System.out.println("before start with ====");
			if(schemeId.startsWith("TW")){
				System.out.println("inside TW======");
			    	buffer.append("<option value='TUBEWELL_INSTALLATION'>");
					buffer.append("Tubewell Installation");
					buffer.append("</option>");
					buffer.append("<option value='SITE_DEVELOPMENT'>");
					buffer.append("Site Development");
					buffer.append("</option>");
					buffer.append("<option value='PUMP_CHAMBER'>");
					buffer.append("Pump Chamber");
					buffer.append("</option>");
					buffer.append("<option value='PUMPING_MACHINERY'>");
					buffer.append("Pumping Machinery");
					buffer.append("</option>");
					/*buffer.append("<option value='DISINFECTION_UNIT'>");
					buffer.append("Disinfection Unit");
					buffer.append("</option>");*/
					buffer.append("<option value='RCC_OHSR'>");
					buffer.append("RCC OHSR");
					buffer.append("</option>");
					buffer.append("<option value='DISTRIBUTION_PIPELINE'>");
					buffer.append("Distribution Pipeline");
					buffer.append("</option>");
				}		
			if(schemeId.startsWith("CA")){
				
				buffer.append("<option value='HEAD_WORK_AND_SITE_DEVELOPMENT'>");
				buffer.append("Head Work & site Development");
				buffer.append("</option>");
				buffer.append("<option value='PUMP_CHAMBER'>");
				buffer.append("Pump Chamber");
				buffer.append("</option>");
				buffer.append("<option value='PUMPING_MACHINERY'>");
				buffer.append("Pumping Machinery");
				buffer.append("</option>");
				/*buffer.append("<option value='DISINFECTION_UNIT'>");
				buffer.append("Disinfection Unit");
				buffer.append("</option>");*/
				buffer.append("<option value='RCC_OHSR'>");
				buffer.append("RCC OHSR");
				buffer.append("</option>");
				buffer.append("<option value='DISTRIBUTION_PIPELINE'>");
				buffer.append("Distribution Pipeline");
				buffer.append("</option>");
				
			}
			if(schemeId.startsWith("SEW")){
				buffer.append("<option value='CONSTRUCTION_OF_STP'>");
				buffer.append("Construction of  STP");
				buffer.append("</option>");
				buffer.append("<option value='FACULATIVE_AND_MATURATION_POND'>");
				buffer.append("Faculative & Maturation Pond");
				buffer.append("</option>");
				buffer.append("<option value='SLUDGE_DRYING_BED'>");
				buffer.append("Sludge Drying bed");
				buffer.append("</option>");
				buffer.append("<option value='HIGH_LEVEL_TANK'>");
				buffer.append("High level Tank");
				buffer.append("</option>");
				buffer.append("<option value='S&S_TANK'>");
				buffer.append("S&S Tank");
				buffer.append("</option>");
				buffer.append("<option value='CLEAR_WATER_TANK'>");
				buffer.append("Clear Water Tank");
				buffer.append("</option>");
				buffer.append("<option value='CIVIL_WORK_OF_GEN_SET_CUM_REST_ROOM'>");
				buffer.append("Civil Work of Gen-set cum Rest room");
				buffer.append("</option>");
				buffer.append("<option value='LYING_OF_SEWER_PIPE_LINE'>");
				buffer.append("Lying of sewer pipe line");
				buffer.append("</option>");
				buffer.append("<option value='MACHINERY_INSTALLATION'>");
				buffer.append("Machinery installation");
				buffer.append("</option>");
				buffer.append("<option value='CONSTRUCTION_OF_MANHOLES'>");
				buffer.append("Construction of Manholes");
				buffer.append("</option>");
				
			}
			if(schemeId.startsWith("IMP")){
				buffer.append("<option value='TUBEWELL'>");
				buffer.append("Tubewell");
				buffer.append("</option>");
				buffer.append("<option value='REPLACEMENT_OF_MACHINERY'>");
				buffer.append("Replacement of Machinery");
				buffer.append("</option>");
				buffer.append("<option value='REPAIR_OF_CIVIL_STRUCTURES'>");
				buffer.append("Repair of Civil Structures");
				buffer.append("</option>");
				buffer.append("<option value='REPLACEMENT_OF_FILTER_MEDIA'>");
				buffer.append("Replacement of filter Media");
				buffer.append("</option>");
				buffer.append("<option value='EXTENSION_OF_PIPELINE'>");
				buffer.append("Extension of Pipeline");
				buffer.append("</option>");
				buffer.append("<option value='REPAIR_OF_PIPELINE'>");
				buffer.append("Repair of Pipeline");
				buffer.append("</option>");
				buffer.append("<option value='INSTALLATION_WATER_METER'>");
				buffer.append("Installation of Water Meter");
				buffer.append("</option>");
				
				
			}
		}
			//System.out.println("Buffer: "+buffer.toString());
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
		List<PackageWorksGridBean> packageWorksGridBeans = new ArrayList<PackageWorksGridBean>();
		
		try {
			packageComponentsDatagrid = Datagrid.getInstance();
			packageComponentsDatagrid.setDataClass(PackageWorksGridBean.class);
			if(!MisUtility.ifEmpty(packageComponentsBeans)){
				PackageWorksGridBean packageWorksGridBean=null;
				for(PackageComponentsBean packageComponentsBean:packageComponentsBeans){
					packageWorksGridBean=new PackageWorksGridBean();
					packageWorksGridBean.setComponentName(packageComponentsBean.getComponentName());
					packageWorksGridBean.setId(packageComponentsBean.getId());
					//packageWorksGridBean.setPackageId(packageComponentsBean.getPackageId());
					packageWorksGridBeans.add(packageWorksGridBean);
				}
			}
			/*List<PackageComponentsBean> componentsBeans = new ArrayList<PackageComponentsBean>(packageComponentsBeans);
			packageComponentsDatagrid.setData(componentsBeans);		
			}else{
			List<PackageComponentsBean> componentsBeans = new ArrayList<PackageComponentsBean>();
			packageComponentsDatagrid.setData(componentsBeans);	
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		packageComponentsDatagrid.setData(packageWorksGridBeans);
		return packageComponentsDatagrid;
	}
	// to check  any package of scheme contain component 
	private void packageBasedComponentLogicCall(Datagrid workDatagrid,String schemeId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
	    String argument1 = "schemeId@component_name";
        @SuppressWarnings("unchecked")
        Collection<PackageWorksGridBean> packageComponentBeans = workDatagrid.getAddedData();
        int count = 0;
      //  if(!MisUtility.ifEmpty(packageComponentBeans)){
        System.out.println("size of this package"+packageComponentBeans.size());
              
              for (PackageWorksGridBean packageComponentBean : packageComponentBeans) {
            	 System.out.println(":component name  is"+packageComponentBean.getComponentName());
                    if(count==0){
                    argument2.append(schemeId+"~"+packageComponentBean.getComponentName());
                    ++count;
                    }
                    else if (count>0){
                          argument2.append("@"+schemeId+"~"+packageComponentBean.getComponentName());
                    }
                }
           //  }
        

		
		try{
		DataSource db = misJdcDaoImpl.getDataSource();
		Connection con = db.getConnection();
		System.out.println("before call method.....");
        CallableStatement cs = con.prepareCall("{ call prwss_main.bl_packagecompname(?,?) }");
        System.out.println("after call method======");
        System.out.println("argument1:"+argument1);
        System.out.println("argument2:"+argument2);
        System.out.println("argument2:"+argument2);
        
        cs.setString(1, argument1);
        cs.setString(2, argument2.toString());
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.execute();
        message = cs.getString(1);
        System.out.println("error message"+message);
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(MisUtility.ifEmpty(message)){
            throw new MISException(MISExceptionCodes.MIS004,message);
      }

	}
	
	private void packageComponentLogicCall(Datagrid workDatagrid,String packageId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
	    String argument1 = "packageId@component_name";
        @SuppressWarnings("unchecked")
        Collection<PackageWorksGridBean> packageComponentBeans = workDatagrid.getAddedData();
        int count = 0;
      //  if(!MisUtility.ifEmpty(packageComponentBeans)){
        System.out.println("size of this package"+packageComponentBeans.size());
              
              for (PackageWorksGridBean packageComponentBean : packageComponentBeans) {
            	 System.out.println(":component name  is"+packageComponentBean.getComponentName());
                    if(count==0){
                    argument2.append(packageId+"~"+packageComponentBean.getComponentName());
                    ++count;
                    }
                    else if (count>0){
                          argument2.append("@"+packageId+"~"+packageComponentBean.getComponentName());
                    }
                }
           //  }
        

		
		try{
		DataSource db = misJdcDaoImpl.getDataSource();
		Connection con = db.getConnection();
        CallableStatement cs = con.prepareCall("{ call prwss_main.bl_compname(?,?) }");
        System.out.println("argument1:"+argument1);
        System.out.println("argument2:"+argument2);
        System.out.println("argument2:"+argument2);
        
        cs.setString(1, argument1);
        cs.setString(2, argument2.toString());
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.execute();
        message = cs.getString(1);
        System.out.println("error message"+message);
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(MisUtility.ifEmpty(message)){
            throw new MISException(MISExceptionCodes.MIS004,message);
      }

	}
	
}
