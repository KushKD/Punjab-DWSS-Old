package com.prwss.mis.ccdu.plan.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.ccdu.plan.CCDUPlanBO;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanDetailBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanHeaderBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.master.training.dao.TrainingBean;
import com.prwss.mis.master.training.dao.TrainingDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class CCDUPlanDetailsAction extends DispatchAction {
	private CCDUPlanBO ccduPlanBO;
	private CCDUPlanMasterDao ccduPlanMasterDao;
	private MISSessionBean misSessionBean;
	private TrainingDao trainingDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setTrainingDao(TrainingDao trainingDao) {
		this.trainingDao = trainingDao;
	}

	private Logger log = Logger.getLogger(CreateCCDUPlanAction.class);
	
	public void setCcduPlanBO(CCDUPlanBO ccduPlanBO) {
		this.ccduPlanBO = ccduPlanBO;
	}

	public void setCcduPlanMasterDao(CCDUPlanMasterDao ccduPlanMasterDao) {
		this.ccduPlanMasterDao = ccduPlanMasterDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
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
		CCDUPlanDetailsForm ccduPlanDetailsForm = (CCDUPlanDetailsForm)form;		
		 List<CCDUPlanHeaderBean> ccduPlanHeaderBeans = null; 
		try {
			ccduPlanHeaderBeans = ccduPlanBO.findCCDUPlanDetail(ccduPlanDetailsForm, statusList);
			if(!MisUtility.ifEmpty(ccduPlanHeaderBeans)){
				request.setAttribute("level2", "true");
				for (CCDUPlanHeaderBean ccduPlanHeaderBean : ccduPlanHeaderBeans) {
				ccduPlanDetailsForm.setPlanId(ccduPlanHeaderBean.getPlanId());
				ccduPlanDetailsForm.setLocationId(ccduPlanHeaderBean.getLocationId());
				ccduPlanDetailsForm.setImplementationCount(ccduPlanHeaderBean.getImplementationCount());
				ccduPlanDetailsForm.setOperationMaintenanceCount(ccduPlanHeaderBean.getOperationMaintenanceCount());
				ccduPlanDetailsForm.setPlanningCount(ccduPlanHeaderBean.getPlanningCount());
				ccduPlanDetailsForm.setPrePlanningCount(ccduPlanHeaderBean.getPrePlanningCount());
				
				ccduPlanDetailsForm.setImplementationCountCS(ccduPlanHeaderBean.getImplementationCountCS());
				ccduPlanDetailsForm.setOperationMaintenanceCountCS(ccduPlanHeaderBean.getOperationMaintenanceCountCS());
				ccduPlanDetailsForm.setPlanningCountCS(ccduPlanHeaderBean.getPlanningCountCS());
				ccduPlanDetailsForm.setPrePlanningCountCS(ccduPlanHeaderBean.getPrePlanningCountCS());
				
				ccduPlanDetailsForm.setImplementationCountNGO(ccduPlanHeaderBean.getImplementationCountNGO());
				ccduPlanDetailsForm.setOperationMaintenanceCountNGO(ccduPlanHeaderBean.getOperationMaintenanceCountNGO());
				ccduPlanDetailsForm.setPlanningCountNGO(ccduPlanHeaderBean.getPlanningCountNGO());
				ccduPlanDetailsForm.setPrePlanningCountNGO(ccduPlanHeaderBean.getPrePlanningCountNGO());
				ccduPlanDetailsForm.setCbSewerageCount(ccduPlanHeaderBean.getCbSewerageCount());
				ccduPlanDetailsForm.setSustainabilityWssCount(ccduPlanHeaderBean.getSustainabilityWssCount());
				ccduPlanDetailsForm.setCcduPlanDetailsDatagrid(getTrainingDetailDatagrid(ccduPlanHeaderBean.getCcduPlanDetailBeans()));
				}
			}else{
				refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.no.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("ccdu.error.find");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			e.printStackTrace();
			
		}
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		CCDUPlanDetailsForm ccduPlanDetailsForm = (CCDUPlanDetailsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			if(!MisUtility.ifEmpty(ccduPlanDetailsForm.getPlanId())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			status = ccduPlanBO.saveCCDUPlanDetail(ccduPlanDetailsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("ccdu.success.save", "Plan details","Location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.error.save", "Plan details","Location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
			System.out.println("1-->MISException:"+e.getCode()+"<>"+e.getMessage());
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				System.out.println("2-->MISException:"+e.getCode()+"<>"+e.getMessage());
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of CB Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of CB Plan Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		try {
			refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In UPDATE");
		this.setAttrib(request);
		CCDUPlanDetailsForm ccduPlanDetailsForm = (CCDUPlanDetailsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = ccduPlanBO.updateCCDUPlanDetail(ccduPlanDetailsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("ccdu.success.update", "Plan details","location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.error.update",  "Plan details","location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.fatal.error.save", "Update of Plan details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("ccdu.fatal.error.save", "Update of Plan details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		CCDUPlanDetailsForm ccduPlanDetailsForm = (CCDUPlanDetailsForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = ccduPlanBO.deleteCCDUPlanDetail(ccduPlanDetailsForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("ccdu.success.delete", "Plan details","location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.error.delete", "Plan details","location -->"+ccduPlanDetailsForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("ccdu.fatal.error.save", "Delete of Plan details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("ccdu.fatal.error.save", "Deletes of Plan details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		
		try {
			CCDUPlanDetailsForm ccduPlanDetailsForm = (CCDUPlanDetailsForm) form;
			refreshCCDUPlanDetailsForm(ccduPlanDetailsForm);
			Set<LabelValueBean> ccduPlanIds = null;
			ccduPlanIds = getPlanIds();
			request.getSession().setAttribute("ccduPlanIds", ccduPlanIds);
			Set<LabelValueBean> locationIds = null;
			locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);
			/*Set<LabelValueBean> trainingIds = null;
			trainingIds = getTrainings();
			request.getSession().setAttribute("trainingIds", trainingIds);*/
			
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}

	private Set<LabelValueBean> getPlanIds(){
		Set<LabelValueBean> ccduPlanIds = null;
		Set<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		try {
			ccduPlanMasterBeans = ccduPlanMasterDao.getDistinctCCDUPlan();
			ccduPlanIds = new HashSet<LabelValueBean>();
			for (CCDUPlanMasterBean ccduPlanMasterBean : ccduPlanMasterBeans) {
				ccduPlanIds.add(new LabelValueBean(ccduPlanMasterBean.getPlanId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+MisUtility.convertDateToString(ccduPlanMasterBean.getFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(ccduPlanMasterBean.getToDate())+")",new Long(ccduPlanMasterBean.getPlanId()).toString()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return ccduPlanIds;
	}
	
	private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		List<String> locationTypeList = new ArrayList<String>();
		try {
			//locationTypeList.add("DPMC");
			locationTypeList.add("DO");
			locationTypeList.add("SPMC");
			locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
			locationIds = new TreeSet<LabelValueBean>();
			for (LocationBean locationBean : locationBeans) {
				locationIds.add(new LabelValueBean(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")",locationBean.getLocationId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return locationIds;
	}
	public ActionForward getTrainingIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		
		Set<TrainingBean> trainingBeans = null;
		StringBuffer buffer = new StringBuffer();		
		String locationId = request.getParameter("locationId");
		try{
			if(locationId.equals("SPMC")){
				trainingBeans = trainingDao.getDistinctTrainingIds("CCDUCST");
			}
			else{
				trainingBeans = trainingDao.getDistinctTrainingIds("CCDUCBT");
			}
			System.out.println("-----------Size is "+trainingBeans.size());
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			 if(!MisUtility.ifEmpty(trainingBeans)){
				 for(TrainingBean trainingBean:trainingBeans){
					buffer.append("<option value=\"").append(trainingBean.getTrainingId()).append("\">");
					buffer.append(trainingBean.getTrainingId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+ trainingBean.getTrainingName());
					buffer.append("</option>");	
				 }
			 }
			 PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e);
		}catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	private void refreshCCDUPlanDetailsForm (CCDUPlanDetailsForm ccduPlanDetailsForm) throws MISException{
			ccduPlanDetailsForm.setImplementationCount(0);
			ccduPlanDetailsForm.setLocationId(null);
			ccduPlanDetailsForm.setOperationMaintenanceCount(0);
			ccduPlanDetailsForm.setPlanId(0);
			ccduPlanDetailsForm.setPlanningCount(0);
			ccduPlanDetailsForm.setPrePlanningCount(0);
			ccduPlanDetailsForm.setCcduPlanDetailsDatagrid(getTrainingDetailDatagrid(null));
		}
		
		private Datagrid getTrainingDetailDatagrid(Set<CCDUPlanDetailBean> ccduPlanDetailBeans) throws MISException {
			List<PlanTrainingDetailGridBean> planTrainingDetailGridBeans = new ArrayList<PlanTrainingDetailGridBean>();
			Datagrid ccduPlanDetailsDatagrid = Datagrid.getInstance();
			try {
				ccduPlanDetailsDatagrid.setDataClass(PlanTrainingDetailGridBean.class);
				if (!MisUtility.ifEmpty(ccduPlanDetailBeans)) {				
					PlanTrainingDetailGridBean planTrainingDetailGridBean = null;
					for (CCDUPlanDetailBean ccduPlanDetailBean : ccduPlanDetailBeans) {
						planTrainingDetailGridBean = new PlanTrainingDetailGridBean();
						planTrainingDetailGridBean.setCount(ccduPlanDetailBean.getCount());
						planTrainingDetailGridBean.setTrainingId(ccduPlanDetailBean.getTrainingBean().getTrainingId());
						planTrainingDetailGridBeans.add(planTrainingDetailGridBean);
					}
				}
				ccduPlanDetailsDatagrid.setData(planTrainingDetailGridBeans);
			} catch (Exception e) {
				log.error(e);
				throw new MISException(e);
				 
			}
			return ccduPlanDetailsDatagrid;
	}
	
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "ttttttttttt");
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@locationId");

	}

}
