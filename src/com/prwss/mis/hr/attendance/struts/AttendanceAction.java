package com.prwss.mis.hr.attendance.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

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
import com.prwss.mis.hr.attendance.HRAttendanceBO;
import com.prwss.mis.hr.attendance.HRAttendanceBean;
import com.prwss.mis.hr.attendance.dao.HRAttendanceDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

public class AttendanceAction extends DispatchAction {
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(AttendanceAction.class);
	private MISSessionBean misSessionBean;
	private HRAttendanceBO hrAttendanceBO;
	private HRAttendanceDao hrAttendanceDao;
	
	
	
	public void setHrAttendanceDao(HRAttendanceDao hrAttendanceDao) {
		this.hrAttendanceDao = hrAttendanceDao;
	}

	public void setHrAttendanceBO(HRAttendanceBO hrAttendanceBO) {
		this.hrAttendanceBO = hrAttendanceBO;
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
			AttendanceForm attendanceForm = (AttendanceForm)form;
			List<HRAttendanceBean> hrAttendanceBeans = null;
			hrAttendanceBeans = hrAttendanceBO.findHRAttendanceForm(attendanceForm, statusList);
			if(!MisUtility.ifEmpty(hrAttendanceBeans)){
				request.setAttribute("attendanceList", hrAttendanceBeans);
//				if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
//					request.setAttribute("level2", "true");
//				}
//				for (HRAttendanceBean hrAttendanceBean : hrAttendanceBeans) {
//					attendanceForm.setLocationId(hrAttendanceBean.getLocationId());
//					request.setAttribute("employeeId", hrAttendanceBean.getEmployeeBean().getEmployeeId());
//					attendanceForm.setAttendanceDate(MisUtility.convertDateToString(hrAttendanceBean.getAttendanceDate()));
//					attendanceForm.setAttendanceStatus(hrAttendanceBean.getAttendanceStatus());
//				}
			}else{
				refreshAttendanceForm(attendanceForm);
				ActionMessages errors = new ActionMessages();
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
		AttendanceForm attendanceForm = (AttendanceForm)form; 
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
			if(!MisUtility.ifEmpty(attendanceForm.getLocationId())||!MisUtility.ifEmpty(attendanceForm.getAttendanceDate())||!MisUtility.ifEmpty(attendanceForm.getEmployeeId())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			status = hrAttendanceBO.saveHRAttendanceForm(attendanceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("attendance.success.save","Attendance of Employee With Employee Id - "+attendanceForm.getEmployeeId(),attendanceForm.getAttendanceDate());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Submission of Attendance");
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
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Attendance");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Attendance");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		AttendanceForm attendanceForm = (AttendanceForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = hrAttendanceBO.updateHRAttendanceForm(attendanceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Attendance Entry","of Employee Id"+attendanceForm.getEmployeeId()+"for "+attendanceForm.getAttendanceDate());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Attendance Entry","of Employee Id"+attendanceForm.getEmployeeId()+"for "+attendanceForm.getAttendanceDate());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Attendance");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Attendance");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshAttendanceForm(attendanceForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		AttendanceForm attendanceForm = (AttendanceForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			status = hrAttendanceBO.postHRAttendanceForm(attendanceForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Attendance of the Employee");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.post","Attendance Of Selected Employees");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Posting failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("hr.ids.not.selected","Posting failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Posting of Attendance");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Posting of Attendance");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshAttendanceForm(attendanceForm);
		return mapping.findForward("display");
	}
		
	

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		AttendanceForm attendanceForm = (AttendanceForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshAttendanceForm(attendanceForm);
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
		request.setAttribute("d__ky", "attendanceDateId@employeeId@locationId");
	}
	
	private void refreshAttendanceForm(AttendanceForm attendanceForm){
		attendanceForm.setAttendanceDate(null);
		attendanceForm.setLocationId(null);
		attendanceForm.setEmployeeId(0);
		attendanceForm.setAttendanceStatus(MISConstants.HR_ATTENDANCE_PRESENT);
		attendanceForm.setAttendenceId(0);
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
//				System.out.println("From Attendance"+employeeBeans);
				if(!MisUtility.ifEmpty(employeeBeans)){
				buffer.append("<option value='0'>");
				buffer.append("Select");
				buffer.append("</option>");
				for (EmployeeBean employeeBean : employeeBeans) {
					buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
					buffer.append(employeeBean.getEmployeeName()+" - ("+employeeBean.getEmployeeId()+")");
					buffer.append("</option>");
				}
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
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true"); 
		try {
			AttendanceForm attendanceForm = (AttendanceForm)form;
			HRAttendanceBean hrAttendanceBean = new HRAttendanceBean();
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(new Long(request.getParameter("employeeId")));
			hrAttendanceBean.setEmployeeBean(employeeBean);
			hrAttendanceBean.setAttendanceDate(stringSqlDateTODate(request.getParameter("attendanceDate")));
			List<HRAttendanceBean> hrAttendanceBeans = null;
			hrAttendanceBeans = hrAttendanceDao.findAttendance(hrAttendanceBean, null);
			if(!MisUtility.ifEmpty(hrAttendanceBeans)){
				for (HRAttendanceBean hrAttendanceBean2 : hrAttendanceBeans) {
					attendanceForm.setLocationId(hrAttendanceBean2.getLocationId());
					request.setAttribute("employeeId", hrAttendanceBean2.getEmployeeBean().getEmployeeId());
					attendanceForm.setAttendanceDate(MisUtility.convertDateToString(hrAttendanceBean2.getAttendanceDate()));
					attendanceForm.setAttendanceStatus(hrAttendanceBean2.getAttendanceStatus());
					attendanceForm.setAttendenceId(hrAttendanceBean2.getAttendenceId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	private Date stringSqlDateTODate(String dateString){
		
		Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		StringTokenizer stringTokenizer = new StringTokenizer(dateString, "-");
		int year = 0 ;
		int month = 0;
		int date = 0;
		while(stringTokenizer.hasMoreTokens()){
			year = new Integer(stringTokenizer.nextToken());
			month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
			date = new Integer(stringTokenizer.nextToken());
		}
		Calendar cal = GregorianCalendar.getInstance(aLocale);		
		cal.set(year, month, date);
		
		return new Date(cal.getTime().getTime());
	}
	
}
