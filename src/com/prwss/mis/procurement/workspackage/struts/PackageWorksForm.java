package com.prwss.mis.procurement.workspackage.struts;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageWorksForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3002241180654061481L;
	private String packageId;
	private String locationId;
	private String planId;
	private long subPlanId;
	private String schemeId;
	private String packageDescription;
	private String methodOfProcurement;
	private String designInvestigationDate;
	private String estPreparedSanctionDate;
	private String prepBidDocDate;
	private String bankNocBidDate;
	private String bidInvitationDate;
	private String bidOpeningDate;
	private String conAwardDecideDate;
	private BigDecimal estimatePackageCost;
	private String bankNocConAwardDate;
	private String conCompletionDate ;
	private String conSignDate ;
	private String postPriorStatus;
	private Datagrid packageComponentsDatagrid;
	private String targetCommissioningDate;
	private BigDecimal anticipatedExpenditureCost;
	private String componentNameId;
	private String villageId;
	private String projectCode;
	
	 
	
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	private boolean submitForm;
	
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	
	
	
	public BigDecimal getAnticipatedExpenditureCost() {
		return anticipatedExpenditureCost;
	}
	public void setAnticipatedExpenditureCost(BigDecimal anticipatedExpenditureCost) {
		this.anticipatedExpenditureCost = anticipatedExpenditureCost;
	}
	public String getTargetCommissioningDate() {
		return targetCommissioningDate;
	}
	public void setTargetCommissioningDate(String targetCommissioningDate) {
		this.targetCommissioningDate = targetCommissioningDate;
	}
	public String getPostPriorStatus() {
		return postPriorStatus;
	}
	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}
	public Datagrid getPackageComponentsDatagrid() {
		return packageComponentsDatagrid;
	}
	public void setPackageComponentsDatagrid(Datagrid packageComponentsDatagrid) {
		this.packageComponentsDatagrid = packageComponentsDatagrid;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public long getSubPlanId() {
		return subPlanId;
	}
	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public String getMethodOfProcurement() {
		return methodOfProcurement;
	}
	public void setMethodOfProcurement(String methodOfProcurement) {
		this.methodOfProcurement = methodOfProcurement;
	}
	public String getDesignInvestigationDate() {
		return designInvestigationDate;
	}
	public void setDesignInvestigationDate(String designInvestigationDate) {
		this.designInvestigationDate = designInvestigationDate;
	}
	public String getEstPreparedSanctionDate() {
		return estPreparedSanctionDate;
	}
	public void setEstPreparedSanctionDate(String estPreparedSanctionDate) {
		this.estPreparedSanctionDate = estPreparedSanctionDate;
	}
	public String getPrepBidDocDate() {
		return prepBidDocDate;
	}
	public void setPrepBidDocDate(String prepBidDocDate) {
		this.prepBidDocDate = prepBidDocDate;
	}
	public String getBankNocBidDate() {
		return bankNocBidDate;
	}
	public void setBankNocBidDate(String bankNocBidDate) {
		this.bankNocBidDate = bankNocBidDate;
	}
	public String getBidInvitationDate() {
		return bidInvitationDate;
	}
	public void setBidInvitationDate(String bidInvitationDate) {
		this.bidInvitationDate = bidInvitationDate;
	}
	public String getBidOpeningDate() {
		return bidOpeningDate;
	}
	public void setBidOpeningDate(String bidOpeningDate) {
		this.bidOpeningDate = bidOpeningDate;
	}
	public String getConAwardDecideDate() {
		return conAwardDecideDate;
	}
	public void setConAwardDecideDate(String conAwardDecideDate) {
		this.conAwardDecideDate = conAwardDecideDate;
	}
	
	public BigDecimal getEstimatePackageCost() {
		return estimatePackageCost;
	}
	public void setEstimatePackageCost(BigDecimal estimatePackageCost) {
		this.estimatePackageCost = estimatePackageCost;
	}
	public String getBankNocConAwardDate() {
		return bankNocConAwardDate;
	}
	public void setBankNocConAwardDate(String bankNocConAwardDate) {
		this.bankNocConAwardDate = bankNocConAwardDate;
	}
	public String getConCompletionDate() {
		return conCompletionDate;
	}
	public void setConCompletionDate(String conCompletionDate) {
		this.conCompletionDate = conCompletionDate;
	}
	public String getConSignDate() {
		return conSignDate;
	}
	public void setConSignDate(String conSignDate) {
		this.conSignDate = conSignDate;
	}
	
	
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
	
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@subPlanId@schemeId@packageId@locationId");
		request.setAttribute("d__auto", "packageId");
        
        if(this.submitForm)
        {
        ActionErrors errors = super.validate(arg0, request);
        this.setSubmitForm(false);
        request.setAttribute("level2", "true");
        request.setAttribute("planId",this.getPlanId());
		request.setAttribute("subPlanId", this.getSubPlanId());
		request.setAttribute("schemeId", this.getSchemeId());
		System.out.println("*****************************"+errors);
        return errors;
        }
        else
        {
             /* System.out.println("*****************NoyIn Validtae*****************");
              System.out.println("this.getPlanId()---------"+this.getPlanId());
              System.out.println("@@@@@@NoyIn Validtae@@@@@@@@");*/
        return null;
        }
	   
	   
	}
	public void setComponentNameId(String componentNameId) {
		this.componentNameId = componentNameId;
	}
	public String getComponentNameId() {
		return componentNameId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageId() {
		return villageId;
	}
	 
	   
	  
}
