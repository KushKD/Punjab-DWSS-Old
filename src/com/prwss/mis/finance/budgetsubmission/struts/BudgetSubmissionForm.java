/**
 * 
 */
package com.prwss.mis.finance.budgetsubmission.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetSubmissionForm  extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7242769805335972651L;
	
	private String programId;
	private String locationId;
	private Datagrid budgetSubmissionDatagrid;
	private String componentId;
	private String subComponentId;
	private String activityId;
	private String codeHeadId;
	private long budgetId;
	private BigDecimal estPhysicalBudgt;
	private BigDecimal estFinanceBudgt;
	private long budgtUnit;
	  
	
	
	public long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSubComponentId() {
		return subComponentId;
	}
	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getCodeHeadId() {
		return codeHeadId;
	}
	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Datagrid getBudgetSubmissionDatagrid() {
		return budgetSubmissionDatagrid;
	}
	public void setBudgetSubmissionDatagrid(Datagrid budgetSubmissionDatagrid) {
		this.budgetSubmissionDatagrid = budgetSubmissionDatagrid;
	}
	public BigDecimal getEstPhysicalBudgt() {
		return estPhysicalBudgt;
	}
	public void setEstPhysicalBudgt(BigDecimal estPhysicalBudgt) {
		this.estPhysicalBudgt = estPhysicalBudgt;
	}
	public BigDecimal getEstFinanceBudgt() {
		return estFinanceBudgt;
	}
	public void setEstFinanceBudgt(BigDecimal estFinanceBudgt) {
		this.estFinanceBudgt = estFinanceBudgt;
	}
	public long getBudgtUnit() {
		return budgtUnit;
	}
	public void setBudgtUnit(long budgtUnit) {
		this.budgtUnit = budgtUnit;
	}
	
	
	
	
	
}
