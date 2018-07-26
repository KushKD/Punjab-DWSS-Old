package com.prwss.mis.hr.employeevaluation.struts;

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
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.targetplan.EmployeeTargetBO;
import com.prwss.mis.hr.targetplan.EmployeeTargetDetailsBean;
import com.prwss.mis.hr.targetplan.EmployeeTargetHeaderBean;
import com.prwss.mis.hr.targetplan.dao.EmployeeTargetHeaderDao;
import com.prwss.mis.hr.targetplan.struts.EmployeeTargetGridBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeEvaluationAction extends DispatchAction {
	private Logger log = Logger.getLogger(EmployeeEvaluationAction.class);
	private MISSessionBean misSessionBean;
	private EmployeeDao employeeDao;
	private EmployeeTargetBO employeeTargetBO;
	private EmployeeTargetHeaderDao employeeTargetHeaderDao;
	
	
	
	public void setEmployeeTargetHeaderDao(
			EmployeeTargetHeaderDao employeeTargetHeaderDao) {
		this.employeeTargetHeaderDao = employeeTargetHeaderDao;
	}

	public void setEmployeeTargetBO(EmployeeTargetBO employeeTargetBO) {
		this.employeeTargetBO = employeeTargetBO;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
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
			EmployeeEvaluationForm employeeEvaluationForm = (EmployeeEvaluationForm)form;
			List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
			employeeTargetHeaderBeans = employeeTargetBO.findEmployeeEvaluationFrom(employeeEvaluationForm, statusList);
			if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
				request.setAttribute("level2", "true");
				for (EmployeeTargetHeaderBean employeeTargetHeaderBean : employeeTargetHeaderBeans) {
					employeeEvaluationForm.setLocationId(employeeTargetHeaderBean.getLocationId());
					employeeEvaluationForm.setPlanFromDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanFromDate()));
					employeeEvaluationForm.setPlanToDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanToDate()));
					employeeEvaluationForm.setEmployeeId(employeeTargetHeaderBean.getEmployeeId());
					request.setAttribute("targetPlanId", employeeTargetHeaderBean.getTargetPlanId());
					employeeEvaluationForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(employeeTargetHeaderBean.getEmployeeTargetDetailsBeans()));
				}
			}else{
				refreshEmployeeEvaluationForm(employeeEvaluationForm);
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
	

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeEvaluationForm employeeEvaluationForm = (EmployeeEvaluationForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = employeeTargetBO.updateEmployeeEvaluationFrom(employeeEvaluationForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Employee Evaluation","Target Plan Code -->"+employeeEvaluationForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Employee Evaluation","Target Plan Code -->"+employeeEvaluationForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Employee Evaluation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Employee Evaluation");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshEmployeeEvaluationForm(employeeEvaluationForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeEvaluationForm  employeeEvaluationForm = (EmployeeEvaluationForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = employeeTargetBO.postEmployeeEvaluation(employeeEvaluationForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Employee Target Plan Id -->"+employeeEvaluationForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Employee Target Plan Id -->"+employeeEvaluationForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Employee Targets");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Employee Targets");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshEmployeeEvaluationForm(employeeEvaluationForm);
		return mapping.findForward("display");
	}
		
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeEvaluationForm employeeEvaluationForm = (EmployeeEvaluationForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshEmployeeEvaluationForm(employeeEvaluationForm);
			employeeEvaluationForm.setReportingOfficerName(misSessionBean.getEmployeeName());
			Set<LabelValueBean> employeeIds = null;
			employeeIds = getEmployees(misSessionBean);
			request.getSession().setAttribute("employeeIds", employeeIds);
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
		request.setAttribute("d__ky", "locationId@planFromDateId@planToDateId@employeeId@targetPlanId");
	}
	
	private void refreshEmployeeEvaluationForm(EmployeeEvaluationForm employeeEvaluationForm){
		employeeEvaluationForm.setEmployeeId(0);
		employeeEvaluationForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(null));
		employeeEvaluationForm.setPlanFromDate(null);
		employeeEvaluationForm.setPlanToDate(null);
		employeeEvaluationForm.setTargetPlanId(0);
	}
	
	private Datagrid getEmployeeTargetDatagrid(Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans) {
	    Datagrid employeeTargetDatagrid= null;
		List<EmployeeTargetGridBean> employeeTargetGridBeans = new ArrayList<EmployeeTargetGridBean>();
		try {
			employeeTargetDatagrid = Datagrid.getInstance();
			employeeTargetDatagrid.setDataClass(EmployeeTargetGridBean.class);
			if (!MisUtility.ifEmpty(employeeTargetDetailsBeans)) {
				EmployeeTargetGridBean employeeTargetGridBean = null;
				for (EmployeeTargetDetailsBean employeeTargetDetailsBean : employeeTargetDetailsBeans) {
					employeeTargetGridBean = new EmployeeTargetGridBean();
					employeeTargetGridBean.setId(employeeTargetDetailsBean.getId());
					employeeTargetGridBean.setPlannerRemarks(employeeTargetDetailsBean.getPlannerRemarks());
					employeeTargetGridBean.setTargetCompletionDate(MisUtility.convertDateToString(employeeTargetDetailsBean.getTargetCompletionDate()));
					employeeTargetGridBean.setTargetName(employeeTargetDetailsBean.getTargetName());
					employeeTargetGridBean.setTargetPlanId(employeeTargetDetailsBean.getTargetPlanId());
					employeeTargetGridBean.setTargetActualCompletionDate(MisUtility.convertDateToString(employeeTargetDetailsBean.getTargetActualCompletionDate()));
					employeeTargetGridBean.setEmployeeRemarks(employeeTargetDetailsBean.getEmployeeRemarks());
					employeeTargetGridBean.setTargetStatus(employeeTargetDetailsBean.getTargetStatus());
					employeeTargetGridBean.setEvaluatorRemarks(employeeTargetDetailsBean.getEvaluatorRemarks());
					employeeTargetGridBeans.add(employeeTargetGridBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		employeeTargetDatagrid.setData(employeeTargetGridBeans);
		return employeeTargetDatagrid;
	}
	
	private Set<LabelValueBean> getEmployees(MISSessionBean misSessionBean){
		Set<LabelValueBean> employees = null;
		Set<EmployeeBean> employeeBeans = null;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setReportingOfficerId(misSessionBean.getEnteredBy());
			employeeBeans = employeeDao.getEmployeeByReportingOfficer(employeeBean, statusList);
			employees = new HashSet<LabelValueBean>();
				if(!MisUtility.ifEmpty(employeeBeans)){
			for (EmployeeBean employeeBean2 : employeeBeans) {
				employees.add(new LabelValueBean(employeeBean2.getEmployeeName()+" - ("+employeeBean2.getEmployeeId()+")",new Long(employeeBean2.getEmployeeId()).toString()));	
					}
				}
		}catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return employees;
	}
	
	public ActionForward fetchTargetPlanId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
				long employeeId = Long.parseLong(request.getParameter("employeeId"));
				employeeTargetHeaderBean.setEmployeeId(employeeId);
				if (request.getSession().getAttribute("misSessionBean") != null) {
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
						employeeTargetHeaderBean.setReportingOfficerId(misSessionBean.getEnteredBy());
					}
				employeeTargetHeaderBeans = new TreeSet<EmployeeTargetHeaderBean>(employeeTargetHeaderDao.findEmployeeTargetHeaderByEmployeeId(employeeTargetHeaderBean, statusList));
				for (EmployeeTargetHeaderBean employeeTargetHeaderBean2 : employeeTargetHeaderBeans) {
					buffer.append("<option value=\"").append(employeeTargetHeaderBean2.getTargetPlanId()).append("\">");
					buffer.append(employeeTargetHeaderBean2.getTargetPlanId()+" - ("+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanToDate()));
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
	

}
