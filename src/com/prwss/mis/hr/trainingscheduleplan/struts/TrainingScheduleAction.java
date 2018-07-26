package com.prwss.mis.hr.trainingscheduleplan.struts;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.trainingscheduleplan.TrainingScheduleHeaderBean;
import com.prwss.mis.hr.trainingscheduleplan.TrainingSchedulePlanBO;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailBean;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleHeaderDao;
import com.prwss.mis.master.training.dao.TrainingBean;
import com.prwss.mis.master.training.dao.TrainingDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TrainingScheduleAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(TrainingScheduleAction.class);
	private MISSessionBean misSessionBean;
	private TrainingSchedulePlanBO trainingSchedulePlanBO;
	private TrainingScheduleHeaderDao trainingScheduleHeaderDao;
private TrainingDao trainingDao;
	
	public void setTrainingDao(TrainingDao trainingDao) {
		this.trainingDao = trainingDao;
	}
	
	public void setTrainingScheduleHeaderDao(
			TrainingScheduleHeaderDao trainingScheduleHeaderDao) {
		this.trainingScheduleHeaderDao = trainingScheduleHeaderDao;
	}


	public void setTrainingSchedulePlanBO(
			TrainingSchedulePlanBO trainingSchedulePlanBO) {
		this.trainingSchedulePlanBO = trainingSchedulePlanBO;
	}
	
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find");
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
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		try {
			TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form;
			List<TrainingScheduleHeaderBean> trainingScheduleHeaderBeans = null;
			trainingScheduleHeaderBeans = trainingSchedulePlanBO.findTrainingSchedulePlan(trainingScheduleForm, statusList);
			if(!MisUtility.ifEmpty(trainingScheduleHeaderBeans)){
				request.setAttribute("level2", "true");
				for (TrainingScheduleHeaderBean trainingScheduleHeaderBean : trainingScheduleHeaderBeans) {
					trainingScheduleForm.setLocationId(trainingScheduleHeaderBean.getLocationId());
					trainingScheduleForm.setPlanFromDate(MisUtility.convertDateToString(trainingScheduleHeaderBean.getPlanFromDate()));
					trainingScheduleForm.setPlanToDate(MisUtility.convertDateToString(trainingScheduleHeaderBean.getPlanToDate()));
					request.setAttribute("trainingPlanId", trainingScheduleHeaderBean.getTrainingPlanId());
					trainingScheduleForm.setTrainingDetailsDatagrid(getTrainingDetailsDatagrid(trainingScheduleHeaderBean.getTrainingScheduleDetailBeans()));
				}
			}else{
				refreshTrainingScheduleForm(trainingScheduleForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Training Schedule Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = trainingSchedulePlanBO.deleteTrainingSchedulePlan(trainingScheduleForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of HR Training Plan");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Employee Target Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshTrainingScheduleForm(trainingScheduleForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form; 
		long trainingPlanId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(trainingScheduleForm.getLocationId())||!MisUtility.ifEmpty(trainingScheduleForm.getPlanFromDate())||!MisUtility.ifEmpty(trainingScheduleForm.getPlanToDate())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			if(MisUtility.ifEmpty(trainingScheduleForm.getTrainingDetailsDatagrid().getAddedData())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			trainingPlanId = trainingSchedulePlanBO.saveTrainingSchedulePlan(trainingScheduleForm, misSessionBean);
			if (!(trainingPlanId==0)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","HR Training Plan","Plan Code -->"+trainingPlanId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","HR Training Plan");
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
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of HR Training Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of HR Training Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshTrainingScheduleForm(trainingScheduleForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = trainingSchedulePlanBO.updateTrainingSchedulePlan(trainingScheduleForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of HR Training Plan");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of HR Training Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshTrainingScheduleForm(trainingScheduleForm);
		return mapping.findForward("display");
	}
		
	

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			Set<LabelValueBean> trainingIds = null;
			trainingIds = getTrainings();
			request.getSession().setAttribute("trainingIds", trainingIds);
			refreshTrainingScheduleForm(trainingScheduleForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@trainingPlanId");
	}
	
	private void refreshTrainingScheduleForm(TrainingScheduleForm trainingScheduleForm){
		trainingScheduleForm.setTrainingPlanId(0);
		trainingScheduleForm.setLocationId(null);
		trainingScheduleForm.setPlanFromDate(null);
		trainingScheduleForm.setPlanToDate(null);
		trainingScheduleForm.setTrainingDetailsDatagrid(getTrainingDetailsDatagrid(null));
		
	}
	
	private Datagrid getTrainingDetailsDatagrid(Set<TrainingScheduleDetailBean> trainingScheduleDetailBeans) {
	    Datagrid trainingDetailsDatagrid= null;
		List<TrainingScheduleDetailGridBean> trainingScheduleDetailGridBeans = new ArrayList<TrainingScheduleDetailGridBean>();
		try {
			trainingDetailsDatagrid = Datagrid.getInstance();
			trainingDetailsDatagrid.setDataClass(TrainingScheduleDetailGridBean.class);
			if (!MisUtility.ifEmpty(trainingScheduleDetailBeans)) {
				TrainingScheduleDetailGridBean trainingScheduleDetailGridBean = null;
				for (TrainingScheduleDetailBean trainingScheduleDetailBean : trainingScheduleDetailBeans) {
					trainingScheduleDetailGridBean = new TrainingScheduleDetailGridBean();
					trainingScheduleDetailGridBean.setId(trainingScheduleDetailBean.getId());
					trainingScheduleDetailGridBean.setNumberOfTraining(trainingScheduleDetailBean.getNumberOfTraining());
					trainingScheduleDetailGridBean.setTargetDestination(trainingScheduleDetailBean.getTargetDestination());
					trainingScheduleDetailGridBean.setTrainingDate(MisUtility.convertDateToString(trainingScheduleDetailBean.getTrainingDate()));
					trainingScheduleDetailGridBean.setTrainingObjective(trainingScheduleDetailBean.getTrainingObjective());
					trainingScheduleDetailGridBean.setRemarks(trainingScheduleDetailBean.getRemarks());
					trainingScheduleDetailGridBeans.add(trainingScheduleDetailGridBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		trainingDetailsDatagrid.setData(trainingScheduleDetailGridBeans);
		return trainingDetailsDatagrid;
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		TrainingScheduleForm trainingScheduleForm = (TrainingScheduleForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = trainingSchedulePlanBO.postTrainingSchedulePlan(trainingScheduleForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.post","HR Training Plan","Plan Code -->"+trainingScheduleForm.getTrainingPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","HR Training Plan");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","HR Training Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshTrainingScheduleForm(trainingScheduleForm);
		return mapping.findForward("display");
	}
	
//	private Set<LabelValueBean> getEmployees(MISSessionBean misSessionBean){
//		Set<LabelValueBean> employees = null;
//		Set<EmployeeBean> employeeBeans = null;
//		try {
//			List<String> statusList = new ArrayList<String>();
//			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//			EmployeeBean employeeBean = new EmployeeBean();
//			employeeBean.setReportingOfficerId(misSessionBean.getEnteredBy());
//			employeeBeans = employeeDao.getEmployeeByReportingOfficer(employeeBean, statusList);
//			employees = new HashSet<LabelValueBean>();
//				if(!MisUtility.ifEmpty(employeeBeans)){
//			for (EmployeeBean employeeBean2 : employeeBeans) {
//				employees.add(new LabelValueBean(employeeBean2.getEmployeeName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean2.getEmployeeId(),new Long(employeeBean2.getEmployeeId()).toString()));	
//					}
//				}
//		}catch (DataAccessException e) {
//			e.printStackTrace();
//			log.error(e.getMessage(),e);
//		}catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return employees;
//	}
//	
	public ActionForward fetchTrainingPlanId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<TrainingScheduleHeaderBean> trainingScheduleHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		TrainingScheduleHeaderBean trainingScheduleHeaderBean = new TrainingScheduleHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				trainingScheduleHeaderBean.setLocationId(request.getParameter("locationId"));
				trainingScheduleHeaderBeans = new TreeSet<TrainingScheduleHeaderBean>(trainingScheduleHeaderDao.findTrainingScheduleHeader(trainingScheduleHeaderBean, statusList));
				buffer.append("<option value='0'>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(trainingScheduleHeaderBeans)){
					for (TrainingScheduleHeaderBean trainingScheduleHeaderBean2 : trainingScheduleHeaderBeans) {
						buffer.append("<option value=\"").append(trainingScheduleHeaderBean2.getTrainingPlanId()).append("\">");
						buffer.append(trainingScheduleHeaderBean2.getTrainingPlanId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+MisUtility.convertDateToString(trainingScheduleHeaderBean2.getPlanFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(trainingScheduleHeaderBean2.getPlanToDate()));
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
	
	private Set<LabelValueBean> getTrainings(){
		Set<LabelValueBean> trainings = null;
		Set<TrainingBean> trainingBeans = null;
		try{
			trainingBeans = trainingDao.getDistinctTrainingIds("HRT");
			trainings = new HashSet<LabelValueBean>();
			for (TrainingBean trainingBean : trainingBeans) {
				trainings.add(new LabelValueBean(trainingBean.getTrainingName(),trainingBean.getTrainingName()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e);
		}
		return trainings;	
	}
	

}
