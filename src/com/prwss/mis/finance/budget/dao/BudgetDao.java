/**
 * 
 */
package com.prwss.mis.finance.budget.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.budget.BudgetBean;



/**
 * @author pjha
 *
 */
public interface BudgetDao {
	public  List<BudgetBean> findBudgetPlan(BudgetBean budgetBean ,List<String> statusList) throws DataAccessException;
	public long saveBudgetPlan(BudgetBean budgetBean) throws DataAccessException;
	public boolean saveOrUpdateBudgetPlan(BudgetBean budgetBean) throws DataAccessException;
	public Set<BudgetBean> getDistinctBudgetId() throws DataAccessException;

}
