package com.prwss.mis.hr.employeeappraisal.struts;

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

public class EmployeeAppraisalAction extends DispatchAction {
	private Logger log = Logger.getLogger(EmployeeAppraisalAction.class);
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
			EmployeeAppraisalForm employeeAppraisalForm = (EmployeeAppraisalForm)form;
			List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
			employeeTargetHeaderBeans = employeeTargetBO.findEmployeeAppraisalFrom(employeeAppraisalForm, statusList);
			if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
				request.setAttribute("level2", "true");
				for (EmployeeTargetHeaderBean employeeTargetHeaderBean : employeeTargetHeaderBeans) {
					employeeAppraisalForm.setPlanFromDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanFromDate()));
					employeeAppraisalForm.setPlanToDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanToDate()));
					employeeAppraisalForm.setTargetPlanId(employeeTargetHeaderBean.getTargetPlanId());
					employeeAppraisalForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(employeeTargetHeaderBean.getEmployeeTargetDetailsBeans()));
				}
			}else{
				refreshEmployeeAppraisalForm(employeeAppraisalForm, misSessionBean);
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
		EmployeeAppraisalForm employeeAppraisalForm = (EmployeeAppraisalForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = employeeTargetBO.updateEmployeeAppraisalFrom(employeeAppraisalForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Target Details","Target Plan Code -->"+employeeAppraisalForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Target Details","Target Plan Code -->"+employeeAppraisalForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Target Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Target Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshEmployeeAppraisalForm(employeeAppraisalForm, misSessionBean);
		return mapping.findForward("display");
	}
		
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		System.out.println("IN UNSPECIFIED EMPLOYEE APPRAISAL");
		EmployeeAppraisalForm employeeAppraisalForm = (EmployeeAppraisalForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshEmployeeAppraisalForm(employeeAppraisalForm,misSessionBean);
//			employeeTargetPlanForm.setReportingOfficerName(misSessionBean.getEmployeeName());
			Set<LabelValueBean> employeeTargetPlan = null;
			employeeTargetPlan = getEmployeeTargetPlan(misSessionBean,request);
			request.getSession().setAttribute("employeeTargetPlan", employeeTargetPlan);
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
		request.setAttribute("d__ky", "packageId@locationId@targetPlanId");
	}
	
	private void refreshEmployeeAppraisalForm(EmployeeAppraisalForm employeeAppraisalForm, MISSessionBean misSessionBean){
		employeeAppraisalForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(null));
		employeeAppraisalForm.setPlanFromDate(null);
		employeeAppraisalForm.setPlanToDate(null);
		employeeAppraisalForm.setTargetPlanId(0);
		employeeAppraisalForm.setLocationId(null);
		employeeAppraisalForm.setEmployeeName(misSessionBean.getEmployeeName());
		
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
	
	private Set<LabelValueBean> getEmployeeTargetPlan(MISSessionBean misSessionBean,HttpServletRequest request){
		Set<LabelValueBean> employeeTargetPlan = null;
		Set<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		long reportingOfficerId = 0;
		EmployeeBean bean = new EmployeeBean();
		try {
			
			EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
			employeeTargetHeaderBean.setEmployeeId(misSessionBean.getEnteredBy());
			employeeTargetHeaderBeans = new TreeSet<EmployeeTargetHeaderBean>(employeeTargetHeaderDao.findEmployeeTargetHeaderByEmployeeId(employeeTargetHeaderBean, statusList));
			employeeTargetPlan = new HashSet<LabelValueBean>();
				if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
					for (EmployeeTargetHeaderBean employeeTargetHeaderBean2 : employeeTargetHeaderBeans) {
						employeeTargetPlan.add(new LabelValueBean(employeeTargetHeaderBean2.getTargetPlanId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanToDate()),new Long(employeeTargetHeaderBean2.getTargetPlanId()).toString()));
						reportingOfficerId = employeeTargetHeaderBean2.getReportingOfficerId();//for reporting officer name
					}
				}
		}catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		//-----------------------------------------------------------------
		/* The following code is for priting reporting officer name */
		if(MisUtility.ifEmpty(reportingOfficerId)){
			bean.setEmployeeId(reportingOfficerId);
			bean = employeeDao.findEmployee(bean, statusList).get(0);
			request.getSession().setAttribute("reportingOfficerName", bean.getEmployeeName());
		}
		//-----------------------------------------------------------------
		return employeeTargetPlan;
	}
}
