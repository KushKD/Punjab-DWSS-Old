package com.prwss.mis.procurement.plan.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class CreateProcPlanForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1419155707224338657L;
	private String planId;
	private String locationId;
	private String planType;
	private String planFrom;
	private String planTo;
	private String planDescription;
	private String programId;
	private boolean submitForm;
	
	
	
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getPlanFrom() {
		return planFrom;
	}
	public void setPlanFrom(String planFrom) {
		this.planFrom = planFrom;
	}
	public String getPlanTo() {
		return planTo;
	}
	public void setPlanTo(String planTo) {
		this.planTo = planTo;
	}
	public String getPlanDescription() {
		return planDescription;
	}
	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
	@Override
	public String toString() {
		return "CreateProcPlanForm [planId=" + planId + ", locationId="
				+ locationId + ", planType=" + planType + ", planFrom="
				+ planFrom + ", planTo=" + planTo + ", planDescription="
				+ planDescription + ", programId=" + programId + "]";
	}
	
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
	
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "planId@locationId");
		request.setAttribute("d__auto", "planId");
        
        if(this.submitForm)
        {
        ActionErrors errors = super.validate(arg0, request);
        System.out.println("In Validtae");
        this.setSubmitForm(false);
        return errors;
        }
        else
        {
              System.out.println("NoyIn Validtae");
        return null;
        }

  }

	}
