/**
 * 
 */
package com.prwss.mis.finance.paymentvoucher.struts;

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

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
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
import com.prwss.mis.finance.paymentvoucher.PaymentVoucherBO;
import com.prwss.mis.finance.voucher.VoucherDetailBean;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.dao.ActivityDao;
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.dao.BankMasterDao;
import com.prwss.mis.masters.committee.dao.CommitteeBean;
import com.prwss.mis.masters.committee.dao.CommitteeDao;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class PaymentVoucherAction extends DispatchAction {
	
	
	private PaymentVoucherBO paymentVoucherBO;
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(PaymentVoucherAction.class);
	private ProgramDao programDao;
	private BudgetDao  budgetDao;
    private ComponentDao componentDao;
    private SubComponentDao subComponentDao;
    private ActivityDao activityDao;
    private AccountsChartDao accountsChartDao;
    private InwardDakDao inwardDakDao;
    private VoucherHeaderDao voucherHeaderDao  ;
    private EmployeeDao employeeDao;
    private VendorDao vendorDao;
    private LocationDao locationDao;
    private BankMasterDao bankMasterDao;
    private VillageDao villageDao;
    private SchemeVillageDao schemeVillageDao;
    private SchemeHeaderDao schemeHeaderDao  ;
    private CommitteeDao committeeDao;
    
	public void setCommitteeDao(CommitteeDao committeeDao) {
		this.committeeDao = committeeDao;
	}


	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}


	public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
	}


	public void setVillageDao(VillageDao villageDao) {
		this.villageDao = villageDao;
	}


	public void setBankMasterDao(BankMasterDao bankMasterDao) {
		this.bankMasterDao = bankMasterDao;
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

	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}


	public void setInwardDakDao(InwardDakDao inwardDakDao) {
		this.inwardDakDao = inwardDakDao;
	}


	public void setAccountsChartDao(AccountsChartDao accountsChartDao) {
		this.accountsChartDao = accountsChartDao;
	}


	public void setActivityDao(ActivityDao activityDao) {
 		this.activityDao = activityDao;
 	}
	

	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}


	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	
	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}


	public void setBudgetDao(BudgetDao budgetDao) {
		this.budgetDao = budgetDao;
	}
	
	
	public void setPaymentVoucherBO(PaymentVoucherBO paymentVoucherBO) {
		this.paymentVoucherBO = paymentVoucherBO;
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
		PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
		List<VoucherHeaderBean> paymentVoucherBeans = null;
		paymentVoucherBeans = paymentVoucherBO.findPaymentVoucher(paymentVoucherForm, statusList);
		
		if(!MisUtility.ifEmpty(paymentVoucherBeans)){
		request.setAttribute("level2", "true");
		for (VoucherHeaderBean paymentVoucherBean : paymentVoucherBeans) {
			paymentVoucherForm.setBankName(paymentVoucherBean.getBankName());
			paymentVoucherForm.setLocationId(paymentVoucherBean.getLocationId());
			request.setAttribute("vocId", paymentVoucherBean.getVocId());
//			request.setAttribute("documentNo", paymentVoucherBean.getDocumentNo());
			request.setAttribute("payeeName", paymentVoucherBean.getPayeePayerId());
			request.setAttribute("schemeCode", paymentVoucherBean.getSchemeCode());
			paymentVoucherForm.setDocumentNo(paymentVoucherBean.getDocumentNo());
			paymentVoucherForm.setBankId(paymentVoucherBean.getBankId());
			request.setAttribute("bankId", paymentVoucherBean.getBankId());
			paymentVoucherForm.setInstrumentDate(MisUtility.convertDateToString(paymentVoucherBean.getInstrumentDate()));
			paymentVoucherForm.setInstrumentType(paymentVoucherBean.getInstrumentType());
			paymentVoucherForm.setLocationId(paymentVoucherBean.getLocationId());
			paymentVoucherForm.setNotes(paymentVoucherBean.getNotes());
			paymentVoucherForm.setPayeeType(paymentVoucherBean.getPayeePayerType());
			paymentVoucherForm.setProgramId(paymentVoucherBean.getProgramId());
			paymentVoucherForm.setPaymentType(paymentVoucherBean.getTypeOfPayment());
			paymentVoucherForm.setPaymentMode(paymentVoucherBean.getPaymentMode());
			paymentVoucherForm.setTransactionDate(MisUtility.convertDateToString(paymentVoucherBean.getTransactionDate()));
			paymentVoucherForm.setVoucherNo(paymentVoucherBean.getVocId());
			paymentVoucherForm.setInstrAmount(paymentVoucherBean.getAmount());
			paymentVoucherForm.setInstrumentNo(paymentVoucherBean.getInstrumentNumber());
			paymentVoucherForm.setPaymentType(paymentVoucherBean.getTypeOfPayment());
			paymentVoucherForm.setVoucherDate(MisUtility.convertDateToString(paymentVoucherBean.getVocDate()));
			paymentVoucherForm.setPaymentVoucherDatagrid(getpaymentVoucherDatagrid(paymentVoucherBean.getVoucherDetailBeans()));
			paymentVoucherForm.setVoucherDate(MisUtility.convertDateToString(paymentVoucherBean.getVocDate()));
			paymentVoucherForm.setTransactionDate(MisUtility.convertDateToString(paymentVoucherBean.getTransactionDate()));
		}
		}else{
			refreshPaymentVoucherForm(paymentVoucherForm);
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
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = paymentVoucherBO.deletePaymentVoucher(paymentVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Payment Voucher Information","Voucher No -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Payment Voucher Information","Voucher No -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshPaymentVoucherForm(paymentVoucherForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
		long vocId = 0;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(paymentVoucherForm.getLocationId())||!MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())||!MisUtility.ifEmpty(paymentVoucherForm.getTransactionDate())||!MisUtility.ifEmpty(paymentVoucherForm.getPayeeType())||!MisUtility.ifEmpty(paymentVoucherForm.getPayeeType())||!MisUtility.ifEmpty(paymentVoucherForm.getProgramId())||!MisUtility.ifEmpty(paymentVoucherForm.getInstrAmount())||!MisUtility.ifEmpty(paymentVoucherForm.getInstrumentNo())||!MisUtility.ifEmpty(paymentVoucherForm.getInstrumentDate())||!MisUtility.ifEmpty(paymentVoucherForm.getDocumentNo())){
				if(!MisUtility.ifEmpty(paymentVoucherForm.getLocationId())){
					stringMessage.append("Please Select Location");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())){
					stringMessage.append("<br> Please Enter Voucher Date");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getTransactionDate())){
					stringMessage.append("<br> Please Enter Transaction Date");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getPayeeType())){
					stringMessage.append("<br> Please Select Payee Type");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getPayeeName())){
					stringMessage.append("<br> Please Select Payee Name");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getProgramId())){
					stringMessage.append("<br> Please Select Program Id");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getInstrAmount())){
					stringMessage.append("<br> Please Enter Amount");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getDocumentNo())){
					stringMessage.append("<br> Please Enter Voucher Number");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getInstrumentNo())){
					stringMessage.append("<br> Please Enter Instrument Number");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getInstrumentDate())){
					stringMessage.append("<br> Please Enter Instrument Date");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())){
				String strngMessage = checkDateFlow(paymentVoucherForm.getVoucherDate());
				if(strngMessage!=null){
					paymentVoucherForm.setVoucherDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}

			vocId = paymentVoucherBO.savePaymentVoucher(paymentVoucherForm, misSessionBean);
			if (vocId !=0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Payment Voucher Information","Voucher No -->"+vocId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshPaymentVoucherForm(paymentVoucherForm);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Payment Voucher Information");
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
			}else if(MISExceptionCodes.MIS010.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("payment.can.modify",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			else{
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
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
			if(!MisUtility.ifEmpty(paymentVoucherForm.getLocationId())||!MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())||!MisUtility.ifEmpty(paymentVoucherForm.getTransactionDate())||!MisUtility.ifEmpty(paymentVoucherForm.getPayeeType())||!MisUtility.ifEmpty(paymentVoucherForm.getPayeeName())||!MisUtility.ifEmpty(paymentVoucherForm.getProgramId())){
				
				if(!MisUtility.ifEmpty(paymentVoucherForm.getLocationId())){
					stringMessage.append("Please Select Location");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())){
					stringMessage.append("<br> Please Enter Voucher Date");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getTransactionDate())){
					stringMessage.append("<br> Please Enter Transaction Date");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getPayeeType())){
					stringMessage.append("<br> Please Select Payee Type");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getPayeeName())){
					stringMessage.append("<br> Please Select Payee Name");
				}
				if(!MisUtility.ifEmpty(paymentVoucherForm.getProgramId())){
					stringMessage.append("<br> Please Select Program Id");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(paymentVoucherForm.getVoucherDate())){
				String strngMessage = checkDateFlow(paymentVoucherForm.getVoucherDate());
				if(strngMessage!=null){
					paymentVoucherForm.setVoucherDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			status = paymentVoucherBO.updatePaymentVoucher(paymentVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Payment Voucher Information","Voucher No -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshPaymentVoucherForm(paymentVoucherForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Payment Voucher Information","Voucher No -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if(MISExceptionCodes.MIS010.equals(e.getCode())){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("payment.can.modify",e.getMessage());
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
				ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Payment Voucher Information");
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
		PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = paymentVoucherBO.postPaymentVoucher(paymentVoucherForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Payment Voucher -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Payment Voucher -->"+paymentVoucherForm.getVoucherNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Payment Voucher");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Payment Voucher");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshPaymentVoucherForm(paymentVoucherForm);
		return mapping.findForward("display");
	}
	
	

	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "voucherNo@locationId");
		request.setAttribute("d__auto", "voucherNo");
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified:"+request.getParameter("d__mode"));
		ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		try {
			PaymentVoucherForm paymentVoucherForm = (PaymentVoucherForm)form;
			
			Set<LabelValueBean> programIds = getProgramIds();
			request.getSession().setAttribute("programIds", programIds);
			
			Set<LabelValueBean> componentIds = getComponentIds();
			request.getSession().setAttribute("componentIds", componentIds);
			
			Set<LabelValueBean> codeHeadIds = getCodeHeadIds();
			request.getSession().setAttribute("codeHeadIds", codeHeadIds);
			
			
			
//			Set<LabelValueBean> bankIds = getBankIds();
//			request.getSession().setAttribute("bankIds", bankIds);
			
			refreshPaymentVoucherForm(paymentVoucherForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	private void refreshPaymentVoucherForm(PaymentVoucherForm paymentVoucherForm){

		paymentVoucherForm.setPaymentVoucherDatagrid(getpaymentVoucherDatagrid(null));
		paymentVoucherForm.setInstrAmount(new BigDecimal(0.00));
		paymentVoucherForm.setDocumentNo(null);
		paymentVoucherForm.setInstrumentDate(null);
		paymentVoucherForm.setInstrumentType(null);
		paymentVoucherForm.setLocationId(null);
		paymentVoucherForm.setNotes(null);
		paymentVoucherForm.setPayeeName(null);
		paymentVoucherForm.setPayeeType(MISConstants.FIN_PAYMENT_OFFICE);
		paymentVoucherForm.setProgramId(null);
		paymentVoucherForm.setPaymentType(null);
		paymentVoucherForm.setPaymentMode(null);
		paymentVoucherForm.setTransactionDate(null);
		paymentVoucherForm.setVoucherNo(0);
		paymentVoucherForm.setPaymentMode("BANK");
		paymentVoucherForm.setVoucherDate(null);
		

	}
	
	private Set<LabelValueBean> getCodeHeadIds() {
		Set<LabelValueBean> codeHeadIds = null;
		List<AccountsChartBean> accountsChartBeans = null;
		try{
			AccountsChartBean accountsChartBean = new AccountsChartBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			accountsChartBeans = accountsChartDao.findAccountsChart1(accountsChartBean, statusList);
			codeHeadIds = new HashSet<LabelValueBean>();
			for (AccountsChartBean accountsChartBean2 : accountsChartBeans) {
				if((accountsChartBean2.getAccountNature().equals(MISConstants.FIN_ACCOUNT_NATURE_PAYMENT) ||(accountsChartBean2.getAccountNature().equals(MISConstants.FIN_ACCOUNT_NATURE_BOTH)) ))
				{codeHeadIds.add(new LabelValueBean(accountsChartBean2.getCodeHeadId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+accountsChartBean2.getCodeHeadIdDescription(),accountsChartBean2.getCodeHeadId()));
				}
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
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
	
	
//	private Set<LabelValueBean> getBankIds(){
//		Set<LabelValueBean> bankIds = null;
//		Set<BankMasterBean> bankMasterBeans = null;
//		try{
//			bankMasterBeans = bankMasterDao.getBankMasterCodes();
//			bankIds = new HashSet<LabelValueBean>();
//			for (BankMasterBean bean : bankMasterBeans) {
//				bankIds.add(new LabelValueBean(bean.getBankName()+" - ("+bean.getBankId()+")",new Long(bean.getBankId()).toString()));
//			}
//			
//		}catch(DataAccessException e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		catch(Exception e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		return bankIds;	
//	}
	
//	private Set<LabelValueBean> getDocumentsNos(){
//		Set<LabelValueBean> documentsNos = null;
//		List<InwardDakBean> inwardDakBeans = null;
//		try{
//			InwardDakBean inwardDakBean = new InwardDakBean();
//			List <String> statusList = new ArrayList<String>() ;
//			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//			inwardDakBeans = inwardDakDao.getInwardDakBeans(inwardDakBean, statusList);
//			documentsNos = new HashSet<LabelValueBean>();
//			for (InwardDakBean inwardDakBean2 : inwardDakBeans) {
//				documentsNos.add(new LabelValueBean(inwardDakBean2.getDocumentNo()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+inwardDakBean2.getDocumentRefNo(),new String(inwardDakBean2.getDocumentNo())));
//				
//			}
//			
//		}catch(DataAccessException e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		catch(Exception e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		return documentsNos;	
//	}
	
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
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("payeeType"))){
				if((request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_EMPLOYEE))){
					if(MisUtility.ifEmpty(request.getParameter("locationId"))){
					employeeBeans = employeeDao.getDistinctEmployeeIds(request.getParameter("locationId"), statusList);
					buffer.append("<option value='' selected>");
					buffer.append("Select");
					buffer.append("</option>");
					if(!MisUtility.ifEmpty(employeeBeans)){
						for (EmployeeBean bean : employeeBeans) {
							buffer.append("<option value=\"").append(bean.getEmployeeId()).append("\">");
							buffer.append(bean.getEmployeeName()+" - ("+bean.getEmployeeId()+")");
							buffer.append("</option>");
						}
					}
					
					}}else if((request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_OFFICE))){
						locationBeans = locationDao.getLocationIdOnTypeList(typeList);
							if(!MisUtility.ifEmpty(locationBeans)){
								buffer.append("<option value='' selected>");
								buffer.append("Select");
								buffer.append("</option>");
								for (LocationBean bean : locationBeans) {
									buffer.append("<option value=\"").append(bean.getLocationId()).append("\">");
									buffer.append(bean.getLocationName()+" - ("+bean.getLocationId()+")");
									buffer.append("</option>");
								}
							
							}
					
					
				}else if((request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VENDOR))){
						vendorBeans = vendorDao.getDistinctVendorCodes();
							if(!MisUtility.ifEmpty(vendorBeans)){
								buffer.append("<option value='' selected>");
								buffer.append("Select");
								buffer.append("</option>");
								for (VendorBean bean : vendorBeans) {
									buffer.append("<option value=\"").append(bean.getVendorId()).append("\">");
									buffer.append(bean.getVendorName()+" - ("+bean.getVendorId()+")");
									buffer.append("</option>");
								}
							
							}
					
					
				}
				else if((request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VILLAGE))){
					if(MisUtility.ifEmpty(request.getParameter("locationId"))){
						LocationBean locationBean = new LocationBean();
						locationBean.setLocationId(request.getParameter("locationId"));
						locationBean = locationDao.getLocation(locationBean);
						CommitteeBean committeeBean = new CommitteeBean();
						committeeBean.setDistrictId(locationBean.getParentLocation());
						List<String> statusList2 = new ArrayList<String>();
						statusList2.add(MISConstants.MASTER_STATUS_VERIFIED); // Temperarily done to allow updation in Village Master// To Be Removed
						statusList2.add(MISConstants.MASTER_STATUS_APPROVED);
						
						List<CommitteeBean> committeeBeans = committeeDao.findCommittee(committeeBean, statusList2);
						if(!MisUtility.ifEmpty(committeeBeans)){
							buffer.append("<option value='' selected>");
							buffer.append("Select");
							buffer.append("</option>");
							for (CommitteeBean committeeBean2 : committeeBeans) {
							buffer.append("<option value=\"").append(committeeBean2.getCommitteeId()).append("\">");
							buffer.append(committeeBean2.getCommitteeName()+" - ("+committeeBean2.getSchemeId()+")");
							buffer.append("</option>");
						  }
						}
						
//						VillageBean villageBean = new VillageBean();
//						villageBean.setDistrictId(locationBean.getParentLocation());
//						villageBean.setHabitationType("MH");
//						
//						List<VillageBean> villageBeans = villageDao.findVillage(villageBean, statusList2);
//						if(!MisUtility.ifEmpty(villageBeans)){
//							for (VillageBean bean:villageBeans) {
//								buffer.append("<option value=\"").append(bean.getVillageId()).append("\">");
//								buffer.append(bean.getVillageName()+" - ("+bean.getVillageId()+")");
//								buffer.append("</option>");
//							}
//						
//						}
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
						buffer.append(activityBean2.getActivityId()+" - ("+activityBean2.getActivityName()+")");
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
//				buffer.append("<option value='0'>");
//				buffer.append("Select");
//				buffer.append("</option>");
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
	
	private Datagrid getpaymentVoucherDatagrid(Set<VoucherDetailBean> paymentVoucherDetailBeans) {
		Datagrid paymentVoucherDatagrid = null;
		try {
			paymentVoucherDatagrid = Datagrid.getInstance();
			paymentVoucherDatagrid.setDataClass(VoucherDetailBean.class);
			if(!MisUtility.ifEmpty(paymentVoucherDetailBeans)){
			List<VoucherDetailBean> paymentVoucherDetailBeans2 = new ArrayList<VoucherDetailBean>(paymentVoucherDetailBeans);
			paymentVoucherDatagrid.setData(paymentVoucherDetailBeans2);		
			}else{
			List<VoucherDetailBean> paymentVoucherDetailBeans2 = new ArrayList<VoucherDetailBean>();
			paymentVoucherDatagrid.setData(paymentVoucherDetailBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return paymentVoucherDatagrid;
	}

	public ActionForward fetchRequestVocId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<VoucherHeaderBean> voucherHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				voucherHeaderBean.setLocationId(request.getParameter("locationId"));
//				voucherHeaderBean.setProgramId(request.getParameter("programId"));
				voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				voucherHeaderBeans = new TreeSet<VoucherHeaderBean>(voucherHeaderDao.findVoucher(voucherHeaderBean, statusList));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+voucherHeaderBeans);
				if(!MisUtility.ifEmpty(voucherHeaderBeans)){
					System.out.println("111");
					for (VoucherHeaderBean voucherHeaderBean2 : voucherHeaderBeans) {
						if(!(voucherHeaderBean2.getPaymentMode().equals(MISConstants.FIN_ADJUSTMENT_MODE))){
							System.out.println("22");
						buffer.append("<option value=\"").append(voucherHeaderBean2.getVocId()).append("\">");
						buffer.append(voucherHeaderBean2.getDocumentNo()+"("+voucherHeaderBean2.getVocId()+")");
						buffer.append("</option>");
						}
					}
				}
			}
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+buffer.toString());
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
	
	public ActionForward fetchSchemeId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<SchemeVillageBean> schemeVillageBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			if((request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VILLAGE))){
				if(MisUtility.ifEmpty(request.getParameter("villageId"))){
				SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
				System.out.println("VillageId-----------"+request.getParameter("villageId"));
				schemeVillageBean.setVillageId(request.getParameter("villageId"));
				schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
				System.out.println("schemeVillage"+schemeVillageBeans);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(schemeVillageBeans)){
					for (SchemeVillageBean bean : schemeVillageBeans) {
						buffer.append("<option value=\"").append(bean.getSchemeId()).append("\">");
						buffer.append(bean.getSchemeId());
						buffer.append("</option>");
					}
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
	
	// The following code is added temperarily  for allowing all the schemes for each vendor   To be removed
	public ActionForward fetchSchemeForVendor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<SchemeHeaderBean> schemeHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_OFFICE)||request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VENDOR)||request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_VILLAGE)){
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(request.getParameter("locationId"));
				locationBean = locationDao.getLocation(locationBean);
				schemeHeaderBean.setDistrictId(locationBean.getParentLocation()); // Scheme is fetched district wise, for same we have to find user location parent location and add that in district id
				schemeHeaderBeans = new TreeSet<SchemeHeaderBean>(schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList));
				
				buffer.append("<option value='N/A'>");
				buffer.append("NA");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(schemeHeaderBeans)){
					for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()).append("\">");
						buffer.append(schemeHeaderBean2.getSchemeName()+"("+schemeHeaderBean2.getSchemeId()+")");
						buffer.append("</option>");
					}
				}
			}
			}else if(request.getParameter("payeeType").equalsIgnoreCase(MISConstants.FIN_PAYMENT_EMPLOYEE)){
				buffer.append("<option value='N/A'>");
				buffer.append("NA");
				buffer.append("</option>");
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
	
	public ActionForward fetchBankIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		BankMasterBean bankMasterBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(request.getParameter("locationId"));
				locationBean = locationDao.getLocation(locationBean);
				bankMasterBean = new BankMasterBean();
				LocationBean bean = new LocationBean();
				bean.setLocationId(locationBean.getParentLocation());
				bankMasterBean.setDistrcit(bean);
				List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(bankMasterBean, statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(bankMasterBeans)){
					for (BankMasterBean bankBean : bankMasterBeans) {
						buffer.append("<option value=\"").append(bankBean.getBankId()).append("\">");
						buffer.append(bankBean.getBankName()+" "+"("+bankBean.getBankBranch()+")-");
						buffer.append(bankBean.getBankId());
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
		   System.out.println("mmmmmm"+fLMonthDate);
		    
		   String myFormatString = "dd-MM-yyyy"; // for example
		   SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		   try{
			   StringBuffer strngMessage = new StringBuffer();
		     date1 = df.parse(voucherDate);
		     date2 = df.parse(prDate);
		     date3 = df.parse(tenDate.toString());
		     date4 = df.parse(fLMonthDate.toString());
		     date5 = df.parse(fCMonthDate.toString());
		     System.out.println("------------Helllo DAte "+date4);
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
		    	 if(date1.after(date5) && (date1.before(date2)||date1.equals(date2))){
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
