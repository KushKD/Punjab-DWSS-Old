package com.prwss.mis.hr.payroll.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import com.prwss.mis.hr.payroll.HRPayrollBO;
import com.prwss.mis.hr.payroll.HRPayrollBean;
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBean;
import com.prwss.mis.hr.salarystructure.dao.HRSalaryStructureDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

public class HRPayrollAction extends DispatchAction {
	
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(HRPayrollAction.class);
	private MISSessionBean misSessionBean;
	private HRSalaryStructureDao hrSalaryStructureDao;
	private HRPayrollBO hrPayrollBO;
	
	
	
	public void setHrPayrollBO(HRPayrollBO hrPayrollBO) {
		this.hrPayrollBO = hrPayrollBO;
	}


	public void setHrSalaryStructureDao(HRSalaryStructureDao hrSalaryStructureDao) {
		this.hrSalaryStructureDao = hrSalaryStructureDao;
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
			HRPayrollForm hrPayrollForm = (HRPayrollForm)form;
			List<HRPayrollBean> hrPayrollBeans = null;
			hrPayrollBeans = hrPayrollBO.findHRPayroll(hrPayrollForm, statusList);
			if(!MisUtility.ifEmpty(hrPayrollBeans)){
				request.setAttribute("level2","true");
				request.setAttribute("hrPayrollBeans", hrPayrollBeans);
			}else{
				refreshHRPayrollForm(hrPayrollForm);
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
		request.setAttribute("level2","false");
		HRPayrollForm hrPayrollForm = (HRPayrollForm)form; 
		long vocId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(hrPayrollForm.getLocationId())||!MisUtility.ifEmpty(hrPayrollForm.getEmployeeId())||!MisUtility.ifEmpty(hrPayrollForm.getTotalAmount())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			String currentDate = MisUtility.now("dd-MM-yyyy");
			StringTokenizer stringTokenizer = new StringTokenizer(currentDate, "-");
			int year = 0 ;
			int month = 0;
			int date = 0;
			while(stringTokenizer.hasMoreTokens()){
				date = new Integer(stringTokenizer.nextToken());
				month = new Integer(stringTokenizer.nextToken()); 
				year = new Integer(stringTokenizer.nextToken());
			}
			System.out.println("MONTH CURRENT"+month);
			System.out.println("FROM MOnth"+new Integer(hrPayrollForm.getPayrollMonth()));
			
			// This code is to check the month for which payroll voucher is generated
			//User is not allowed to generate payroll voucher other than the current month, also year of generation can be current year
			if((month != new Integer(hrPayrollForm.getPayrollMonth())|| (new Integer(hrPayrollForm.getPayrollYear())!=year))){
				throw new MISException(MISExceptionCodes.MIS007,"Please Check Your Month and Year Of Payroll Generation");
			}
			//****************************************************************************************************************
			vocId = hrPayrollBO.saveHRPayroll(hrPayrollForm, misSessionBean);
			
			if (vocId!=0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("payroll.success.save",hrPayrollForm.getEmployeeId(),vocId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("fatal.error.save","Generation of Payroll Voucher");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("salary.duplicate.entry","Generation Of Payroll Voucher Failed For Employee Id - "+hrPayrollForm.getEmployeeId()," As Entry Already Exist For The Specified Month And Year As Given");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS007.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("payroll.year.month.incorrect");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			else{
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
			ActionMessage message = new ActionMessage("fatal.error.save","Generation of Payroll Voucher");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		HRPayrollForm hrPayrollForm = (HRPayrollForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshHRPayrollForm(hrPayrollForm);
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
	
	private void refreshHRPayrollForm(HRPayrollForm hrPayrollForm){
		hrPayrollForm.setEmployeeId(0);
		hrPayrollForm.setLocationId(null);
		hrPayrollForm.setPayrollMonth(null);
		hrPayrollForm.setPayrollYear(null);
		hrPayrollForm.setTotalAmount(0);
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
				//System.out.println("Type"+request.getParameter("employeeType"));
				employeeBeans = employeeDao.getDistinctEmployeeIds(request.getParameter("locationId"),statusList);
				buffer.append("<option value='0'>");
				buffer.append("Select Employee");
				buffer.append("</option>");
				for (EmployeeBean employeeBean : employeeBeans) {
					if(employeeBean.getEmployeeType().equals("CONTRACTUAL")){
						System.out.println("111111111111111111111"+employeeBean.getEmployeeType());
							String contractEndDate = MisUtility.convertDateToString(employeeBean.getContractEndDate());
						 
							String contractExtentionDate = MisUtility.convertDateToString(employeeBean.getContractExtendedUpto());
						 
							String myFormatString = "dd-MM-yyyy";
							 
						   SimpleDateFormat df = new SimpleDateFormat(myFormatString);
						   StringBuffer currentBuffer = new StringBuffer();
						   Calendar cal = new GregorianCalendar();
						   currentBuffer.append(cal.get(Calendar.DAY_OF_MONTH));
						   currentBuffer.append("-");
						   currentBuffer.append(cal.get(Calendar.MONTH)+1);
						   currentBuffer.append("-");
						   currentBuffer.append(cal.get(Calendar.YEAR));
						   Date currentDate = df.parse(currentBuffer.toString());
						   
						   if(MisUtility.ifEmpty(contractEndDate)){
							   
							   Date contEndDate = df.parse(contractEndDate);
							   if(currentDate.before(contEndDate)){
								   buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
									buffer.append(employeeBean.getEmployeeId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean.getEmployeeName());
									buffer.append("</option>");
							   }else if(MisUtility.ifEmpty(contractExtentionDate)){
								   Date contractExtenUpto = df.parse(contractExtentionDate);
								   if(currentDate.before(contractExtenUpto)){
									   buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
										buffer.append(employeeBean.getEmployeeId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean.getEmployeeName());
										buffer.append("</option>");
								   }
							   }
							   
						   }

					}else{
						System.out.println("hjjjjjjjjjjj"+employeeBean.getEmployeeType());
					buffer.append("<option value=\"").append(employeeBean.getEmployeeId()).append("\">");
					buffer.append(employeeBean.getEmployeeId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+employeeBean.getEmployeeName());
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
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		   } catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward fetchSalary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		HRSalaryStructureBean hrSalaryStructureBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(new Integer(request.getParameter("employeeId")).intValue())){
				HRSalaryStructureBean salaryStructureBean = new HRSalaryStructureBean();
				EmployeeBean bean = new EmployeeBean();
				bean.setEmployeeId(new Long(request.getParameter("employeeId")));
				salaryStructureBean.setEmployeeBean(bean);
				hrSalaryStructureBean = hrSalaryStructureDao.findSalary(salaryStructureBean, statusList).get(0);
				if(MisUtility.ifEmpty(hrSalaryStructureBean.getTotalAmount()))	
				buffer.append(hrSalaryStructureBean.getTotalAmount());
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
