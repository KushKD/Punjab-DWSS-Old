package com.prwss.mis.masters.employee.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.prwss.mis.masters.employee.EmployeeBO;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeContactExtendedBean;
import com.prwss.mis.masters.employee.dao.EmployeeContactExtendedGridBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationDao;
import com.prwss.mis.masters.employee.dao.EmployeePromotionHistoryBean;
import com.prwss.mis.masters.employee.dao.EmployeeQualificationBean;
import com.prwss.mis.masters.employee.dao.EmploymentHistoryBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeAction extends DispatchAction {

	private EmployeeBO employeeBO;
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(EmployeeAction.class);
	private LocationDao locationDao;
	private EmployeeDesignationDao employeeDesignationDao;
	
	public void setEmployeeDesignationDao(
			EmployeeDesignationDao employeeDesignationDao) {
		this.employeeDesignationDao = employeeDesignationDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setEmployeeBO(EmployeeBO employeeBO) {
		this.employeeBO = employeeBO;
	}

	private MISSessionBean misSessionBean;
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
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
		try{
		EmployeeForm employeeForm = (EmployeeForm)form;
		System.out.println("========================"+employeeForm.getEmployeeId());
		List<EmployeeBean> employeeBeans = null;
		employeeBeans = employeeBO.findEmployee(employeeForm, statusList);
		if(!MisUtility.ifEmpty(employeeBeans)){
			request.setAttribute("level2", "true");
			System.out.println("inside");
			//System.out.println("listtttttttttt-----"+employeeBeans);
			request.setAttribute("employeeCodeList",employeeBeans);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
//			for (EmployeeBean employeeBean : employeeBeans) {
//				employeeForm.setAddressLine1(employeeBean.getAddressLine1());
//				employeeForm.setAddressLine2(employeeBean.getAddressLine2());
//				employeeForm.setAppointedWing(employeeBean.getAppointedWing());
//				employeeForm.setAppointmentNo(employeeBean.getAppointmentNo());
//				employeeForm.setCity(employeeBean.getCity());
//				employeeForm.setDateOfBirth(MisUtility.convertDateToString(employeeBean.getDateOfBirth()));
//				employeeForm.setDesignation(employeeBean.getDesignationId());
//				employeeForm.setEmployeeHistoryGrid(getEmployementHistoryDatagrid(employeeBean.getEmploymentHistoryBeans()));
//				employeeForm.setEmployeeId(employeeBean.getEmployeeId());
//				employeeForm.setEmployeeName(employeeBean.getEmployeeName());
//				employeeForm.setSpecialization(employeeBean.getSpecialization());
//				employeeForm.setEmployeeQualificationGrid(getEmployeeQualificationDatagrid(employeeBean.getEmployeeQualificationBeans()));
//				employeeForm.setEmployeeType(employeeBean.getEmployeeType());
//				employeeForm.setFatherName(employeeBean.getFatherName());
//				employeeForm.setGender(employeeBean.getGender());
//				employeeForm.setJoiningDate(MisUtility.convertDateToString(employeeBean.getJoiningDate()));
//				employeeForm.setMaritalStatus(employeeBean.getMaritalStatus());
//				employeeForm.setMobilePhoneNo(employeeBean.getMobilePhone());
//				employeeForm.setNationality(employeeBean.getNationality());
//				employeeForm.setOfficeEmail(employeeBean.getOfficialEmailId());
//				employeeForm.setPanNo(employeeBean.getPanNo());
//				employeeForm.setPersonalEmail(employeeBean.getPersonalEmailId());
//				employeeForm.setPinCode(employeeBean.getPin());
//				employeeForm.setSanctionNo(employeeBean.getSanctionNo());
//				employeeForm.setState(employeeBean.getState());
//				employeeForm.setStreet(employeeBean.getStreet());
//				employeeForm.setLocationId(employeeBean.getLocationId());
//				employeeForm.setWorkPhoneNo(employeeBean.getWorkPhone());
//				employeeForm.setReportingOfficerLocation(employeeBean.getReportingOfficerLoactionId());
//				employeeForm.setReportingOfficerId(employeeBean.getReportingOfficerId());
//				employeeForm.setCategory(employeeBean.getCategory());
//				
//				employeeForm.setRetirementDate(MisUtility.convertDateToString(employeeBean.getRetirementDate()));
//				if(employeeBean.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_PERMANENT)){
//					employeeForm.setEmployeePromotionGrid(getEmployeePromotionDatagrid(employeeBean.getEmployeePromotionHistoryBeans()));
//					employeeForm.setPayScale(employeeBean.getPayscale());
//					employeeForm.setPermanentEmployeeId(employeeBean.getPermanentEmployeeId());
//				}else{
//					employeeForm.setContractExtendedUpto(MisUtility.convertDateToString(employeeBean.getContractExtendedUpto()));
//					employeeForm.setContractEndDate(MisUtility.convertDateToString(employeeBean.getContractEndDate()));
//					employeeForm.setContractStartDate(MisUtility.convertDateToString(employeeBean.getContractStartDate()));
//					employeeForm.setContractExtentionNo(employeeBean.getContractExtentionNo());
//					employeeForm.setContractExtentionDate(MisUtility.convertDateToString(employeeBean.getContractExtentionDate()));
//				}
//			}
		}else{
			refreshEmployeeForm(employeeForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	}catch (Exception e) {
		e.printStackTrace();
		log.error(e.getLocalizedMessage(),e);
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
	}
		return mapping.findForward("display");
	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String employeeId = request.getParameter("employeeId");
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		EmployeeForm  employeeForm = (EmployeeForm)form;
		try {
			List<EmployeeBean> employeeBeans = null;
			employeeForm.setEmployeeId(Long.parseLong(employeeId));
		//	SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMaster(schemeHeaderVillageForm, statusList);
			//List<SchemeHeaderBean> schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
			employeeBeans = employeeBO.findEmployee(employeeForm, statusList);
			if(!MisUtility.ifEmpty(employeeBeans)){
				for (EmployeeBean employeeBean : employeeBeans) {
				employeeForm.setAddressLine1(employeeBean.getAddressLine1());
				employeeForm.setAddressLine2(employeeBean.getAddressLine2());
				employeeForm.setAppointedWing(employeeBean.getAppointedWing());
				employeeForm.setAppointmentNo(employeeBean.getAppointmentNo());
				employeeForm.setCity(employeeBean.getCity());
				employeeForm.setDateOfBirth(MisUtility.convertDateToString(employeeBean.getDateOfBirth()));
				request.setAttribute("designation",employeeBean.getDesigId());
				employeeForm.setDesignation(String.valueOf(employeeBean.getDesigId()));
				employeeForm.setEmployeeHistoryGrid(getEmployementHistoryDatagrid(employeeBean.getEmploymentHistoryBeans()));
				employeeForm.setEmployeeId(employeeBean.getEmployeeId());
				employeeForm.setEmployeeName(employeeBean.getEmployeeName());
				employeeForm.setSpecialization(employeeBean.getSpecialization());
				employeeForm.setEmployeeQualificationGrid(getEmployeeQualificationDatagrid(employeeBean.getEmployeeQualificationBeans()));
				employeeForm.setEmployeeType(employeeBean.getEmployeeType());
				employeeForm.setFatherName(employeeBean.getFatherName());
				employeeForm.setGender(employeeBean.getGender());
				employeeForm.setJoiningDate(MisUtility.convertDateToString(employeeBean.getJoiningDate()));
				employeeForm.setMaritalStatus(employeeBean.getMaritalStatus());
				employeeForm.setMobilePhoneNo(employeeBean.getMobilePhone());
				employeeForm.setNationality(employeeBean.getNationality());
				employeeForm.setOfficeEmail(employeeBean.getOfficialEmailId());
				employeeForm.setPanNo(employeeBean.getPanNo());
				employeeForm.setPersonalEmail(employeeBean.getPersonalEmailId());
				employeeForm.setPinCode(employeeBean.getPin());
				employeeForm.setSanctionNo(employeeBean.getSanctionNo());
				employeeForm.setState(employeeBean.getState());
				employeeForm.setStreet(employeeBean.getStreet());
				employeeForm.setLocationId(employeeBean.getLocationId());
				request.setAttribute("reportingOfficerIds", employeeBean.getReportingOfficerId());
				employeeForm.setWorkPhoneNo(employeeBean.getWorkPhone());
				employeeForm.setReportingOfficerLocation(employeeBean.getReportingOfficerLoactionId());
				employeeForm.setReportingOfficerId(employeeBean.getReportingOfficerId());
				employeeForm.setCategory(employeeBean.getCategory());
				
				employeeForm.setRetirementDate(MisUtility.convertDateToString(employeeBean.getRetirementDate()));
				if(employeeBean.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_PERMANENT)){
					employeeForm.setEmployeePromotionGrid(getEmployeePromotionDatagrid(employeeBean.getEmployeePromotionHistoryBeans()));
					employeeForm.setPayScale(employeeBean.getPayscale());
					employeeForm.setPermanentEmployeeId(employeeBean.getPermanentEmployeeId());
				}else{
					//employeeForm.setContractExtendedUpto(MisUtility.convertDateToString(employeeBean.getContractExtendedUpto()));
					employeeForm.setContractEndDate(MisUtility.convertDateToString(employeeBean.getContractEndDate()));
					employeeForm.setContractStartDate(MisUtility.convertDateToString(employeeBean.getContractStartDate()));
					//employeeForm.setContractExtentionNo(employeeBean.getContractExtentionNo());
					//employeeForm.setContractExtentionDate(MisUtility.convertDateToString(employeeBean.getContractExtentionDate()));
					employeeForm.setContractExtentionGrid(getEmployeeContractDatagrid(employeeBean.getEmployeeContactExtendedBeans()));
				}
			}
			
}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		EmployeeForm employeeForm = (EmployeeForm)form;
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

			status = employeeBO.deleteEmployee(employeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Employee Information ","Employee ID -->"+employeeForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("custom.message",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		try {
			refreshEmployeeForm(employeeForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		EmployeeForm employeeForm = (EmployeeForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			StringBuffer strngMessage = new StringBuffer();
if(!MisUtility.ifEmpty(employeeForm.getEmployeeName())||!MisUtility.ifEmpty(employeeForm.getDateOfBirth())||!MisUtility.ifEmpty(employeeForm.getFatherName())||!MisUtility.ifEmpty(employeeForm.getJoiningDate())||!MisUtility.ifEmpty(employeeForm.getGender())||!MisUtility.ifEmpty(employeeForm.getAppointedWing())||!MisUtility.ifEmpty(employeeForm.getEmployeeType())||!MisUtility.ifEmpty(employeeForm.getAppointmentNo())||!MisUtility.ifEmpty(employeeForm.getLocationId())||!MisUtility.ifEmpty(employeeForm.getReportingOfficerId())||!MisUtility.ifEmpty(employeeForm.getDesignation())){
				
				if(!MisUtility.ifEmpty(employeeForm.getEmployeeName())){
					strngMessage.append(" Please Enter Employee Name");
				}
				if(!MisUtility.ifEmpty(employeeForm.getFatherName())){
					strngMessage.append("<br> Please Enter Father Name");
				}
				if(!MisUtility.ifEmpty(employeeForm.getDateOfBirth())){
					strngMessage.append("<br> Please Enter Date of Birth");
				}
				if(!MisUtility.ifEmpty(employeeForm.getJoiningDate())){
					strngMessage.append("<br> Please Enter Date of Joining");
				}
				if(!MisUtility.ifEmpty(employeeForm.getGender())){
					strngMessage.append("<br> Please Select Gender");
				}
				if(!MisUtility.ifEmpty(employeeForm.getDesignation())){
					strngMessage.append("<br> Please Select Designation at Joining");
				}
				if(!MisUtility.ifEmpty(employeeForm.getAppointedWing())){
					strngMessage.append("<br> Please Select Appointed Wing");
				}
				if(!MisUtility.ifEmpty(employeeForm.getEmployeeType())){
					strngMessage.append("<br> Please Select Employee Type");
				}
				if(!MisUtility.ifEmpty(employeeForm.getAppointmentNo())){
					strngMessage.append("<br> Please Enter AppointmentNo ");
				}
				if(!MisUtility.ifEmpty(employeeForm.getLocationId())){
					strngMessage.append("<br> Please Select Location");
				}
				if(!MisUtility.ifEmpty(employeeForm.getReportingOfficerId())){
					strngMessage.append("<br> Please Select Reporting Officer");
				}
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			//	throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(MisUtility.ifEmpty(employeeForm.getJoiningDate())){
				Calendar cal = new GregorianCalendar(); 
				   StringBuffer prevDatev= new StringBuffer();
				   prevDatev.append(cal.get(Calendar.DAY_OF_MONTH));
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.MONTH)+1);
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.YEAR));
				   String prDate = prevDatev.toString();
				   boolean statusDate	= checkDateFlow(employeeForm.getJoiningDate(),prDate);
				   if(!statusDate){
					   	strngMessage.append("Invalid Date Of Joining");
						strngMessage.append("<br> Date of Joining should be before today's Date");
						employeeForm.setJoiningDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
				   statusDate = checkDateFlow(employeeForm.getDateOfBirth(),employeeForm.getJoiningDate());
				   if(!statusDate){
					   	strngMessage.append("Invalid Date Of Joining");
						strngMessage.append("<br> Date of Joining should be greater than Date of birth");
						employeeForm.setJoiningDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
			}
			if(MisUtility.ifEmpty(employeeForm.getRetirementDate())){
				boolean statusDate = checkDateFlow(employeeForm.getJoiningDate(), employeeForm.getRetirementDate());
				 if(!statusDate){
					   	strngMessage.append("Invalid Date Of Retirement");
						strngMessage.append("<br> Date Of Retirement should be greater than Date of Joining ");
						employeeForm.setRetirementDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
				
			}
			status = employeeBO.updateEmployee(employeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Employee Information ","Employee ID -->"+employeeForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshEmployeeForm(employeeForm);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
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
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
//		try {
//			refreshEmployeeForm(employeeForm);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		EmployeeForm employeeForm = (EmployeeForm)form;
		long employeeId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			StringBuffer strngMessage = new StringBuffer();
		if(!MisUtility.ifEmpty(employeeForm.getEmployeeName())||!MisUtility.ifEmpty(employeeForm.getDateOfBirth())||!MisUtility.ifEmpty(employeeForm.getFatherName())||!MisUtility.ifEmpty(employeeForm.getJoiningDate())||!MisUtility.ifEmpty(employeeForm.getGender())||!MisUtility.ifEmpty(employeeForm.getAppointedWing())||!MisUtility.ifEmpty(employeeForm.getEmployeeType())||!MisUtility.ifEmpty(employeeForm.getAppointmentNo())||!MisUtility.ifEmpty(employeeForm.getLocationId())||!MisUtility.ifEmpty(employeeForm.getReportingOfficerId())||!MisUtility.ifEmpty(employeeForm.getDesignation())){
				
				if(!MisUtility.ifEmpty(employeeForm.getEmployeeName())){
					strngMessage.append(" Please Enter Employee Name");
				}
				if(!MisUtility.ifEmpty(employeeForm.getFatherName())){
					strngMessage.append("<br> Please Enter Father Name");
				}
				if(!MisUtility.ifEmpty(employeeForm.getDateOfBirth())){
					strngMessage.append("<br> Please Enter Date of Birth");
				}
				if(!MisUtility.ifEmpty(employeeForm.getJoiningDate())){
					strngMessage.append("<br> Please Enter Date of Joining");
				}
				if(!MisUtility.ifEmpty(employeeForm.getGender())){
					strngMessage.append("<br> Please Select Gender");
				}
				if(!MisUtility.ifEmpty(employeeForm.getDesignation())){
					strngMessage.append("<br> Please Select Designation at Joining");
				}
				if(!MisUtility.ifEmpty(employeeForm.getAppointedWing())){
					strngMessage.append("<br> Please Select Appointed Wing");
				}
				if(!MisUtility.ifEmpty(employeeForm.getEmployeeType())){
					strngMessage.append("<br> Please Select Employee Type");
				}
				if(!MisUtility.ifEmpty(employeeForm.getAppointmentNo())){
					strngMessage.append("<br> Please Enter AppointmentNo ");
				}
				if(!MisUtility.ifEmpty(employeeForm.getLocationId())){
					strngMessage.append("<br> Please Select Location");
				}
				if(!MisUtility.ifEmpty(employeeForm.getReportingOfficerId())){
					strngMessage.append("<br> Please Select Reporting Officer");
				}
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			//	throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(MisUtility.ifEmpty(employeeForm.getDateOfBirth())){
				Calendar cal = new GregorianCalendar(); 
				   cal.add(Calendar.YEAR,-18);
				   StringBuffer prevDatev= new StringBuffer();
				   prevDatev.append(cal.get(Calendar.DAY_OF_MONTH));
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.MONTH)+1);
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.YEAR));
				   String prDate = prevDatev.toString();
			boolean status	= checkDateFlow(employeeForm.getDateOfBirth(),prDate);
			System.out.println(status);
			System.out.println("inn");
				if(!status){
					strngMessage.append("Invalid Date Of Birth");
					strngMessage.append("<br> Date of Birth should be 18 years before today's Date");
					employeeForm.setDateOfBirth(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			if(MisUtility.ifEmpty(employeeForm.getJoiningDate())){
				Calendar cal = new GregorianCalendar(); 
				   StringBuffer prevDatev= new StringBuffer();
				   prevDatev.append(cal.get(Calendar.DAY_OF_MONTH));
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.MONTH)+1);
				   prevDatev.append("-");
				   prevDatev.append(cal.get(Calendar.YEAR));
				   String prDate = prevDatev.toString();
				   boolean status	= checkDateFlow(employeeForm.getJoiningDate(),prDate);
				   if(!status){
					   	strngMessage.append("Invalid Date Of Joining");
						strngMessage.append("<br> Date of Joining should be before today's Date");
						employeeForm.setJoiningDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
				   status = checkDateFlow(employeeForm.getDateOfBirth(),employeeForm.getJoiningDate());
				   if(!status){
					   	strngMessage.append("Invalid Date Of Joining");
						strngMessage.append("<br> Date of Joining should be greater than Date of birth");
						employeeForm.setJoiningDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
			}
			if(MisUtility.ifEmpty(employeeForm.getRetirementDate())){
				boolean status = checkDateFlow(employeeForm.getJoiningDate(), employeeForm.getRetirementDate());
				 if(!status){
					   	strngMessage.append("Invalid Date Of Retirement");
						strngMessage.append("<br> Date Of Retirement should be greater than Date of Joining ");
						employeeForm.setRetirementDate(null);
						throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}
				
			}
			employeeId = employeeBO.saveEmployee(employeeForm, misSessionBean);
			if (MisUtility.ifEmpty(employeeId)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Employee Information ","new auto generated Employee ID -->"+employeeId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshEmployeeForm(employeeForm);

			}else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
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
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
//		try {
//			refreshEmployeeForm(employeeForm);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return mapping.findForward("display");
	}
	
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {
		EmployeeForm employeeForm = (EmployeeForm)form;
		this.setAttrib(request);
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

			status = employeeBO.postEmployee(employeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Employee Information ","Employee ID -->"+employeeForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Employee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		try {
			refreshEmployeeForm(employeeForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Unspecified........Employee");
		this.setAttrib(request);
		try {
			EmployeeForm employeeForm = (EmployeeForm)form;
			refreshEmployeeForm(employeeForm);
			Set<LabelValueBean> locationIds = null;
			locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);
			Set<LabelValueBean> employeeIds = null;
			employeeIds = getEmployees();
			request.getSession().setAttribute("employeeIds", employeeIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	private void refreshEmployeeForm(EmployeeForm employeeForm){
		employeeForm.setAddressLine1(null);
		employeeForm.setAddressLine2(null);
		employeeForm.setAppointedWing(null);
		employeeForm.setAppointmentNo(null);
		employeeForm.setCity(null);
		employeeForm.setContractEndDate(null);
		employeeForm.setContractStartDate(null);
		employeeForm.setContractExtentionDate(null);
		employeeForm.setContractExtentionDate(null);
		employeeForm.setRetirementDate(null);
		employeeForm.setDateOfBirth(null);
		employeeForm.setDesignation(null);
		employeeForm.setEmployeeHistoryGrid(getEmployementHistoryDatagrid(null));
		employeeForm.setEmployeeId(0);
		employeeForm.setReportingOfficerId(0);
		employeeForm.setEmployeeIds(null);
		employeeForm.setEmployeeName(null);
		employeeForm.setEmployeeQualificationGrid(getEmployeeQualificationDatagrid(null));
		employeeForm.setEmployeePromotionGrid(getEmployeePromotionDatagrid(null));
		employeeForm.setContractExtentionGrid(getEmployeeContractDatagrid(null));
		 
		employeeForm.setEmployeeType(null);
		employeeForm.setFatherName(null);
		employeeForm.setGender(null);
		employeeForm.setJoiningDate(null);
		employeeForm.setMaritalStatus(null);
		employeeForm.setMobilePhoneNo(null);
		employeeForm.setNationality(null);
		employeeForm.setOfficeEmail(null);
		employeeForm.setPanNo(null);
		employeeForm.setPermanentEmployeeId(0);
		employeeForm.setPersonalEmail(null);
		employeeForm.setPinCode(null);
		employeeForm.setQualification(null);
		employeeForm.setSanctionNo(null);
		employeeForm.setState(null);
		employeeForm.setStreet(null);
		employeeForm.setWorkPhoneNo(null);
	}
	private Datagrid getEmployementHistoryDatagrid(Set<EmploymentHistoryBean> employmentHistoryBeans) {
		Datagrid employementHistoryDatagrid = null;
		List<EmployementHistoryGridBean> employementHistoryGridBeans = new ArrayList<EmployementHistoryGridBean>();
		try {
			employementHistoryDatagrid = Datagrid.getInstance();
			employementHistoryDatagrid.setDataClass(EmployementHistoryGridBean.class);
			if (!MisUtility.ifEmpty(employmentHistoryBeans)) {
				EmployementHistoryGridBean employementHistoryGridBean = null;
				for (EmploymentHistoryBean employmentHistoryBean : employmentHistoryBeans) {
					employementHistoryGridBean = new EmployementHistoryGridBean();
					employementHistoryGridBean.setCompanyName(employmentHistoryBean.getCompanyName());
					employementHistoryGridBean.setDurationInMonths(employmentHistoryBean.getDurationInMonths());
					employementHistoryGridBean.setFromDate(MisUtility.convertDateToString(employmentHistoryBean.getFromDate()));
					employementHistoryGridBean.setToDate(MisUtility.convertDateToString(employmentHistoryBean.getToDate()));
					employementHistoryGridBean.setNatureOfBusiness(employmentHistoryBean.getNatureOfBusiness());
					employementHistoryGridBean.setReasonForLeaving(employmentHistoryBean.getReasonForLeaving());
					employementHistoryGridBean.setId(employmentHistoryBean.getId());
					employementHistoryGridBeans.add(employementHistoryGridBean);
				}				
			}
			employementHistoryDatagrid.setData(employementHistoryGridBeans);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return employementHistoryDatagrid;
	}
	
	private Datagrid getEmployeePromotionDatagrid(Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans){
		Datagrid employeePromotionDatagrid = null;
		List<EmployeePromotionHistoryGridBean> employeePromotionHistoryGridBeans = new ArrayList<EmployeePromotionHistoryGridBean>();
		try {
			employeePromotionDatagrid = Datagrid.getInstance();
			employeePromotionDatagrid.setDataClass(EmployeePromotionHistoryGridBean.class);
			if (!MisUtility.ifEmpty(employeePromotionHistoryBeans)) {
				EmployeePromotionHistoryGridBean employeePromotionHistoryGridBean = null;
				for (EmployeePromotionHistoryBean employeePromotionHistoryBean : employeePromotionHistoryBeans) {
					employeePromotionHistoryGridBean = new EmployeePromotionHistoryGridBean();
					employeePromotionHistoryGridBean.setDateOfPromotion(MisUtility.convertDateToString(employeePromotionHistoryBean.getDateOfPromotion()));
					employeePromotionHistoryGridBean.setId(employeePromotionHistoryBean.getId());
					employeePromotionHistoryGridBean.setPayrollExtended(employeePromotionHistoryBean.getPayrollExtended());
					employeePromotionHistoryGridBean.setPromotedDesignation(employeePromotionHistoryBean.getPromotedDesignation());
					employeePromotionHistoryGridBeans.add(employeePromotionHistoryGridBean);
				}				
			}
			employeePromotionDatagrid.setData(employeePromotionHistoryGridBeans );
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return employeePromotionDatagrid;
	}
	
	private Datagrid getEmployeeContractDatagrid(Set<EmployeeContactExtendedBean> employeeContactExtendedBeans){
		Datagrid employeeContractDatagrid = null;
		List<EmployeeContactExtendedGridBean> employeeContactExtendedGridBeans = new ArrayList<EmployeeContactExtendedGridBean>();
		try{
			employeeContractDatagrid = Datagrid.getInstance();
			employeeContractDatagrid.setDataClass(EmployeeContactExtendedGridBean.class);
			if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
				EmployeeContactExtendedGridBean employeeContactExtendedGridBean = null;
				for(EmployeeContactExtendedBean employeeContactExtendedBean : employeeContactExtendedBeans){
					employeeContactExtendedGridBean = new EmployeeContactExtendedGridBean();
					employeeContactExtendedGridBean.setExtentionContarctNo(employeeContactExtendedBean.getExtentionContarctNo());
					employeeContactExtendedGridBean.setExtentionContractDate(MisUtility.convertDateToString(employeeContactExtendedBean.getExtentionContractDate()));
					employeeContactExtendedGridBean.setExtentionContractUpto(MisUtility.convertDateToString(employeeContactExtendedBean.getExtentionContractUpto()));
					employeeContactExtendedGridBean.setId(employeeContactExtendedBean.getId());
					employeeContactExtendedGridBeans.add(employeeContactExtendedGridBean);
					
				}
			}
			employeeContractDatagrid.setData(employeeContactExtendedGridBeans);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return employeeContractDatagrid;
	}
	
	private Datagrid getEmployeeQualificationDatagrid(Set<EmployeeQualificationBean> employeeQualificationBeans) {
		Datagrid employeeQualificationDatagrid = null;
		try {
			employeeQualificationDatagrid = Datagrid.getInstance();
			employeeQualificationDatagrid.setDataClass(EmployeeQualificationBean.class);
			if (!MisUtility.ifEmpty(employeeQualificationBeans)) {
				employeeQualificationDatagrid.setData(new ArrayList<EmployeeQualificationBean>(employeeQualificationBeans));
			}else{
				employeeQualificationDatagrid.setData(new ArrayList<EmployeeQualificationBean>());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return employeeQualificationDatagrid;
	}
	
	
	private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try {
			List<String> locationTypeList = new ArrayList<String>();
			locationTypeList.add("DPMC");
			locationTypeList.add("DO");
			locationTypeList.add("SPMC");
			locationTypeList.add("CIRCLE");
			locationTypeList.add("ZONE");
			locationTypeList.add("Sub-Division");
			locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
			locationIds = new HashSet<LabelValueBean>();
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
	
	private Set<LabelValueBean> getEmployees(){
		Set<LabelValueBean> employees = null;
		Set<EmployeeBean> employeeBeans = null;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			employeeBeans = employeeDao.getDistinctEmployeeIds(null,statusList);
			employees = new HashSet<LabelValueBean>();
				if(!MisUtility.ifEmpty(employeeBeans)){
			for (EmployeeBean employeeBean : employeeBeans) {
				employees.add(new LabelValueBean(employeeBean.getEmployeeName()+" - ("+employeeBean.getEmployeeId()+")",new Long(employeeBean.getEmployeeId()).toString()));	
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
	public ActionForward getEmployeeByDeployedLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<EmployeeBean> employeeBeans= new TreeSet<EmployeeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				EmployeeBean employeeBean=new EmployeeBean();
				employeeBean.setLocationId(request.getParameter("locationId"));
				employeeBeans = employeeDao.getEmployeeByDeployedLocation(employeeBean,statusList);
				buffer.append("<option value=''>Please Select </option>");
				
				for (EmployeeBean employeeBean2: employeeBeans) {
					String designation=null;
					buffer.append("<option value=\"").append(employeeBean2.getEmployeeId()).append("\">");
					Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans= employeeBean2.getEmployeePromotionHistoryBeans();
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans2=new ArrayList<EmployeePromotionHistoryBean>();
						
						for (EmployeePromotionHistoryBean employeePromotionHistoryBean : employeePromotionHistoryBeans) {
							employeePromotionHistoryBeans2.add(employeePromotionHistoryBean);
							
							/*	designation=employeePromotionHistoryBean.getPromotedDesignation();							
							break;*/
						}
						Collections.sort(employeePromotionHistoryBeans2);
						designation=employeePromotionHistoryBeans2.get(employeePromotionHistoryBeans2.size()-1).getPromotedDesignation();
					}else{
						designation=employeeBean2.getDesignationId();
					}					
					buffer.append(employeeBean2.getEmployeeName()+" - "+designation+" - ("+employeeBean2.getEmployeeId()+")");
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
	
	 
	public boolean checkDateFlow(String date,String prDate){
		System.out.println("date-------"+date);
		Date date1 = null;
		Date date2 = null;
		System.out.println("date checking");
		
		   String myFormatString = "dd-MM-yyyy"; // for example
		   SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		   try{
		     date1 = df.parse(date);
		     date2 = df.parse(prDate);
		   } catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		   if(date2.before(date1)){
			   return false;
		   }else{
			   return true;
		   }
	}
	 
	public ActionForward getEmployeeDegignation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<EmployeeDesignationBean> employeeDesignationBeans = new ArrayList<EmployeeDesignationBean>();
		EmployeeDesignationBean employeeDesignationBean = new EmployeeDesignationBean();
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("employee type================="+request.getParameter("employeeType"));
			if(MisUtility.ifEmpty(request.getParameter("employeeType"))){
				employeeDesignationBean.setEmployeeType(request.getParameter("employeeType"));
				employeeDesignationBeans = employeeDesignationDao.getEmployeeDesignation(employeeDesignationBean);
				buffer.append("<option value=''>Select </option>");
			if(!MisUtility.ifEmpty(employeeDesignationBeans))	{
				for(EmployeeDesignationBean empDesignationBean : employeeDesignationBeans){
					buffer.append("<option value=\"").append(empDesignationBean.getDesignationId()).append("\">");
					buffer.append(empDesignationBean.getDesignationName());
					buffer.append("</option>");
				//	System.out.println(buffer);
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
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "employeeId@employeeName@birthDateId@fatherNameId");
		request.setAttribute("d__auto","employeeId");

	}


	
}
