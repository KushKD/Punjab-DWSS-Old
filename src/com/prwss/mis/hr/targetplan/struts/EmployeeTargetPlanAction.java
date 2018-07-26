package com.prwss.mis.hr.targetplan.struts;

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
import com.prwss.mis.hr.targetplan.EmployeeTargetBO;
import com.prwss.mis.hr.targetplan.EmployeeTargetDetailsBean;
import com.prwss.mis.hr.targetplan.EmployeeTargetHeaderBean;
import com.prwss.mis.hr.targetplan.dao.EmployeeTargetHeaderDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeTargetPlanAction extends DispatchAction {
	private Logger log = Logger.getLogger(EmployeeTargetPlanAction.class);
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
			EmployeeTargetPlanForm employeeTargetPlanForm = (EmployeeTargetPlanForm)form;
			List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
			employeeTargetHeaderBeans = employeeTargetBO.findEmployeeTargetPlanFrom(employeeTargetPlanForm, statusList);
			if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
				request.setAttribute("level2", "true");
				for (EmployeeTargetHeaderBean employeeTargetHeaderBean : employeeTargetHeaderBeans) {
					employeeTargetPlanForm.setLocationId(employeeTargetHeaderBean.getLocationId());
					employeeTargetPlanForm.setPlanFromDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanFromDate()));
					employeeTargetPlanForm.setPlanToDate(MisUtility.convertDateToString(employeeTargetHeaderBean.getPlanToDate()));
					employeeTargetPlanForm.setEmployeeId(employeeTargetHeaderBean.getEmployeeId());
					request.setAttribute("targetPlanId", employeeTargetHeaderBean.getTargetPlanId());
					employeeTargetPlanForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(employeeTargetHeaderBean.getEmployeeTargetDetailsBeans()));
				}
			}else{
				refreshEmployeeTargetPlanForm(employeeTargetPlanForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Target Plan");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeTargetPlanForm employeeTargetPlanForm = (EmployeeTargetPlanForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = employeeTargetBO.deleteEmployeeTargetPlanFrom(employeeTargetPlanForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Employee Target Plan","Plan Code -->"+employeeTargetPlanForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Employee Target Plan","Plan Code -->"+employeeTargetPlanForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Employee Target Plan");
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
		refreshEmployeeTargetPlanForm(employeeTargetPlanForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		EmployeeTargetPlanForm employeeTargetPlanForm = (EmployeeTargetPlanForm)form; 
		long targetPlanId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(employeeTargetPlanForm.getPlanFromDate().trim().equals("")||employeeTargetPlanForm.getPlanFromDate().trim().equals("''")||employeeTargetPlanForm.getPlanToDate().trim().equals("")|| !(MisUtility.ifEmpty(employeeTargetPlanForm.getEmployeeId()))){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			if(MisUtility.ifEmpty(employeeTargetPlanForm.getEmployeeTargetDatagrid().getAddedData())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			targetPlanId = employeeTargetBO.saveEmployeeTargetPlanFrom(employeeTargetPlanForm, misSessionBean);
			if (!(targetPlanId==0)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Employee Targets","Target Plan Code -->"+targetPlanId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Employee Targets");
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
		refreshEmployeeTargetPlanForm(employeeTargetPlanForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeTargetPlanForm employeeTargetPlanForm = (EmployeeTargetPlanForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = employeeTargetBO.updateEmployeeTargetPlanFrom(employeeTargetPlanForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Employee Targets","Target Plan Code -->"+employeeTargetPlanForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.update","Employee Targets","Target Plan Code -->"+employeeTargetPlanForm.getTargetPlanId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Package Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Package Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshEmployeeTargetPlanForm(employeeTargetPlanForm);
		return mapping.findForward("display");
	}
		
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EmployeeTargetPlanForm employeeTargetPlanForm = (EmployeeTargetPlanForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshEmployeeTargetPlanForm(employeeTargetPlanForm);
			employeeTargetPlanForm.setReportingOfficerName(misSessionBean.getEmployeeName());
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
		request.setAttribute("d__ky", "employeeId@locationId@targetPlanId");
	}
	
	private void refreshEmployeeTargetPlanForm(EmployeeTargetPlanForm employeeTargetPlanForm){
		employeeTargetPlanForm.setEmployeeId(0);
		employeeTargetPlanForm.setEmployeeTargetDatagrid(getEmployeeTargetDatagrid(null));
		employeeTargetPlanForm.setPlanFromDate(null);
		employeeTargetPlanForm.setPlanToDate(null);
		employeeTargetPlanForm.setTargetPlanId(0);
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
				
				employeeTargetHeaderBeans = new TreeSet<EmployeeTargetHeaderBean>(employeeTargetHeaderDao.findEmployeeTargetHeaderByEmployeeId(employeeTargetHeaderBean, statusList)).descendingSet();// For fetch in descending order As we wish to print Target Plan Id Latest created by user
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (EmployeeTargetHeaderBean employeeTargetHeaderBean2 : employeeTargetHeaderBeans) {
					buffer.append("<option value=\"").append(employeeTargetHeaderBean2.getTargetPlanId()).append("\">");
					buffer.append(employeeTargetHeaderBean2.getTargetPlanId()+" - ("+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(employeeTargetHeaderBean2.getPlanToDate())+")");
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
