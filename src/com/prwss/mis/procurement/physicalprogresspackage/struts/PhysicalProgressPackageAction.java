package com.prwss.mis.procurement.physicalprogresspackage.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
import com.prwss.mis.masters.village.dao.PrwssVillageViewBeanWithMhOh;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsDao;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.physicalprogresspackage.PackageComponentsProgressBO;
import com.prwss.mis.procurement.physicalprogresspackage.dao.PhysicalProgressComponentsBean;
import com.prwss.mis.procurement.physicalprogresspackage.dao.PrwssSigningContractViewBean;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanDao;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanSchemeDao;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;
import com.prwss.mis.procurement.workspackage.struts.PackageWorksGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PhysicalProgressPackageAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(PhysicalProgressPackageAction.class);
	private MISSessionBean misSessionBean;
	private CreateProcPlanDao createProcPlanDao;
	private ProcSubPlanDao procSubPlanDao;
	private ProcSubPlanSchemeDao procSubPlanSchemeDao;
	private PackageHeaderDao packageHeaderDao;
	private PackageComponentsDao packageComponentsDao;
	private PackageComponentsProgressBO packageComponentsProgressBO;
    private MISJdcDaoImpl misJdcDaoImpl;
    
    public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	
	public void setPackageComponentsProgressBO(
			PackageComponentsProgressBO packageComponentsProgressBO) {
		this.packageComponentsProgressBO = packageComponentsProgressBO;
	}

	public void setPackageComponentsDao(PackageComponentsDao packageComponentsDao) {
		this.packageComponentsDao = packageComponentsDao;
	}

	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
		this.createProcPlanDao = createProcPlanDao;
	}

	public void setProcSubPlanDao(ProcSubPlanDao procSubPlanDao) {
		this.procSubPlanDao = procSubPlanDao;
	}

	public void setProcSubPlanSchemeDao(ProcSubPlanSchemeDao procSubPlanSchemeDao) {
		this.procSubPlanSchemeDao = procSubPlanSchemeDao;
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
			PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form;
			List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = null;
			physicalProgressComponentsBeans = packageComponentsProgressBO.findPhysicalProgressPackageForm(physicalProgressPackageForm, statusList);
			if(!MisUtility.ifEmpty(physicalProgressComponentsBeans)){
				request.setAttribute("level2", "true");
				request.setAttribute("locationId", physicalProgressPackageForm.getLocationId());
				request.setAttribute("planId", physicalProgressPackageForm.getPlanId());
				request.setAttribute("subPlanId", physicalProgressPackageForm.getSubPlanId());
				request.setAttribute("schemeId", physicalProgressPackageForm.getSchemeId());
				request.setAttribute("packageId", physicalProgressPackageForm.getPackageId());
				request.setAttribute("componentName", physicalProgressPackageForm.getComponentName());
				physicalProgressPackageForm.setComponentPhysicalProgressDatagrid(getPackageComponentsProgressDatagrid(physicalProgressComponentsBeans));
				


			}else{
				refreshPhysicalPackageProgressForm(physicalProgressPackageForm);
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
//	public ActionForward delete(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws MISException {
//		this.setAttrib(request);
//		PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form;
//		boolean status = false;
//		try {
//			if (request.getSession().getAttribute("misSessionBean") != null) {
//				{
//					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
//				}
//			} else {
//				return mapping.findForward("login");
//			}
//
//			status = packageComponentsProgressBO.deletePhysicalProgressPackageForm(physicalProgressPackageForm, misSessionBean);
//			if (status){
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("success.delete","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//
//			} else {
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("error.delete","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//			}
//		} catch (MISException e) {
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//				ActionErrors errors = new ActionErrors();
//				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Package");
//				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//				saveErrors(request, errors);
//		} catch (Exception e) {
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//			ActionErrors errors = new ActionErrors();
//			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Package");
//			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//			saveErrors(request, errors);
//		}
//		refreshProcPackageGoodsForm(packageGoodsForm);
//		return mapping.findForward("display");
//	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form; 
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
			if(physicalProgressPackageForm.getPackageId().trim().equals("")||physicalProgressPackageForm.getPackageId().trim().equals("''")||physicalProgressPackageForm.getPlanId().trim().equals("''")||physicalProgressPackageForm.getSubPlanId()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(!MisUtility.ifEmpty(physicalProgressPackageForm.getComponentName())||!MisUtility.ifEmpty(physicalProgressPackageForm.getPercentCompletion())||!MisUtility.ifEmpty(physicalProgressPackageForm.getAsOnDate())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
				
			if(MisUtility.ifEmpty(physicalProgressPackageForm.getComponentPhysicalProgressDatagrid().getAddedData())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			/*boolean dateStatus = checkDate(physicalProgressPackageForm.getAsOnDate());
			if(!dateStatus){
				throw new MISException(MISExceptionCodes.MIS004,"As on Date can not be future date");
			}*/
			checkSigningOfContract(physicalProgressPackageForm.getPackageId());
			packageComponentLogicCall(physicalProgressPackageForm.getComponentPhysicalProgressDatagrid(),physicalProgressPackageForm.getPackageId());
			status = packageComponentsProgressBO.savePhysicalProgressPackageForm(physicalProgressPackageForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				
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
			}
			else if(MISExceptionCodes.MIS003.equals(e.getCode())){
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
		refreshPhysicalPackageProgressForm(physicalProgressPackageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			System.out.println("as on date in modify mode==="+physicalProgressPackageForm.getAsOnDate());
			/*boolean dateStatus = checkDate(physicalProgressPackageForm.getAsOnDate());
			if(!dateStatus){
				throw new MISException(MISExceptionCodes.MIS004,"As on Date can not be future date");
			}*/
			checkSigningOfContract(physicalProgressPackageForm.getPackageId());
			packageComponentLogicCall(physicalProgressPackageForm.getComponentPhysicalProgressDatagrid(),physicalProgressPackageForm.getPackageId());
			status = packageComponentsProgressBO.updatePhysicalProgressPackageForm(physicalProgressPackageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS003.equals(e.getCode())){
				 System.out.println("miscode===="+e.getCode());
				 log.error(e.getLocalizedMessage(),e);
				 e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			  }
			else{
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
		refreshPhysicalPackageProgressForm(physicalProgressPackageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = packageComponentsProgressBO.postPhysicalProgressPackageForm(physicalProgressPackageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Package","Package Name -->"+physicalProgressPackageForm.getPackageId());
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
		
		refreshPhysicalPackageProgressForm(physicalProgressPackageForm);
		return mapping.findForward("display");
	}
	
	
	private Datagrid getPackageComponentsProgressDatagrid(List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans) {
		    Datagrid packageComponentsDatagrid = null;
			List<PhysicalProgressComponentsGridBean> physicalProgressComponentsGridBeans = new ArrayList<PhysicalProgressComponentsGridBean>();
			try {
				packageComponentsDatagrid = Datagrid.getInstance();
				packageComponentsDatagrid.setDataClass(PhysicalProgressComponentsGridBean.class);
				if (!MisUtility.ifEmpty(physicalProgressComponentsBeans)) {
					PhysicalProgressComponentsGridBean progressComponentsGridBean = null;
					for (PhysicalProgressComponentsBean physicalProgressComponentsBean : physicalProgressComponentsBeans) {
						progressComponentsGridBean = new PhysicalProgressComponentsGridBean();
						progressComponentsGridBean.setAsOnDate(MisUtility.convertDateToString(physicalProgressComponentsBean.getAsOnDate()));
						progressComponentsGridBean.setId(physicalProgressComponentsBean.getId());
						progressComponentsGridBean.setPercentCompletion(physicalProgressComponentsBean.getPercentCompletion());
						progressComponentsGridBean.setPackageId(physicalProgressComponentsBean.getPackageId());
						progressComponentsGridBean.setComponentName(physicalProgressComponentsBean.getComponentName());
						physicalProgressComponentsGridBeans.add(progressComponentsGridBean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
			packageComponentsDatagrid.setData(physicalProgressComponentsGridBeans);
			return packageComponentsDatagrid;
		}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PhysicalProgressPackageForm physicalProgressPackageForm = (PhysicalProgressPackageForm)form;
		System.out.println("IN UNSPCIFIED Package Non Consulatancy");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshPhysicalPackageProgressForm(physicalProgressPackageForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	private PhysicalProgressPackageForm refreshPhysicalPackageProgressForm(PhysicalProgressPackageForm physicalProgressPackageForm){
		physicalProgressPackageForm.setPackageId(null);
		physicalProgressPackageForm.setPackageType(null);
		physicalProgressPackageForm.setComponentName(null);
		physicalProgressPackageForm.setLocationId(null);
		physicalProgressPackageForm.setAsOnDate(null);
		physicalProgressPackageForm.setPercentCompletion(null);
		physicalProgressPackageForm.setComponentPhysicalProgressDatagrid(getPackageComponentsProgressDatagrid(null));
		return physicalProgressPackageForm;
		
	}
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@subPlanId@schemeId@packageId@locationId");
	}
	
	public ActionForward fetchPackageIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<PackageHeaderBean> packageHeaderBeans = new TreeSet<PackageHeaderBean>();
		StringBuffer buffer = new StringBuffer();
		long subPlanId = 0;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(MisUtility.ifEmpty(request.getParameter("schemeId"))){
				System.out.println("From Fetch Package"+request.getParameter("schemeId"));
				subPlanId = new Long(request.getParameter("subPlanId"));
				packageHeaderBeans = packageHeaderDao.getPackageIds(request.getParameter("schemeId"),subPlanId);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
			for (PackageHeaderBean packageHeaderBean : packageHeaderBeans ) {
					buffer.append("<option value=\"").append(packageHeaderBean.getPackageId()).append("\">");
					buffer.append(packageHeaderBean.getPackageId());
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
	
	public ActionForward fetchPackageComponents(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<PackageComponentsBean> packageComponentsBeans = new ArrayList<PackageComponentsBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(MisUtility.ifEmpty(request.getParameter("packageId"))){
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(request.getParameter("packageId"));
				packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
			 
				if(!MisUtility.ifEmpty(packageComponentsBeans)){
					for (PackageComponentsBean bean : packageComponentsBeans ) {
						buffer.append("<option value=\"").append(bean.getComponentName()).append("\">");
						buffer.append(bean.getComponentName());
						buffer.append("</option>");
					}		
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 && !(buffer.equals("null")) ){
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
	
	public ActionForward fetchPlanIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println("in FetchPlanIds GOODS");
		try {
			boolean releaseStatus = false;
			System.out.println("Location"+request.getParameter("locationId"));
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				String procurementType = null;
				createProcPlanBeans = createProcPlanDao.getProcPlanIds(request.getParameter("locationId"),releaseStatus,procurementType);
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
				buffer.append("<option value='' selected>");
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
				/*buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");*/
			for (SubPlanSchemeBean planSchemeBean : subPlanSchemeBeans) {
			    	buffer.append("<option value=\"").append(planSchemeBean.getSchemeId()).append("\">");
					buffer.append(planSchemeBean.getSchemeName()+"("+planSchemeBean.getSchemeId()+")");
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
	
	public boolean checkDate(String asOnDate){
		StringBuffer currentDate = new StringBuffer();
		java.util.Date curDate = null;
		java.util.Date asOnDates = null;
		
		Calendar cal = new GregorianCalendar();
		currentDate.append(cal.get(Calendar.DAY_OF_MONTH));
		currentDate.append("-"+cal.get(Calendar.MONTH)+1);
		currentDate.append("-"+cal.get(Calendar.YEAR));
		 String myFormatString = "dd-MM-yyyy"; // for example
		 SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		try{
				curDate = df.parse(asOnDate);
				asOnDates = df.parse(currentDate.toString());
				if(curDate.after(asOnDates)){
					return false;
				}
		}catch (java.text.ParseException e) {
			e.printStackTrace();
		   } 
		
		return true;
	}

	public void checkSigningOfContract(String packageId)
			throws MISException {
			//	Set<TenderHeaderBean> tenders = null;
		
		    List<PrwssSigningContractViewBean> sigingContractBeans = new ArrayList<PrwssSigningContractViewBean>();
		    try {
			//List<String> statusList = new ArrayList<String>();
			//statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			//statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			
			if(MisUtility.ifEmpty(packageId)){
				
				sigingContractBeans = packageComponentsDao.findSigningOfContract(packageId);
			 
				for(PrwssSigningContractViewBean sigingContractBean:sigingContractBeans ){
					
					if(sigingContractBean.getSigningOfContract()==null){
						throw new MISException(MISExceptionCodes.MIS003,"Please first enter signing of contract date of package["+packageId+"]");
					}
					
				}
				
				}
			  }
				 catch (DataAccessException e) {
					log.error(e.getMostSpecificCause());
				} 
				
			}
	
	
	private void packageComponentLogicCall(Datagrid workDatagrid,String packageId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
	    String argument1 = "packageId@component_name@asOnDate";
        @SuppressWarnings("unchecked")
        Collection<PhysicalProgressComponentsGridBean> packageComponentBeans = workDatagrid.getAddedData();
        int count = 0;
      //  if(!MisUtility.ifEmpty(packageComponentBeans)){
        System.out.println("size of this package"+packageComponentBeans.size());
              
              for (PhysicalProgressComponentsGridBean packageComponentBean : packageComponentBeans) {
            	 System.out.println(":component name  is"+packageComponentBean.getComponentName());
                    if(count==0){
                    argument2.append(packageId+"~"+packageComponentBean.getComponentName()+"~"+packageComponentBean.getAsOnDate());
                    ++count;
                    }
                    else if (count>0){
                          argument2.append("@"+packageId+"~"+packageComponentBean.getComponentName()+"~"+packageComponentBean.getAsOnDate());
                    }
                }
           //  }
        

		
		try{
		DataSource db = misJdcDaoImpl.getDataSource();
		Connection con = db.getConnection();
        CallableStatement cs = con.prepareCall("{ call prwss_main.bl_physicalcompname(?,?) }");
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
            throw new MISException(MISExceptionCodes.MIS003,message);
      }

	}
	

}
