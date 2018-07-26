package com.prwss.mis.hr.trainingmanagent.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import com.prwss.mis.hr.trainingmanagent.HRTrainingManagementBO;
import com.prwss.mis.hr.trainingmanagent.HRTrainingManagementBean;
import com.prwss.mis.hr.trainingscheduleplan.TrainingScheduleHeaderBean;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailBean;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailDao;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleHeaderDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class HRTrainingManagementAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(HRTrainingManagementAction.class);
	private MISSessionBean misSessionBean;
	private TrainingScheduleHeaderDao trainingScheduleHeaderDao;
	private EmployeeDao employeeDao;
	private TrainingScheduleDetailDao trainingScheduleDetailDao;
	private HRTrainingManagementBO hrTrainingManagementBO;
	
	public void setHrTrainingManagementBO(
			HRTrainingManagementBO hrTrainingManagementBO) {
		this.hrTrainingManagementBO = hrTrainingManagementBO;
	}


	public void setTrainingScheduleDetailDao(
			TrainingScheduleDetailDao trainingScheduleDetailDao) {
		this.trainingScheduleDetailDao = trainingScheduleDetailDao;
	}


	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}


	public void setTrainingScheduleHeaderDao(
			TrainingScheduleHeaderDao trainingScheduleHeaderDao) {
		this.trainingScheduleHeaderDao = trainingScheduleHeaderDao;
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
			request.setAttribute("level2", "true");
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
			HRTrainingManagementForm hrTrainingManagementForm = (HRTrainingManagementForm)form;
			List<HRTrainingManagementBean> hrTrainingManagementBeans = null;
			hrTrainingManagementBeans = hrTrainingManagementBO.findHRTrainingManagementBean(hrTrainingManagementForm, statusList);
			hrTrainingManagementForm.setLocationId(hrTrainingManagementForm.getLocationId());
			request.setAttribute("trainingPlanId", hrTrainingManagementForm.getTrainingPlanId());
			request.setAttribute("trainingObjective", hrTrainingManagementForm.getTrainingObjective());
			if(!MisUtility.ifEmpty(hrTrainingManagementBeans)){
				hrTrainingManagementForm.setEmployeeTrainingDetailDatagrid(getEmployeeTrainingDetailDatagrid(hrTrainingManagementBeans));
			}else{
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("No.record", "Found For ","This Transaction. Please Submit Employee Training Details For The Training--->"+hrTrainingManagementForm.getTrainingObjective());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
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
	
		
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		HRTrainingManagementForm hrTrainingManagementForm = (HRTrainingManagementForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

//			if(MisUtility.ifEmpty(hrTrainingManagementForm.getEmployeeTrainingDetailDatagrid().getAddedData())){
//				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
//			}
			status = hrTrainingManagementBO.updateHRTrainingManagementBean(hrTrainingManagementForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","HR Training Management","Training Objective -->"+hrTrainingManagementForm.getTrainingObjective());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","HR Training Management","Training Objective -->"+hrTrainingManagementForm.getTrainingObjective());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
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
		refreshHRTrainingManagementForm(hrTrainingManagementForm);
		return mapping.findForward("display");
	}
		
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		HRTrainingManagementForm hrTrainingManagementForm = (HRTrainingManagementForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshHRTrainingManagementForm(hrTrainingManagementForm);
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
		request.setAttribute("d__ky", "locationId@trainingPlanId@trainingObjectiveId");
	}
	
	private void refreshHRTrainingManagementForm(HRTrainingManagementForm hrTrainingManagementForm){
		hrTrainingManagementForm.setLocationId(null);
		hrTrainingManagementForm.setTrainingObjective(null);
		hrTrainingManagementForm.setTrainingPlanId(0);
		hrTrainingManagementForm.setEmployeeTrainingDetailDatagrid(getEmployeeTrainingDetailDatagrid(null));
	}
	
	private Datagrid getEmployeeTrainingDetailDatagrid(List<HRTrainingManagementBean> hrTrainingManagementBeans) {
	    Datagrid employeeTrainingDetailDatagrid= null;
		List<HRTrainingManagementGridBean> hrTrainingManagementGridBeans = new ArrayList<HRTrainingManagementGridBean>();
		try {
			employeeTrainingDetailDatagrid = Datagrid.getInstance();
			employeeTrainingDetailDatagrid.setDataClass(HRTrainingManagementGridBean.class);
			if (!MisUtility.ifEmpty(hrTrainingManagementBeans)) {
				HRTrainingManagementGridBean hrTrainingManagementGridBean = null;
				for (HRTrainingManagementBean hrTrainingManagementBean : hrTrainingManagementBeans) {
					hrTrainingManagementGridBean = new HRTrainingManagementGridBean();
					hrTrainingManagementGridBean.setId(hrTrainingManagementBean.getId());
					hrTrainingManagementGridBean.setEmployeeId(hrTrainingManagementBean.getEmployeeId());
					hrTrainingManagementGridBean.setFromDate(MisUtility.convertDateToString(hrTrainingManagementBean.getFromDate()));
					hrTrainingManagementGridBean.setToDate(MisUtility.convertDateToString(hrTrainingManagementBean.getToDate()));
					hrTrainingManagementGridBean.setRemarks(hrTrainingManagementBean.getRemarks());
					hrTrainingManagementGridBean.setTrainingVenue(hrTrainingManagementBean.getTrainingVenue());
					hrTrainingManagementGridBeans.add(hrTrainingManagementGridBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		employeeTrainingDetailDatagrid.setData(hrTrainingManagementGridBeans);
		return employeeTrainingDetailDatagrid;
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
		TrainingScheduleHeaderBean trainingScheduleHeaderBean = new TrainingScheduleHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				trainingScheduleHeaderBean.setLocationId(request.getParameter("locationId"));
				trainingScheduleHeaderBeans = new TreeSet<TrainingScheduleHeaderBean>(trainingScheduleHeaderDao.findTrainingScheduleHeader(trainingScheduleHeaderBean, statusList));
				buffer.append("<option value='' selected>");
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
	
	public ActionForward fetchEmployees(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<EmployeeBean> employeeBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				employeeBeans = employeeDao.getDistinctEmployeeIds(request.getParameter("locationId"),statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (EmployeeBean employeeBean : employeeBeans) {
					
					buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
					buffer.append(employeeBean.getEmployeeName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean.getEmployeeId());
					buffer.append("</option>");
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
	
	public ActionForward fetchTrainingObjective(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("trainingPlanId"))){
				System.out.println("IN");
				TrainingScheduleDetailBean trainingScheduleDetailBean = new TrainingScheduleDetailBean();
				trainingScheduleDetailBean.setTrainingPlanId(new Long(request.getParameter("trainingPlanId")));
				trainingScheduleDetailBeans = trainingScheduleDetailDao.findTrainingScheduleDetailBeans(trainingScheduleDetailBean, statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (TrainingScheduleDetailBean scheduleDetailBean : trainingScheduleDetailBeans) {
					buffer.append("<option value=\"").append(scheduleDetailBean.getTrainingObjective()).append("\">");
					buffer.append(scheduleDetailBean.getTrainingObjective());
					buffer.append("</option>");
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
	


}
