package com.prwss.mis.tender.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.tender.ContractBO;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;

public class CommittedLiabilityReportAction extends DispatchAction {

	@SuppressWarnings("unused")
	private ContractBO contractBO;
	private ContractDao contractDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	
	private Logger log = Logger.getLogger(ContractManagementAction.class);
	
	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public void setContractBO(ContractBO contractBO) {
		this.contractBO = contractBO;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		String jasperFile="/tender/reports/committedLiabilityReport.jasper";
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("jasperFile",jasperFile);
		this.setAttrib(request);
		return mapping.findForward("display");
	}

	@SuppressWarnings("unchecked")
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jasperFile="/tender/reports/committedLiabilityReport.jasper";		
		CommittedLiabilityReportForm committedLiabilityReportForm=(CommittedLiabilityReportForm)form;
		String selectLocation=committedLiabilityReportForm.getSelectLocation();
		String locationId=committedLiabilityReportForm.getLocationId();
		String fromDate=committedLiabilityReportForm.getFromDate();
		String toDate=committedLiabilityReportForm.getToDate();
		String selectContract=committedLiabilityReportForm.getSelectContract();
		String contractId=committedLiabilityReportForm.getContractId();
		String approvalStatus=committedLiabilityReportForm.getApprovalStatus();
		String where="", queryString="";
		if(selectLocation.equals("S")){
			where=where+"location_id='"+locationId+"' and contract_date>='"+fromDate+"' and contract_date<='"+toDate+"'";
			queryString=queryString+"Location: "+locationId+", Period from "+fromDate +" to "+toDate;
		}else{
			where=where+" contract_date>='"+fromDate+"' and contract_date<='"+toDate+"'";
			queryString=queryString+"Location: All, Period from "+fromDate +" to "+toDate;
		}
		if(!approvalStatus.equals("UA")){
			where=where+ " and t_contract_mgmt_hdr.status='"+ approvalStatus+"'";
			queryString=queryString+", Approval Status: "+approvalStatus;
		}
		if(selectContract.equals("S")){
			where=where+ "and contract_id='"+ contractId+"'";
			queryString=queryString+", Contract ID : "+ contractId;
		}
		System.out.println("Action: "+where);
		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();
		/*JRFileVirtualizer virtualizer = new JRFileVirtualizer(1, "tmp");
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		parameters.put("where", where);
		parameters.put("queryString", queryString);
		misJasperUtil.exportToPDF(jasperFile,"CommitedLiability", parameters, request, response);				
	}	
	
	@SuppressWarnings("unchecked")
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jasperFile="/tender/reports/committedLiabilityReport.jasper";
		CommittedLiabilityReportForm committedLiabilityReportForm=(CommittedLiabilityReportForm)form;
		String selectLocation=committedLiabilityReportForm.getSelectLocation();
		String locationId=committedLiabilityReportForm.getLocationId();
		String fromDate=committedLiabilityReportForm.getFromDate();
		String toDate=committedLiabilityReportForm.getToDate();
		String where="", queryString="";
		String selectContract=committedLiabilityReportForm.getSelectContract();
		String contractId=committedLiabilityReportForm.getContractId();
		String approvalStatus=committedLiabilityReportForm.getApprovalStatus();
		if(selectLocation.equals("S")){
			where=where+"location_id='"+locationId+"' and contract_date>='"+fromDate+"' and contract_date<='"+toDate+"'";
			queryString=queryString+"Location: "+locationId+", Period from "+fromDate +" to "+toDate;
		}else{
			where=where+" contract_date>='"+fromDate+"' and contract_date<='"+toDate+"'";
			queryString=queryString+"Location: All, Period from "+fromDate +" to "+toDate;
		}
		if(!approvalStatus.equals("UA")){
			where=where+ " and t_contract_mgmt_hdr.status='"+ approvalStatus+"'";
			queryString=queryString+", Approval Status: "+approvalStatus;
		}
		if(selectContract.equals("S")){
			where=where+ "and t_contract_mgmt_hdr.contract_id='"+ contractId+"'";
			queryString=queryString+", Contract ID : "+ contractId;
		}
		System.out.println(where);
		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();
		/*JRFileVirtualizer virtualizer = new JRFileVirtualizer(1, "tmp");
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		parameters.put("where", where);
		parameters.put("queryString", queryString);
		misJasperUtil.exportToExcel(jasperFile,"CommitedLiability", parameters, request, response);
	}	
		
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		this.setAttrib(request);
		System.out.println("Unspecified.......Committed Liability");
		return mapping.findForward("cScreen");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward fetchContract(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ContractHeaderBean> contractHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		System.out.println("selectLocation: "+request.getParameter("selectLocation"));
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				if(MisUtility.ifEmpty(request.getParameter("selectLocation"))){
					if(request.getParameter("selectLocation").equalsIgnoreCase("S"))
						contractHeaderBeans = contractDao.getContractCodes(request.getParameter("locationId"));
					else{ 
						//List<String> loctionIds = (List)request.getSession().getAttribute("userLocations");
						//contractHeaderBeans = contractDao.getContractCodes(null);
				}	
			}
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
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}
}
