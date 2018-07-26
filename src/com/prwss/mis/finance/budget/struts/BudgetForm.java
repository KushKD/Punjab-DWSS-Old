/**
 * 
 */
package com.prwss.mis.finance.budget.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author pjha
 *
 */
public class BudgetForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2681353094735257574L;
	
	private long budgetId;
	private String programId;
	
	private String budgetFromDate;
	private String budgetToDate;
	public long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public String getBudgetFromDate() {
		return budgetFromDate;
	}
	public void setBudgetFromDate(String budgetFromDate) {
		this.budgetFromDate = budgetFromDate;
	}
	public String getBudgetToDate() {
		return budgetToDate;
	}
	public void setBudgetToDate(String budgetToDate) {
		this.budgetToDate = budgetToDate;
	}
	
	

}
