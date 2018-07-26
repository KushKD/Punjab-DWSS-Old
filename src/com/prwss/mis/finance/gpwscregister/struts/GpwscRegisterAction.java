package com.prwss.mis.finance.gpwscregister.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.prwss.mis.finance.gpwscregister.GpwscRegisterBO;
import com.prwss.mis.finance.gpwscregister.dao.GpwscRegisterBean;
import com.prwss.mis.finance.gpwscregister.dao.GpwscRegisterDao;
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.dao.BankMasterDao;
import com.prwss.mis.masters.committee.dao.CommitteeBankBean;
import com.prwss.mis.masters.committee.dao.CommitteeBankDao;
import com.prwss.mis.masters.committee.dao.CommitteeBean;
import com.prwss.mis.masters.committee.dao.CommitteeDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class GpwscRegisterAction extends DispatchAction {
	
	private MISSessionBean misSessionBean;
	private LocationDao locationDao;
	private BankMasterDao bankMasterDao;
	private CommitteeDao committeeDao;
	private CommitteeBankDao committeeBankDao;
	private GpwscRegisterBO gpwscRegisterBO;
	private GpwscRegisterDao gpwscRegisterDao;
	
	
	public void setGpwscRegisterDao(GpwscRegisterDao gpwscRegisterDao) {
		this.gpwscRegisterDao = gpwscRegisterDao;
	}

	public void setGpwscRegisterBO(GpwscRegisterBO gpwscRegisterBO) {
		this.gpwscRegisterBO = gpwscRegisterBO;
	}

	public void setCommitteeDao(CommitteeDao committeeDao) {
		this.committeeDao = committeeDao;
	}

	public void setCommitteeBankDao(CommitteeBankDao committeeBankDao) {
		this.committeeBankDao = committeeBankDao;
	}
	
	public BankMasterDao getBankMasterDao() {
		return bankMasterDao;
	}

	public void setBankMasterDao(BankMasterDao bankMasterDao) {
		this.bankMasterDao = bankMasterDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		List<GpwscRegisterBean> gpwscRegisterBeans = null;
		System.out.println("-------------GpwscId "+gpwscRegisterForm.getGpwcId());
		gpwscRegisterBeans = gpwscRegisterBO.findGpwsc(gpwscRegisterForm, statusList);
		if(!MisUtility.ifEmpty(gpwscRegisterBeans)){
//			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
//			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
		request.setAttribute("gpwscRegisterBeans", gpwscRegisterBeans);
		ActionMessages errors = new ActionMessages();
		ActionMessage message = new ActionMessage("find.list");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, errors);
		}else{
			refreshGpwscForm(gpwscRegisterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");

	}


	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		refreshGpwscForm(gpwscRegisterForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "transactionType@locationId@villageId@blockId@gpwcId@transactionNumberId@schemeId");
		request.setAttribute("d__auto", "transactionNumberId");
	}
	
	public ActionForward fetchBankIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		BankMasterBean bankMasterBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("gpwcId"))){
				long committeeId=new Long(request.getParameter("gpwcId"));
				CommitteeBankBean committeeBankBean=new CommitteeBankBean();
				committeeBankBean.setCommitteeId(committeeId);		
				System.out.println("Committee Id is "+committeeId);
				List<CommitteeBankBean> committeeBankBeans=committeeBankDao.findCommitteeBank(committeeBankBean, statusList);
				 
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					buffer.append("<option value=''>Please Select</option>");
					for (CommitteeBankBean committeeBankBean2 : committeeBankBeans) {
						buffer.append("<option value=\"").append(committeeBankBean2.getBankId()).append("\">");
						buffer.append(committeeBankBean2.getBankName()+" - ("+committeeBankBean2.getBankId()+")-Account Number: "+committeeBankBean2.getAccountNumber());
						buffer.append("</option>");
						/*if(committeeBankBean2.getAccountType().equals("O&M A/c"))
						request.("O&M", "true");*/
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
	
	public ActionForward fetchReciptType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		List<String> receiptType1=new ArrayList<String>();
		List<String> receiptType2=new ArrayList<String>();
		receiptType1.add(MISConstants.LOC_FOR_INSTL_1);
		receiptType1.add(MISConstants.LOC_FOR_INSTL_2);
		receiptType1.add(MISConstants.LOC_FOR_INSTL_3);
		receiptType1.add(MISConstants.GPWSC_BENEFICIARY_SHARE);
		receiptType1.add(MISConstants.LOC_FOR_GAP_FUND);
		receiptType1.add(MISConstants.GAP_FUND_VOLUNTARILY);
		receiptType1.add(MISConstants.GAP_FUND_NON_BUDGETARY);
		receiptType1.add(MISConstants.GAP_FUND_UNTIED_FUNDS);
		receiptType1.add(MISConstants.MONTHLY_REVENUE);
		receiptType1.add(MISConstants.OTHER_INCOME);
		receiptType1.add(MISConstants.TDS);
		receiptType1.add(MISConstants.VAT);
		receiptType1.add(MISConstants.LABOUR_CESS);
		receiptType1.add(MISConstants.SECURITY_RETENTION);
		receiptType2.add(MISConstants.MONTHLY_REVENUE);
		try {
			if(MisUtility.ifEmpty(request.getParameter("bankId"))&&MisUtility.ifEmpty(request.getParameter("gpwcId"))){
				long committeeId=new Long(request.getParameter("gpwcId"));
				CommitteeBankBean committeeBankBean=new CommitteeBankBean();
				committeeBankBean.setCommitteeId(committeeId);		
				committeeBankBean.setBankId(new Long(request.getParameter("bankId")));
				System.out.println("Committee Id is "+committeeId+" Bank Id "+request.getParameter("bankId"));
				List<CommitteeBankBean> committeeBankBeans=committeeBankDao.findCommitteeBank(committeeBankBean, statusList);
				 
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					buffer.append("<option value=''>Please Select</option>");
					if(committeeBankBeans.get(0).getAccountType().equals("O&M A/c")||committeeBankBeans.get(0).getAccountType().equals("O&M")||committeeBankBeans.get(0).equals("O&M ACCOUNT")){
						for(String receiptType:receiptType2){	
							buffer.append("<option value=\"").append(receiptType).append("\">");
							buffer.append(receiptType);
							buffer.append("</option>");
						}
					}
					else{
						for(String receiptType:receiptType1){	
							buffer.append("<option value=\"").append(receiptType).append("\">");
							buffer.append(receiptType);
							buffer.append("</option>");
						}
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
	
	
	public ActionForward fetchGPWSCIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		System.out.println("Village_id:"+request.getParameter("villageId"));
		System.out.println("scheme_id:"+request.getParameter("schemeId"));
		try {
			if(MisUtility.ifEmpty(request.getParameter("villageId")) && MisUtility.ifEmpty(request.getParameter("schemeId"))){
				CommitteeBean committeeBean = new CommitteeBean();
				committeeBean.setVillageId(request.getParameter("villageId"));
				committeeBean.setSchemeId(request.getParameter("schemeId"));
				committeeBean.setsLCStatus("GPWSC");
				
				List<CommitteeBean> committeeBeans = committeeDao.findCommittee(committeeBean, statusList);
				if(!MisUtility.ifEmpty(committeeBeans)){
					stringBuilder.append("<option value=''>Please Select</option>");
					for (CommitteeBean bean : committeeBeans) {
						stringBuilder.append("<option value=\"").append(bean.getCommitteeId()).append("\">");
						stringBuilder.append(bean.getCommitteeName()+" - ("+bean.getCommitteeId()+")");
						stringBuilder.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(stringBuilder.toString()) && stringBuilder.length() > 1 ){
				out.print(stringBuilder.toString());
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
	
	private void refreshGpwscForm(GpwscRegisterForm gpwscRegisterForm)
	{
		gpwscRegisterForm.setBankId(0);
		gpwscRegisterForm.setGpwcId(0);
		gpwscRegisterForm.setBillNo(null);
		gpwscRegisterForm.setDateOfTransaction(null);
		gpwscRegisterForm.setLocationId(null);
		gpwscRegisterForm.setPayeeName(null);
		gpwscRegisterForm.setReceiptActivity(null);
		gpwscRegisterForm.setPaymentAmount(new BigDecimal(0.0));
		gpwscRegisterForm.setReceiptAmount(new BigDecimal(0.0));
		gpwscRegisterForm.setReceiptType(null);
		gpwscRegisterForm.setSchemeId(null);
		gpwscRegisterForm.setTransactionNumber(0);
		gpwscRegisterForm.setTransactionType(null);
		gpwscRegisterForm.setPaymentActivity(null);
	}
	
	private void blankOthers(GpwscRegisterForm gpwscRegisterForm)
	{
		gpwscRegisterForm.setTransactionNumber(0);
		gpwscRegisterForm.setDateOfTransaction(null);		
		gpwscRegisterForm.setPayeeName(null);
		gpwscRegisterForm.setBillNo(null);		
		gpwscRegisterForm.setReceiptActivity(null);
		gpwscRegisterForm.setPaymentAmount(new BigDecimal(0.0));
		gpwscRegisterForm.setReceiptAmount(new BigDecimal(0.0));		
		gpwscRegisterForm.setPaymentActivity(null);
		gpwscRegisterForm.setTransactionType(null);
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		long transactionNumber = 0;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			
			if(!MisUtility.ifEmpty(gpwscRegisterForm.getGpwcId())|| !MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
				
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getGpwcId())){
					stringMessage.append("Please select GPWSC");
				}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
					stringMessage.append("<br> Please Enter Date of Transaction");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			
			if(MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
				String strngMessage = checkDateFlow(gpwscRegisterForm.getDateOfTransaction());
				if(strngMessage!=null){
					gpwscRegisterForm.setDateOfTransaction(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}

			checkGpwscForm(gpwscRegisterForm);
			transactionNumber = gpwscRegisterBO.saveGpwsc(gpwscRegisterForm, misSessionBean);
			if (transactionNumber != 0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "GPWSC Transaction","Transaction Number -->"+transactionNumber);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				//refreshGpwscForm(gpwscRegisterForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Saving GPWSC Transaction");
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
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				//refreshGpwscForm(gpwscRegisterForm);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			//refreshGpwscForm(gpwscRegisterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving GPWSC Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}finally{
			
			if(gpwscRegisterForm.getLocationId()!=null)
			gpwscRegisterForm.setLocationId(gpwscRegisterForm.getLocationId());
			if(gpwscRegisterForm.getBlockId()!=null){			
				
				request.setAttribute("blockId", gpwscRegisterForm.getBlockId());
				gpwscRegisterForm.setBlockId(gpwscRegisterForm.getBlockId());
			}
			if(gpwscRegisterForm.getVillageId()!=null){				
				
				request.setAttribute("villageId", gpwscRegisterForm.getVillageId());
				gpwscRegisterForm.setVillageId(gpwscRegisterForm.getVillageId());
			}
			if(gpwscRegisterForm.getSchemeId()!=null){
				
				request.setAttribute("schemeId",gpwscRegisterForm.getSchemeId());
				gpwscRegisterForm.setSchemeId(gpwscRegisterForm.getSchemeId());
			}
			
			request.setAttribute("gpwcId", gpwscRegisterForm.getGpwcId());
			gpwscRegisterForm.setGpwcId(gpwscRegisterForm.getGpwcId());
				
			request.setAttribute("bankId",gpwscRegisterForm.getBankId());
			blankOthers(gpwscRegisterForm);
			
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		boolean status = false;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getGpwcId())|| !MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
				
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getGpwcId())){
					stringMessage.append("Please select GPWSC Id");
				}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
					stringMessage.append("<br> Please Enter Date of Transaction");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
				
			checkGpwscForm(gpwscRegisterForm);
			if(MisUtility.ifEmpty(gpwscRegisterForm.getDateOfTransaction())){
				String strngMessage = checkDateFlow(gpwscRegisterForm.getDateOfTransaction());
				if(strngMessage!=null){
					gpwscRegisterForm.setDateOfTransaction(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			status = gpwscRegisterBO.updateGpwsc(gpwscRegisterForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "GPWSC Transaction","modified successfully");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				//refreshGpwscForm(gpwscRegisterForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Updating GPWSC Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Updating failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Updating failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
//				refreshGpwscForm(gpwscRegisterForm);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updating failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
//			refreshGpwscForm(gpwscRegisterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving GPWSC Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		finally{
			
			if(gpwscRegisterForm.getLocationId()!=null)
			gpwscRegisterForm.setLocationId(gpwscRegisterForm.getLocationId());
			if(gpwscRegisterForm.getBlockId()!=null){			
				
				request.setAttribute("blockId", gpwscRegisterForm.getBlockId());
				gpwscRegisterForm.setBlockId(gpwscRegisterForm.getBlockId());
			}
			if(gpwscRegisterForm.getVillageId()!=null){				
				
				request.setAttribute("villageId", gpwscRegisterForm.getVillageId());
				gpwscRegisterForm.setVillageId(gpwscRegisterForm.getVillageId());
			}
			if(gpwscRegisterForm.getSchemeId()!=null){
				
				request.setAttribute("schemeId",gpwscRegisterForm.getSchemeId());
				gpwscRegisterForm.setSchemeId(gpwscRegisterForm.getSchemeId());
			}
			
			request.setAttribute("gpwcId", gpwscRegisterForm.getGpwcId());
			gpwscRegisterForm.setGpwcId(gpwscRegisterForm.getGpwcId());
			
			
			request.setAttribute("bankId",gpwscRegisterForm.getBankId());
			blankOthers(gpwscRegisterForm);
			
		}
		return mapping.findForward("display");
	}
	
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = gpwscRegisterBO.deleteGpwsc(gpwscRegisterForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "GPWSC Transaction","deleted successfully");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Deletion GPWSC Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Deletion failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Deletion failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				refreshGpwscForm(gpwscRegisterForm);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			refreshGpwscForm(gpwscRegisterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of GPWSC Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = gpwscRegisterBO.postGpwsc(gpwscRegisterForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "GPWSC Transaction","authorized successfully");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Post of GPWSC Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Post of failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Post of failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				refreshGpwscForm(gpwscRegisterForm);
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			refreshGpwscForm(gpwscRegisterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of GPWSC Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	private void checkGpwscForm(GpwscRegisterForm gpwscRegisterForm) throws MISException{
		StringBuffer stringMessage = new StringBuffer();
		if(gpwscRegisterForm.getTransactionType().equalsIgnoreCase(MISConstants.FIN_VOC_TYPE_PAYMENT)){
			if(!MisUtility.ifEmpty(gpwscRegisterForm.getPayeeName())|| !MisUtility.ifEmpty(gpwscRegisterForm.getPaymentAmount())){
				
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getPayeeName())){
					stringMessage.append("<br> Please Enter Payee Name");
				}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getPaymentAmount())){
					stringMessage.append("<br> Please Enter Payment Amount");
				}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getPaymentType())){
					stringMessage.append("<br> Please Select Payment Type");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			gpwscRegisterForm.setReceiptAmount(new BigDecimal(0.0));
			gpwscRegisterForm.setReceiptType(null);
			gpwscRegisterForm.setReceiptActivity(null);
		}else if(gpwscRegisterForm.getTransactionType().equalsIgnoreCase(MISConstants.FIN_VOC_TYPE_RECEIPT)){
			if(!MisUtility.ifEmpty(gpwscRegisterForm.getReceiptAmount())|| !MisUtility.ifEmpty(gpwscRegisterForm.getReceiptType())){

				if(!MisUtility.ifEmpty(gpwscRegisterForm.getReceiptAmount())){
					stringMessage.append("<br> Please Enter Receipt Amount");
				}
				if(!MisUtility.ifEmpty(gpwscRegisterForm.getReceiptType())){
					stringMessage.append("<br> Please Enter Receipt Type");
				}
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			gpwscRegisterForm.setBillNo(null);
			gpwscRegisterForm.setPaymentAmount(new BigDecimal(0.0));
			gpwscRegisterForm.setPaymentActivity(null);
			gpwscRegisterForm.setPayeeName(null);
			gpwscRegisterForm.setPaymentType(null);
		}
	}
	
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		GpwscRegisterForm gpwscRegisterForm = (GpwscRegisterForm)form;
		GpwscRegisterBean gpwscRegisterBean = new GpwscRegisterBean();
//		CommitteeBean committeeBean = new CommitteeBean();
		gpwscRegisterBean.setCommitteeBean(null);
		gpwscRegisterBean.setTransactionNumber(new Long(request.getParameter("transactionNumber")));
		System.out.println("Transuction Number is -----------------"+request.getParameter("transactionNumber"));
		try {
			
			GpwscRegisterBean gpwscRegisterBean2 = gpwscRegisterDao.findGPWSCRegister(gpwscRegisterBean, null).get(0);
			if(MisUtility.ifEmpty(gpwscRegisterBean2)){
				
				gpwscRegisterForm.setLocationId(gpwscRegisterBean2.getLocationId());
				request.setAttribute("blockId", gpwscRegisterBean2.getCommitteeBean().getBlockId());
				request.setAttribute("villageId", gpwscRegisterBean2.getCommitteeBean().getVillageId());
				request.setAttribute("gpwcId", gpwscRegisterBean2.getCommitteeBean().getCommitteeId());
				request.setAttribute("bankId",gpwscRegisterBean2.getBankId());
				request.setAttribute("schemeId", gpwscRegisterBean2.getSchemeId());
				gpwscRegisterForm.setBlockId(gpwscRegisterBean2.getCommitteeBean().getBlockId());
				gpwscRegisterForm.setBankId(gpwscRegisterBean2.getBankId());
				gpwscRegisterForm.setBillNo(gpwscRegisterBean2.getBillNo());
				gpwscRegisterForm.setDateOfTransaction(MisUtility.convertDateToString(gpwscRegisterBean2.getDateOfTransaction()));
				gpwscRegisterForm.setPayeeName(gpwscRegisterBean2.getPayeeName());
				gpwscRegisterForm.setTransactionType(gpwscRegisterBean2.getTransactionType());
				gpwscRegisterForm.setPaymentAmount(gpwscRegisterBean2.getPaymentAmount());
				gpwscRegisterForm.setPaymentType(gpwscRegisterBean2.getPaymentType());
				gpwscRegisterForm.setReceiptActivity(gpwscRegisterBean2.getReceiptActivity());
				gpwscRegisterForm.setReceiptAmount(gpwscRegisterBean2.getReceiptAmount());
				gpwscRegisterForm.setReceiptType(gpwscRegisterBean2.getReceiptType());
				gpwscRegisterForm.setSchemeId(gpwscRegisterBean2.getSchemeId());
				gpwscRegisterForm.setTransactionNumber(gpwscRegisterBean2.getTransactionNumber());
				gpwscRegisterForm.setTransactionType(gpwscRegisterBean2.getTransactionType());
				gpwscRegisterForm.setPaymentActivity(gpwscRegisterBean2.getPaymentActivity());
				long gpwcId = gpwscRegisterBean2.getCommitteeBean().getCommitteeId();
				System.out.println("********************"+gpwcId);
				gpwscRegisterForm.setGpwcId(gpwcId);
				}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		
	return mapping.findForward("display");
	
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
		     
		     if(date2.before(date1)){
		    	 	strngMessage.append("Invalid Transction Date");
					strngMessage.append("<br> Transction Date should be before today's Date");
					return strngMessage.toString();
			  }
		     if(date1.before(date3)){
		    	 if(date1.after(date4) && date1.before(date3)){																	//date2=current date
		    		 return null;																								//date4=1st of last month
		    	 }else{																											//date5=1st of current month
		    		 strngMessage.append("Invalid Transction Date");
						strngMessage.append("<br> Transction Date should be greater than"+" ("+fLMonthDate+") "+"and"+" less than or equal("+tenDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		     if(date1.after(date3)){
		    	 if(date1.after(date5) &&(date1.before(date2)||date1.equals(date2))){
		    		 return null;
		    	 }else{
		    		 strngMessage.append("Invalid Transction Date");
						strngMessage.append("<br> Transction Date should be between"+" ("+fCMonthDate+") "+"and"+" ("+prDate+")");
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

