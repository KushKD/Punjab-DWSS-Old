package com.prwss.mis.hr.leave.struts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import com.prwss.mis.hr.leave.LeaveManagementBO;
import com.prwss.mis.hr.leave.LeaveManagementBean;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceBean;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceDao;
import com.prwss.mis.hr.leave.dao.LeaveManagementDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class LeaveAction extends DispatchAction {
	private Logger log = Logger.getLogger(LeaveAction.class);
	private LeaveManagementBO leaveManagementBO;
	private MISSessionBean misSessionBean;
	private LeaveManagementDao leaveManagementDao;
	private LeaveBalanceDao leaveBalanceDao;
	
	public void setLeaveBalanceDao(LeaveBalanceDao leaveBalanceDao) {
		this.leaveBalanceDao = leaveBalanceDao;
	}

	public void setLeaveManagementDao(LeaveManagementDao leaveManagementDao) {
		this.leaveManagementDao = leaveManagementDao;
	}

	public void setLeaveManagementBO(LeaveManagementBO leaveManagementBO) {
		this.leaveManagementBO = leaveManagementBO;
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
			LeaveForm leaveForm = (LeaveForm)form;
			List<LeaveManagementBean> leaveManagementBeans = null;
			leaveManagementBeans = leaveManagementBO.findLeaveManagementForm(leaveForm, statusList,misSessionBean);
			if(!MisUtility.ifEmpty(leaveManagementBeans)){
				request.setAttribute("level2","true");
				refreshLeaveForm(leaveForm,misSessionBean);
					for (LeaveManagementBean leaveManagementBean2 : leaveManagementBeans) {
						leaveForm.setLeaveFromDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveFromDate()));
						leaveForm.setLeaveToDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveToDate()));
						leaveForm.setLeaveReason(leaveManagementBean2.getLeaveReason());
						leaveForm.setLocationId(leaveManagementBean2.getLocationId());
						leaveForm.setLeaveId(leaveManagementBean2.getLeaveId());
					}

			}else{
				refreshLeaveForm(leaveForm,misSessionBean);
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
			throws Exception {
		this.setAttrib(request);
		LeaveForm leaveForm = (LeaveForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = leaveManagementBO.deleteLeaveManagementForm(leaveForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Your Leave","Leave Id-->"+leaveForm.getLeaveId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Your Leave","Leave Id-->"+leaveForm.getLeaveId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS006.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fail.update",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Leave");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshLeaveForm(leaveForm,misSessionBean);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		LeaveForm leaveForm = (LeaveForm)form; 
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(leaveForm.getLocationId())||!MisUtility.ifEmpty(leaveForm.getLeaveFromDate())||!MisUtility.ifEmpty(leaveForm.getLeaveToDate())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			if(!allowLeaveAppliedToLeaveBalance(leaveForm, misSessionBean)){
				throw new MISException(MISExceptionCodes.MIS005,"Leave Applied Exception");
			}

			status = leaveManagementBO.updateLeaveManagementForm(leaveForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Your Leave","Leave Id-->"+leaveForm.getLeaveId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Your Leave","Leave Id-->"+leaveForm.getLeaveId());
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
			}else if(MISExceptionCodes.MIS006.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fail.update",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);}
			else if(MISExceptionCodes.MIS005.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("leave.applied.error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshLeaveForm(leaveForm,misSessionBean);
		return mapping.findForward("display");
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		LeaveForm leaveForm = (LeaveForm)form; 
		long leaveId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(leaveForm.getLocationId())||!MisUtility.ifEmpty(leaveForm.getLeaveFromDate())||!MisUtility.ifEmpty(leaveForm.getLeaveToDate())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			if(!allowLeaveAppliedToLeaveBalance(leaveForm, misSessionBean)){
				throw new MISException(MISExceptionCodes.MIS005,"Leave Applied Exception");
			}
			leaveId = leaveManagementBO.saveLeaveManagementForm(leaveForm, misSessionBean);
			if (leaveId!=0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("leave.success.save",leaveId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Submission of Leave");
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
			}else if(MISExceptionCodes.MIS005.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("leave.applied.error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Leave");
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
		refreshLeaveForm(leaveForm,misSessionBean);
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		LeaveForm leaveForm = (LeaveForm)form;
		System.out.println("IN");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		refreshLeaveForm(leaveForm,misSessionBean);
		getMyLeave(request);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@leaveId");
		request.setAttribute("d__auto", "locationId@leaveId@leaveEntitled@leaveAvailed@leaveBalance");
	}
	
	private void refreshLeaveForm(LeaveForm leaveForm,MISSessionBean misSessionBean){
		leaveForm.setLeaveFromDate(null);
		leaveForm.setLeaveToDate(null);
		leaveForm.setLeaveId(0);
		leaveForm.setLeaveReason(null);
		Map<String,Long> leaveMap = null;
		leaveMap = getLeaveBalance(misSessionBean);
		if(!MisUtility.ifEmpty(leaveMap)){
		leaveForm.setLeaveBalance(leaveMap.get("leaveBalance"));
		leaveForm.setLeaveEntitled(leaveMap.get("totalEligible"));
		leaveForm.setLeaveAvailed(leaveMap.get("leaveAvailed"));
		}else {
			leaveForm.setLeaveBalance(0);
			leaveForm.setLeaveEntitled(0);
			leaveForm.setLeaveAvailed(0);
		}
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true"); 
		try {
			System.out.println("IN POPULATE");
			LeaveForm leaveForm = (LeaveForm)form;
			List<LeaveManagementBean> leaveManagementBeans = null;
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
			leaveManagementBean.setLeaveId(new Long(request.getParameter("leaveId")));
			leaveManagementBeans = leaveManagementDao.findLeave(leaveManagementBean, null);
			if(!MisUtility.ifEmpty(leaveManagementBeans)){
				for (LeaveManagementBean leaveManagementBean2 : leaveManagementBeans) {
					leaveForm.setLeaveFromDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveFromDate()));
					leaveForm.setLeaveToDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveToDate()));
					leaveForm.setLeaveReason(leaveManagementBean2.getLeaveReason());
					leaveForm.setLocationId(leaveManagementBean2.getLocationId());
					leaveForm.setLeaveId(leaveManagementBean2.getLeaveId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	private void getMyLeave(HttpServletRequest request){
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				try {
					List<String> statusList = new ArrayList<String>();
					statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					EmployeeBean bean = new EmployeeBean();
					bean.setEmployeeId(misSessionBean.getEnteredBy());
					List<LeaveManagementBean> leaveManagementBeans = null;
					LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
					leaveManagementBean.setEntByEmployeeBean(bean);
					leaveManagementBeans = leaveManagementDao.findLeave(leaveManagementBean, statusList);
					if(!MisUtility.ifEmpty(leaveManagementBeans)){
						request.setAttribute("leaveManagementBeans", leaveManagementBeans);
					}
				} catch (DataAccessException e) {
					e.printStackTrace();
					log.error(e.getLocalizedMessage(),e);
				}
			}
		}
	}
	
	private Map<String,Long> getLeaveBalance(MISSessionBean misSessionBean){
		Map<String,Long> leaveMap = new HashMap<String, Long>(); 
		long leaveBalance = 0;
		long totalAvailed = 0;
		long totalEligible = 0;
		try {
			System.out.println("IN LEAVE BALANCE");
			String currentDate= MisUtility.now("dd-MM-yyyy");
			StringTokenizer stringTokenizer = new StringTokenizer(currentDate, "-");
			int year = 0 ;
			int month = 0;
			int date = 0;
			while(stringTokenizer.hasMoreTokens()){
				date = new Integer(stringTokenizer.nextToken());
				month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
				year = new Integer(stringTokenizer.nextToken());
			}
			LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
			leaveBalanceBean.setYearFor(year);
			leaveBalanceBean.setEmployeeId(misSessionBean.getEnteredBy());
			LeaveBalanceBean leaveBalanceBean2 = null;
			System.out.println("Action leaveBalanceBean: "+leaveBalanceBean.getEmployeeId());
			leaveBalanceBean2 = leaveBalanceDao.findLeaveBalance(leaveBalanceBean, null).get(0); 
			if(MisUtility.ifEmpty(leaveBalanceBean2)){
				totalAvailed = leaveBalanceBean2.getTotalAvailed();
				totalEligible = leaveBalanceBean2.getTotalEligibileLeave();
				leaveBalance = totalEligible-totalAvailed;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		leaveMap.put("leaveAvailed", totalAvailed);
		leaveMap.put("totalEligible", totalEligible);
		leaveMap.put("leaveBalance", leaveBalance);
		return leaveMap;
	}
	
	// To check entered Leave are not greater than leave balance and also year of leave applied is same as that of the current year
	private boolean allowLeaveAppliedToLeaveBalance(LeaveForm leaveForm,MISSessionBean misSessionBean){
		boolean status = false;
		long leaveBalance = 0;
		long appliedLeaveByUser = 0;
		try {
			Map<String,Long> leaveBalanceMap = getLeaveBalance(misSessionBean);
			leaveBalance = leaveBalanceMap.get("leaveBalance");
			
			String currentDate = MisUtility.now("dd-MM-yyyy");
			Date leaveFromDate = MisUtility.convertStringToDate(leaveForm.getLeaveFromDate());
			Date leaveToDate = MisUtility.convertStringToDate(leaveForm.getLeaveToDate());
			StringTokenizer stringTokenizer = new StringTokenizer(currentDate, "-");
			int year = 0 ;
			int month = 0;
			int date = 0;
			while(stringTokenizer.hasMoreTokens()){
				date = new Integer(stringTokenizer.nextToken());
				month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
				year = new Integer(stringTokenizer.nextToken());
			}
			
			Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
			Calendar cal = GregorianCalendar.getInstance(aLocale);
			
			cal.setTime(leaveFromDate);				
			int leaveFromYear = cal.get(Calendar.YEAR) ;		// To fetch Year
			
			cal.setTime(leaveToDate);
			int leaveToYear = cal.get(Calendar.YEAR);			//to fetch year
			
			if(leaveFromYear!=year){
				return false;
			}
			appliedLeaveByUser = MisUtility.getNumberOfDays(leaveFromDate, leaveToDate);
			if(appliedLeaveByUser<=leaveBalance && appliedLeaveByUser>0)
				return true;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	

}
