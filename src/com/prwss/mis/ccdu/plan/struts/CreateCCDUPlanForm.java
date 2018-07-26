package com.prwss.mis.ccdu.plan.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class CreateCCDUPlanForm extends ValidatorForm {
	
	/**
	 *  Initial Version
	 */
	private static final long serialVersionUID = -2103882067672685481L;
	
	private long planId;
	private String planStartDate;
	private String planEndDate;
	
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public String getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
	}
	public String getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		
	}
	
	

}
