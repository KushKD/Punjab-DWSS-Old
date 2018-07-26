package com.prwss.mis.finance.billapproval.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakDao;
import com.prwss.mis.finance.billapproval.BillApprovalBO;
import com.prwss.mis.finance.billapproval.dao.BillApprovalBean;
import com.prwss.mis.hr.attendance.dao.HRAttendanceDao;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;
import com.prwss.mis.tender.contract.dao.ContractDetailDao;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;

public class BillApprovalAction extends DispatchAction {
	
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(BillApprovalAction.class);
	private MISSessionBean misSessionBean;
	private BillApprovalBO billApprovalBO;
	private HRAttendanceDao hrAttendanceDao;
	private ContractDao contractDao;
	private ContractDetailDao contractDetailDao;
	private InwardDakDao inwardDakDao;
	
	
	public void setBillApprovalBO(BillApprovalBO billApprovalBO) {
		this.billApprovalBO = billApprovalBO;
	}

	public void setInwardDakDao(InwardDakDao inwardDakDao) {
		this.inwardDakDao = inwardDakDao;
	}

	public void setContractDetailDao(ContractDetailDao contractDetailDao) {
		this.contractDetailDao = contractDetailDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
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
			BillApprovalForm billApprovalForm = (BillApprovalForm)form;
			List<BillApprovalBean> billApprovalBeans = null;
			billApprovalBeans = billApprovalBO.findBillApproval(billApprovalForm, statusList);
			if(!MisUtility.ifEmpty(billApprovalBeans)){
				request.setAttribute("billApprovalBeans", billApprovalBeans);
				
			}else{
				refreshBillApprovalForm(billApprovalForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire Bill Approval");
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
		BillApprovalForm billApprovalForm = (BillApprovalForm)form;
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
			if(!MisUtility.ifEmpty(billApprovalForm.getLocationId())||!MisUtility.ifEmpty(billApprovalForm.getBillAmount())||!MisUtility.ifEmpty(billApprovalForm.getReleasedAmount())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			System.out.println("VendorId"+billApprovalForm.getVendorId());
			vocId = billApprovalBO.saveBillApproval(billApprovalForm, misSessionBean);
			
			if (vocId!=0){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("billApproval.success.save",vocId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Generation of Payement Voucher");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			 if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Generation of Payement Voucher");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Generation of Payement Voucher");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshBillApprovalForm(billApprovalForm);
		return mapping.findForward("display");
	}
	

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		BillApprovalForm billApprovalForm = (BillApprovalForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			refreshBillApprovalForm(billApprovalForm);
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
	
	private void refreshBillApprovalForm(BillApprovalForm billApprovalForm){
		billApprovalForm.setLocationId(null);
		billApprovalForm.setContractId(null);
		billApprovalForm.setMilestonId(0);
		billApprovalForm.setBillAmount(new BigDecimal(0.0));
		billApprovalForm.setReleasedAmount(new BigDecimal(0.0));
		billApprovalForm.setDocumentNo(null);
		billApprovalForm.setDocumentReferenceNo(null);
		billApprovalForm.setVendorId(null);
		
	}
	
	public ActionForward fetchContracts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<ContractHeaderBean> contractHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				contractHeaderBeans = contractDao.getContractCodes(request.getParameter("locationId"));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ContractHeaderBean contractHeaderBean : contractHeaderBeans) {
					buffer.append("<option value=\"").append(contractHeaderBean.getContractId()).append("\">");
					buffer.append(contractHeaderBean.getContractId());
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
	
	public ActionForward fetchContractsDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<ContractDetailBean> contractDetailBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("contractId"))){
				contractDetailBeans = new TreeSet<ContractDetailBean>(contractDetailDao.getContractDetails(request.getParameter("contractId")));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ContractDetailBean contractDetailBean : contractDetailBeans) {
					buffer.append("<option value=\"").append(contractDetailBean.getMilestoneId()).append("\">");
					buffer.append(contractDetailBean.getMilestone()+" - ("+contractDetailBean.getMilestoneId()+")");
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
	
//	public ActionForward fetchVendorDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws MISException {
//		StringBuffer buffer = new StringBuffer();
//		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//		ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
//		try {
//			if(MisUtility.ifEmpty(request.getParameter("contractId"))){
//				contractHeaderBean.setContractId(request.getParameter("contractId"));
//				ContractHeaderBean contractHeaderBean2 = contractDao.findContracts(contractHeaderBean, statusList).get(0);
//				System.out.println("@@@@@@@@@@@@@@@"+contractHeaderBean2);
//				if(MisUtility.ifEmpty(contractHeaderBean2)){
//					buffer.append(contractHeaderBean2.getVendorBean().getVendorId());
//				}
//			}
//			PrintWriter out = response.getWriter();
//			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
//				out.print(buffer.toString());
//			}
//		} catch (DataAccessException e) {
//			log.error(e);
//			e.printStackTrace();
//		} catch (IOException e) {
//			log.error(e);
//			e.printStackTrace();
//		} catch (Exception e) {
//			log.error(e);
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public ActionForward fetchDocumentNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<InwardDakBean> inwardDakBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		InwardDakBean inwardDakBean = new InwardDakBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				inwardDakBean.setLocationId(request.getParameter("locationId"));
				// we need to add type of document here only INVOICE/Bill Type
				inwardDakBeans = new TreeSet<InwardDakBean>(inwardDakDao.getInwardDakBeans(inwardDakBean, statusList));
				buffer.append("<option value='null'>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(inwardDakBeans)){
					for (InwardDakBean bean : inwardDakBeans) {
						buffer.append("<option value=\"").append(bean.getDocumentNo()).append("\">");
						buffer.append(bean.getDocumentNo());
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
	
	public ActionForward fetchDocumentReferenceNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		InwardDakBean inwardDakBean = new InwardDakBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("documentNo"))){
				inwardDakBean.setDocumentNo(request.getParameter("documentNo"));
				// we need to add type of document here only INVOICE/Bill Type
				inwardDakBean = inwardDakDao.getInwardDakBeans(inwardDakBean, statusList).get(0);
				if(MisUtility.ifEmpty(inwardDakBean)){
						buffer.append(inwardDakBean.getDocumentRefNo());
					
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

}

