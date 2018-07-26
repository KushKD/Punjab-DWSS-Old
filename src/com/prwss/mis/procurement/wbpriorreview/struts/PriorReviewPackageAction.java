package com.prwss.mis.procurement.wbpriorreview.struts;

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
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.wbpriorreview.PriorReviewBO;

public class PriorReviewPackageAction extends DispatchAction {
	Logger log = Logger.getLogger(PriorReviewPackageAction.class);
	private MISSessionBean misSessionBean;
    private CreateProcPlanDao createProcPlanDao;
    private PriorReviewBO priorReviewBO;
    private PackageHeaderDao packageHeaderDao;
	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	public void setPriorReviewBO(PriorReviewBO priorReviewBO) {
		this.priorReviewBO = priorReviewBO;
	}

	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
	this.createProcPlanDao = createProcPlanDao;
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
			PriorReviewPackageForm priorReviewPackageForm = (PriorReviewPackageForm)form;
			List<PackageHeaderBean> packageHeaderBeans = null; 
			packageHeaderBeans = priorReviewBO.findPriorReviewPackage(priorReviewPackageForm, statusList);
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				request.setAttribute("packageHeaderBeans", packageHeaderBeans);
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("find.list");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
					
			}else{
				refreshPriorReviewPackageForm(priorReviewPackageForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of World Bank Prior Review Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PriorReviewPackageForm priorReviewPackageForm = (PriorReviewPackageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = priorReviewBO.updatePriorReviewPackage(priorReviewPackageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","World Bank Prior Review Details","Package Name -->"+priorReviewPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","World Bank Prior Review Details","Package Name -->"+priorReviewPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of World Bank Prior Review Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of World Bank Prior Review Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshPriorReviewPackageForm(priorReviewPackageForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "packageId@locationId@estimatedPackageCostId@subPlanId@planId@typeOfProcurementId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PriorReviewPackageForm priorReviewPackageForm = (PriorReviewPackageForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshPriorReviewPackageForm(priorReviewPackageForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	private void refreshPriorReviewPackageForm(PriorReviewPackageForm priorReviewPackageForm){
		priorReviewPackageForm.setLocationId(null);
		priorReviewPackageForm.setPlanId(null);
		priorReviewPackageForm.setSubPlanId(0);
		priorReviewPackageForm.setPackageId(null);
		priorReviewPackageForm.setPostPriorStatus(null);
		priorReviewPackageForm.setTypeOfProcurement(null);
		priorReviewPackageForm.setWbNocDate(null);
		priorReviewPackageForm.setWbNumber(null);
		priorReviewPackageForm.setWbRemarks(null);
		priorReviewPackageForm.setWbStatus(null);
		priorReviewPackageForm.setEstimatedPackageCost(new BigDecimal(0.00));
	}
	
	
	
	public ActionForward fetchPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				createProcPlanBeans = createProcPlanDao.getProcPlanIdsOnPlanType(request.getParameter("locationId"), request.getParameter("procurementType"));
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
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		try {
			PriorReviewPackageForm priorReviewPackageForm = (PriorReviewPackageForm)form;
			if(MisUtility.ifEmpty(request.getParameter("packageId"))){
			String packageId = request.getParameter("packageId");
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(packageId);
			packageHeaderBean.setLocationId(priorReviewPackageForm.getLocationId());
			packageHeaderBean = packageHeaderDao.findPackageForPriorReview(packageHeaderBean,null).get(0);
			if(MisUtility.ifEmpty(packageHeaderBean)){
				priorReviewPackageForm.setLocationId(packageHeaderBean.getLocationId());
				request.setAttribute("planId",packageHeaderBean.getPlanId());
				priorReviewPackageForm.setTypeOfProcurement(packageHeaderBean.getPackageType());
				priorReviewPackageForm.setPackageId(packageHeaderBean.getPackageId());
				priorReviewPackageForm.setEstimatedPackageCost(packageHeaderBean.getEstimatePackageCost());
				priorReviewPackageForm.setSubPlanId(packageHeaderBean.getSubPlanId());
				priorReviewPackageForm.setWbNocDate(MisUtility.convertDateToString(packageHeaderBean.getWbNocDate()));
				
				priorReviewPackageForm.setWbBidDocNocDate(MisUtility.convertDateToString(packageHeaderBean.getWbBidDocNocDate()));
				priorReviewPackageForm.setWbContractAwardNocDate(MisUtility.convertDateToString(packageHeaderBean.getWbContractAwardNocDate()));
				
				priorReviewPackageForm.setWbNumber(packageHeaderBean.getWbNumber());
				priorReviewPackageForm.setWbRemarks(packageHeaderBean.getWbRemarks());
				priorReviewPackageForm.setWbStatus(packageHeaderBean.getWbStatus());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		
		return mapping.findForward("display");
	}
}
