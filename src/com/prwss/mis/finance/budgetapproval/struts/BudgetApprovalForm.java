/**
 * 
 */
package com.prwss.mis.finance.budgetapproval.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetApprovalForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124241623542318283L;
	
	private String programId;
	private String locationId;
	private Datagrid budgetApprovalDatagrid;
	private String componentId;
	private String subComponentId;
	private String activityId;
	private String codeHeadId;
	private long budgetId;
	private BigDecimal estPhysicalBudgt;
	private BigDecimal estFinanceBudgt;
	private BigDecimal appPhysicalBudgt;
	private BigDecimal appFinanceBudgt;
	
	private long budgtUnit;
	
	
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
	public BigDecimal getAppPhysicalBudgt() {
		return appPhysicalBudgt;
	}
	public void setAppPhysicalBudgt(BigDecimal appPhysicalBudgt) {
		this.appPhysicalBudgt = appPhysicalBudgt;
	}
	public BigDecimal getAppFinanceBudgt() {
		return appFinanceBudgt;
	}
	public void setAppFinanceBudgt(BigDecimal appFinanceBudgt) {
		this.appFinanceBudgt = appFinanceBudgt;
	}
	public long getBudgtUnit() {
		return budgtUnit;
	}
	public void setBudgtUnit(long budgtUnit) {
		this.budgtUnit = budgtUnit;
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
	public Datagrid getBudgetApprovalDatagrid() {
		return budgetApprovalDatagrid;
	}
	public void setBudgetApprovalDatagrid(Datagrid budgetApprovalDatagrid) {
		this.budgetApprovalDatagrid = budgetApprovalDatagrid;
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
	public long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}
	

}
