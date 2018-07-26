package com.prwss.mis.tender.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ContractManagementForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971739949912412509L;
	private String contractNo;
	private String vendorId;
	private String vendorName;
	private String contractDate;
	private String actualCompletionDate;
	private String revisedContractDate;
	private Datagrid milestoneDatagrid;
	private double maxLd;
	private double ldAmount;
	private String locationId;
	private double revisedContractAmount;
	private String tenderId;
	
	
	
	public double getRevisedContractAmount() {
		return revisedContractAmount;
	}
	public void setRevisedContractAmount(double revisedContractAmount) {
		this.revisedContractAmount = revisedContractAmount;
	}

	private boolean submitForm;
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public double getMaxLd() {
		return maxLd;
	}
	public void setMaxLd(double maxLd) {
		this.maxLd = maxLd;
	}
	public double getLdAmount() {
		return ldAmount;
	}
	public void setLdAmount(double ldAmount) {
		this.ldAmount = ldAmount;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getRevisedContractDate() {
		return revisedContractDate;
	}
	public void setRevisedContractDate(String revisedContractDate) {
		this.revisedContractDate = revisedContractDate;
	}
	public Datagrid getMilestoneDatagrid() {
		return milestoneDatagrid;
	}
	public void setMilestoneDatagrid(Datagrid milestoneDatagrid) {
		this.milestoneDatagrid = milestoneDatagrid;
	}
	@Override
	public String toString() {
		return "ContractManagementForm [contractNo=" + contractNo
				+ ", vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", contractDate=" + contractDate + ", revisedContractDate="
				+ revisedContractDate + ", milestoneDatagrid="
				+ milestoneDatagrid + ", maxLd=" + maxLd + ", ldAmount="
				+ ldAmount + ", locationId=" + locationId + "]";
	}
	public void setActualCompletionDate(String actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public String getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	public String getTenderId() {
		return tenderId;
	}
	
/*	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
	
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "contractNo@locationId");
		System.out.println("!!!!!!!!"+this.contractNo);
        if(this.submitForm)
        {
        ActionErrors errors = super.validate(arg0, request);
        this.setSubmitForm(false);
        request.setAttribute("contractId", this.contractNo);
        request.setAttribute("level2", "true");
        return errors;
        }
        else
        {
        return null;
        }

  }
*/	
}
