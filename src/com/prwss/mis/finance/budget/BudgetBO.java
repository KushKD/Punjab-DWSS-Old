/**
 * 
 */
package com.prwss.mis.finance.budget;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.budget.struts.BudgetForm;

/**
 * @author pjha
 *
 */
public interface BudgetBO {

public List<BudgetBean> findBudgetPlanFrom(BudgetForm budgetForm, List<String> statusList) throws MISException;
	
	public boolean saveBudgetPlanFrom(BudgetForm budgetForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateBudgetPlanFrom(BudgetForm budgetForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteBudgetPlanFrom(BudgetForm budgetForm,  MISSessionBean misSessionBean) throws MISException;
	public long createBudgetPlan(BudgetForm budgetForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postBudgetPlanFrom(BudgetForm budgetForm,  MISSessionBean misSessionBean) throws MISException;

}
