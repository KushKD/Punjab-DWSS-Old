package com.prwss.mis.finance.budgetdetail.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;

public interface BudgetDetailDao {
	public List<BudgetDetailBean> findBudgetDetails(BudgetDetailBean budgetDetailBean, List<String> statusList) throws DataAccessException;

	public boolean saveBudgetDetails(Collection<BudgetDetailBean> budgetDetailBeans) throws DataAccessException;

	public boolean updateBudgetDetails(Collection<BudgetDetailBean> budgetDetailBeans) throws DataAccessException;

}
