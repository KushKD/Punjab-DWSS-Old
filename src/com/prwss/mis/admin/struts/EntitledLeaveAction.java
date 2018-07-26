package com.prwss.mis.admin.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.prwss.mis.hr.leave.LeaveManagementBean;
import com.prwss.mis.hr.leave.balance.EntitledLeaveBO;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceBean;
import com.prwss.mis.hr.leave.struts.LeaveForm;
import com.prwss.mis.hr.salarystructure.struts.HRSalaryStructureForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public class EntitledLeaveAction extends DispatchAction{
	private EmployeeDao employeeDao;
	private MISSessionBean misSessionBean;
	private EntitledLeaveBO entitledLeaveBO;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}
	
	public void setEntitledLeaveBO(EntitledLeaveBO entitledLeaveBO) {
		this.entitledLeaveBO = entitledLeaveBO;
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		EntitledLeaveForm entitledLeaveForm = (EntitledLeaveForm)form;
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
			if(!MisUtility.ifEmpty(entitledLeaveForm.getEmployeeId())||!MisUtility.ifEmpty(entitledLeaveForm.getYearFor())|| !MisUtility.ifEmpty(entitledLeaveForm.getTotalEligibileLeave())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			System.out.println("Location Id=="+entitledLeaveForm.getLocationId());
			System.out.println("EmployyeId=="+entitledLeaveForm.getEmployeeId());
			System.out.println("Year For=="+entitledLeaveForm.getYearFor());
			System.out.println("Entitled Leave"+entitledLeaveForm.getTotalEligibileLeave());
			
			status = entitledLeaveBO.saveEntitledLeaveForm(entitledLeaveForm, misSessionBean);
			System.out.println(status);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Employee Entitled Leave Successfully"," Employee Id - "+entitledLeaveForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshEntitledLeavForm(entitledLeaveForm, misSessionBean);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Submission of  Entitled Leave");
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
			EntitledLeaveForm entitledLeaveForm = (EntitledLeaveForm)form;
			System.out.println(entitledLeaveForm.getEmployeeId());
			System.out.println(entitledLeaveForm.getLocationId());
			System.out.println(entitledLeaveForm.getYearFor());
			System.out.println(entitledLeaveForm.getTotalEligibileLeave());
			List<LeaveBalanceBean>  leaveBalanceBeans = null;
			leaveBalanceBeans = entitledLeaveBO.findEntitledLeaveForm(entitledLeaveForm, statusList);
			if(!MisUtility.ifEmpty(leaveBalanceBeans)){
				request.setAttribute("level2","true");
				//refreshLeaveForm(leaveForm,misSessionBean);
					for (LeaveBalanceBean leaveBalanceBean : leaveBalanceBeans) {
							entitledLeaveForm.setLocationId(leaveBalanceBean.getLocationId());
							entitledLeaveForm.setEmployeeId(leaveBalanceBean.getEmployeeId());
							request.setAttribute("employeeId",leaveBalanceBean.getEmployeeId());
							entitledLeaveForm.setYearFor(leaveBalanceBean.getYearFor());
							request.setAttribute("yearFor",leaveBalanceBean.getYearFor());
							entitledLeaveForm.setTotalEligibileLeave((leaveBalanceBean.getTotalEligibileLeave()));
					}

			}else{
				 
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshEntitledLeavForm(entitledLeaveForm, misSessionBean);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Entitled Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	public ActionForward fetchEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		//Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
		Set<EmployeeBean> employeeBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		StringBuffer buffer = new StringBuffer();
		try {
			boolean releaseStatus = false;
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				String locationId = request.getParameter("locationId"); 
				EmployeeBean employeeBean = new EmployeeBean();
				 employeeBean.setLocationId(locationId);
				employeeBeans = employeeDao.getDistinctEmployeeIds(locationId, null);
				buffer.append("<option value='0'>Select</option>");
				
				for (EmployeeBean employeeBean2: employeeBeans) {
					buffer.append("<option value=\"").append(employeeBean2.getEmployeeId()).append("\">");
					buffer.append(employeeBean2.getEmployeeName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean2.getEmployeeId());
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
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "employeeId@locationId@yearFor");
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		try {
			System.out.println("Unspecified------------------------------------");
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	private void refreshEntitledLeavForm(EntitledLeaveForm entitledLeaveForm,MISSessionBean misSessionBean){
		entitledLeaveForm.setLocationId(null);
		entitledLeaveForm.setEmployeeId(0);
		entitledLeaveForm.setYearFor(0);
		entitledLeaveForm.setTotalEligibileLeave(0);
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("update");
		this.setAttrib(request);
		EntitledLeaveForm entitledLeaveForm = (EntitledLeaveForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(entitledLeaveForm.getEmployeeId())||!MisUtility.ifEmpty(entitledLeaveForm.getYearFor())|| !MisUtility.ifEmpty(entitledLeaveForm.getTotalEligibileLeave())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			status = entitledLeaveBO.updateEntitledLeaveForm(entitledLeaveForm,misSessionBean);
			System.out.println(status);
			 
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Entitled Leave","Employee Id-->"+entitledLeaveForm.getEmployeeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Entitled Leave","Employee Id-->"+entitledLeaveForm.getEmployeeId());
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
			ActionMessage message = new ActionMessage("fatal.error.save","Submission of Entitled Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updation of Entitled Leave");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshEntitledLeavForm(entitledLeaveForm, misSessionBean);
		return mapping.findForward("display");
	}
	

}
