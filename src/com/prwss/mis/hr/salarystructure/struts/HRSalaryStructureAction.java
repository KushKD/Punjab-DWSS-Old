package com.prwss.mis.hr.salarystructure.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBO;
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

public class HRSalaryStructureAction extends DispatchAction {
	
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(HRSalaryStructureAction.class);
	private MISSessionBean misSessionBean;
	private HRSalaryStructureBO hrSalaryStructureBO;
	
	
	
	public void setHrSalaryStructureBO(HRSalaryStructureBO hrSalaryStructureBO) {
		this.hrSalaryStructureBO = hrSalaryStructureBO;
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
			HRSalaryStructureForm hrSalaryStructureForm = (HRSalaryStructureForm)form;
			List<HRSalaryStructureBean> hrSalaryStructureBeans = null;
			hrSalaryStructureBeans = hrSalaryStructureBO.findHRSalaryForm(hrSalaryStructureForm, statusList);
			if(!MisUtility.ifEmpty(hrSalaryStructureBeans)){
				request.setAttribute("level2","true");
				System.out.println("action=++++++++++="+hrSalaryStructureBeans);
				for (HRSalaryStructureBean hrSalaryStructureBean : hrSalaryStructureBeans) {
					hrSalaryStructureForm.setLocationId(hrSalaryStructureBean.getLocationId());
					hrSalaryStructureForm.setConveyanceAllowance(hrSalaryStructureBean.getConveyanceAllowance());
					hrSalaryStructureForm.setDearnessAllowance(hrSalaryStructureBean.getDearnessAllowance());
					hrSalaryStructureForm.setEducationAllowance(hrSalaryStructureBean.getEducationAllowance());
					hrSalaryStructureForm.setEmployeeId(hrSalaryStructureBean.getEmployeeBean().getEmployeeId());
					request.setAttribute("employeeId", hrSalaryStructureBean.getEmployeeBean().getEmployeeId());
					hrSalaryStructureForm.setBasicPay(hrSalaryStructureBean.getBasicPay());
					hrSalaryStructureForm.setHra(hrSalaryStructureBean.getHra());
					hrSalaryStructureForm.setIncomeTax(hrSalaryStructureBean.getIncomeTax());
					hrSalaryStructureForm.setLeaveWithoutPay(hrSalaryStructureBean.getLeaveWithoutPay());
					hrSalaryStructureForm.setTotalAmount(hrSalaryStructureBean.getTotalAmount());
					hrSalaryStructureForm.setTds(hrSalaryStructureBean.getTds());
					hrSalaryStructureForm.setReimbursement(hrSalaryStructureBean.getReimbursement());
					hrSalaryStructureForm.setProvidentFund(hrSalaryStructureBean.getProvidentFund());
					hrSalaryStructureForm.setPersonalAllowance(hrSalaryStructureBean.getPersonalAllowance());
					hrSalaryStructureForm.setFromDate(MisUtility.convertDateToString(hrSalaryStructureBean.getFromDate()));
					hrSalaryStructureForm.setAppointmentNo(hrSalaryStructureBean.getEmployeeBean().getAppointmentNo());
					hrSalaryStructureForm.setOthers(hrSalaryStructureBean.getOthers());
					if(hrSalaryStructureBean.getEmployeeBean().getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_CONTRACTUAL)){
					hrSalaryStructureForm.setDateOfJoining(MisUtility.convertDateToString(hrSalaryStructureBean.getEmployeeBean().getContractStartDate()));
					}else{
					hrSalaryStructureForm.setDateOfJoining(MisUtility.convertDateToString(hrSalaryStructureBean.getEmployeeBean().getJoiningDate()));
					}
					hrSalaryStructureForm.setDesignation(hrSalaryStructureBean.getEmployeeBean().getDesignationId());
					hrSalaryStructureForm.setPanNo(hrSalaryStructureBean.getEmployeeBean().getPanNo());
					hrSalaryStructureForm.setEmployeeType(hrSalaryStructureBean.getEmployeeBean().getEmployeeType());
					hrSalaryStructureForm.setWing(hrSalaryStructureBean.getEmployeeBean().getAppointedWing());
				}
			}else{
				refreshHRSalaryStructureForm(hrSalaryStructureForm);
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
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		HRSalaryStructureForm hrSalaryStructureForm = (HRSalaryStructureForm)form; 
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
			if(!MisUtility.ifEmpty(hrSalaryStructureForm.getLocationId())||!MisUtility.ifEmpty(hrSalaryStructureForm.getEmployeeId())||!MisUtility.ifEmpty(hrSalaryStructureForm.getTotalAmount())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			status = hrSalaryStructureBO.saveHRSalaryForm(hrSalaryStructureForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Employee Salary Structure Successfully"," Employee Id - "+hrSalaryStructureForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Submission of Salary Structure");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("salary.duplicate.entry","Saving Failed As ,",e.getMessage());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Salary Structure");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Salary Structure");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		HRSalaryStructureForm hrSalaryStructureForm = (HRSalaryStructureForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = hrSalaryStructureBO.updateHRSalaryForm(hrSalaryStructureForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Salary Structure","of Employee Id"+hrSalaryStructureForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Salary Structure","of Employee Id"+hrSalaryStructureForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {{
			if (MISExceptionCodes.MIS007.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("salary.duplicate.entry","Saving Failed As ,",e.getMessage());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Salary Structure");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		
		}}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Salary Structure");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshHRSalaryStructureForm(hrSalaryStructureForm);
		return mapping.findForward("display");
	}
		
	

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		HRSalaryStructureForm hrSalaryStructureForm = (HRSalaryStructureForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshHRSalaryStructureForm(hrSalaryStructureForm);
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
		request.setAttribute("d__ky", "employeeId@locationId");
	}
	
	private void refreshHRSalaryStructureForm(HRSalaryStructureForm hrSalaryStructureForm){
		hrSalaryStructureForm.setLocationId(null);
		hrSalaryStructureForm.setAppointmentNo(null);
		hrSalaryStructureForm.setConveyanceAllowance(0);
		hrSalaryStructureForm.setDateOfJoining(null);
		hrSalaryStructureForm.setDearnessAllowance(0);
		hrSalaryStructureForm.setDesignation(null);
		hrSalaryStructureForm.setEducationAllowance(0);
		hrSalaryStructureForm.setEmployeeId(0);
		hrSalaryStructureForm.setEmployeeType(null);
		hrSalaryStructureForm.setHra(0);
		hrSalaryStructureForm.setIncomeTax(0);
		hrSalaryStructureForm.setLeaveWithoutPay(0);
		hrSalaryStructureForm.setPanNo(null);
		hrSalaryStructureForm.setWing(null);
		hrSalaryStructureForm.setTotalAmount(0);
		hrSalaryStructureForm.setToDate(null);
		hrSalaryStructureForm.setTds(0);
		hrSalaryStructureForm.setReimbursement(0);
		hrSalaryStructureForm.setProvidentFund(0);
		hrSalaryStructureForm.setPersonalAllowance(0);
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
					buffer.append("Select Employee");
					buffer.append("</option>");
				for (EmployeeBean employeeBean : employeeBeans) {
					buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
					buffer.append(employeeBean.getEmployeeId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean.getEmployeeName());
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
	public ActionForward fetchEmployeeType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<EmployeeBean> employeeBeans = null;
		StringBuffer employeeType = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
				employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
				if(!MisUtility.ifEmpty(employeeBeans)){
					employeeType.append(employeeBeans.get(0).getEmployeeType());
				}	
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(employeeType.toString()) && employeeType.length() > 1 ){
				out.print(employeeType.toString());
			}
			System.out.println("Type is="+employeeType);
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
	public ActionForward fetchEmployeeWing(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<EmployeeBean> employeeBeans = null;
		StringBuffer employeeWing = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
				employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
				if(!MisUtility.ifEmpty(employeeBeans)){
					employeeWing.append(employeeBeans.get(0).getAppointedWing());
				}	
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(employeeWing.toString()) && employeeWing.length() > 1 ){
				out.print(employeeWing.toString());
			}
			System.out.println("Type is="+employeeWing);
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

	public ActionForward fetchEmployeeDesignation
(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<EmployeeBean> employeeBeans = null;
		StringBuffer employeeDesignation = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
				employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
				if(!MisUtility.ifEmpty(employeeBeans)){
					employeeDesignation.append(employeeBeans.get(0).getDesignationId());
				}	
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(employeeDesignation.toString()) && employeeDesignation.length() > 1 ){
				out.print(employeeDesignation.toString());
			}
			System.out.println("Type is="+employeeDesignation);
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
	public ActionForward fetchEmployeeJoiningDate
	(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws MISException {
			List<EmployeeBean> employeeBeans = null;
			StringBuffer employeejoinDate = new StringBuffer();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			try {
				if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
					employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
					if(!MisUtility.ifEmpty(employeeBeans)){
						employeejoinDate.append(MisUtility.convertDateToString(employeeBeans.get(0).getJoiningDate()));
					}	
				}
				PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(employeejoinDate.toString()) && employeejoinDate.length() > 1 ){
					out.print(employeejoinDate.toString());
				}
				System.out.println("Type is="+employeejoinDate);
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
	public ActionForward fetchEmployeeAppNo
	(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws MISException {
			List<EmployeeBean> employeeBeans = null;
			StringBuffer employeeAppNo = new StringBuffer();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			try {
				if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
					employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
					if(!MisUtility.ifEmpty(employeeBeans)){
						employeeAppNo.append(employeeBeans.get(0).getAppointmentNo());
					}	
				}
				PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(employeeAppNo.toString()) && employeeAppNo.length() > 1 ){
					out.print(employeeAppNo.toString());
				}
				System.out.println("Type is="+employeeAppNo);
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
	public ActionForward fetchEmployeePanNo
	(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws MISException {
			List<EmployeeBean> employeeBeans = null;
			StringBuffer employeePanNo = new StringBuffer();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			try {
				if(MisUtility.ifEmpty(request.getParameter("employeeId"))){
					employeeBeans = employeeDao.getDistinctEmployeeDetails(new Long(request.getParameter("employeeId")),statusList);
					if(!MisUtility.ifEmpty(employeeBeans)){
						employeePanNo.append(employeeBeans.get(0).getPanNo());
					}	
				}
				PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(employeePanNo.toString()) && employeePanNo.length() > 1 ){
					out.print(employeePanNo.toString());
				}
				System.out.println("Type is="+employeePanNo);
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
