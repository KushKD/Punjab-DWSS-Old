package com.prwss.mis.procurement.subplan.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ProcSubPlanForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889333962248883860L;
	private long subPlanId;
	private String locationId;
	private String planId;
	private String subPlanDescription;
	private Datagrid attachedSchemeDatagrid;
	private Datagrid attachedSchemeDatagrid2;
	private String blockId;
	private String schemeId;
	private String villageId;
	private String subPlanName;
	private boolean submitForm;
	private String planType;
	private double schemeEstimatedCostInRs;
	private int totalNoPackages;
	private String projectCode;
	
	
 
	public Datagrid getAttachedSchemeDatagrid2() {
		return attachedSchemeDatagrid2;
	}
	public void setAttachedSchemeDatagrid2(Datagrid attachedSchemeDatagrid2) {
		this.attachedSchemeDatagrid2 = attachedSchemeDatagrid2;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public long getSubPlanId() {
		return subPlanId;
	}
	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
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
	public String getSubPlanDescription() {
		return subPlanDescription;
	}
	public void setSubPlanDescription(String subPlanDescription) {
		this.subPlanDescription = subPlanDescription;
	}
	public Datagrid getAttachedSchemeDatagrid() {
		return attachedSchemeDatagrid;
	}
	public void setAttachedSchemeDatagrid(Datagrid attachedSchemeDatagrid) {
		this.attachedSchemeDatagrid = attachedSchemeDatagrid;
	}
	
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
	
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@locationId@subPlanId");
		request.setAttribute("d__auto", "subPlanId");
        
        if(this.submitForm)
        {
        ActionErrors errors = super.validate(arg0, request);
        this.setSubmitForm(false);
        request.setAttribute("level2", "true");
        request.setAttribute("planId",this.getPlanId());
		request.setAttribute("subPlanId", this.getSubPlanId());
        return errors;
        }
        else
        {
            //  System.out.println("NoyIn Validtae");
        return null;
        }

  }
	public void setSubPlanName(String subPlanName) {
		this.subPlanName = subPlanName;
	}
	public String getSubPlanName() {
		return subPlanName;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getPlanType() {
		return planType;
	}
	public void setSchemeEstimatedCostInRs(double schemeEstimatedCostInRs) {
		this.schemeEstimatedCostInRs = schemeEstimatedCostInRs;
	}
	public double getSchemeEstimatedCostInRs() {
		return schemeEstimatedCostInRs;
	}
	public void setTotalNoPackages(int totalNoPackages) {
		this.totalNoPackages = totalNoPackages;
	}
	public int getTotalNoPackages() {
		return totalNoPackages;
	}
	 
	
	

}
