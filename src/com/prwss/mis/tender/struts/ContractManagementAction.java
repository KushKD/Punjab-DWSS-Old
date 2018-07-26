  package com.prwss.mis.tender.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.tender.ContractBO;
import com.prwss.mis.tender.award.dao.TenderAwardBean;
import com.prwss.mis.tender.award.dao.TenderAwardDao;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;
import com.prwss.mis.tender.contract.dao.ContractManagementInfoBean;
import com.prwss.mis.tender.contract.dao.ContractManagementInfoDao;
import com.prwss.mis.tender.dao.TenderDao;
import com.prwss.mis.tender.dao.TenderHeaderBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ContractManagementAction extends DispatchAction {

	private ContractBO contractBO;
	private MISSessionBean misAuditBean;
	private ContractDao contractDao;
	private SchemeHeaderDao schemeHeaderDao;
	private TenderDao tenderDao;
	private TenderAwardDao tenderAwardDao;
	private ContractManagementInfoDao contractManagementInfoDao;
	
	public void setContractManagementInfoDao(
			ContractManagementInfoDao contractManagementInfoDao) {
		this.contractManagementInfoDao = contractManagementInfoDao;
	}
	public void setTenderAwardDao(TenderAwardDao tenderAwardDao) {
		this.tenderAwardDao = tenderAwardDao;
	}
	private Logger log = Logger.getLogger(ContractManagementAction.class);
	
	public void setTenderDao(TenderDao tenderDao) {
		this.tenderDao = tenderDao;
	}
	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}
	public void setContractBO(ContractBO contractBO) {
		this.contractBO = contractBO;
	}
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		List<ContractHeaderBean> contractHeaderBeans = null;
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null
				&& MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		try {
			ContractManagementForm contractManagementForm = (ContractManagementForm) form;
			System.out.println("contract no in form"+contractManagementForm.getContractNo());
			contractHeaderBeans = contractBO.findContract(contractManagementForm, statusList);
			String contractNumber = contractManagementForm.getContractNo();
			Datagrid milestoneDatagrid = null;
			if (!MisUtility.ifEmpty(contractHeaderBeans)) {
				request.setAttribute("level2", "true");
				for (ContractHeaderBean contractHeaderBean : contractHeaderBeans) {
					contractManagementForm.setContractDate(MisUtility.convertDateToString(contractHeaderBean.getContractDate()));
					contractManagementForm.setRevisedContractDate(MisUtility.convertDateToString(contractHeaderBean.getRevisedContractDate()));
					contractManagementForm.setActualCompletionDate(MisUtility.convertDateToString(contractHeaderBean.getActualCompletionDate()));
					contractManagementForm.setContractNo(contractHeaderBean.getContractId());
					request.setAttribute("locationId", contractHeaderBean.getLocationId());
					request.setAttribute("tenderId", contractHeaderBean.getTenderId());
					request.setAttribute("contractId", contractHeaderBean.getContractId());
					contractManagementForm.setLdAmount(contractHeaderBean.getLdAmount());
					contractManagementForm.setLocationId(contractHeaderBean.getLocationId());
					contractManagementForm.setMaxLd(contractHeaderBean.getMaxLDRate());
					contractManagementForm.setVendorId(contractHeaderBean.getVendorBean().getVendorId());
					contractManagementForm.setVendorName(contractHeaderBean.getVendorBean().getVendorName());
					contractManagementForm.setRevisedContractAmount(contractHeaderBean.getRevisedContractAmount());
					contractManagementForm.setTenderId(contractHeaderBean.getTenderId()+contractHeaderBean);
					System.out.println(contractHeaderBean.getContractDetailBeans());
					milestoneDatagrid = getContractDatagrid(contractHeaderBean.getContractDetailBeans());
					contractManagementForm.setMilestoneDatagrid(milestoneDatagrid);
				}
			}else{
				refreshContractForm(contractManagementForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for Contract no. -> ",contractNumber);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getCause());
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		ContractManagementForm contractManagementForm = (ContractManagementForm) form;
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			log.info("Contract Update : Your session timed out");
			return mapping.findForward("login");
		}
		try {
			//String actualComDate=contractManagementForm.getActualCompletionDate();
			if(MisUtility.ifEmpty(contractManagementForm.getActualCompletionDate())){
				Date contractDate=MisUtility.convertStringToDate(contractManagementForm.getContractDate());
				Date actualCompletionDate=MisUtility.convertStringToDate(contractManagementForm.getActualCompletionDate());
				if((contractDate.compareTo(actualCompletionDate))>0){
					throw new MISException(MISExceptionCodes.MIS004,"Actual Contract Completion Date  must be greater then Contract Start Date");
				   }
				}
			status = contractBO.updateContract(contractManagementForm,	misAuditBean);
			if (status)

			{
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",	"Contract Management", "Contract No -> "
								+ contractManagementForm.getContractNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Contract Management", "Contract No -> "
								+ contractManagementForm.getContractNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			refreshContractForm(contractManagementForm);
		} 
		catch(MISException e){
		 if(MISExceptionCodes.MIS004.equals(e.getCode())){
			 System.out.println("miscode===="+e.getCode());
			 log.error(e.getLocalizedMessage(),e);
			 e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("required.fields",e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		  }
		 else{
			   ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Contract Management", "Contract No -> "
								+ contractManagementForm.getContractNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				e.printStackTrace();
				//log.debug("Error in updating Contract \t" + e);
				throw e;
		 }
		}
		catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.save", "Contract Management", "Contract No -> "
							+ contractManagementForm.getContractNo());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			e.printStackTrace();
			log.debug("Error in updating Contract \t" + e);
			throw e;
		}
		
		
		return mapping.findForward("display");
	}


	private void refreshContractForm (ContractManagementForm contractManagementForm)throws MISException{ 
		contractManagementForm.setContractDate(null);
		contractManagementForm.setRevisedContractDate(null);
		contractManagementForm.setActualCompletionDate(null);
		contractManagementForm.setContractNo(null);
		contractManagementForm.setLdAmount(0);
		contractManagementForm.setLocationId(null);
		contractManagementForm.setMaxLd(0);
		contractManagementForm.setVendorId(null);
		contractManagementForm.setVendorName(null);
		contractManagementForm.setMilestoneDatagrid(getContractDatagrid(null));
		contractManagementForm.setRevisedContractAmount(0.0);
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		try {
			ContractManagementForm contractManagementForm = (ContractManagementForm) form;
			refreshContractForm(contractManagementForm);
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}

	private Datagrid getContractDatagrid(Set<ContractDetailBean> contractDetailBeans) throws MISException {
		List<ContractDetailGridBean> detailGridBeans = new ArrayList<ContractDetailGridBean>();
		Datagrid milestoneDatagrid = Datagrid.getInstance();
		milestoneDatagrid.setDataClass(ContractDetailGridBean.class);
		try {
			if (!MisUtility.ifEmpty(contractDetailBeans)) {
				ContractDetailGridBean contractDetailGridBean = null;
				for (ContractDetailBean contractDetailBean : contractDetailBeans) {
					contractDetailGridBean = new ContractDetailGridBean();
					contractDetailGridBean.setActualCompletionDate(MisUtility.convertDateToString(contractDetailBean.getActualCompletionDate()));
					contractDetailGridBean.setAmountDue(contractDetailBean.getAmountDue());
					contractDetailGridBean.setBillAmount(contractDetailBean.getBillAmount());
					contractDetailGridBean.setBillDate(MisUtility.convertDateToString(contractDetailBean.getBillDate()));
					contractDetailGridBean.setBillNo(contractDetailBean.getBillNo());
					contractDetailGridBean.setContractId(contractDetailBean.getContractId());
					contractDetailGridBean.setMilestone(contractDetailBean.getMilestone());
					contractDetailGridBean.setMilestoneId(contractDetailBean.getMilestoneId());
					contractDetailGridBean.setMilestoneStatus(contractDetailBean.getMilestoneStatus());
					contractDetailGridBean.setRevisedCompletionDate(MisUtility.convertDateToString(contractDetailBean.getRevisedCompletionDate()));
					contractDetailGridBean.setReleaseAmount(contractDetailBean.getReleaseAmount());
					contractDetailGridBean.setEstimatedCompletionDate(MisUtility.convertDateToString(contractDetailBean.getEstimatedCompletionDate()));
					detailGridBeans.add(contractDetailGridBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		milestoneDatagrid.setData(detailGridBeans);
		return milestoneDatagrid;

	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@tenderId");

	}
	public ActionForward fetchContractIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		//Set<ContractHeaderBean> contractHeaderBeans= new TreeSet<ContractHeaderBean>();
		List<TenderAwardBean> tenderAwardBeans = new ArrayList<TenderAwardBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			List<String> statusList = new ArrayList<String>();	
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			TenderAwardBean tenderAwardBeanFind=new TenderAwardBean();
			
			if(MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("tenderId"))){
				tenderAwardBeanFind.setTenderId(request.getParameter("tenderId"));
				System.out.println("ten"+request.getParameter("tenderId"));
				tenderAwardBeans=tenderAwardDao.findTenderAwarded(tenderAwardBeanFind, statusList);
				
				if(!MisUtility.ifEmpty(tenderAwardBeans)){
					TenderAwardBean tenderAwardBean=tenderAwardBeans.get(0);
					buffer.append(tenderAwardBean.getContractNo());					
					System.out.println("Contract Id"+tenderAwardBean.getContractNo());
					System.out.println("TenderId"+request.getParameter("tenderId"));
					System.out.println("Locationnnnnnn"+request.getParameter("locationId"));					
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
//	public ActionForward fetchTenderIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response)throws MISException {
//		List<TenderHeaderBean> tenderHeaderBeans= null;
//		List<SchemeHeaderBean> schemeHeaderBeans = null;
//		Set<ContractHeaderBean> contractHeaderBeans= new TreeSet<ContractHeaderBean>();
//		StringBuffer buffer = new StringBuffer();
//	 
//		try {
//			List<String> statusList = new ArrayList<String>();
//			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
//				System.out.println("locaton"+request.getParameter("locationId"));
//				contractHeaderBeans=contractDao.getContractCodes(request.getParameter("locationId"));
//				buffer.append("<option>");
//				buffer.append("Select Tender No.");
//				buffer.append("</option>");
//								
//				for (ContractHeaderBean contractHeaderBean : contractHeaderBeans) {
//					 String schemeName = null;
//					 String packageDescription = null;
//					 String tenderType = null;
//					 System.out.println("tender id==========="+contractHeaderBean.getTenderId());
//					 TenderHeaderBean tenderHeaderBean = new TenderHeaderBean();
//					 tenderHeaderBean.setLocationId(request.getParameter("locationId"));
//				   	 tenderHeaderBean.setTenderId(contractHeaderBean.getTenderId());
//				 	 tenderHeaderBeans = tenderDao.findTender(tenderHeaderBean, statusList);
//					SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
//					schemeHeaderBean.setLocationId(request.getParameter("locationId"));
//					schemeHeaderBean.setSchemeId(tenderHeaderBeans.get(0).getSchemeCode());
//					log.debug("Location"+request.getParameter("locationId"));
//					schemeHeaderBeans=schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
//					
//					if(!MisUtility.ifEmpty(tenderHeaderBeans))
//					{
//						packageDescription = tenderHeaderBeans.get(0).getPackageDescription();
//						tenderType = tenderHeaderBeans.get(0).getTenderType();
//					}
//					if(!MisUtility.ifEmpty(schemeHeaderBeans)){
//						schemeName=schemeHeaderBeans.get(0).getSchemeName();
//					}
//					buffer.append("<option value=\"").append(contractHeaderBean.getTenderId()).append("\">");
//					if(tenderType.equals("WORKS")){
//						buffer.append(contractHeaderBean.getTenderId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+schemeName);
//					}else{
//						buffer.append(contractHeaderBean.getTenderId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+packageDescription);
//					}
//					
//					buffer.append("</option>");	
//					System.out.println(buffer);
//				}		
//			}
//			//System.out.println("-------------------------------------------------------");
//			//System.out.println("Contract Buffer tender: "+buffer.toString());
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
//		return null;
//	}
	public ActionForward fetchTenderIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
	 
		List<ContractManagementInfoBean> contractManagementInfoBeans = null;
		StringBuffer buffer = new StringBuffer();
	 
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
			//	System.out.println("locaton-----------"+request.getParameter("locationId"));
				contractManagementInfoBeans=contractManagementInfoDao.getContractCodes(request.getParameter("locationId"));
				buffer.append("<option>");
				buffer.append("Select Tender No.");
				buffer.append("</option>");
								
				for (ContractManagementInfoBean contractManagementInfoBean : contractManagementInfoBeans) {
					 String schemeName = contractManagementInfoBean.getSchemeName();
					 String packageDescription = contractManagementInfoBean.getPackageDescription();
					 String tenderType = contractManagementInfoBean.getTenderType();
					// System.out.println("tender id==========="+contractManagementInfoBean.getTenderId());
					 
 
					buffer.append("<option value=\"").append(contractManagementInfoBean.getTenderId()).append("\">");
					if(tenderType.equals("WORKS")){
						buffer.append(schemeName+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+contractManagementInfoBean.getTenderId());
					}else{
						buffer.append(packageDescription+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+contractManagementInfoBean.getTenderId());
					}
					
					buffer.append("</option>");	
				//	System.out.println(buffer);
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
