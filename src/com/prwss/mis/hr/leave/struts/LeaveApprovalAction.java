package com.prwss.mis.hr.leave.struts;

import java.util.ArrayList;
import java.util.List;

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
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceDao;
import com.prwss.mis.hr.leave.dao.LeaveManagementDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class LeaveApprovalAction extends DispatchAction {
	private Logger log = Logger.getLogger(LeaveApprovalAction.class);
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
			LeaveApprovalForm leaveApprovalForm = (LeaveApprovalForm)form;
			List<LeaveManagementBean> leaveManagementBeans = null;
			leaveManagementBeans = leaveManagementBO.findLeaveApprovalForm(leaveApprovalForm, statusList, misSessionBean);
				request.setAttribute("level2","true");
				request.setAttribute("leaveApprovalList", leaveManagementBeans);

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
		boolean status = false;
		LeaveApprovalForm leaveApprovalForm = (LeaveApprovalForm)form;
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
			
			status = leaveManagementBO.saveLeaveApprovalForm(leaveApprovalForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("leave.success.update","Leave ID -> "+leaveApprovalForm.getLeaveId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			}else{
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Leave");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
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
				ActionMessage message = new ActionMessage("fail.update",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS005.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fail.update",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS006.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.Operation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Leave");
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
		request.setAttribute("op", "tffffffffffff");
		request.setAttribute("Rpt","ent");
		refreshLeaveApprovalForm(leaveApprovalForm);
		getForApprovalLeave(request);
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("op", "tffffffffffff");
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("Rpt","ent");
		LeaveApprovalForm leaveApprovalForm = (LeaveApprovalForm)form;
		System.out.println("IN");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshLeaveApprovalForm(leaveApprovalForm);
		getForApprovalLeave(request);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "leaveId@leaveStatus");
	}
	
	private void refreshLeaveApprovalForm(LeaveApprovalForm leaveApprovalForm){
		leaveApprovalForm.setLeaveFromDate(null);
		leaveApprovalForm.setLeaveId(0);
		leaveApprovalForm.setLeaveReason(null);
		leaveApprovalForm.setLeaveRequestFromDate(null);
		leaveApprovalForm.setLeaveRequestToDate(null);
		leaveApprovalForm.setOfficerComments(null);
		leaveApprovalForm.setLeaveStatus(null);
	}
	
	
	private void getForApprovalLeave(HttpServletRequest request){
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
					leaveManagementBean.setReportingOfficerEmployeeBean(bean);
					leaveManagementBean.setLeaveStatus(MISConstants.HR_LEAVE_NO_ACTION);
					leaveManagementBeans = leaveManagementDao.findLeave(leaveManagementBean, statusList);
						request.setAttribute("leaveApprovalList", leaveManagementBeans);
				} catch (DataAccessException e) {
					e.printStackTrace();
					log.error(e.getLocalizedMessage(),e);
				}
			}
		}
	}

	public ActionForward getLeaveToApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws MISException{
		request.setAttribute("op", "ftfffffffffff");
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("Rpt","ent");
		long leaveId = new Long(request.getParameter("leaveId"));
//		List<LeaveManagementBean> leaveManagementBeans = null;
		LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
		leaveManagementBean.setLeaveId(leaveId);
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		LeaveApprovalForm approvalForm = (LeaveApprovalForm)form;
		LeaveManagementBean leaveManagementBean2 = leaveManagementDao.findLeave(leaveManagementBean, statusList).get(0);
		if(MisUtility.ifEmpty(leaveManagementBean2)){
			approvalForm.setLeaveId(leaveManagementBean2.getLeaveId());
			approvalForm.setLeaveReason(leaveManagementBean2.getLeaveReason());
			approvalForm.setLeaveStatus(leaveManagementBean2.getLeaveStatus());
			approvalForm.setLeaveFromDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveFromDate()));
			approvalForm.setLeaveToDate(MisUtility.convertDateToString(leaveManagementBean2.getLeaveToDate()));
			approvalForm.setOfficerComments(leaveManagementBean2.getOfficerComments());
		}
			return mapping.findForward("approval");	
	}
}
