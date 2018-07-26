/**
 * 
 */
package com.prwss.mis.finance.receiptvoucher.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.prwss.mis.daktask.inwarddak.dao.InwardDakDao;
import com.prwss.mis.finance.accountschart.AccountsChartBean;
import com.prwss.mis.finance.accountschart.dao.AccountsChartDao;
import com.prwss.mis.finance.budget.BudgetBean;
import com.prwss.mis.finance.budget.dao.BudgetDao;
import com.prwss.mis.finance.receiptvoucher.ReceiptVoucherBO;
import com.prwss.mis.finance.voucher.VoucherDetailBean;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.dao.ActivityDao;
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.dao.BankMasterDao;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class ReceiptVoucherAction extends DispatchAction {

	
	Logger log = Logger.getLogger(ReceiptVoucherAction.class);
	private MISSessionBean misSessionBean;
	private ReceiptVoucherBO receiptVoucherBO;
	private ProgramDao programDao;
	private BudgetDao  budgetDao;
     private ComponentDao componentDao;
     private SubComponentDao subComponentDao;
     private ActivityDao activityDao;
     private AccountsChartDao accountsChartDao;
     private InwardDakDao inwardDakDao;
     private VoucherHeaderDao voucherHeaderDao;
     private EmployeeDao employeeDao;
     private VendorDao vendorDao;
     private LocationDao locationDao;
     private BankMasterDao bankMasterDao;
     
     
     
 	public void setBankMasterDao(BankMasterDao bankMasterDao) {
 		this.bankMasterDao = bankMasterDao;
 	}

     
	
	
     
     
     
     
	
	public void setInwardDakDao(InwardDakDao inwardDakDao) {
		this.inwardDakDao = inwardDakDao;
	}

	

	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}


	public ProgramDao getProgramDao() {
		return programDao;
	}

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	public BudgetDao getBudgetDao() {
		return budgetDao;
	}

	public void setBudgetDao(BudgetDao budgetDao) {
		this.budgetDao = budgetDao;
	}

	public ComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	public SubComponentDao getSubComponentDao() {
		return subComponentDao;
	}

	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public AccountsChartDao getAccountsChartDao() {
		return accountsChartDao;
	}

	public void setAccountsChartDao(AccountsChartDao accountsChartDao) {
		this.accountsChartDao = accountsChartDao;
	}

	public ReceiptVoucherBO getReceiptVoucherBO() {
		return receiptVoucherBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find");
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
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		List<VoucherHeaderBean> receiptVoucherBeans = null;
		receiptVoucherBeans = receiptVoucherBO.findReceiptVoucher(receiptVoucherForm, statusList);
		if(!MisUtility.ifEmpty(receiptVoucherBeans)){
		request.setAttribute("level2", "true");
		for (VoucherHeaderBean receiptVoucherBean : receiptVoucherBeans) {
			
			receiptVoucherForm.setInstrAmount(receiptVoucherBean.getAmount());
//			receiptVoucherForm.setBankName(receiptVoucherBean.getBankName());
			receiptVoucherForm.setBankId(receiptVoucherBean.getBankId());
			receiptVoucherForm.setDocumentNo(receiptVoucherBean.getDocumentNo());
			request.setAttribute("vocId", receiptVoucherBean.getVocId());
//			request.setAttribute("documentNo", receiptVoucherBean.getDocumentNo());
			request.setAttribute("payerName", receiptVoucherBean.getPayeePayerId());
			receiptVoucherForm.setDocumentNo(receiptVoucherBean.getDocumentNo());
			receiptVoucherForm.setInstrumentDate(MisUtility.convertDateToString(receiptVoucherBean.getInstrumentDate()));
			receiptVoucherForm.setInstrumentType(receiptVoucherBean.getInstrumentType());
			receiptVoucherForm.setLocationId(receiptVoucherBean.getLocationId());
			receiptVoucherForm.setNotes(receiptVoucherBean.getNotes());
			receiptVoucherForm.setPayerType(receiptVoucherBean.getPayeePayerType());
			receiptVoucherForm.setProgramId(receiptVoucherBean.getProgramId());
			receiptVoucherForm.setReceiptType(receiptVoucherBean.getPaymentMode());
			receiptVoucherForm.setReceiptMode(receiptVoucherBean.getTypeOfReceipt());
			receiptVoucherForm.setTransactionDate(MisUtility.convertDateToString(receiptVoucherBean.getTransactionDate()));
			receiptVoucherForm.setVoucherNo(receiptVoucherBean.getVocId());
			receiptVoucherForm.setVoucherDate(MisUtility.convertDateToString(receiptVoucherBean.getVocDate()));
			receiptVoucherForm.setReceiptVoucherDatagrid(getreceiptVoucherDatagrid(receiptVoucherBean.getVoucherDetailBeans()));
			
			
		}
		}else{
			refreshReceiptVoucherForm(receiptVoucherForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		//System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = receiptVoucherBO.deleteReceiptVoucher(receiptVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Receipt Voucher Information","Voucher No -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Receipt Voucher Information","Voucher No -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshReceiptVoucherForm(receiptVoucherForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		long vocId = 0;
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {				
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");				
			} else {
				//System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			StringBuffer stringMessage = new StringBuffer();
			System.out.println(receiptVoucherForm.getVoucherNo()+" Voucher Number");
			if(!MisUtility.ifEmpty(receiptVoucherForm.getLocationId())||!MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())||!MisUtility.ifEmpty(receiptVoucherForm.getTransactionDate())||!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())||!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())||!MisUtility.ifEmpty(receiptVoucherForm.getProgramId())||!MisUtility.ifEmpty(receiptVoucherForm.getInstrAmount())||!MisUtility.ifEmpty(receiptVoucherForm.getInstrumentNo())||!MisUtility.ifEmpty(receiptVoucherForm.getInstrumentDate())||!MisUtility.ifEmpty(receiptVoucherForm.getDocumentNo())){
				if(!MisUtility.ifEmpty(receiptVoucherForm.getLocationId())){
					stringMessage.append("Please Select Location");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())){
					stringMessage.append("<br> Please Enter Voucher Date");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getTransactionDate())){
					stringMessage.append("<br> Please Enter Transaction Date");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())){
					stringMessage.append("<br> Please Select Payee Type");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getPayerName())){
					stringMessage.append("<br> Please Select Payee Name");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getProgramId())){
					stringMessage.append("<br> Please Select Program Id");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getInstrAmount())){
					stringMessage.append("<br> Please Enter Amount");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getDocumentNo())){
					stringMessage.append("<br> Please Enter Voucher Number");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getInstrumentNo())){
					stringMessage.append("<br> Please Enter Instrument Number");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getInstrumentDate())){
					stringMessage.append("<br> Please Enter Instrument Date");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())){
				String strngMessage = checkDateFlow(receiptVoucherForm.getVoucherDate());
				if(strngMessage!=null){
					System.out.println("------------Check");
					receiptVoucherForm.setVoucherDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			vocId = receiptVoucherBO.saveReceiptVoucher(receiptVoucherForm, misSessionBean);
			if (vocId !=0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Receipt Voucher Information","Voucher No -->"+vocId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshReceiptVoucherForm(receiptVoucherForm);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Receipt Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			StringBuffer stringMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(receiptVoucherForm.getLocationId())||!MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())||!MisUtility.ifEmpty(receiptVoucherForm.getTransactionDate())||!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())||!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())||!MisUtility.ifEmpty(receiptVoucherForm.getProgramId())){
				if(!MisUtility.ifEmpty(receiptVoucherForm.getLocationId())){
					stringMessage.append("Please Select Location");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())){
					stringMessage.append("<br> Please Enter Voucher Date");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getTransactionDate())){
					stringMessage.append("<br> Please Enter Transaction Date");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getPayerType())){
					stringMessage.append("<br> Please Select Payee Type");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getPayerName())){
					stringMessage.append("<br> Please Select Payee Name");
				}
				if(!MisUtility.ifEmpty(receiptVoucherForm.getProgramId())){
					stringMessage.append("<br> Please Select Program Id");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(receiptVoucherForm.getVoucherDate())){
				String strngMessage = checkDateFlow(receiptVoucherForm.getVoucherDate());
				if(strngMessage!=null){
					receiptVoucherForm.setVoucherDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}

			status = receiptVoucherBO.updateReceiptVoucher(receiptVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Receipt Voucher Information","Voucher No -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshReceiptVoucherForm(receiptVoucherForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Receipt Voucher Information","Voucher No -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Receipt Voucher Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = receiptVoucherBO.postReceiptVoucher(receiptVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Receipt Voucher -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Receipt Voucher -->"+receiptVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Receipt Voucher");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Receipt Voucher");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshReceiptVoucherForm(receiptVoucherForm);
		return mapping.findForward("display");
	}
	
	public void setReceiptVoucherBO(ReceiptVoucherBO receiptVoucherBO) {
		this.receiptVoucherBO = receiptVoucherBO;
	}


	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@voucherNo");
		request.setAttribute("d__auto", "voucherNo");
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.println("unspecified:"+request.getParameter("d__mode"));
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		ReceiptVoucherForm receiptVoucherForm = (ReceiptVoucherForm)form;
		try {
			
			
			Set<LabelValueBean> programIds = getProgramIds();
			request.getSession().setAttribute("programIds", programIds);
			
			Set<LabelValueBean> componentIds = getComponentIds();
			request.getSession().setAttribute("componentIds", componentIds);
			Set<LabelValueBean> bankIds = getBankIds();
			request.getSession().setAttribute("bankIds", bankIds);
			Set<LabelValueBean> codeHeadIds = getCodeHeadIds();
			request.getSession().setAttribute("codeHeadIds", codeHeadIds);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		refreshReceiptVoucherForm(receiptVoucherForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	
	@SuppressWarnings("null")
	private Set<LabelValueBean> getCodeHeadIds() {
		Set<LabelValueBean> codeHeadIds = new TreeSet<LabelValueBean>();
		List<AccountsChartBean> accountsChartBeans = null;
		try{
			
			AccountsChartBean accountsChartBean = new AccountsChartBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			accountsChartBeans = accountsChartDao.findAccountsChart1(accountsChartBean, statusList);
			System.out.println("Hello"+accountsChartBeans.size());
			
			for (AccountsChartBean accountsChartBean2 : accountsChartBeans) {								
					codeHeadIds.add(new LabelValueBean(accountsChartBean2.getCodeHeadId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+accountsChartBean2.getCodeHeadIdDescription(),accountsChartBean2.getCodeHeadId()));								
			}
			
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		System.out.println("CodeHeadIds : "+codeHeadIds);
		return codeHeadIds;	
	}

	
	private Set<LabelValueBean> getProgramIds(){
		Set<LabelValueBean> programIds = null;
		List<ProgramBean> programBeans = null;
		try{
			ProgramBean programBean = new ProgramBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			programBeans = programDao.findProgram(programBean, statusList);
			programIds = new HashSet<LabelValueBean>();
			for (ProgramBean programBean1 : programBeans) {
				programIds.add(new LabelValueBean(programBean1.getProgramName(),programBean1.getProgramId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return programIds;	
	}
	
	public ActionForward fetchBudgetId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<BudgetBean> budgetBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		BudgetBean budgetBean = new BudgetBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("programId"))){
				budgetBean.setProgramId(request.getParameter("programId"));
				budgetBeans = new TreeSet<BudgetBean>(budgetDao.findBudgetPlan(budgetBean, statusList));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(budgetBeans)){
					for (BudgetBean budgetBean2 : budgetBeans) {
						buffer.append("<option value=\"").append(budgetBean2.getBudgetId()).append("\">");
						buffer.append(budgetBean2.getBudgetId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+MisUtility.convertDateToString(budgetBean2.getBudgetFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(budgetBean2.getBudgetToDate())+")");
						buffer.append("</option>");
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
	
	public ActionForward fetchActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ActivityBean> activityBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		ActivityBean activityBean = new ActivityBean();
		SubComponentBean subComponentBean = new SubComponentBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("subComponentId"))){
				subComponentBean.setSubComponentId(request.getParameter("subComponentId"));
				activityBean.setSubComponent(subComponentBean);
				activityBeans = new TreeSet<ActivityBean>(activityDao.findActivity(activityBean, statusList));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(activityBeans)){
					for (ActivityBean activityBean2 : activityBeans) {
						buffer.append("<option value=\"").append(activityBean2.getActivityId()).append("\">");
						buffer.append(activityBean2.getActivityId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+activityBean2.getActivityName());
						buffer.append("</option>");
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
	
	public ActionForward fetchRequestVocId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<VoucherHeaderBean> voucherHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
		//System.out.println("IN FETCH VOC ID");
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				voucherHeaderBean.setLocationId(request.getParameter("locationId"));
//				voucherHeaderBean.setProgramId(request.getParameter("programId"));
				voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_RECEIPT);
				voucherHeaderBeans = new TreeSet<VoucherHeaderBean>(voucherHeaderDao.findVoucher(voucherHeaderBean, statusList));
				//System.out.println("------------------------------------"+voucherHeaderBeans);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(voucherHeaderBeans)){
					for (VoucherHeaderBean voucherHeaderBean2 : voucherHeaderBeans) {
						//if(!(voucherHeaderBean2.getPaymentMode().equals(MISConstants.FIN_ADJUSTMENT_MODE))){
						buffer.append("<option value=\"").append(voucherHeaderBean2.getVocId()).append("\">");
						buffer.append(voucherHeaderBean2.getDocumentNo()+"("+voucherHeaderBean2.getVocId()+")");
						//buffer.append(voucherHeaderBean2.getVocId());
						buffer.append("</option>");
						//}
					}
				}
			//	System.out.println("***********  Buffer ************"+buffer.toString());
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
	
	public ActionForward fetchSubComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Set<SubComponentBean> subComponentBeans = null;
			StringBuffer buffer = new StringBuffer();
			if(MisUtility.ifEmpty(request.getParameter("componentId"))){
				String componentId = request.getParameter("componentId");
				subComponentBeans = subComponentDao.getDistinctSubComponentCodes(componentId);

				log.debug("subComponentBeans\t"+subComponentBeans);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for(SubComponentBean subComponent: subComponentBeans){
					buffer.append("<option value=\"").append(subComponent.getSubComponentId()).append("\">");
					buffer.append(subComponent.getSubComponentId()).append(MISConstants.LABEL_VALUE_BEAN_SEPARATOR).append(subComponent.getSubComponentName());
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
	
	private Set<LabelValueBean> getComponentIds() {
		Set<LabelValueBean> componentIds = null;
		List<ComponentBean> componentBeans = null;
		try{
			ComponentBean componentBean = new ComponentBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			componentBeans = componentDao.findComponent(componentBean, statusList);
			componentIds = new HashSet<LabelValueBean>();
			for (ComponentBean componentBean2 : componentBeans) {
				componentIds.add(new LabelValueBean(componentBean2.getComponentId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+componentBean2.getComponentName(),componentBean2.getComponentId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return componentIds;	
	}
	
	private Datagrid getreceiptVoucherDatagrid(Set<VoucherDetailBean> voucherDetailBeans) {
		Datagrid receiptVoucherDatagrid = null;
		try {
			receiptVoucherDatagrid = Datagrid.getInstance();
			receiptVoucherDatagrid.setDataClass(VoucherDetailBean.class);
			if(!MisUtility.ifEmpty(voucherDetailBeans)){
			List<VoucherDetailBean> voucherDetailBeans2 = new ArrayList<VoucherDetailBean>(voucherDetailBeans);
			receiptVoucherDatagrid.setData(voucherDetailBeans2);		
			}else{
			List<VoucherDetailBean> voucherDetailBeans2 = new ArrayList<VoucherDetailBean>();
			receiptVoucherDatagrid.setData(voucherDetailBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return receiptVoucherDatagrid;
	}
	
	private Set<LabelValueBean> getBankIds(){
		Set<LabelValueBean> bankIds = new TreeSet<LabelValueBean>();
		Set<BankMasterBean> bankMasterBeans = null;
		try{
			
				bankMasterBeans = bankMasterDao.getBankMasterCodes();
				
					for (BankMasterBean bean : bankMasterBeans) {
						if(bean.getDistrcit()!=null)
						bankIds.add(new LabelValueBean(bean.getBankName()+" - ("+bean.getBankId()+")- ("+bean.getDistrcit().getLocationName()+")",new Long(bean.getBankId()).toString()));
					}	
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return bankIds;	
	}
	
	public ActionForward fetchPayeeId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<LocationBean> locationBeans = null;
		Set<EmployeeBean> employeeBeans = null;
		Set<VendorBean> vendorBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		typeList.add(MISConstants.MIS_LOCATION_TYPE_DPMC);
		typeList.add(MISConstants.MIS_LOCATION_TYPE_DO);
		typeList.add(MISConstants.MIS_LOCATION_TYPE_SPMC);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		//System.out.println("IN FETCH PAYEEE NAME -------------------");
		try {
			if(MisUtility.ifEmpty(request.getParameter("payerType"))){
				if((request.getParameter("payerType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_EMPLOYEE))){
					if(MisUtility.ifEmpty(request.getParameter("locationId"))){
					employeeBeans = employeeDao.getDistinctEmployeeIds(request.getParameter("locationId"), statusList);
					if(!MisUtility.ifEmpty(employeeBeans)){
						buffer.append("<option value='' selected>");
						buffer.append("Select");
						buffer.append("</option>");
						for (EmployeeBean bean : employeeBeans) {
							buffer.append("<option value=\"").append(bean.getEmployeeId()).append("\">");
							buffer.append(bean.getEmployeeName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+bean.getEmployeeId()+")");
							buffer.append("</option>");
						}
					}
					
					}}else if((request.getParameter("payerType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_OFFICE))){
						locationBeans = locationDao.getLocationIdOnTypeList(typeList);
							if(!MisUtility.ifEmpty(locationBeans)){
								buffer.append("<option value='' selected>");
								buffer.append("Select");
								buffer.append("</option>");
								for (LocationBean bean : locationBeans) {
									buffer.append("<option value=\"").append(bean.getLocationId()).append("\">");
									buffer.append(bean.getLocationName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+bean.getLocationId()+")");
									buffer.append("</option>");
								}
							
							}
					
					
				}else if((request.getParameter("payerType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VENDOR))){
						vendorBeans = vendorDao.getDistinctVendorCodes();
							if(!MisUtility.ifEmpty(vendorBeans)){
								for (VendorBean bean : vendorBeans) {
									buffer.append("<option value='' selected>");
									buffer.append("Select");
									buffer.append("</option>");
									buffer.append("<option value=\"").append(bean.getVendorId()).append("\">");
									buffer.append(bean.getVendorName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+bean.getVendorId()+")");
									buffer.append("</option>");
								}
							
							}
					
					
				}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
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
	
	
	
	private void refreshReceiptVoucherForm(ReceiptVoucherForm receiptVoucherForm){
		receiptVoucherForm.setReceiptVoucherDatagrid(getreceiptVoucherDatagrid(null));
		
		
      
       receiptVoucherForm.setInstrAmount(new BigDecimal(0.00));
       receiptVoucherForm.setDocumentNo(null);
       receiptVoucherForm.setInstrumentDate(null);
       receiptVoucherForm.setInstrumentType(null);
       receiptVoucherForm.setLocationId(null);
       receiptVoucherForm.setNotes(null);
       receiptVoucherForm.setPayerName(null);
       receiptVoucherForm.setProgramId(null);
       receiptVoucherForm.setReceiptType(null);
       receiptVoucherForm.setReceiptMode(null);
       receiptVoucherForm.setTransactionDate(null);
       receiptVoucherForm.setVoucherNo(0);
       receiptVoucherForm.setVoucherDate(null);
		
		
	}
	
	
//	public ActionForward fetchDocumentNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response)throws MISException {
//		Set<InwardDakBean> inwardDakBeans = null;
//		StringBuffer buffer = new StringBuffer();
//		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		InwardDakBean inwardDakBean = new InwardDakBean();
//		try {
//			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
//				inwardDakBean.setLocationId(request.getParameter("locationId"));
//				inwardDakBeans = new TreeSet<InwardDakBean>(inwardDakDao.getInwardDakBeans(inwardDakBean, statusList));
//				if(!MisUtility.ifEmpty(inwardDakBeans)){
//					for (InwardDakBean bean : inwardDakBeans) {
//						buffer.append("<option value=\"").append(bean.getDocumentNo()).append("\">");
//						buffer.append(bean.getDocumentRefNo()+"("+bean.getDocumentNo()+")");
//						buffer.append("</option>");
//					}
//				}
//			}
//			PrintWriter out = response.getWriter();
//			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
//				out.print(buffer.toString());
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			log.error(e);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			log.error(e);
//		} catch (IOException e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		
//		return null;
//	}
	private String checkDateFlow(String voucherDate) {
		   Date date1 = null;
		   Date date2 = null;
		   Date date3 = null; 
		   Date date4 = null;
		   Date date5 = null;
		   Calendar cal = new GregorianCalendar(); 
		   StringBuffer prevDatev= new StringBuffer();
		   //getting current date
		   prevDatev.append(cal.get(Calendar.DAY_OF_MONTH));
		   prevDatev.append("-");
		   prevDatev.append(cal.get(Calendar.MONTH)+1);
		   prevDatev.append("-");
		   prevDatev.append(cal.get(Calendar.YEAR));
		   String prDate = prevDatev.toString();
		   
		   //setting 10th date of current month
		   Calendar cal1 = new GregorianCalendar();
		   cal1.set(Calendar.DAY_OF_MONTH, 10);
		   StringBuffer tenDate = new StringBuffer();
		   tenDate.append(cal1.get(Calendar.DAY_OF_MONTH));
		   tenDate.append("-");
		   tenDate.append(cal1.get(Calendar.MONTH)+1);
		   tenDate.append("-");
		   tenDate.append(cal1.get(Calendar.YEAR));
		   
		   //setting last month 1 date
		   Calendar cal2 = new GregorianCalendar();
		   cal2.set(Calendar.DAY_OF_MONTH, 1);
		   cal2.add(Calendar.MONTH,-1);
		   cal2.add(Calendar.DAY_OF_MONTH, -1);
		   StringBuffer fLMonthDate = new StringBuffer();
		   fLMonthDate.append(cal2.get(Calendar.DAY_OF_MONTH));
		   fLMonthDate.append("-");
		   fLMonthDate.append(cal2.get(Calendar.MONTH)+1);
		   fLMonthDate.append("-");
		   fLMonthDate.append(cal2.get(Calendar.YEAR));
		   
		   //setting current month 1st date
		   Calendar cal3 = new GregorianCalendar();
		   StringBuffer fCMonthDate = new StringBuffer();
		   cal3.set(Calendar.DAY_OF_MONTH, 1);
		   fCMonthDate.append(cal3.get(Calendar.DAY_OF_MONTH));
		   fCMonthDate.append("-");
		   fCMonthDate.append(cal3.get(Calendar.MONTH)+1);
		   fCMonthDate.append("-");
		   fCMonthDate.append(cal3.get(Calendar.YEAR));
		  // System.out.println("mmmmmm"+fLMonthDate);
		    
		   String myFormatString = "dd-MM-yyyy"; // for example
		   SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		   try{
			   StringBuffer strngMessage = new StringBuffer();
		     date1 = df.parse(voucherDate);
		     date2 = df.parse(prDate);
		     date3 = df.parse(tenDate.toString());
		     date4 = df.parse(fLMonthDate.toString());
		     date5 = df.parse(fCMonthDate.toString());
		     
		     if(date2.before(date1)){
		    	 	strngMessage.append("Invalid Voucher Date");
					strngMessage.append("<br> Voucher Date should be before today's Date");
					return strngMessage.toString();
			  }
		     if(date1.before(date3)){
		    	 if(date1.after(date4) && date1.before(date3)){																	//date2=current date
		    		 return null;																								//date4=1st of last month
		    	 }else{																											//date5=1st of current month
		    		 strngMessage.append("Invalid Voucher Date");
						strngMessage.append("<br> Voucher Date should be greater than"+" ("+fLMonthDate+") "+"and"+" less than or equal("+tenDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		     if(date1.after(date3)){
		    	 if(date1.after(date5) &&(date1.before(date2)||date1.equals(date2))){
		    		 return null;
		    	 }else{
		    		 strngMessage.append("Invalid Voucher Date");
						strngMessage.append("<br> Voucher Date should be between"+" ("+fCMonthDate+") "+"and"+" ("+prDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		   } 
		   catch (java.text.ParseException e) {
			e.printStackTrace();
		   } 

		return null;
	}

	
}

